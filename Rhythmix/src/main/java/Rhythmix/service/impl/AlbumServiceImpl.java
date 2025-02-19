package rom41k.Rhythmix.service.impl;

import lombok.RequiredArgsConstructor;
import rom41k.Rhythmix.database.entity.Album;
import rom41k.Rhythmix.repository.AlbumRepository;
import rom41k.Rhythmix.service.interfaces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final  AlbumRepository albumRepository;

    @Override
    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public List<Album> findByArtistId(Long artistId) {
        return albumRepository.findByArtistId(artistId);
    }

    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
