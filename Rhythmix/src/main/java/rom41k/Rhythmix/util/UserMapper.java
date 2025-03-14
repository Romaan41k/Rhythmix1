package rom41k.Rhythmix.util;

import org.springframework.stereotype.Component;
import rom41k.Rhythmix.database.entity.User;
import rom41k.Rhythmix.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO convertToDto(User user) {
        List<String> playlists = user.getPlaylists().stream()
                .map(playlist -> playlist.getName())
                .collect(Collectors.toList());

        List<String> tracks = user.getTracks().stream()
                .map(track -> track.getTitle())
                .collect(Collectors.toList());

        return new UserDTO(user.getAccount().getEmail(), user.getName(),
                tracks, playlists, user.getAccount().getRole().name());
    }
}
