package yehor.epam.servletController;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import yehor.epam.actions.ActionCommand;
import yehor.epam.actions.ActionFactory;
import yehor.epam.connection.ConnectionPool;
import yehor.epam.services.CookieService;
import yehor.epam.utilities.LoggerManager;

import java.io.IOException;

@WebServlet(name = "controller", value = "/main")
public class Controller extends HttpServlet {
    private static final Logger logger = LoggerManager.getLogger(Controller.class);

    @Override
    public void init() {
        logger.info("Main servlet was initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession() == null) {
            CookieService cookieService = new CookieService();
            cookieService.initCookies(request);
        }
        ActionFactory factory = new ActionFactory();
        ActionCommand command = factory.defineCommand(request);
        command.execute(request, response);
    }

}