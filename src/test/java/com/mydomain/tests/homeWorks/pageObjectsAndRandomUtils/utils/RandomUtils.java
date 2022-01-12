package com.mydomain.tests.homeWorks.pageObjectsAndRandomUtils.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    public static String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }
        return result.toString(); // рандомная генерация строки из заданного пула символов
    }

    public static int getRandomInt(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min; // рандомная генерация целого числа int
    }

    public static Long getRandomLong(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max); // рандомная генерация целого числа Long
    }

    public static String getRandomPhone() {
        return getRandomLong(11111111111111111L, 99999999999999999L) + ""; // рандомная генерация номера телефона
    }

    public static String getRandomPhone(String code) {
        return code + getRandomPhone(); // рандомная генерация номера телефона принимающая на вход строку code
    }

    public static String getDifficultRandolPhone(String code) {
        return code + " (" + getRandomLong(111L, 999L) + ") " + getRandomLong(11111L, 99999L) + "-" + getRandomLong(111L, 999999L);
        // пример рандомной генерации номера телефона со сложным форматом
    }

    public static String getRandomEmail() {
        String emailDomain = "@email.com";
        return getRandomString(10) + emailDomain;
    }

}
