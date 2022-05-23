package yehor.epam.actions.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.exception.AuthException;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.User;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

import static yehor.epam.utilities.JspPagePathConstants.ERROR_500_PAGE_PATH;
import static yehor.epam.utilities.JspPagePathConstants.MAIN_PAGE_PATH;
import static yehor.epam.utilities.OtherConstants.*;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LoggerManager.getLogger(LoginCommand.class);
    private String classSimpleName = LoginCommand.class.getSimpleName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + classSimpleName + " execute command");
            final String login = request.getParameter("login");
            final String password = request.getParameter("password");
            final UserDAO userDao = factory.getUserDao();
            userAuth(request, response, login, password, userDao);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error("Couldn't execute " + classSimpleName + " command", e);
        }
    }

    private void userAuth(HttpServletRequest request, HttpServletResponse response, String login, String password, UserDAO userDao)
            throws ServletException, IOException {
        try {
            final User user = userDao.getUser(login, password);
            HttpSession session = request.getSession();
            session.setAttribute(USER_ID, user.getId());
            session.setAttribute(USER_ROLE, user.getUserRole());
            logger.info("User with id: " + user.getId() + ", role = " + user.getUserRole() + " login");
        } catch (AuthException e) {
            logger.warn("Couldn't find user", e);
            logger.warn("Couldn't find user with login: " + request.getParameter("login") + " pass: " + request.getParameter("password"));
            request.setAttribute(REQUEST_PARAM_ERROR_MESSAGE, e.getMessage());
            logger.debug("Forward to " + ERROR_500_PAGE_PATH + " from " + classSimpleName);
            request.getRequestDispatcher(ERROR_500_PAGE_PATH).forward(request, response);
        }
    }
}
