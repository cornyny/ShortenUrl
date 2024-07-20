package com.nayu.ShortenUrl.controller;

import java.math.BigInteger;

public class Base62 {
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = ALPHABET.length();

    public static String encode(byte[] data) {
        // Convert byte array to a BigInteger
        BigInteger number = new BigInteger(1, data); // Create a positive BigInteger from byte array

        // Debug print statement
        System.out.println("Number after conversion: " + number);

        // Encode to Base62
        StringBuilder sb = new StringBuilder();
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divAndRem = number.divideAndRemainder(BigInteger.valueOf(BASE));
            int index = divAndRem[1].intValue();
            sb.append(ALPHABET.charAt(index));
            number = divAndRem[0];
        }

        // Reverse and return the result
        return sb.reverse().toString();
    }
}
