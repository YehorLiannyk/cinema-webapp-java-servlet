package yehor.epam.actions.commands.sessions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.entities.Film;
import yehor.epam.entities.Session;
import yehor.epam.exceptions.ServiceException;
import yehor.epam.services.FilmService;
import yehor.epam.services.SessionService;
import yehor.epam.services.impl.ErrorService;
import yehor.epam.services.impl.FilmServiceImpl;
import yehor.epam.services.impl.SessionServiceImpl;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.RedirectManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_SESSIONS_SETTING_PAGE;
import static yehor.epam.utilities.constants.OtherConstants.*;

/**
 * Admin add Session Command
 */
public class AddSessionCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(AddSessionCommand.class);
    private static final String CLASS_NAME = AddSessionCommand.class.getName();
    private final SessionService sessionService;
    private final FilmService filmService;

    public AddSessionCommand() {
        sessionService = new SessionServiceImpl();
        filmService = new FilmServiceImpl();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("Called execute() in " + CLASS_NAME);
        try {
            final Map<String, String> sessionParamMap = getSessionParamMap(request);
            final List<String> errorList = sessionService.getSessionValidErrorList(sessionParamMap);
            if (errorList.isEmpty()) {
                final Session session = getSession(sessionParamMap);
                sessionService.save(session);
                response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_SESSIONS_SETTING_PAGE));
            } else {
                forwardWithErrors(request, response, errorList);
            }
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    /**
     * Forward to add session page with error list
     *
     * @param request   {@link HttpServletRequest}
     * @param response  {@link HttpServletResponse}
     * @param errorList received error List from validation service
     */
    private void forwardWithErrors(HttpServletRequest request, HttpServletResponse response, List<String> errorList) {
        VALID_ERROR_SESSION_PARAM_LIST.stream()
                .filter(error -> request.getAttribute(error) != null)
                .forEach(error -> request.setAttribute(error, false));
        errorList.forEach(error -> request.setAttribute(error, true));
        new AddSessionPageCommand().execute(request, response);
    }

    /**
     * Get session from params Map
     *
     * @param sessionParamMap session parameters map
     * @return formed Session object
     */
    private Session getSession(Map<String, String> sessionParamMap) throws ServiceException {
        final int filmId = Integer.parseInt(sessionParamMap.get(FILM_ID_PARAM));
        final Film film = filmService.getById(filmId);
        final Session session = new Session();
        session.setTime(LocalTime.parse(sessionParamMap.get(SESSION_TIME_PARAM)));
        session.setDate(LocalDate.parse(sessionParamMap.get(SESSION_DATE_PARAM)));
        session.setTicketPrice(new BigDecimal(sessionParamMap.get(SESSION_PRICE_PARAM)));
        session.setFilm(film);
        return session;
    }

    /**
     * Get Map of needed parameters for session creation
     *
     * @param request {@link HttpServletRequest}
     * @return map of needed parameters for session creation
     */
    private Map<String, String> getSessionParamMap(HttpServletRequest request) {
        Map<String, String> sessionParamMap = new HashMap<>();
        sessionParamMap.put(FILM_ID_PARAM, request.getParameter(FILM_ID_PARAM));
        sessionParamMap.put(SESSION_TIME_PARAM, request.getParameter(SESSION_TIME_PARAM));
        sessionParamMap.put(SESSION_DATE_PARAM, request.getParameter(SESSION_DATE_PARAM));
        sessionParamMap.put(SESSION_PRICE_PARAM, request.getParameter(SESSION_PRICE_PARAM));
        return sessionParamMap;
    }

}
