package ruslan.kovshar.mmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.kovshar.mmdb.dto.CreateMovieNoteDto;
import ruslan.kovshar.mmdb.model.MovieNote;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.security.jwt.JwtTokenProvider;
import ruslan.kovshar.mmdb.service.MovieNoteService;
import ruslan.kovshar.mmdb.service.utils.UserExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/movie_notes")
public class MovieNoteRestController {

    private final MovieNoteService movieNoteService;
    private final UserExtractor userExtractor;

    @Autowired
    public MovieNoteRestController(MovieNoteService movieNoteService,
                                   UserExtractor userExtractor) {
        this.movieNoteService = movieNoteService;
        this.userExtractor = userExtractor;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMovieNote(@RequestBody CreateMovieNoteDto createMovieNoteDto,
                                          HttpServletRequest request) {
        User user = userExtractor.extract(request);
        MovieNote movieNote = movieNoteService.create(createMovieNoteDto, user);

        Map<String, Long> response = new HashMap<>();
        response.put("movie_note_id", movieNote.getId());

        return ResponseEntity.ok(response);
    }

}
