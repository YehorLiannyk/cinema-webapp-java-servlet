package yehor.epam.services;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import yehor.epam.dao.factories.DAOFactory;
import yehor.epam.utilities.LoggerManager;

public class ScheduleService {
    private static final Logger logger = LoggerManager.getLogger(ScheduleService.class);

    /*
    parameterNames: command, isAvailable, filmIds, minTicketPrice, maxTicketPrice,
    parameterMap: {command=[schedule], isAvailable=[], filmIds=[film10, film6, film3], minTicketPrice=[1], maxTicketPrice=[123]}
    //
    parameterNames: command, sortBy, sortMethod,
    parameterMap: {command=[schedule], sortBy=[filmNameSortBy], sortMethod=[descendingSortMethod]}
    */

    public void CheckRequest(HttpServletRequest request, DAOFactory factory) {
        final String filtered = request.getParameter("filtered");
        if (filtered != null && filtered.equals("true")) {

        } else {

        }
    }
}
