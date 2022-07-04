package yehor.epam.utilities;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * Class service for encryption password by salt
 */
public class PassEncryptionManager {
    private static final Random SECURE_RANDOM = new SecureRandom();
    private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;

    /**
     * Method to generate the password salt value
     *
     * @param length length of salt
     * @return
     */
    public String getSaltValue(int length) {
        StringBuilder finalValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            finalValue.append(CHARACTERS.charAt(SECURE_RANDOM.nextInt(CHARACTERS.length())));
        }
        return new String(finalValue);
    }

    /**
     * Method to generate the hash value
     *
     * @param password source password
     * @param salt     salt value
     * @return return the key in its primary encoding format, or null if this key does not support encoding.
     */
    private byte[] hash(char[] password, byte[] salt) {
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

    /**
     * Method to encrypt the password using the original password and salt value
     *
     * @param password source password
     * @param salt     salt value
     * @return encrypted password
     */
    public String generateSecurePassword(String password, String salt) {
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        return Base64.getEncoder().encodeToString(securePassword);
    }

    /**
     * Method to verify if both password matches or not
     *
     * @param providedPassword received clean password
     * @param securedPassword  received encrypted password
     * @param salt             salt value
     * @return true if passwords are equal and false if not
     */
    public boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean finalValue = false;
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        /* Check if two passwords are equal */
        finalValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        return finalValue;
    }
}
