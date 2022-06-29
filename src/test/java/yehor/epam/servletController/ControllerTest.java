package yehor.epam.servletController;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import yehor.epam.actions.CommandFactory;

import java.io.IOException;

import static org.mockito.Mockito.*;

class ControllerTest {
    private final CommandFactory factory = spy(CommandFactory.class);
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private Controller controller = spy(Controller.class);

    @Test
    void doGet() throws ServletException, IOException {
        controller.doGet(request, response);
        verify(request).getRequestDispatcher(anyString());
    }

    @Test
    void doPost() {
        controller.doPost(request, response);
        verify(request).getRequestDispatcher(anyString());
    }
}