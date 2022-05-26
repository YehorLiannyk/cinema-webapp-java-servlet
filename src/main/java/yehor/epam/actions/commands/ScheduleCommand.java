package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.GenreDAO;
import yehor.epam.dao.SessionDAO;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.entities.Genre;
import yehor.epam.entities.Session;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import static yehor.epam.utilities.JspPagePathConstants.SCHEDULE_PAGE_PATH;

public class ScheduleCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ScheduleCommand.class);
    private static final String CLASS_NAME = ScheduleCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + CLASS_NAME + " execute command");
            List<Session> sessionList = null;
            List<Genre> genreList = null;

            logged(request);

            sessionList = getSessionList(factory);

            request.setAttribute("sessionList", sessionList);
            genreList = getGenreList(factory);
            request.setAttribute("genreList", genreList);
            request.getRequestDispatcher(SCHEDULE_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }


    private List<Session> getSessionList(DAOFactory factory) {
        final SessionDAO sessionDAO = factory.getSessionDao();
        return sessionDAO.findAll();
    }

    private List<Genre> getGenreList(DAOFactory factory) {
        final GenreDAO genreDAO = factory.getGenreDAO();
        return genreDAO.findAll();
    }

    public String convertWithIteration(Map<String, String[]> map) {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : map.keySet()) {
            mapAsString.append(key + "=" + Arrays.toString(map.get(key)) + ", ");
        }
        mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }

    private void logged(HttpServletRequest request) {
        final Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder enumeration = new StringBuilder();
        while (parameterNames.hasMoreElements())
            enumeration.append(parameterNames.nextElement()).append(", ");
        logger.debug("parameterNames: " + enumeration.toString());


        final Map<String, String[]> parameterMap = request.getParameterMap();
        logger.debug("parameterMap: " + convertWithIteration(parameterMap));
    }
}
