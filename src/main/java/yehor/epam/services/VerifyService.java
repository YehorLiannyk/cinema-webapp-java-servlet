package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface VerifyService {
    /**
     * Validate Google Recaptcha
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    void captchaValidation(HttpServletRequest request, HttpServletResponse response);
}
