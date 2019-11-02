package com.lessons.firebase.quotes.utils;

import java.util.Random;

/**
 * Class generates random word
 * for query photo from api
 */

public class WordGenerator {

    public static String getRandomWord(){
        String[] strings = {"awesome", " ", "scenery", "photos", "home", "world", "architecture", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getLifeWord(){
        String[] strings = {"nature", "world", " ", "child",  "" };
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getLoveWord(){
        String[] strings = {"couples", "family", "young", "", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getWisdomWord(){
        String[] strings = {"book", "knowledge", "young", " ", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getMotivationalWord(){
        String[] strings = {"quotes", "word", "nature", "success", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    public static String getFunnyWord(){
        String[] strings = {"humor", "world","peoples", " ", " "};
        Random random = new Random();
        int i = random.nextInt(strings.length);
        return strings[i];
    }

    // return random boolean
    public static boolean getRandomBoolean(){
        Random random = new Random();
        return random.nextBoolean();
    }
}
