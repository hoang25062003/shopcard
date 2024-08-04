/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utill;

import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class RandomStringGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new SecureRandom();

    public static String generateRandomString(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Length must be a positive number");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Example usage
        int length = 10;
        String randomString = generateRandomString(length);
        System.out.println("Random String of length " + length + ": " + randomString);
    }
}
