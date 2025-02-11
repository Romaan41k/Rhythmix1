package rom41k.Rhythmix.service;

import rom41k.Rhythmix.models.TrackLike;
import rom41k.Rhythmix.repository.TrackLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrackLikeService {

    @Autowired
    private TrackLikeRepository trackLikeRepository;

    public TrackLike likeTrack(TrackLike trackLike) {
        return trackLikeRepository.save(trackLike);
    }

    public boolean isTrackLiked(Long userId, Long trackId) {
        return trackLikeRepository.existsByUserIdAndTrackId(userId, trackId);
    }

    public void unlikeTrack(Long userId, Long trackId) {
        trackLikeRepository.deleteByUserIdAndTrackId(userId, trackId);
    }
}
