package rom41k.Rhythmix.repository;

import rom41k.Rhythmix.database.entity.ArtistSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistSubscriptionRepository extends JpaRepository<ArtistSubscription, Long> {
    List<ArtistSubscription> findByUserId(Long userId);
    void deleteByUserIdAndArtistId(Long userId, Long artistId);
}
