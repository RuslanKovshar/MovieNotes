package ruslan.kovshar.mmdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ruslan.kovshar.mmdb.model.MovieWatchList;
import ruslan.kovshar.mmdb.model.User;

import java.util.List;

@Repository
public interface MovieWatchListRepository extends JpaRepository<MovieWatchList, Long> {
    List<MovieWatchList> findAllByUser(User user);
}
