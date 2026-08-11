# ArtQuiz API

API REST desarrollada en **Spring Boot** para una aplicación de retos diarios y aprendizaje sobre arte e historia del arte. El backend expone endpoints para gestionar autores, movimientos artísticos, obras, usuarios y la lógica del reto diario.

Este proyecto es un ejercicio de aprendizaje enfocado en dominar Spring Boot en profundidad: JPA/Hibernate, arquitectura por capas, DTOs y validación, Docker, y (próximamente) Spring Security con JWT.

---

## Stack técnico

| Tecnología | Uso |
|---|---|
| Java 17 | Lenguaje base |
| Spring Boot 4.1.0 | Framework principal |
| Maven | Gestión de dependencias y build |
| Spring Web (MVC) | API REST |
| Spring Data JPA (Hibernate) | Persistencia |
| PostgreSQL 17 | Base de datos |
| Lombok | Reducción de código boilerplate |
| Bean Validation (Jakarta) | Validación de DTOs |
| springdoc-openapi (Swagger) | Documentación interactiva de la API |
| Docker Compose | Entorno local de base de datos |
| Adminer | Cliente visual para inspeccionar la BD |

---

## Requisitos previos

- JDK 17
- Maven
- Docker y Docker Compose

---

## Puesta en marcha

### 1. Levantar la base de datos

Desde la raíz del proyecto:

```bash
docker compose up -d
```

Esto levanta dos servicios:

