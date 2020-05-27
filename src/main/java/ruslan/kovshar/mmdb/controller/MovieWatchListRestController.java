package ruslan.kovshar.mmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.kovshar.mmdb.dto.CreateMovieWatchListDto;
import ruslan.kovshar.mmdb.model.MovieWatchList;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.service.MovieWatchListService;
import ruslan.kovshar.mmdb.service.utils.UserExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

}
