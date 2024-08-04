package utill;

import java.security.SecureRandom;
import java.util.Random;

public class RandomNumberGenerator {

    private static final Random RANDOM = new SecureRandom();

    public static String generateRandom6DigitNumber() {
        int number = RANDOM.nextInt(900000) + 100000;
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        String random6DigitNumber = generateRandom6DigitNumber();
        System.out.println("Random 6-digit Number: " + random6DigitNumber);
    }
}
