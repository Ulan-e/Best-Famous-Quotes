package com.ulan.app.quotes.data.database;

import java.util.Random;
import java.util.UUID;

public class DaoDataHelper {

    public static String randomUuid(){
        return UUID.randomUUID().toString();
    }

    public static int randomInt(){
        Random random = new Random();
        return random.nextInt();
    }

    public static boolean randomBoolean(){
        return Math.random() < 0.5;
    }
}