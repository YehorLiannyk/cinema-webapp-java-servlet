package yehor.epam.utilities.constants;

/**
 * Constants of all servlet's commands used by CommandFactory
 */
public final class CommandConstants {
    /**
     * Main Servlet (<code>Controller</code>) value
     */
    public static final String COMMAND_MAIN_SERVLET = "main";
    /**
     * Command to send email
     */
    public static final String COMMAND_SEND_MAIL = "sendMail";
    /**
     * Command to send ticket via email
     */
    public static final String COMMAND_SEND_TICKET_VIA_MAIL = "sendTicketViaMail";
    /**
     * Command to download ticket
     */
    public static final String COMMAND_DOWNLOAD_PDF_TICKET = "downloadTicket";

    // Menu pages
    /**
     * Command to go to main page
     */
    public static final String COMMAND_VIEW_MAIN_PAGE = "mainPage";
    /**
     * Command to go to Schedule of Sessions page
     */
    public static final String COMMAND_VIEW_SCHEDULE_PAGE = "schedulePage";

    // Signing commands
    /**
     * Command to go to login page
     */
    public static final String COMMAND_VIEW_LOGIN_PAGE = "loginPage";
    /**
     * Command to authorization and authentication user
     */
    public static final String COMMAND_LOGIN = "login";
    /**
     * Command to go to register page
     */
    public static final String COMMAND_VIEW_REGISTER_PAGE = "registerPage";
    /**
     * Command to register user and insert him to Database
     */
    public static final String COMMAND_REGISTER = "register";
    /**
     * Command to logout user and clean cookie
     */
    public static final String COMMAND_LOGOUT = "logout";

    /**
     * Command to go to user profile page
     */
    public static final String COMMAND_VIEW_PROFILE_PAGE = "profilePage";
    /**
     * Command to go to error page
     */
    public static final String COMMAND_VIEW_ERROR_PAGE = "errorPage";

    // films
    /**
     * Command to go to adding film page
     */
    public static final String COMMAND_VIEW_ADD_FILM_PAGE = "addFilmPage";
    /**
     * Command to add film to database
     */
    public static final String COMMAND_ADD_FILM = "addFilm";
    /**
     * Command to go to admin films setting page
     */
    public static final String COMMAND_VIEW_FILMS_SETTING_PAGE = "filmsSettingPage";
    /**
     * Command to go to film info page
     */
    public static final String COMMAND_VIEW_FILM_PAGE_PAGE = "filmPage";
    /**
     * Command to delete film from database
     */
    public static final String COMMAND_DELETE_FILM = "deleteFilm";

    // sessions
    /**
     * Command to go to adding session page
     */
    public static final String COMMAND_VIEW_ADD_SESSION_PAGE = "addSessionPage";
    /**
     * Command to insert session to Database
     */
    public static final String COMMAND_ADD_SESSION = "addSession";
    /**
     * Command to go to admin sessions setting page
     */
    public static final String COMMAND_VIEW_SESSIONS_SETTING_PAGE = "sessionsSettingPage";
    /**
     * Command to go to user session page
     */
    public static final String COMMAND_VIEW_SESSION_PAGE = "sessionPage";
    /**
     * Command to go to admin session info page
     */
    public static final String COMMAND_VIEW_SESSION_INFO_PAGE = "sessionInfoPage";
    /**
     * Command to delete session from DB
     */
    public static final String COMMAND_DELETE_SESSION = "deleteSession";

    /**
     * Command to go to buy ticket page
     */
    public static final String COMMAND_VIEW_BUY_TICKET_PAGE = "buyTicketPage";
    /**
     * Command to buy ticket and insert it to DB
     */
    public static final String COMMAND_BUY_TICKET = "buyTicket";
    /**
     * Command to go to success payment page
     */
    public static final String COMMAND_VIEW_SUCCESS_PAY_PAGE = "successPayPage";

    private CommandConstants() {
    }
}
