package yehor.epam.services.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.jsp.jstl.core.Config;
import org.slf4j.Logger;
import yehor.epam.entities.Film;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.exceptions.PdfException;
import yehor.epam.exceptions.WritePdfToResponseException;
import yehor.epam.services.TicketPdfService;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.constants.OtherConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static yehor.epam.utilities.constants.OtherConstants.FONTS_BAHNSCHRIFT_TTF_PATH;

/**
 * Class service for ticket in particular for PDF formation purpose
 */
public class TicketPdfServiceImpl implements TicketPdfService {
    private static final Logger logger = LoggerManager.getLogger(TicketPdfServiceImpl.class);
    private final Font headFont;

    /**
     * Create TicketService object and set main Font for pdf file
     */
    public TicketPdfServiceImpl() {
        BaseFont unicode = null;
        try {
            unicode = BaseFont.createFont(FONTS_BAHNSCHRIFT_TTF_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            logger.error("Couldn't set front for PDF", e);
            throw new PdfException("Couldn't set front for PDF", e);
        }
        headFont = new Font(unicode, 12);
    }

    @Override
    public ByteArrayOutputStream formPDFTicket(Ticket ticket, Locale locale) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setTotalWidth(PageSize.A4.getWidth() - 25);


            final Session session = ticket.getSession();
            final Film film = session.getFilm();
            final Seat seat = ticket.getSeat();
            final String ticketPriceInFormat = getTicketPriceInFormat(ticket);

            logger.debug("Locale in PDF service. Lang = {}, country = {}", locale.getLanguage(), locale.getCountry());
            final ResourceBundle bundle = ResourceBundle.getBundle("i18n", locale);

            addRowToTable(bundle.getString("ticket.pdf.number") + ": ", table, String.valueOf(ticket.getId()));
            addRowToTable(bundle.getString("ticket.pdf.film") + ": ", table, film.getName());
            addRowToTable(bundle.getString("ticket.pdf.date") + ": ", table, session.getDate().toString());
            addRowToTable(bundle.getString("ticket.pdf.time") + ": ", table, session.getTime().toString());
            addRowToTable(bundle.getString("ticket.pdf.rowNo") + ": ", table, String.valueOf(seat.getRowNumber()));
            addRowToTable(bundle.getString("ticket.pdf.placeNo") + ": ", table, String.valueOf(seat.getPlaceNumber()));
            addRowToTable(bundle.getString("ticket.pdf.ticketPrice") + ": ", table, ticketPriceInFormat);

            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(table);
            document.close();
        } catch (DocumentException e) {
            logger.error("Couldn't create pdf ticket", e);
            throw new PdfException("Couldn't create pdf ticket", e);
        }

        return outputStream;
    }

    @Override
    public void writePdfToResponse(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response) {
        try {
            writeByteArrayOutputStreamToResponse(byteArrayOutputStream, response);
        } catch (IOException e) {
            logger.error("Couldn't write pdf ticket (byteArrayOutputStream) to HttpServletResponse", e);
            throw new WritePdfToResponseException("Couldn't write pdf ticket to HttpServletResponse", e);
        }
    }

    private void writeByteArrayOutputStreamToResponse(ByteArrayOutputStream byteArrayOutputStream, HttpServletResponse response)
            throws IOException {
        try {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=" + OtherConstants.DEF_TICKET_FILENAME + ".pdf");
            ServletOutputStream servletOutputStream = response.getOutputStream();
            byteArrayOutputStream.writeTo(servletOutputStream);
        } catch (Exception e) {
            logger.error("Handled error when trying to write PDF to output", e);
            throw new PdfException("Handled error when trying to write PDF to output", e);
        } finally {
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
            }
        }
    }

    /**
     * Add appropriate row to PDF table
     *
     * @param string text
     * @param table  the PDF table
     * @param ticket Ticket
     */
    private void addRowToTable(String string, PdfPTable table, String ticket) {
        PdfPCell lCell;
        PdfPCell rCell;
        lCell = new PdfPCell(new Phrase(string, headFont));
        setLeftCell(lCell);
        table.addCell(lCell);

        rCell = new PdfPCell(new Phrase(ticket, headFont));
        setRightCell(rCell);
        table.addCell(rCell);
    }

    private void setLeftCell(PdfPCell lCell) {
        lCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    }

    private void setRightCell(PdfPCell cell) {
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
        cell.setPaddingRight(5);
    }

    /**
     * get ticket price from BigDecimal in two digits after comma format
     *
     * @param ticket ticket
     * @return formatted value in String
     */
    private String getTicketPriceInFormat(Ticket ticket) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        return df.format(ticket.getTicketPrice());
    }
}
