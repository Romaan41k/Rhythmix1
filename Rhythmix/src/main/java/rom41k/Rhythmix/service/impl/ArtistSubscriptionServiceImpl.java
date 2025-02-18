package rom41k.Rhythmix.service.impl;

import rom41k.Rhythmix.database.entity.ArtistSubscription;
import rom41k.Rhythmix.repository.ArtistSubscriptionRepository;
import rom41k.Rhythmix.service.interfaces.ArtistSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistSubscriptionServiceImpl implements ArtistSubscriptionService {

    @Autowired
    private ArtistSubscriptionRepository artistSubscriptionRepository;

    @Override
    public ArtistSubscription subscribeToArtist(ArtistSubscription subscription) {
        return artistSubscriptionRepository.save(subscription);
    }

    @Override
    public List<ArtistSubscription> findByUserId(Long userId) {
        return artistSubscriptionRepository.findByUserId(userId);
    }

    @Override
    public void unsubscribeFromArtist(Long userId, Long artistId) {
        artistSubscriptionRepository.deleteByUserIdAndArtistId(userId, artistId);
    }
}
