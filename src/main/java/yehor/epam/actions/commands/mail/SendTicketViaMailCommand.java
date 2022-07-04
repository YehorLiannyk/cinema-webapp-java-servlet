package yehor.epam.actions.commands.mail;

/**
 * Send ticket via email command
 */
/*public class SendTicketViaMailCommand implements BaseCommand {
    private static final Logger logger = LoggerManager.getLogger(SendTicketViaMailCommand.class);
    private static final String CLASS_NAME = SendTicketViaMailCommand.class.getName();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (DAOFactory factory = new MySQLFactory()) {
            logger.info("Entry to " + CLASS_NAME + " execute command");

            int ticketId = Integer.parseInt(request.getParameter("ticketId"));
            final Ticket ticket = factory.getTicketDao().findById(ticketId);

            final int userId = ticket.getUser().getId();
            logger.debug("userId: " + userId);
            final User user = factory.getUserDao().findById(userId);

            request.setAttribute("ticket", ticket);
            request.setAttribute("mailSubject", "Your ticket");
            request.setAttribute("user", user);

            new SendMailCommand().execute(request, response);
        } catch (Exception e) {
            ErrorService.handleException(request, response, CLASS_NAME, e);
        }
    }
}*/
