package ruslan.kovshar.mmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.kovshar.mmdb.dto.CreateMovieWatchListDto;
import ruslan.kovshar.mmdb.model.MovieWatchList;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.repository.MovieWatchListRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class MovieWatchListService {
    private final MovieWatchListRepository movieWatchListRepository;

    @Autowired
    public MovieWatchListService(MovieWatchListRepository movieWatchListRepository) {
        this.movieWatchListRepository = movieWatchListRepository;
    }

    public MovieWatchList add(CreateMovieWatchListDto createMovieWatchListDto, User user) {
        MovieWatchList movieWatchList = createMovieWatchListDto.toMovieWatchList();
        movieWatchList.setUser(user);

        return movieWatchListRepository.save(movieWatchList);
    }

    public boolean delete(User user, Long id) {
        Optional<MovieWatchList> watchListOptional = user.getMovieWatchLists()
                .stream()
                .filter(movieWatchList -> Objects.equals(movieWatchList.getId(), id))
                .findAny();

        if (watchListOptional.isPresent()) {
            MovieWatchList movieWatchList = watchListOptional.get();
            movieWatchListRepository.delete(movieWatchList);
            return true;
        }
        return false;
    }
}
