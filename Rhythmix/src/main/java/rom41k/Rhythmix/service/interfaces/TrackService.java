package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    Track uploadTrack(Track track);
    Optional<Track> findById(Long id);
    List<Track> findByGenre(String genre);
    List<Track> findByArtistId(Long artistId);
    void deleteTrack(Long id);
    void incrementLikes(Long trackId);
    void incrementListens(Long trackId);
}