package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Session;
import yehor.epam.utilities.LoggerManager;

import java.util.List;

import static yehor.epam.utilities.JspPagePathConstants.SCHEDULE_PAGE_PATH;

public class ScheduleCommand implements ActionCommand {
    private static final Logger logger = LoggerManager.getLogger(ScheduleCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + ScheduleCommand.class.getSimpleName() + " execute command");
            final SessionDAO sessionDAO = factory.getSessionDao();
            final List<Session> sessionsList = sessionDAO.findAll();
            request.setAttribute("sessionsList", sessionsList);
            request.getRequestDispatcher(SCHEDULE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error("Couldn't execute " + ScheduleCommand.class.getSimpleName() + " command", e);
        }
    }
}
