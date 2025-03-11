package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.TrackLike;

public interface TrackLikeService {
    TrackLike likeTrack(TrackLike trackLike);
    boolean isTrackLiked(Long userId, Long trackId);
    void unlikeTrack(Long userId, Long trackId);
}