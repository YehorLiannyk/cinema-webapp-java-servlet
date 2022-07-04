package yehor.epam.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import yehor.epam.exceptions.VerifyException;
import yehor.epam.services.VerifyService;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

class VerifyServiceImplTest {
    private VerifyService verifyService = spy(VerifyServiceImpl.class);

    @Test
    void captchaValidationThrowVerifyException() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("g-recaptcha-response")).thenReturn(null);
        verifyService.captchaValidation(request, response);
        verify(response, only()).isCommitted();
    }

    @Test
    void captchaValidation() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("g-recaptcha-response")).thenReturn("response");
        verifyService.captchaValidation(request, response);
        verify(verifyService).captchaValidation(request, response);
    }

}