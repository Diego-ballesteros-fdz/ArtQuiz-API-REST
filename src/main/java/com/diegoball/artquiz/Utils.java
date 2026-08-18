package com.diegoball.artquiz;

import java.util.concurrent.ThreadLocalRandom;

public class Utils {

    public static int createRandom(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
