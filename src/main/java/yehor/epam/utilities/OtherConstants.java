package yehor.epam.utilities;

import java.time.Duration;

public final class OtherConstants {
    // Filter
    public static final String USER_ID = "userID";
    public static final String USER_ROLE = "userRole";

    // Errors
    public static final String LOGIN_ERROR = "loginError";
    public static final String REQUEST_PARAM_ERROR_MESSAGE = "errorMessage";
    /**
     * Lifetime of Cookie login in seconds, equal to 180 days
     */
    public static final int COOKIE_LOGIN_LIFETIME = (int) Duration.ofDays(180).toSeconds();

}
