package rom41k.Rhythmix.service;

import rom41k.Rhythmix.models.Album;
import rom41k.Rhythmix.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    public List<Album> findByArtistId(Long artistId) {
        return albumRepository.findByArtistId(artistId);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }


}
