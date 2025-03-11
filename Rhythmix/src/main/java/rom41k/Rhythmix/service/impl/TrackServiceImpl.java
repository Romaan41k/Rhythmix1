package rom41k.Rhythmix.service.impl;

import lombok.RequiredArgsConstructor;
import rom41k.Rhythmix.database.entity.Track;
import rom41k.Rhythmix.repository.TrackRepository;
import rom41k.Rhythmix.service.interfaces.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {

    private final TrackRepository trackRepository;

    @Override
    public Track uploadTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public Optional<Track> findById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public List<Track> findByGenre(String genre) {
        return trackRepository.findByGenre(genre);
    }

    @Override
    public List<Track> findByArtistId(Long artistId) {
        return trackRepository.findByArtistId(artistId);
    }

    @Override
    public void deleteTrack(Long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public void incrementLikes(Long trackId) {
        trackRepository.incrementLikes(trackId);
    }

    @Override
    public void incrementListens(Long trackId) {
        trackRepository.incrementListens(trackId);
    }
}