import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.servlet.http.HttpServletResponse;
import yehor.epam.entities.User;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TryTest {
    private final static String path = "/WEB-INF/view/index.jsp";

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        // you can mock concrete classes, not only interfaces
        LinkedList linkedList = mock(LinkedList.class);
        // stubbing appears before the actual execution
        when(linkedList.get(0)).thenReturn("irst");
        // the following prints "first"
        System.out.println(linkedList.get(0));
        // the following prints "null" because get(999) was not stubbed
        System.out.println(linkedList.get(999));

        // mock creation
        List mockedList = mock(List.class);
        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        //mockedList.clear();
        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        //verify(mockedList).clear();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        User user = mock(User.class);

        //when(request.getAttribute("user")).thenReturn(user);
        request.setAttribute("user", user);
        verify(request).getAttribute("user");
    }
}
