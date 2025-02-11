package rom41k.Rhythmix.controller;

import rom41k.Rhythmix.models.TrackLike;
import rom41k.Rhythmix.service.TrackLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track-likes")
public class TrackLikeController {

    @Autowired
    private TrackLikeService trackLikeService;

    @PostMapping
    public ResponseEntity<TrackLike> likeTrack(@RequestBody TrackLike trackLike) {
        TrackLike savedTrackLike = trackLikeService.likeTrack(trackLike);
        return ResponseEntity.ok(savedTrackLike);
    }

    @DeleteMapping
    public ResponseEntity<Void> unlikeTrack(@RequestParam Long userId, @RequestParam Long trackId) {
        trackLikeService.unlikeTrack(userId, trackId);
        return ResponseEntity.ok().build();
    }
}
