package yehor.epam.actions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface BaseCommand {
    /**
     * Execute command due to received instructions from HttpServletRequest and defined by CommandFactory
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
