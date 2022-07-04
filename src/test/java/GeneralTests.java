import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.junit.Test;
import yehor.epam.exceptions.ConnectionException;
import yehor.epam.connection.ConnectionPool;
import yehor.epam.filters.EncodingFilter;
import yehor.epam.filters.LocaleFilter;
import yehor.epam.filters.SecurityFilter;
import yehor.epam.listeners.UserRoleListener;

import java.io.IOException;
import java.sql.Connection;

import static org.mockito.Mockito.*;

public class GeneralTests {
    @Test
    public void connectionPoolTest() throws ConnectionException {
        ConnectionPool pool = mock(ConnectionPool.class);
        when(pool.getConnection()).thenReturn(mock(Connection.class));
        when(pool.getConnection()).thenThrow(ConnectionException.class);
    }

    @Test
    public void filtersTest() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        final FilterConfig filterConfig = mock(FilterConfig.class);

        SecurityFilter securityFilter = mock(SecurityFilter.class);
        securityFilter.doFilter(request, response, filterChain);
        verify(securityFilter, times(1)).doFilter(request, response, filterChain);

        doNothing().when(securityFilter).doFilter(request, response, filterChain);
        doNothing().when(securityFilter).destroy();
        doNothing().when(securityFilter).init(filterConfig);

        EncodingFilter encodingFilter = mock(EncodingFilter.class);
        encodingFilter.doFilter(request, response, filterChain);
        verify(encodingFilter, times(1)).doFilter(request, response, filterChain);

        LocaleFilter localeFilter = mock(LocaleFilter.class);
        localeFilter.doFilter(request, response, filterChain);
        verify(localeFilter, times(1)).doFilter(request, response, filterChain);
    }

    @Test
    public void listenerTest() {
        UserRoleListener listener = mock(UserRoleListener.class);
        final HttpSessionBindingEvent event = mock(HttpSessionBindingEvent.class);
        listener.attributeAdded(event);
        verify(listener, times(1)).attributeAdded(event);
    }

}
