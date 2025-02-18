package rom41k.Rhythmix.service.impl;

import rom41k.Rhythmix.database.entity.Playlist;
import rom41k.Rhythmix.repository.PlaylistRepository;
import rom41k.Rhythmix.service.interfaces.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public List<Playlist> findByUserId(Long userId) {
        return playlistRepository.findByUserId(userId);
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }
}
