package yehor.epam.services.impl;


import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import yehor.epam.utilities.LoggerManager;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Class service of Email sender (not working)
 */
/*public class MailService {
   // private static final Logger logger = LoggerManager.getLogger(MailService.class);
   // private static final String CLASS_NAME = MailService.class.getName();

    // як я не намагався, але так і не зміг реалізувати відправлення емейлом квитка користувачеві.
    // змінював порти (465/587/25), пробував ставити Property як для tsl, так і для ssl
    // використовував Gmail, в акаунті прибрав весь можливий захист.
    // далі пробував localhost - все одно.
    // Томкет теж дивився як налаштовувати, але не допомогло
    // Брандмауер також вимкнув :)

    *//*public void sendEmail(String host, String port, final String username, final String password, String toAddress, String subject, String msg) throws MessagingException {

        Session session = null;
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            session = (Session) envCtx.lookup("mail/NomDeLaRessource");
        } catch (Exception e) {
            logger.error("Couldn't gen context or session", e);
        }

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(username));
            InternetAddress[] to = new InternetAddress[1];
            to[0] = new InternetAddress(toAddress);
            message.setRecipients(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setContent(msg, "text/html;charset=UTF-8");

            Thread t = Thread.currentThread();
            ClassLoader ccl = t.getContextClassLoader();
            t.setContextClassLoader(session.getClass().getClassLoader());
            try {
                Transport.send(message);
            } finally {
                t.setContextClassLoader(ccl);
            }
            logger.info("The e-mail was sent successfully");
        } catch (MessagingException e) {
            logger.error("Couldn't send email", e);
        }*//*

        *//*Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host); //smtp.gmail.com
        props.put("mail.smtp.port", port); //587

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toAddress));
        message.setSubject(subject);
        message.setText(msg);

        Transport.send(message);

        logger.debug("Successfully sent email");*//*


        /*//*/ sets SMTP server properties
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        //prop.put("mail.smtp.host", "gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");


        *//**//*prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");*//**//*

        logger.debug("Set props in " + CLASS_NAME);

        // creates a new session with an authenticator
        final Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(prop, authenticator);

        logger.debug("Auth and Session are completed");
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(toAddress)
        );
        message.setSubject("Testing Gmail TLS");
        message.setText("Dear Mail Crawler,"
                + "\n\n Please do not spam my email!");
        logger.debug("Message is set");

        Transport.send(message, username, password);

        logger.debug("Transport.send(msg);");*//*

        *//*
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        Session session = (Session) envCtx.lookup("mail/Session");

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        InternetAddress[] to = new InternetAddress[1];
        to[0] = new InternetAddress(toAddress);
        message.setRecipients(Message.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(mssg, "text/plain");
        Transport.send(message);
        logger.debug("FINE");

    }*//*

}*/
