package rom41k.Rhythmix.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rom41k.Rhythmix.database.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @EntityGraph(attributePaths = {"tracks", "playlists"})
    Optional<User> findById(Long id);
    Optional<User> findByAccount_Email(String email);
}
