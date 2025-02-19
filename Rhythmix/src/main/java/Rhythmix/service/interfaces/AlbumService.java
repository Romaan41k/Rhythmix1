package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Album createAlbum(Album album);
    Optional<Album> findById(Long id);
    List<Album> findByArtistId(Long artistId);
    void deleteAlbum(Long id);
}
