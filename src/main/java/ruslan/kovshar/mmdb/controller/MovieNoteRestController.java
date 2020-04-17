package ruslan.kovshar.mmdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ruslan.kovshar.mmdb.dto.CreateMovieNoteDto;
import ruslan.kovshar.mmdb.dto.GetMovieNoteDto;
import ruslan.kovshar.mmdb.model.MovieNote;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.service.MovieNoteService;
import ruslan.kovshar.mmdb.service.utils.UserExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/get")
    public List<GetMovieNoteDto> getMovieNotes(HttpServletRequest request) {
        User user = userExtractor.extract(request);
        return movieNoteService.getUserMovieNotes(user);
    }

    @GetMapping("/get/{id}")
    public GetMovieNoteDto getMovieNote(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        User user = userExtractor.extract(request);
        return movieNoteService.getUserMovieNoteById(user, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMovieNote(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        User user = userExtractor.extract(request);
        movieNoteService.delete(user, id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Long>> updateMovieNote(@PathVariable(name = "id") Long id,
                                                             @RequestBody CreateMovieNoteDto createMovieNoteDto,
                                                             HttpServletRequest request) {
        User user = userExtractor.extract(request);

        MovieNote movieNote = movieNoteService.update(user, id, createMovieNoteDto);

        Map<String, Long> response = new HashMap<>();
        response.put("movie_note_id", movieNote.getId());

        return ResponseEntity.ok(response);
    }
}
