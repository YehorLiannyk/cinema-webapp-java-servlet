package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.MAIN_PAGE_PATH;

public class RegisterCommand implements ActionCommand {
    private static final Logger logger = LoggerManager.getLogger(RegisterCommand.class);
    private final String classSimpleName = RegisterCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + classSimpleName + " execute command");
            final User user = getUserFromRequest(request);
            final UserDAO userDao = factory.getUserDao();
            final boolean inserted = userDao.insert(user);
            if (inserted) {
                CookieService cookieService = new CookieService();
                cookieService.addLoginCookie(response, user);
            }
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error("Couldn't execute " + classSimpleName + " command", e);
        }
    }

    private User getUserFromRequest(HttpServletRequest request) {
        return new User(
                request.getParameter("firstName"),
                request.getParameter("secondName"),
                request.getParameter("email"),
                request.getParameter("password"),
                request.getParameter("phoneNumber"),
                fromCheckboxToBoolean(request.getParameter("notification"))
        );
    }

    private boolean fromCheckboxToBoolean(String checkboxValue) {
        return checkboxValue != null && checkboxValue.equals("on");
    }
}
