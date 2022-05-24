package yehor.epam.utilities;

public final class CommandConstants {
    /**
     * Main Servlet (<code>Controller</code>) value
     */
    public static final String COMMAND_MAIN_SERVLET = "main";
    // Menu pages
    public static final String COMMAND_VIEW_MAIN = "mainPage";
    public static final String COMMAND_VIEW_SCHEDULE = "schedule";
    // Signing commands
    public static final String COMMAND_VIEW_LOGIN = "loginPage";
    public static final String COMMAND_LOGIN = "login";
    public static final String COMMAND_VIEW_REGISTER = "registerPage";
    public static final String COMMAND_REGISTER = "register";
    public static final String COMMAND_LOGOUT = "logout";
    // Profile
    public static final String COMMAND_VIEW_PROFILE_PAGE = "profilePage";
    //
    public static final String COMMAND_VIEW_ERROR = "errorPage";
    // films
    public static final String COMMAND_VIEW_ADD_FILM = "addFilmPage";
    public static final String COMMAND_ADD_FILM = "addFilm";
    public static final String COMMAND_VIEW_ALL_FILMS_PAGE = "allFilmsPage";

    private CommandConstants() {
    }
}
