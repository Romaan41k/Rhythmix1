package rom41k.Rhythmix.service.impl;

import lombok.RequiredArgsConstructor;
import rom41k.Rhythmix.database.entity.GenreSubscription;
import rom41k.Rhythmix.repository.GenreSubscriptionRepository;
import rom41k.Rhythmix.service.interfaces.GenreSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreSubscriptionServiceImpl implements GenreSubscriptionService {

    private final GenreSubscriptionRepository genreSubscriptionRepository;

    @Override
    public GenreSubscription subscribeToGenre(GenreSubscription subscription) {
        return genreSubscriptionRepository.save(subscription);
    }

    @Override
    public List<GenreSubscription> findByUserId(Long userId) {
        return genreSubscriptionRepository.findByUserId(userId);
    }

    @Override
    public void unsubscribeFromGenre(Long userId, String genre) {
        genreSubscriptionRepository.deleteByUserIdAndGenre(userId, genre);
    }
}