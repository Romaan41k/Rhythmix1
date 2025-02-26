package rom41k.Rhythmix.repository;

import rom41k.Rhythmix.database.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TrackRepository extends JpaRepository<Track, Long> {
    List<Track> findByGenre(String genre);
    List<Track> findByArtistId(Long artistId);

    @Transactional
    @Modifying
    @Query("UPDATE Track t SET t.likesCount = t.likesCount + 1 WHERE t.id = :trackId")
    void incrementLikes(Long trackId);

    @Transactional
    @Modifying
    @Query("UPDATE Track t SET t.listensCount = t.listensCount + 1 WHERE t.id = :trackId")
    void incrementListens(Long trackId);
}
