package com.utils;

public class DecodeUtil {


    public static String decode(String encodedText) {
        int len = encodedText.length();
        char[] encrypted = new char[len];
        char[] decrypted = new char[len];
        for (int i = 0; i < len; i++) {
            encrypted[i] = encodedText.charAt(i);
            encrypted[i] -= 7;
            decrypted[i] = encrypted[i];
        }
        System.out.println();
        return String.valueOf(decrypted);
    }
}

