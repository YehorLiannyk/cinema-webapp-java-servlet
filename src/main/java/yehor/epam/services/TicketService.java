package yehor.epam.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.log4j.Logger;
import yehor.epam.entities.Film;
import yehor.epam.entities.Seat;
import yehor.epam.entities.Session;
import yehor.epam.entities.Ticket;
import yehor.epam.utilities.LoggerManager;
import yehor.epam.utilities.OtherConstants;
import yehor.epam.utilities.exception.PDFException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import static yehor.epam.utilities.OtherConstants.DEFAULT_CURRENCY;
import static yehor.epam.utilities.OtherConstants.FONTS_BAHNSCHRIFT_TTF_PATH;


public class TicketService {
    private static final Logger logger = LoggerManager.getLogger(TicketService.class);
    private final Font headFont;

    public TicketService() {
        BaseFont unicode = null;
        try {
            unicode = BaseFont.createFont(FONTS_BAHNSCHRIFT_TTF_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            logger.error("Couldn't set front for PDF", e);
            throw new PDFException("Couldn't set front for PDF", e);
        }
        headFont = new Font(unicode, 12);
    }

    public ByteArrayOutputStream formPDFTicket(Ticket ticket) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setTotalWidth(PageSize.A4.getWidth() - 25);


            final Session session = ticket.getSession();
            final Film film = session.getFilm();
            final Seat seat = ticket.getSeat();

            PdfPCell lCell = null;
            PdfPCell rCell = null;

            addRowToTable("Ticket id: ", table, String.valueOf(ticket.getId()));
            //
            addRowToTable("Film name: ", table, film.getName());
            //
            addRowToTable("Date: ", table, session.getDate().toString());
            //
            addRowToTable("Time: ", table, session.getTime().toString());
            //
            addRowToTable("Row: ", table, String.valueOf(seat.getRowNumber()));
            //
            addRowToTable("Place: ", table, String.valueOf(seat.getPlaceNumber()));

            lCell = new PdfPCell(new Phrase("Ticket price: ", headFont));
            setLeftCell(lCell);
            table.addCell(lCell);

            final String ticketPriceInFormat = getTicketPriceInFormat(ticket);

            rCell = new PdfPCell(new Phrase(ticketPriceInFormat + " " + DEFAULT_CURRENCY));
            setRightCell(rCell);
            table.addCell(rCell);
            //

            //fillTable(table, ticket);

            PdfWriter.getInstance(document, outputStream);
            document.open();
            document.add(table);

            document.close();

        } catch (DocumentException e) {
            logger.error("Exception in " + TicketService.class.getName(), e);
        }

        return outputStream;
    }

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

    private String getTicketPriceInFormat(Ticket ticket) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        return df.format(ticket.getTicketPrice());
    }

}
