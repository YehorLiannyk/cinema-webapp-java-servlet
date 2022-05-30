package yehor.epam.tags;

import jakarta.servlet.jsp.JspTagException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import yehor.epam.entities.Film;
import yehor.epam.entities.Genre;

import java.io.IOException;
import java.util.List;

/**
 * Class of implementing film's genreList tag, which print Film's Genres and commas
 */
public class FilmGenresTag extends TagSupport {
    private Film film;

    public void setFilm(Film film) {
        this.film = film;
    }

    @Override
    public int doStartTag() throws JspTagException {
        try {
            JspWriter out = pageContext.getOut();
            final List<Genre> genreList = film.getGenreList();
            final int size = genreList.size();
            for (int i = 0; i < size; i++) {
                final Genre genre = genreList.get(i);
                if (i < size - 1) {
                    out.write(genre.getName() + ", ");
                } else {
                    out.write(genre.getName());
                }
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }
}
