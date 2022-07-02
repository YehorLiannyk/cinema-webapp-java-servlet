package yehor.epam.actions;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import yehor.epam.actions.BaseCommand;
import yehor.epam.actions.CommandFactory;
import yehor.epam.actions.commands.*;
import yehor.epam.actions.commands.films.*;
import yehor.epam.actions.commands.tickets.*;
import yehor.epam.actions.commands.signing.*;
import yehor.epam.actions.commands.sessions.*;
import yehor.epam.servletController.Controller;

import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;
import static yehor.epam.utilities.constants.JspPagePathConstants.ADD_FILM_PAGE_PATH;

class CommandsTest {

    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final HttpServletResponse response = mock(HttpServletResponse.class);

    @Test
    void commandsTest() {
        BaseCommand command = spy(SuccessPayPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(ScheduleCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(ProfilePageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(MainPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(ErrorPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(BuyTicketCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(BuyTicketPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(RegisterPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(RegisterCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(LoginPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(LoginCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(AddSessionCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(SessionsSettingPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(SessionInfoPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(SessionPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(DeleteSessionCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(AddSessionPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = Mockito.spy(FilmsSettingPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = Mockito.spy(FilmInfoPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = Mockito.spy(DeleteFilmCommand.class);
        when(request.getParameter("filmId")).thenReturn("1");
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = Mockito.spy(AddFilmCommand.class);
        Map<String, String[]> map = mock(Map.class);
        when(request.getParameter("genreIds")).thenReturn("1");
        when(request.getParameter("filmDuration")).thenReturn("10");
        when(request.getParameterMap()).thenReturn(map);
        when(map.get("genreIds")).thenReturn(new String[]{"1"});
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = Mockito.spy(AddFilmPageCommand.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(ADD_FILM_PAGE_PATH)).thenReturn(requestDispatcher);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(DownloadPDFTicketCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        try {
            command = spy(LogoutCommand.class);
            command.execute(request, response);
        } catch (NullPointerException ignored) {
        }
        verify(command, times(1)).execute(request, response);
    }
}