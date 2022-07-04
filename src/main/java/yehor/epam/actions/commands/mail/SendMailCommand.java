package yehor.epam.actions.commands.mail;

/**
 * Send mail command
 */
/*public class SendMailCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SendMailCommand.class);
    private static final String CLASS_NAME = SendMailCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.info("Entry to " + CLASS_NAME + " execute command");

            ServletContext context = request.getServletContext();
            String host = context.getInitParameter("host");
            String port = context.getInitParameter("port");
            String user = context.getInitParameter("user");
            String pass = context.getInitParameter("pass");
            logger.debug("Info server, host:" + host + "port: " + port + " user: " + user + " pass: " + pass);

            // reads form fields
            String recipient = ((User) request.getAttribute("user")).getEmail();
            String subject = (request.getAttribute("mailSubject")).toString();
            String content = ((Ticket) request.getAttribute("ticket")).toString();
            logger.debug("Info receiver, recipient:" + recipient + " subject: " + subject + " content: " + content);

            sendEmail(host, port, user, pass, recipient, subject, content);

            response.sendRedirect(RedirectManager.getRedirectLocation(COMMAND_VIEW_PROFILE_PAGE));
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }

    *//**
     * send mail command
     *
     * @param host      host
     * @param port      port
     * @param user      sender
     * @param pass      sender mail password
     * @param recipient recipient
     * @param subject   mail subject
     * @param content   mail content
     * @throws Exception
     *//*
    private void sendEmail(String host, String port, String user, String pass, String recipient, String subject, String content) throws Exception {
        try {
            MailService mailService = new MailService();
            mailService.sendEmail(host, port, user, pass, recipient, subject, content);
        } catch (Exception e) {
            logger.error("Couldn't send mail", e);
            throw new Exception("Couldn't send mail", e);
        }
    }
}*/
