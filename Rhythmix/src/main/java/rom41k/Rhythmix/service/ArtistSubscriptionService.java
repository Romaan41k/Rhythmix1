package rom41k.Rhythmix.service;

import rom41k.Rhythmix.models.ArtistSubscription;
import rom41k.Rhythmix.repository.ArtistSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistSubscriptionService {

    @Autowired
    private ArtistSubscriptionRepository artistSubscriptionRepository;

    public ArtistSubscription subscribeToArtist(ArtistSubscription subscription) {
        return artistSubscriptionRepository.save(subscription);
    }

    public List<ArtistSubscription> findByUserId(Long userId) {
        return artistSubscriptionRepository.findByUserId(userId);
    }

    public void unsubscribeFromArtist(Long userId, Long artistId) {
        artistSubscriptionRepository.deleteByUserIdAndArtistId(userId, artistId);
    }
}
