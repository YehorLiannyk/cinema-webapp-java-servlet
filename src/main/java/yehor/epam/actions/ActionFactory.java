package yehor.epam.actions;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.actions.commands.*;
import yehor.epam.utilities.LoggerManager;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.ActionCommandConstants.*;

/**
 * Factory class from Factory method pattern
 * Define received command and create appropriate object which implementing ActionCommand interface
 */
public class ActionFactory {
    private static final Logger logger = LoggerManager.getLogger(ActionFactory.class);

    private Map<String, ActionCommand> commandMap;

    private void init() {
        commandMap = new HashMap<>();

        commandMap.put(ACTION_VIEW_MAIN, new MainPageCommand());
        commandMap.put(ACTION_VIEW_SCHEDULE, new ScheduleCommand());
        //login
        commandMap.put(ACTION_VIEW_LOGIN, new LoginPageCommand());
        commandMap.put(ACTION_LOGIN, new LoginCommand());
        //register
        commandMap.put(ACTION_VIEW_REGISTER, new RegisterPageCommand());
        commandMap.put(ACTION_REGISTER, new RegisterCommand());
        //logout
        commandMap.put(ACTION_LOGOUT, new LogoutCommand());

    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        if (commandMap == null || commandMap.isEmpty()) {
            init();
        }
        String command = request.getParameter("command");
        /*if (command == null || command.isEmpty())
            command = (String) request.getAttribute("command");*/

        logger.debug("ActionFactory received command: " + command);
        if (command == null || command.isEmpty())
            return commandMap.get(ACTION_VIEW_MAIN);
        return commandMap.get(command);
    }
}
