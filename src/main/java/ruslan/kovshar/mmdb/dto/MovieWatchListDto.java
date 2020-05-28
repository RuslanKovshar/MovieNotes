package ruslan.kovshar.mmdb.dto;

import ruslan.kovshar.mmdb.model.MovieWatchList;

import javax.validation.constraints.NotNull;

public class MovieWatchListDto {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private boolean isWatched;

    public static MovieWatchListDto fromMovieWatchList(MovieWatchList movieWatchList) {
        return new MovieWatchListDto(
                movieWatchList.getId(),
                movieWatchList.getTitle(),
                movieWatchList.isWatched()
        );
    }

    public MovieWatchListDto() {
    }

    public MovieWatchListDto(Long id, String title, boolean isWatched) {
        this.id = id;
        this.title = title;
        this.isWatched = isWatched;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWatched() {
        return isWatched;
    }

    public void setWatched(boolean watched) {
        isWatched = watched;
    }
}
