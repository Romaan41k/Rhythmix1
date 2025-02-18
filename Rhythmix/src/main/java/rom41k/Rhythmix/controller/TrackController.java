package rom41k.Rhythmix.controller;

import rom41k.Rhythmix.database.entity.Track;
import rom41k.Rhythmix.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping("/upload")
    public ResponseEntity<Track> uploadTrack(@RequestBody Track track) {
        Track uploadedTrack = trackService.uploadTrack(track);
        return ResponseEntity.ok(uploadedTrack);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> getTrackById(@PathVariable Long id) {
        return trackService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Track>> getTracksByGenre(@PathVariable String genre) {
        List<Track> tracks = trackService.findByGenre(genre);
        return ResponseEntity.ok(tracks);
    }

    @PatchMapping("/{id}/like")
    public ResponseEntity<Void> likeTrack(@PathVariable Long id) {
        trackService.incrementLikes(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/listen")
    public ResponseEntity<Void> listenTrack(@PathVariable Long id) {
        trackService.incrementListens(id);
        return ResponseEntity.ok().build();
    }


}
