package yehor.epam.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import org.slf4j.Logger;
import yehor.epam.utilities.LoggerManager;

import static yehor.epam.utilities.constants.OtherConstants.USER_ROLE;

/**
 * Listener that log changing users' roles
 */
@WebListener
public class UserRoleListener implements HttpSessionAttributeListener {
    private static final Logger logger = LoggerManager.getLogger(UserRoleListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals(USER_ROLE)) {
            logger.info("Some user has received role: " + event.getValue().toString());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(USER_ROLE)) {
            logger.info("Some user has lost role: " + event.getValue().toString());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals(USER_ROLE)) {
            logger.info("Some user has changed role to " + event.getValue().toString());
        }
    }
}
