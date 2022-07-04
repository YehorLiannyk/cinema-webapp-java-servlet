package yehor.epam.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.spy;
import static yehor.epam.utilities.constants.OtherConstants.SALT_LENGTH;

class PassEncryptionManagerTest {

    private PassEncryptionManager manager = spy(PassEncryptionManager.class);

    @Test
    void getSaltValue() {
        final String saltValue = manager.getSaltValue(anyInt());
        Assertions.assertNotNull(saltValue);
    }

    @Test
    void generateSecurePassword() {
        final String saltValue = manager.getSaltValue(SALT_LENGTH);
        final String password = manager.generateSecurePassword("213", saltValue);
        Assertions.assertNotNull(password);
    }

    @Test
    void verifyUserPassword() {
        final String saltValue = manager.getSaltValue(SALT_LENGTH);
        final String password = "123";
        final String encodePass = manager.generateSecurePassword(password, saltValue);
        final boolean b = manager.verifyUserPassword(password, encodePass, saltValue);
        Assertions.assertTrue(b);
    }

    @Test
    void notVerifyUserPassword() {
        final String saltValue = manager.getSaltValue(SALT_LENGTH);
        final String password = "123";
        final String encodePass = manager.generateSecurePassword(password, saltValue);
        final boolean b = manager.verifyUserPassword("321", encodePass, saltValue);
        Assertions.assertFalse(b);
    }
}