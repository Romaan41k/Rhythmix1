package rom41k.Rhythmix.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "genre_subscriptions")
public class GenreSubscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false, updatable = false)
    private LocalDateTime subscribedAt = LocalDateTime.now();
}