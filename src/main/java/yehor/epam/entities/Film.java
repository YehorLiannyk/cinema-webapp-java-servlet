package yehor.epam.entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Film extends BaseEntity {
    Duration duration;
    List<Genre> genreList;
    private String name;
    private String description;
    private String posterUrl;

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
}
