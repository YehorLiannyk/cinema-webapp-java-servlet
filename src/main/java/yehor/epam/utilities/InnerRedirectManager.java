package yehor.epam.utilities;

import static yehor.epam.utilities.CommandConstants.COMMAND_MAIN_SERVLET;

/**
 * Manager for inner app redirection among commands
 */
public class InnerRedirectManager {
    private InnerRedirectManager() {
    }

    public static String getRedirectLocation(String command) {
        return COMMAND_MAIN_SERVLET + "?command=" + command;

    }
}
