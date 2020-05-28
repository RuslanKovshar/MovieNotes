package ruslan.kovshar.mmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.kovshar.mmdb.dto.CreateMovieWatchListDto;
import ruslan.kovshar.mmdb.dto.MovieWatchListDto;
import ruslan.kovshar.mmdb.model.MovieWatchList;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.repository.MovieWatchListRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public boolean delete(User user, long id) {
        Optional<MovieWatchList> watchListOptional = checkForMovieWatchList(user, id);

        if (watchListOptional.isPresent()) {
            MovieWatchList movieWatchList = watchListOptional.get();
            movieWatchListRepository.delete(movieWatchList);
            return true;
        }
        return false;
    }

    private Optional<MovieWatchList> checkForMovieWatchList(User user, long id) {
        return user.getMovieWatchLists()
                .stream()
                .filter(movieWatchList -> Objects.equals(movieWatchList.getId(), id))
                .findAny();
    }

    public Optional<MovieWatchListDto> get(User user, long id) {
        Optional<MovieWatchList> watchListOptional = checkForMovieWatchList(user, id);

        Optional<MovieWatchListDto> optional = Optional.empty();

        if (watchListOptional.isPresent()) {
            MovieWatchList movieWatchList = watchListOptional.get();
            MovieWatchListDto movieWatchListDto = MovieWatchListDto.fromMovieWatchList(movieWatchList);
            optional = Optional.of(movieWatchListDto);
        }

        return optional;
    }

    public List<MovieWatchListDto> getAll(User user) {
        return user.getMovieWatchLists()
                .stream()
                .map(MovieWatchListDto::fromMovieWatchList)
                .collect(Collectors.toList());
    }
}
