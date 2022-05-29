package yehor.epam.utilities;

import java.time.Duration;
import java.time.LocalTime;

public final class OtherConstants {
    /**
     * Param name of user's id in session/cookies
     */
    public static final String USER_ID = "userID";
    /**
     * Param name of user's role in session/cookies
     */
    public static final String USER_ROLE = "userRole";
    /**
     * Param name for set error messages
     */
    public static final String REQUEST_PARAM_ERROR_MESSAGE = "errorMessage";
    /**
     * Minimum set time for Session
     */
    public static final LocalTime MIN_SESSION_TIME = LocalTime.parse("09:00");
    /**
     * Maximum set time for Session
     */
    public static final LocalTime MAX_SESSION_TIME = LocalTime.parse("22:00");
    /**
     * Lifetime of Cookie login in seconds, equal to 180 days
     */
    public static final int COOKIE_LOGIN_LIFETIME = (int) Duration.ofDays(180).toSeconds();

    public static final int COOKIE_LANG_LIFETIME = (int) Duration.ofDays(360).toSeconds();

    public static final String LANG = "lang";

    public static final String DEFAULT_LANG = "ua";

    public static final String DEFAULT_CURRENCY = "UAH";

    public static final String FONTS_BAHNSCHRIFT_TTF_PATH = "fonts/bahnschrift.ttf";

    // for captcha
    public static final String CAPTCHA_SECRET_KEY = "6LeFvCUgAAAAAK4QAJyBYzzvvAZRweyc_RbaaprK";
    public static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";

    // Constants for Filter and Sorter on Schedule page
    public static final String SESSION_FILTER_SHOW_PARAM_NAME = "show";
    public static final String SESSION_FILTER_SHOW_ONLY_AVAILABLE = "showOnlyAvailable";
    //
    public static final String SESSION_SORT_BY_PARAM_NAME = "sortBy";
    public static final String SESSION_SORT_BY_DATETIME = "dateTimeSortBy";
    public static final String SESSION_SORT_BY_FILM_NAME = "filmNameSortBy";
    public static final String SESSION_SORT_BY_SEATS_REMAIN = "seatsRemainSortBy";
    //
    public static final String SESSION_SORT_METHOD_PARAM_NAME = "sortMethod";
    public static final String SESSION_SORT_METHOD_DESC = "descendingSortMethod";

    private OtherConstants() {
    }
}
