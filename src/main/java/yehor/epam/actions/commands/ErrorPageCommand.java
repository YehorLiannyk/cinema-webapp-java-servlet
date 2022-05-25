package yehor.epam.actions.commands;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.services.ErrorService;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.JspPagePathConstants.ERROR_PAGE_PATH;

public class ErrorPageCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(ErrorPageCommand.class);
    private String className = ErrorPageCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        try {
            logger.debug("Forward to error page");
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        } catch (Exception e) {
            logger.error(className + "got exception: ", e);
            //ErrorService.handleException(request, response, className, e);
        }
    }
}
