package ruslan.kovshar.mmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ruslan.kovshar.mmdb.dto.CreateMovieWatchListDto;
import ruslan.kovshar.mmdb.dto.MovieWatchListDto;
import ruslan.kovshar.mmdb.model.MovieWatchList;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.service.MovieWatchListService;
import ruslan.kovshar.mmdb.service.utils.UserExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/movie_watchlist")
public class MovieWatchListRestController {

    private final MovieWatchListService movieWatchListService;
    private final UserExtractor userExtractor;

    @Autowired
    public MovieWatchListRestController(MovieWatchListService movieWatchListService,
                                        UserExtractor userExtractor) {
        this.movieWatchListService = movieWatchListService;
        this.userExtractor = userExtractor;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovieWatchList(@RequestBody CreateMovieWatchListDto createMovieWatchListDto,
                                               HttpServletRequest request) {
        User user = userExtractor.extract(request);
        MovieWatchList movieWatchList = movieWatchListService.add(createMovieWatchListDto, user);

        Map<String, Long> response = new HashMap<>();
        response.put("id", movieWatchList.getId());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllWatchLists(HttpServletRequest request) {
        User user = userExtractor.extract(request);

        List<MovieWatchListDto> watchListDtoList = movieWatchListService.getAll(user);
        return ResponseEntity.ok().body(watchListDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWatchList(@PathVariable long id, HttpServletRequest request) {
        User user = userExtractor.extract(request);
        Optional<MovieWatchListDto> listDtoOptional = movieWatchListService.get(user, id);
        if (listDtoOptional.isPresent()) {
            MovieWatchListDto movieWatchListDto = listDtoOptional.get();
            return ResponseEntity.ok(movieWatchListDto);
        } else {
            return ResponseEntity.badRequest().body("Invalid id: " + id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovieWatchList(@PathVariable long id,
                                                       HttpServletRequest request) {
        User user = userExtractor.extract(request);
        boolean isDeleted = movieWatchListService.delete(user, id);
        if (isDeleted) {
            return ResponseEntity.ok().body("Entity with id " + id + " successfully deleted");
        } else {
            return ResponseEntity.badRequest().body("Invalid id: " + id);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMovieWatchList(@RequestBody @Validated MovieWatchListDto movieWatchListDto,
                                                  HttpServletRequest request) {
        User user = userExtractor.extract(request);

        boolean isUpdated = movieWatchListService.update(movieWatchListDto, user);

        long id = movieWatchListDto.getId();
        if (isUpdated) {
            return ResponseEntity.ok().body("Entity with id " + id + " successfully updated");
        } else {
            return ResponseEntity.badRequest().body("Invalid id: " + id);
        }
    }

}
