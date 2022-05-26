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

    public static final String FILTERED = "filtered";
    public static final String SORTED = "sorted";

    public static final String SESSION_FILTER_IS_AVAILABLE = "isAvailable";
    public static final String SESSION_FILTER_IS_NOT_AVAILABLE = "isNotAvailable";
    public static final String SESSION_FILTER_FILM_IDS = "filmIds";
    public static final String SESSION_FILTER_MIN_TICKET_PRICE = "minTicketPrice";
    public static final String SESSION_FILTER_MAX_TICKET_PRICE = "maxTicketPrice";


    /*public static final String[] SESSION_SORTER_NAMES_ARRAY =
            new String[]{"sortBy", "sortMethod"};

    public static final String[] SESSION_SORTER_BY_PARAM_NAMES_ARRAY =
            new String[]{"dateTimeSortBy", "filmNameSortBy", "seatsRemainSortBy"};

    public static final String[] SESSION_SORTER_METHOD_NAMES_ARRAY =
            new String[]{"ascendingSortMethod", "descendingSortMethod"};*/

    private OtherConstants() {
    }
}
