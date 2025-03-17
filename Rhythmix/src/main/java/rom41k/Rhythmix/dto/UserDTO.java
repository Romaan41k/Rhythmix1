package rom41k.Rhythmix.dto;

import java.util.List;

public record UserDTO(String email, String name, List<String> tracks, List<String> playlists, String role) {}