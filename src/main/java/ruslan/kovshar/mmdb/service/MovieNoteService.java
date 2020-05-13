package ruslan.kovshar.mmdb.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.kovshar.mmdb.dto.CreateMovieNoteDto;
import ruslan.kovshar.mmdb.dto.GetMovieNoteDto;
import ruslan.kovshar.mmdb.model.MovieNote;
import ruslan.kovshar.mmdb.model.User;
import ruslan.kovshar.mmdb.repository.MovieNoteRepository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<GetMovieNoteDto> getUserMovieNotes(User user) {
        return movieNoteRepository.findAllByUser(user)
                .stream()
                .map(GetMovieNoteDto::fromMovieDto)
                .collect(Collectors.toList());
    }

    public GetMovieNoteDto getUserMovieNoteById(User user, Long id) {
        MovieNote movieNote = checkIfUserHaveMovieNote(user, id);

        return GetMovieNoteDto.fromMovieDto(movieNote);
    }

    public void delete(User user, Long id) {
        MovieNote movieNote = checkIfUserHaveMovieNote(user, id);

        movieNoteRepository.delete(movieNote);
    }

    private MovieNote checkIfUserHaveMovieNote(User user, Long id) {
        return movieNoteRepository.findByUserAndId(user, id)
                .orElseThrow(() -> new RuntimeException("User " + user.getUsername() + " doesn`t have note with id " + id));
    }

    public MovieNote update(User user, Long id, CreateMovieNoteDto createMovieNoteDto) {
        MovieNote movieNote = checkIfUserHaveMovieNote(user, id);

        String title = createMovieNoteDto.getTitle();
        movieNote.setTitle(title != null ? title : movieNote.getTitle());
        LocalDate releaseDate = createMovieNoteDto.getReleaseDate();
        movieNote.setReleaseDate(releaseDate != null ? releaseDate : movieNote.getReleaseDate());

        return movieNoteRepository.save(movieNote);
    }
}
