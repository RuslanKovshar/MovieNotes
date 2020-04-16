package ruslan.kovshar.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ruslan.kovshar.mmdb.model.MovieNote;

import java.time.LocalDate;

public class CreateMovieNoteDto {

    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;
    private String title;

    public MovieNote toMovieNote() {
        return new MovieNote(releaseDate, title);
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "CreateMovieNoteDto{" +
                "releaseDate=" + releaseDate +
                ", title='" + title + '\'' +
                '}';
    }
}
