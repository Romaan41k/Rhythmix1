package rom41k.Rhythmix.database.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "track_likes")
public class TrackLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @Column(nullable = false, updatable = false)
    private LocalDateTime likedAt = LocalDateTime.now();
}