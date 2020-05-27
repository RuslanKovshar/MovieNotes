package ruslan.kovshar.mmdb.dto;

import ruslan.kovshar.mmdb.model.MovieWatchList;

public class CreateMovieWatchListDto {

    private String title;
    private boolean isWatched;

    public MovieWatchList toMovieWatchList() {
        return new MovieWatchList(title, isWatched);
    }

    public CreateMovieWatchListDto() {
    }

    public CreateMovieWatchListDto(String title, boolean isWatched) {
        this.title = title;
        this.isWatched = isWatched;
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

    @Override
    public String toString() {
        return "CreateMovieWatchListDto{" +
                "title='" + title + '\'' +
                ", isWatched=" + isWatched +
                '}';
    }
}
