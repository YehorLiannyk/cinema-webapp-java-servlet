package yehor.epam.services;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class PassEncryptionManager {
    /* Declaration of variables */
    private static final Random SECURE_RANDOM = new SecureRandom();
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    /* Method to generate the salt value. */
    public String getSaltValue(int length) {
        StringBuilder finalValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            finalValue.append(CHARACTERS.charAt(SECURE_RANDOM.nextInt(CHARACTERS.length())));
        }
        return new String(finalValue);
    }

    /* Method to generate the hash value */
    public byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /* Method to encrypt the password using the original password and salt value. */
    public String generateSecurePassword(String password, String salt) {
        String finalValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        finalValue = Base64.getEncoder().encodeToString(securePassword);
        return finalValue;
    }

    /* Method to verify if both password matches or not */
    public boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean finalValue = false;
        /* Generate New secure password with the same salt */
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        /* Check if two passwords are equal */
        finalValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        return finalValue;
    }
}
