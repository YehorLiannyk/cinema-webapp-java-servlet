package yehor.epam.utilities.constants;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Class containing different constants
 */
public final class OtherConstants {
    public static final int DEF_PAGING_SIZE = 6;
    public static final String PAGE_NO_PARAM = "page";
    public static final String PAGE_SIZE_PARAM = "size";
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
    /**
     * Lifetime of Cookie Local in seconds, equal to 360 days
     */
    public static final int COOKIE_LANG_LIFETIME = (int) Duration.ofDays(360).toSeconds();
    /**
     * Language/locale param name
     */
    public static final String LANG = "lang";
    /**
     * Default Language param value
     */
    public static final String DEFAULT_LANG = "ua";
    /**
     * Default currency param value
     */
    public static final String DEFAULT_CURRENCY = "UAH";
    /**
     * Path to font for PDF former
     */
    public static final String FONTS_BAHNSCHRIFT_TTF_PATH = "fonts/bahnschrift.ttf";

    /**
     * Secret key for Recaptcha
     */
    public static final String CAPTCHA_SECRET_KEY = "6LeFvCUgAAAAAK4QAJyBYzzvvAZRweyc_RbaaprK";
    /**
     * URL for Recaptcha
     */
    public static final String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify";

    // Constants for Filter and Sorter on Schedule page
    /**
     * Schedule page filter param name
     */
    public static final String SESSION_FILTER_SHOW_PARAM_NAME = "show";
    /**
     * Schedule page filter "show only available" param value
     */
    public static final String SESSION_FILTER_SHOW_ONLY_AVAILABLE = "onlyAvailable";
    /**
     * Schedule page filter "show all" param value
     */
    public static final String SESSION_FILTER_SHOW_ALL = "all";

    /**
     * Schedule page sorter param name
     */
    public static final String SESSION_SORT_BY_PARAM_NAME = "sortBy";
    /**
     * Schedule page sorter by datetime param value
     */
    public static final String SESSION_SORT_BY_DATETIME = "dateTime";
    /**
     * Schedule page sorter by film name param value
     */
    public static final String SESSION_SORT_BY_FILM_NAME = "filmName";
    /**
     * Schedule page sorter by remaining seats param value
     */
    public static final String SESSION_SORT_BY_SEATS_REMAIN = "seatsRemain";

    /**
     * Schedule page sort method param name
     */
    public static final String SESSION_SORT_METHOD_PARAM_NAME = "sortMethod";
    /**
     * Schedule page descending sort method
     */
    public static final String SESSION_SORT_METHOD_DESC = "desc";
    /**
     * Schedule page ascending sort method
     */
    public static final String SESSION_SORT_METHOD_ASC = "asc";
    /**
     * Salt length for password encrypt
     */
    public static final int SALT_LENGTH = 30;
    /**
     * Default name for ticket file name
     */
    public static final String DEF_TICKET_FILENAME= "ticket";

    private OtherConstants() {
    }
}
