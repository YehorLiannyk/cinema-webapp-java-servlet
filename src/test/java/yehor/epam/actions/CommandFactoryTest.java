package yehor.epam.actions;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import yehor.epam.actions.commands.MainPageCommand;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.when;
import static yehor.epam.utilities.constants.CommandConstants.COMMAND_VIEW_MAIN_PAGE;

class CommandFactoryTest {
    private final HttpServletRequest request = mock(HttpServletRequest.class);
    private final CommandFactory factory = spy(CommandFactory.class);

    @Test
    void defineCommand() {
        final String command = "command";
        Assertions.assertInstanceOf(BaseCommand.class, factory.defineCommand(request));

        when(request.getParameter(command)).thenReturn(null);
        Assertions.assertInstanceOf(MainPageCommand.class, factory.defineCommand(request));
        when(request.getParameter(command)).thenReturn("");
        Assertions.assertInstanceOf(MainPageCommand.class, factory.defineCommand(request));
        when(request.getParameter(command)).thenReturn(COMMAND_VIEW_MAIN_PAGE);
        Assertions.assertInstanceOf(MainPageCommand.class, factory.defineCommand(request));
    }
}