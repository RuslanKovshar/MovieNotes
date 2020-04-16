package ruslan.kovshar.mmdb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ruslan.kovshar.mmdb.dto.CreateMovieNoteDto;
import ruslan.kovshar.mmdb.model.MovieNote;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.repository.MovieNoteRepository;

@Service
public class MovieNoteService {

    private final MovieNoteRepository movieNoteRepository;

    @Autowired
    public MovieNoteService(MovieNoteRepository movieNoteRepository) {
        this.movieNoteRepository = movieNoteRepository;
    }

    public MovieNote create(CreateMovieNoteDto createMovieNoteDto, User user) {
        MovieNote movieNote = createMovieNoteDto.toMovieNote();
        movieNote.setUser(user);
        return movieNoteRepository.save(movieNote);
    }

}