- **`db`** — PostgreSQL 17, expuesto en el puerto `5432`, con datos persistidos en un volumen nombrado (`postgres_data`) para que no se pierdan al reiniciar el contenedor.
- **`adminer`** — cliente web para explorar la base de datos, disponible en [http://localhost:8081](http://localhost:8081).

Credenciales de conexión (definidas en `docker-compose.yml`):

| Campo | Valor |
|---|---|
| Servidor (en Adminer) | `db` |
| Usuario | `Admin` |
| Contraseña | `Admin123.` |
| Base de datos | `artquiz_db` |

### 2. Arrancar la aplicación

```bash
mvn spring-boot:run
```

La API queda disponible en [http://localhost:8080](http://localhost:8080).

Al arrancar, Hibernate crea o actualiza automáticamente el esquema de la base de datos a partir de las entidades (`spring.jpa.hibernate.ddl-auto=update`).

### 3. Documentación interactiva (Swagger)

Con la aplicación arrancada:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Especificación OpenAPI (JSON)**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## Configuración

El archivo `src/main/resources/application.properties` contiene la configuración de conexión a la base de datos:

```properties
spring.application.name=artquiz

spring.datasource.url=jdbc:postgresql://localhost:5432/artquiz_db
spring.datasource.username=Admin
spring.datasource.password=Admin123.
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> `show-sql=true` está activo para poder ver en consola el SQL real que ejecuta Hibernate. Se recomienda desactivarlo en un entorno de producción.

---

## Estructura del proyecto

```
com.diegoball.artquiz
├── controllers/      → Endpoints REST (capa HTTP)
├── services/         → Lógica de negocio
├── repositories/      → Acceso a datos (Spring Data JPA)
├── entities/          → Entidades JPA (tablas)
│   └── base/           → Clases base compartidas (@MappedSuperclass)
├── dto/               → Objetos de transferencia, agrupados por entidad
│   ├── author/
│   ├── artmovement/
│   └── ...
├── enums/             → Enumerados compartidos entre capas
└── ArtquizApplication.java
```

### Por qué esta estructura

- **Separación por capas** (Controller → Service → Repository): el Controller nunca contiene lógica de negocio ni construye entidades directamente; toda esa responsabilidad vive en el Service.
- **DTOs específicos por operación**: se usa un DTO distinto para creación y para actualización parcial (`XxxCreateDTO` / `XxxUpdateDTO`) en lugar de un DTO único, porque las reglas de validación difieren entre ambos casos (por ejemplo, un campo obligatorio en la creación puede ser opcional en una actualización parcial).
- **Nunca se expone la entidad JPA directamente en peticiones de entrada**: evita que el cliente pueda enviar campos sensibles como `id` o `role` que no le corresponde decidir.

---

## Modelo de dominio

### Entidades de contenido

- **`Author`** — autores de las obras (nombre, años de nacimiento/muerte, nacionalidad, biografía).
- **`ArtMovement`** — movimientos artísticos (nombre, siglo de inicio/fin, ubicación, descripción).
- **`Artwork`** — obras de arte, relacionadas con `Author` y `ArtMovement` mediante `@ManyToOne` (`fetch = FetchType.LAZY` explícito, para evitar cargar relaciones completas innecesariamente).

### Entidades de usuario y gamificación

- **`User`** — cuenta de usuario. Usa `UUID` como clave primaria (en lugar de un `Long` autoincremental) para evitar la enumeración de usuarios a través de la URL. Incluye auditoría (creación, actualización, borrado lógico) mediante una clase base `Auditable` (`@MappedSuperclass`).
- **`Challenge`** — el "pool" de retos posibles: combinación de una obra (`Artwork`) y un tipo de pregunta (`QuestionType`), por ejemplo "¿de qué autor es esta obra?".
- **`DailyChallenge`** — histórico del reto correspondiente a cada día (fecha única). No se sobrescribe: cada día genera una fila nueva, lo que permite conservar el histórico completo de retos pasados.
- **`ChallengeAttempt`** — el intento de un usuario de responder al reto de un día concreto.

### Reglas de negocio relevantes

- El reto diario no repite un `Challenge` del pool hasta que se hayan agotado todas las combinaciones disponibles.
- Un usuario no puede responder más de una vez al reto del mismo día.
- La contraseña de `User` se almacena **sin cifrar todavía** — esto es una tarea pendiente marcada explícitamente en el código (`// PENDIENTE DE SEGURIDAD`), a resolver cuando se implemente Spring Security con `BCryptPasswordEncoder`.

---

## Endpoints disponibles

| Recurso | Base path |
|---|---|
| Autores | `/api/authors` |
| Movimientos artísticos | `/api/artmovement` |
| Obras | `/api/artworks` |
| Usuarios | `/api/user` |

Cada recurso expone, salvo excepciones señaladas en el código:

- `GET /` — listar todos
- `GET /{id}` — obtener uno por id
- `POST /` — crear
- `PATCH /{id}` — actualización parcial
- `DELETE /{id}` — eliminar

Consulta Swagger UI para el detalle completo de cada endpoint, esquemas de petición/respuesta y pruebas interactivas.

---

## Roadmap

- [x] Fase 1 — CRUD de contenido (`Author`, `ArtMovement`, `Artwork`)
- [x] Fase 1.5 — CRUD de `User` (sin seguridad activa todavía)
- [ ] Fase 2 — Spring Security + JWT (hash de contraseñas, autenticación, autorización por rol)
- [ ] Fase 3 — Lógica de negocio del reto diario (`Challenge`, `DailyChallenge`, `ChallengeAttempt`, rachas y puntuación)
- [ ] Fase 4 — Tests (JUnit, Mockito, Testcontainers), manejo global de excepciones (`@ControllerAdvice`), migraciones con Flyway
- [ ] Fase 5 — Dockerización completa de la app, CI/CD, Actuator

---

## Notas de desarrollo

- Las dependencias externas al BOM de Spring (como `springdoc-openapi`) requieren especificar su versión explícitamente en el `pom.xml`, ya que no están gestionadas por `spring-boot-starter-parent`.
- Los datos de catálogo (`Author`, `ArtMovement`, `Artwork`) están pensados para poblarse mediante un script de carga inicial desde fuentes públicas como Wikidata, en lugar de introducirse a mano.
