package yehor.epam.utilities;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_MAIN_SERVLET;

/**
 * Manager for app inner redirection among commands
 */
public class RedirectManager {
    private RedirectManager() {
    }

    /**
     * Form path to redirect to page of some command
     *
     * @param command received command
     * @return path
     */
    public static String getRedirectLocation(String command) {
        return COMMAND_MAIN_SERVLET + "?command=" + command;

    }
}
