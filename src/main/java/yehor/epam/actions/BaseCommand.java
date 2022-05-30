package yehor.epam.actions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface of all servlet's commands
 */
public interface BaseCommand {
    /**
     * Execute command due to received instructions from HttpServletRequest and defined by CommandFactory
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    void execute(HttpServletRequest request, HttpServletResponse response);
}
