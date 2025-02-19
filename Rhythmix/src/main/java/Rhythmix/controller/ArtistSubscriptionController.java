package rom41k.Rhythmix.controller;

import rom41k.Rhythmix.database.entity.ArtistSubscription;
import rom41k.Rhythmix.service.interfaces.ArtistSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artist-subscriptions")
public class ArtistSubscriptionController {

    @Autowired
    private ArtistSubscriptionService artistSubscriptionService;

    @PostMapping
    public ResponseEntity<ArtistSubscription> subscribeToArtist(@RequestBody ArtistSubscription subscription) {
        ArtistSubscription savedSubscription = artistSubscriptionService.subscribeToArtist(subscription);
        return ResponseEntity.ok(savedSubscription);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ArtistSubscription>> getSubscriptionsByUser(@PathVariable Long userId) {
        List<ArtistSubscription> subscriptions = artistSubscriptionService.findByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribeFromArtist(@RequestParam Long userId, @RequestParam Long artistId) {
        artistSubscriptionService.unsubscribeFromArtist(userId, artistId);
        return ResponseEntity.ok().build();
    }
}
