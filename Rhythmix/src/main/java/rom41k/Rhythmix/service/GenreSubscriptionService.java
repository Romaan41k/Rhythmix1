package rom41k.Rhythmix.service;

import rom41k.Rhythmix.models.GenreSubscription;
import rom41k.Rhythmix.repository.GenreSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreSubscriptionService {

    @Autowired
    private GenreSubscriptionRepository genreSubscriptionRepository;

    public GenreSubscription subscribeToGenre(GenreSubscription subscription) {
        return genreSubscriptionRepository.save(subscription);
    }

    public List<GenreSubscription> findByUserId(Long userId) {
        return genreSubscriptionRepository.findByUserId(userId);
    }

    public void unsubscribeFromGenre(Long userId, String genre) {
        genreSubscriptionRepository.deleteByUserIdAndGenre(userId, genre);
    }
}
