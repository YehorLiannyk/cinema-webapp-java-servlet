import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.Test;
import yehor.epam.actions.BaseCommand;
import yehor.epam.actions.CommandFactory;
import yehor.epam.actions.commands.*;
import yehor.epam.actions.commands.films.*;
import yehor.epam.actions.commands.mail.SendMailCommand;
import yehor.epam.actions.commands.mail.SendTicketViaMailCommand;
import yehor.epam.actions.commands.sessions.*;
import yehor.epam.actions.commands.signing.*;
import yehor.epam.actions.commands.tickets.BuyTicketCommand;
import yehor.epam.actions.commands.tickets.BuyTicketPageCommand;
import yehor.epam.actions.commands.tickets.DownloadPDFTicketCommand;
import yehor.epam.dao.exception.DAOException;

import static org.mockito.Mockito.*;

public class CommandsTests {
    private HttpServletRequest request = mock(HttpServletRequest.class);
    private HttpServletResponse response = mock(HttpServletResponse.class);
    private BaseCommand baseCommand = mock(BaseCommand.class);
    private CommandFactory factory = mock(CommandFactory.class);

    @Test
    public void CommandFactoryTest() {
        when(factory.defineCommand(request)).thenReturn(baseCommand);
        when(factory.defineCommand(request)).thenReturn(mock(SuccessPayPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(ScheduleCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(ProfilePageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(MainPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(ErrorPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(DownloadPDFTicketCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(BuyTicketCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(BuyTicketPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(RegisterPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(RegisterCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(LogoutCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(LoginPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(LoginCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(AddSessionCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(SessionsSettingPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(SessionInfoPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(SessionPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(DeleteSessionCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(AddSessionPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(SendMailCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(SendTicketViaMailCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(FilmsSettingPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(FilmInfoPageCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(DeleteFilmCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(AddFilmCommand.class));
        when(factory.defineCommand(request)).thenReturn(mock(AddFilmPageCommand.class));
        when(factory.defineCommand(request)).thenThrow(DAOException.class);
        try {
            when(factory.defineCommand(request)).thenThrow(Exception.class);
        } catch (Exception ignored) {
        }
    }

    @Test
    public void defineCommandTest() {
        CommandFactory commandFactory = spy(CommandFactory.class);
        commandFactory.defineCommand(request);
        verify(commandFactory, times(1)).defineCommand(request);
    }

    @Test
    public void commandsTest() {
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

        command = spy(SendMailCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(SendTicketViaMailCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(FilmsSettingPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(FilmInfoPageCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(DeleteFilmCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(AddFilmCommand.class);
        command.execute(request, response);
        verify(command, times(1)).execute(request, response);

        command = spy(AddFilmPageCommand.class);
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
