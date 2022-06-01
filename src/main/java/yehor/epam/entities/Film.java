package yehor.epam.entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Film entity
 */
public class Film extends BaseEntity {
    /**
     * Film's duration
     */
    Duration duration;
    /**
     * Film's genres
     */
    List<Genre> genreList;
    /**
     * Film's name
     */
    private String name;
    /**
     * Film's description (non required)
     */
    private String description;
    /**
     * URL of Film's poster
     */
    private String posterUrl;

    public Film() {
    }

    public Film(int id, String name, String description, String posterUrl, Duration duration, List<Genre> genreList) {
        super(id);
        this.name = name;
        this.description = description;
        this.posterUrl = posterUrl;
        this.duration = duration;
        this.genreList = genreList;
    }

    public Film(int id, String name, String description, String posterUrl, Duration duration) {
        this(id, name, description, posterUrl, duration, new ArrayList<>());
    }

    public Film(String name, String description, String posterUrl, Duration duration) {
        this.name = name;
        this.description = description;
        this.posterUrl = posterUrl;
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public long getDurationInMinutes() {
        return duration.toMinutes();
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return "Film{" +
                "duration=" + duration +
                ", genreList=" + genreList +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
