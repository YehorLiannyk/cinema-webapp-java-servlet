package yehor.epam.utilities.constants;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

/**
 * Class containing different constants
 */
public final class OtherConstants {
    /**
     * Default pagination size
     */
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
    public static final String DEFAULT_LANG = "uk";
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
    public static final String DEF_TICKET_FILENAME = "ticket";

    /*  SOME PARAM NAMES  */
    //user
    public static final String F_NAME_PARAM = "firstName";
    public static final String L_NAME_PARAM = "lastName";
    public static final String EMAIL_PARAM = "email";
    public static final String PASS_PARAM = "password";
    public static final String PASS_CONFIRM_PARAM = "confirm_password";
    public static final String PHONE_PARAM = "phoneNumber";
    public static final String NOTIFICATION_PARAM = "notification";
    //film
    public static final String FILM_NAME_PARAM = "filmName";
    public static final String FILM_DESCR_PARAM = "filmDescription";
    public static final String GENRE_IDS_PARAM = "genreIds";
    public static final String POSTER_URL_PARAM = "posterUrl";
    public static final String FILM_DURATION_PARAM = "filmDuration";
    public static final String FILM_ID_PARAM = "filmId";
    //session
    public static final String SESSION_DATE_PARAM = "date";
    public static final String SESSION_TIME_PARAM = "time";
    public static final String SESSION_PRICE_PARAM = "ticketPrice";

    /*  VALIDATION  */
    // patterns
    public static final String PHONE_NUMBER_PATTERN = "^(380[0-9]{9})|(^$)|(^\\s*$)$"; // empty/has a gap/is a number
    public static final String ONLY_LETTERS_PATTERN = "^[a-zA-Z]+$";
    public static final String ONLY_DIGITS_PATTERN = "^[0-9]+$";
    public static final String DATE_PATTERN = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
    public static final String TIME_PATTERN = "^(\\d\\d:\\d\\d)$";
    public static final String EMAIL_PATTERN = "^(([^<>()[\\]\\\\.,;:\\s@\\\"]]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,10}))$";
    // user
    public static final int MAX_F_NAME_LENGTH = 45;
    public static final int MIN_F_NAME_LENGTH = 1;
    public static final int MAX_L_NAME_LENGTH = 45;
    public static final int MIN_L_NAME_LENGTH = 1;
    public static final int MAX_PASS_LENGTH = 120;
    public static final int MIN_PASS_LENGTH = 3;
    public static final int MAX_EMAIL_LENGTH = 320;
    public static final int MIN_EMAIL_LENGTH = 6;
    // session
    public static final int MIN_TICKET_COST = 1;
    public static final int MAX_TICKET_COST = 5000;
    // film
    public static final int MAX_FILM_NAME_LENGTH = 100;
    public static final int MAX_FILM_DESC_LENGTH = 800;
    public static final int MIN_FILM_DURATION_IN_MINUTE = 10;
    public static final int MAX_FILM_DURATION_IN_MINUTE = 300;
    public static final int MAX_URL_LENGTH = 2000;

    /*  VALIDATION ERROR PARAM    */
    // user param
    public static final String VALID_F_NAME_LENGTH = "fName_length";
    public static final String VALID_F_NAME_EMPTY = "fName_empty";
    public static final String VALID_F_NAME_INVALID = "fName_invalid";
    public static final String VALID_L_NAME_LENGTH = "lName_length";
    public static final String VALID_L_NAME_EMPTY = "lName_empty";
    public static final String VALID_L_NAME_INVALID = "lName_invalid";
    public static final String VALID_EMAIL_LENGTH = "email_length";
    public static final String VALID_EMAIL_EMPTY = "email_empty";
    public static final String VALID_EMAIL_INVALID = "email_invalid";
    public static final String VALID_PASS_EMPTY = "pass_empty";
    public static final String VALID_PASS_LENGTH = "pass_length";
    public static final String VALID_PASS_CONFIRM_EMPTY = "passConfirm_empty";
    public static final String VALID_PASS_CONFIRM_NOT_EQUAL = "passConfirm_notEqual";
    public static final String VALID_PHONE_INVALID = "phone_invalid";
    // film param
    public static final String VALID_FILM_NAME_LENGTH = "filmName_length";
    public static final String VALID_FILM_NAME_EMPTY = "filmName_empty";
    public static final String VALID_FILM_DESC_LENGTH = "filmDesc_length";
    public static final String VALID_GENRE_LIST_EMPTY = "genreList_empty";
    public static final String VALID_URL_LENGTH = "url_length";
    public static final String VALID_URL_EMPTY = "url_empty";
    public static final String VALID_DURATION_RANGE = "duration_range";
    public static final String VALID_DURATION_EMPTY = "duration_empty";
    // session param
    public static final String VALID_FILM_EMPTY = "film_empty";
    public static final String VALID_DATE_EMPTY = "date_empty";
    public static final String VALID_DATE_RANGE = "date_range";
    public static final String VALID_DATE_INVALID = "date_invalid";
    public static final String VALID_TIME_EMPTY = "time_empty";
    public static final String VALID_TIME_RANGE = "time_range";
    public static final String VALID_TIME_INVALID = "time_invalid";
    public static final String VALID_PRICE_EMPTY = "price_empty";
    public static final String VALID_PRICE_RANGE = "price_range";
    public static final String VALID_PRICE_INVALID = "price_invalid";

    /**
     * List of all possible error params for user validation
     */
    public static final List<String> VALID_ERROR_USER_PARAM_LIST = List.of(
            VALID_F_NAME_LENGTH, VALID_F_NAME_EMPTY, VALID_F_NAME_INVALID, VALID_L_NAME_LENGTH, VALID_L_NAME_EMPTY,
            VALID_L_NAME_INVALID, VALID_EMAIL_LENGTH, VALID_EMAIL_EMPTY, VALID_EMAIL_INVALID, VALID_PASS_EMPTY,
            VALID_PASS_LENGTH, VALID_PHONE_INVALID, VALID_PASS_CONFIRM_EMPTY, VALID_PASS_CONFIRM_NOT_EQUAL
    );
    /**
     * List of all possible error params for film validation
     */
    public static final List<String> VALID_ERROR_FILM_PARAM_LIST = List.of(
            VALID_FILM_NAME_LENGTH, VALID_FILM_NAME_EMPTY, VALID_FILM_DESC_LENGTH, VALID_URL_LENGTH,
            VALID_URL_EMPTY, VALID_DURATION_RANGE, VALID_DURATION_EMPTY, VALID_GENRE_LIST_EMPTY
    );
    /**
     * List of all possible error params for session validation
     */
    public static final List<String> VALID_ERROR_SESSION_PARAM_LIST = List.of(
            VALID_FILM_EMPTY, VALID_TIME_EMPTY, VALID_TIME_RANGE, VALID_TIME_INVALID, VALID_DATE_EMPTY,
            VALID_DATE_RANGE, VALID_DATE_INVALID, VALID_PRICE_EMPTY, VALID_PRICE_RANGE, VALID_PRICE_INVALID
    );

    private OtherConstants() {
    }
}
