package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.USER_PROFILE_PAGE_PATH;

public class ProfilePageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ProfilePageCommand.class);
    private final String className = ProfilePageCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");
/*            final String login = request.getParameter("login");
            final String password = request.getParameter("password");
            final UserDAO userDao = factory.getUserDao();*/
            request.getRequestDispatcher(USER_PROFILE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }
}
