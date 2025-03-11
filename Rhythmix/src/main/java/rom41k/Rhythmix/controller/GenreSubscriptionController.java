package rom41k.Rhythmix.controller;

import rom41k.Rhythmix.database.entity.GenreSubscription;
import rom41k.Rhythmix.service.interfaces.GenreSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre-subscriptions")
public class GenreSubscriptionController {

    @Autowired
    private GenreSubscriptionService genreSubscriptionService;

    @PostMapping
    public ResponseEntity<GenreSubscription> subscribeToGenre(@RequestBody GenreSubscription subscription) {
        GenreSubscription savedSubscription = genreSubscriptionService.subscribeToGenre(subscription);
        return ResponseEntity.ok(savedSubscription);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GenreSubscription>> getSubscriptionsByUser(@PathVariable Long userId) {
        List<GenreSubscription> subscriptions = genreSubscriptionService.findByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribeFromGenre(@RequestParam Long userId, @RequestParam String genre) {
        genreSubscriptionService.unsubscribeFromGenre(userId, genre);
        return ResponseEntity.ok().build();
    }
}