package yehor.epam.actions.commands;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.dao.factories.MySQLFactory;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

public class BuyTicketCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(BuyTicketCommand.class);
    private String className = BuyTicketCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try (DAOFactory factory = new MySQLFactory()) {
            logger.debug("Created DAOFactory in " + className + " execute command");


/*            final FilmDAO filmDAO = factory.getFilmDAO();
            final List<Film> filmList = filmDAO.findAll();
            request.getSession().setAttribute("filmList", filmList);
            request.getRequestDispatcher(MAIN_PAGE_PATH).forward(request, response);*/
        } catch (Exception e) {
            ErrorService.handleException(request, response, className, e);
        }
    }
}
