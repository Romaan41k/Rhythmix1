package rom41k.Rhythmix.service.impl;

import lombok.RequiredArgsConstructor;
import rom41k.Rhythmix.database.entity.TrackLike;
import rom41k.Rhythmix.repository.TrackLikeRepository;
import rom41k.Rhythmix.service.interfaces.TrackLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackLikeServiceImpl implements TrackLikeService {

    private final TrackLikeRepository trackLikeRepository;

    @Override
    public TrackLike likeTrack(TrackLike trackLike) {
        return trackLikeRepository.save(trackLike);
    }

    @Override
    public boolean isTrackLiked(Long userId, Long trackId) {
        return trackLikeRepository.existsByUserIdAndTrackId(userId, trackId);
    }

    @Override
    public void unlikeTrack(Long userId, Long trackId) {
        trackLikeRepository.deleteByUserIdAndTrackId(userId, trackId);
    }
}
