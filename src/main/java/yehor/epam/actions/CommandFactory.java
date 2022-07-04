package yehor.epam.actions;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import yehor.epam.actions.commands.*;
import yehor.epam.actions.commands.films.*;
import yehor.epam.actions.commands.sessions.*;
import yehor.epam.actions.commands.signing.*;
import yehor.epam.actions.commands.tickets.BuyTicketCommand;
import yehor.epam.actions.commands.tickets.BuyTicketPageCommand;
import yehor.epam.actions.commands.tickets.DownloadPDFTicketCommand;
import yehor.epam.utilities.LoggerManager;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.constants.CommandConstants.*;

/**
 * Factory class from Factory method pattern
 * Define received command and create appropriate object which implementing BaseCommand interface
 */
public class CommandFactory {
    private static final Logger logger = LoggerManager.getLogger(CommandFactory.class);

    /**
     * Map contains String commands and BaseCommand objects
     */
    private Map<String, BaseCommand> commandMap;

    private void init() {
        commandMap = new HashMap<>();

        // general pages
        commandMap.put(COMMAND_VIEW_MAIN_PAGE, new MainPageCommand());
        commandMap.put(COMMAND_VIEW_SCHEDULE_PAGE, new ScheduleCommand());
        commandMap.put(COMMAND_VIEW_ERROR_PAGE, new ErrorPageCommand());

        // login, register, logout
        commandMap.put(COMMAND_VIEW_LOGIN_PAGE, new LoginPageCommand());
        commandMap.put(COMMAND_LOGIN, new LoginCommand());
        commandMap.put(COMMAND_VIEW_REGISTER_PAGE, new RegisterPageCommand());
        commandMap.put(COMMAND_REGISTER, new RegisterCommand());
        commandMap.put(COMMAND_LOGOUT, new LogoutCommand());

        // profile
        commandMap.put(COMMAND_VIEW_PROFILE_PAGE, new ProfilePageCommand());

        // films
        commandMap.put(COMMAND_VIEW_ADD_FILM_PAGE, new AddFilmPageCommand());
        commandMap.put(COMMAND_ADD_FILM, new AddFilmCommand());
        commandMap.put(COMMAND_VIEW_FILMS_SETTING_PAGE, new FilmsSettingPageCommand());
        commandMap.put(COMMAND_VIEW_FILM_PAGE_PAGE, new FilmInfoPageCommand());
        commandMap.put(COMMAND_DELETE_FILM, new DeleteFilmCommand());

        // sessions
        commandMap.put(COMMAND_VIEW_ADD_SESSION_PAGE, new AddSessionPageCommand());
        commandMap.put(COMMAND_ADD_SESSION, new AddSessionCommand());
        commandMap.put(COMMAND_VIEW_SESSIONS_SETTING_PAGE, new SessionsSettingPageCommand());
        commandMap.put(COMMAND_VIEW_SESSION_PAGE, new SessionPageCommand());
        commandMap.put(COMMAND_VIEW_SESSION_INFO_PAGE, new SessionInfoPageCommand());

        //ticket
        commandMap.put(COMMAND_VIEW_BUY_TICKET_PAGE, new BuyTicketPageCommand());
        commandMap.put(COMMAND_BUY_TICKET, new BuyTicketCommand());
        commandMap.put(COMMAND_VIEW_SUCCESS_PAY_PAGE, new SuccessPayPageCommand());
        commandMap.put(COMMAND_DELETE_SESSION, new DeleteSessionCommand());
        //commandMap.put(COMMAND_SEND_MAIL, new SendMailCommand());
        //commandMap.put(COMMAND_SEND_TICKET_VIA_MAIL, new SendTicketViaMailCommand());
        commandMap.put(COMMAND_DOWNLOAD_PDF_TICKET, new DownloadPDFTicketCommand());
    }

    /**
     * Method defining received String command and return appropriate BaseCommand object
     *
     * @param request HttpServletRequest
     * @return appropriate BaseCommand object by received String command
     */
    public BaseCommand defineCommand(HttpServletRequest request) {
        if (commandMap == null || commandMap.isEmpty()) {
            init();
        }

        String command = request.getParameter("command");
        logger.info("CommandFactory received command: " + command);

        if (command == null || command.isEmpty())
            return commandMap.get(COMMAND_VIEW_MAIN_PAGE);
        return commandMap.get(command);
    }
}
