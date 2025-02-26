package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    Playlist createPlaylist(Playlist playlist);
    Optional<Playlist> findById(Long id);
    List<Playlist> findByUserId(Long userId);
    void deletePlaylist(Long id);
}
