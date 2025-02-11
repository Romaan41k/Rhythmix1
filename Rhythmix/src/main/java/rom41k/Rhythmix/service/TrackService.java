package rom41k.Rhythmix.service;

import rom41k.Rhythmix.models.Track;
import rom41k.Rhythmix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public Track uploadTrack(Track track) {
        return trackRepository.save(track);
    }

    public Optional<Track> findById(Long id) {
        return trackRepository.findById(id);
    }

    public List<Track> findByGenre(String genre) {
        return trackRepository.findByGenre(genre);
    }

    public List<Track> findByArtistId(Long artistId) {
        return trackRepository.findByArtistId(artistId);
    }

    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }

    public void incrementLikes(Long trackId) {
        trackRepository.incrementLikes(trackId);
    }

    public void incrementListens(Long trackId) {
        trackRepository.incrementListens(trackId);
    }
}
