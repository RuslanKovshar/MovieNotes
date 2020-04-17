package ruslan.kovshar.mmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ruslan.kovshar.mmdb.model.MovieNote;

import java.time.LocalDate;

public class GetMovieNoteDto {
    private Long id;

    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;
    private String title;

    public static GetMovieNoteDto fromMovieDto(MovieNote movieNote) {
        return new GetMovieNoteDto(
                movieNote.getId(),
                movieNote.getReleaseDate(),
                movieNote.getTitle()
        );
    }

    public GetMovieNoteDto(Long id, LocalDate releaseDate, String title) {
        this.id = id;
        this.releaseDate = releaseDate;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
