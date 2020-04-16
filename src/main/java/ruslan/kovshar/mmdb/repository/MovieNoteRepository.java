package ruslan.kovshar.mmdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.kovshar.mmdb.model.MovieNote;
import ruslan.kovshar.mmdb.model.User;

import java.util.List;

@Repository
public interface MovieNoteRepository extends JpaRepository<MovieNote, Long> {
    List<MovieNote> findAllByUser(User user);
}
