package yehor.epam.servletController;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import yehor.epam.actions.BaseCommand;
import yehor.epam.actions.CommandFactory;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.CommandConstants.COMMAND_MAIN_SERVLET;

/**
 * Main Servlet
 */
@WebServlet(name = "controller", value = "/" + COMMAND_MAIN_SERVLET + "")
public class Controller extends HttpServlet {
    private static final Logger logger = LoggerManager.getLogger(Controller.class);

    @Override
    public void init() {
        logger.info("Main servlet was initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response);
    }

    /**
     * Method receiving all GET and POST requests
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        CommandFactory factory = new CommandFactory();
        BaseCommand command = factory.defineCommand(request);
        command.execute(request, response);
    }
}