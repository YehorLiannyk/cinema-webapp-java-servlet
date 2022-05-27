package yehor.epam.actions;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.actions.commands.*;
import yehor.epam.actions.commands.films.AddFilmCommand;
import yehor.epam.actions.commands.films.AddFilmPageCommand;
import yehor.epam.actions.commands.films.FilmsSettingPageCommand;
import yehor.epam.actions.commands.sessions.AddSessionCommand;
import yehor.epam.actions.commands.sessions.AddSessionPageCommand;
import yehor.epam.actions.commands.sessions.SessionsSettingPageCommand;
import yehor.epam.actions.commands.signing.*;
import yehor.epam.utilities.LoggerManager;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.CommandConstants.*;

/**
 * Factory class from Factory method pattern
 * Define received command and create appropriate object which implementing BaseCommand interface
 */
public class CommandFactory {
    private static final Logger logger = LoggerManager.getLogger(CommandFactory.class);

    private Map<String, BaseCommand> commandMap;

    private void init() {
        commandMap = new HashMap<>();

        commandMap.put(COMMAND_VIEW_MAIN, new MainPageCommand());
        commandMap.put(COMMAND_VIEW_SCHEDULE, new ScheduleCommand());
        //login
        commandMap.put(COMMAND_VIEW_LOGIN, new LoginPageCommand());
        commandMap.put(COMMAND_LOGIN, new LoginCommand());
        //register
        commandMap.put(COMMAND_VIEW_REGISTER, new RegisterPageCommand());
        commandMap.put(COMMAND_REGISTER, new RegisterCommand());
        //logout
        commandMap.put(COMMAND_LOGOUT, new LogoutCommand());
        //profile
        commandMap.put(COMMAND_VIEW_PROFILE_PAGE, new ProfilePageCommand());
        //error
        commandMap.put(COMMAND_VIEW_ERROR, new ErrorPageCommand());
        //films
        commandMap.put(COMMAND_VIEW_ADD_FILM, new AddFilmPageCommand());
        commandMap.put(COMMAND_ADD_FILM, new AddFilmCommand());
        commandMap.put(COMMAND_VIEW_FILMS_SETTING_PAGE, new FilmsSettingPageCommand());
        //sessions
        commandMap.put(COMMAND_VIEW_ADD_SESSION, new AddSessionPageCommand());
        commandMap.put(COMMAND_ADD_SESSION, new AddSessionCommand());
        commandMap.put(COMMAND_VIEW_SESSIONS_SETTING_PAGE, new SessionsSettingPageCommand());
        commandMap.put(COMMAND_VIEW_SESSION_PAGE, new SessionPageCommand());
        //ticket
        commandMap.put(COMMAND_VIEW_BUY_TICKET_PAGE, new BuyTicketPageCommand());
        commandMap.put(COMMAND_BUY_TICKET, new BuyTicketCommand());
        commandMap.put(COMMAND_VIEW_SUCCESS_PAY_PAGE, new SuccessPayPageCommand());

    }

    public BaseCommand defineCommand(HttpServletRequest request) {
        if (commandMap == null || commandMap.isEmpty()) {
            init();
        }
        String command = request.getParameter("command");
        /*if (command == null || command.isEmpty())
            command = (String) request.getAttribute("command");*/

        logger.debug("CommandFactory received command: " + command);
        if (command == null || command.isEmpty())
            return commandMap.get(COMMAND_VIEW_MAIN);
        return commandMap.get(command);
    }
}
