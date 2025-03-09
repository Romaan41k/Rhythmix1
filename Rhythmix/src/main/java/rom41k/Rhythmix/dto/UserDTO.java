package rom41k.Rhythmix.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String email;
    private String name;
    private List<String> tracks;
    private List<String> playlists;
    private String role;

}

