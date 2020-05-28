package ruslan.kovshar.mmdb.dto;

import ruslan.kovshar.mmdb.model.MovieWatchList;

public class GetMovieWatchListDto {
    private Long id;
    private String title;
    private boolean isWatched;

    public static GetMovieWatchListDto fromMovieWatchList(MovieWatchList movieWatchList) {
        return new GetMovieWatchListDto(
                movieWatchList.getId(),
                movieWatchList.getTitle(),
                movieWatchList.isWatched()
        );
    }

    public GetMovieWatchListDto() {
    }

    public GetMovieWatchListDto(Long id, String title, boolean isWatched) {
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
