package yehor.epam.services;


import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import yehor.epam.utilities.LoggerManager;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Properties;

public class MailService {
    private static final Logger logger = LoggerManager.getLogger(MailService.class);
    private static final String CLASS_NAME = MailService.class.getName();


    public void sendEmail(String host, String port,
                          final String userName, final String password, String toAddress,
                          String subject, String message) throws AddressException,
            MessagingException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        //properties.put("mail.smtp.port", 465);
        //properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.starttls.enable", "true");

        logger.debug("Set props in " + CLASS_NAME);

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        logger.debug("Auth is completed");

        Session session = Session.getInstance(properties, auth);

        logger.debug("Session is got");


        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        logger.debug("Message is created");

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        ZonedDateTime zonedDateTime = getZonedDateTime();
        msg.setSentDate(Date.from(zonedDateTime.toInstant()));
        msg.setText(message);

        logger.debug("Message is set");


        // sends the e-mail
        Transport.send(msg);
        logger.debug("Transport.send(msg);");

    }

    private ZonedDateTime getZonedDateTime() {
        ZoneId systemTimeZone = ZoneId.systemDefault();
        final LocalDate currentLocalDate = LocalDate.now();
        return currentLocalDate.atStartOfDay(systemTimeZone);
    }

}
