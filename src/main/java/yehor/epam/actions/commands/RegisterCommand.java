package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.UserDAO;
import yehor.epam.dao.exception.RegisterException;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.User;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.InnerRedirectManager;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.CommandConstants.COMMAND_VIEW_PROFILE_PAGE;
import static yehor.epam.utilities.OtherConstants.*;

public class RegisterCommand implements BaseCommand {
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
                logger.debug("User was inserted");
                final HttpSession session = request.getSession(true);
                session.setAttribute(USER_ID, user.getId());
                session.setAttribute(USER_ROLE, user.getUserRole());
                CookieService cookieService = new CookieService();
                cookieService.loginCookie(response, user);
            } else logger.debug("User wasn't inserted");
            response.sendRedirect(InnerRedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
        } catch (RegisterException e) {
            request.setAttribute(REQUEST_PARAM_ERROR_MESSAGE, e.getMessage());
            logger.debug("Forward to errorPage from " + classSimpleName);
            new ErrorPageCommand().execute(request, response);
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
