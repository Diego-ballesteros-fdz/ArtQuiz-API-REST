package com.diegoball.artquiz.entities.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class Auditable {

    @Column(name = "created_by_id")
    private UUID createdById;

    private LocalDateTime created;

    @Column(name = "updated_by_id")
    private UUID updatedById;

    private LocalDateTime updated;

    @Column(name = "soft_deleted")
    private Boolean softDeleted = false;

    @Column(name = "deleted_by_id")
    private UUID deletedById;

    private LocalDateTime deleted;
}
