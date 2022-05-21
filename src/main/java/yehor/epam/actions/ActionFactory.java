package yehor.epam.actions;

import jakarta.servlet.http.HttpServletRequest;
import yehor.epam.actions.commands.MainPageCommand;
import yehor.epam.actions.commands.ScheduleCommand;

import java.util.HashMap;
import java.util.Map;

import static yehor.epam.utilities.ActionCommandConstants.ACTION_VIEW_MAIN;
import static yehor.epam.utilities.ActionCommandConstants.ACTION_VIEW_SCHEDULE;

public class ActionFactory {
    private Map<String, ActionCommand> commandMap;

    private void init() {
        commandMap = new HashMap<>();

        commandMap.put(ACTION_VIEW_MAIN, new MainPageCommand());
        commandMap.put(ACTION_VIEW_SCHEDULE, new ScheduleCommand());
    }

    public ActionCommand defineCommand(HttpServletRequest request) {
        if (commandMap == null || commandMap.isEmpty()) {
            init();
        }
        final String command = request.getParameter("command");
        if (command == null || command.isEmpty())
            return commandMap.get(ACTION_VIEW_MAIN);
        return commandMap.get(command);
    }
}
