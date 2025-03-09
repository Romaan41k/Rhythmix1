package rom41k.Rhythmix.dto;

import lombok.Data;

@Data
public class UserAccountDTO {
    private String email;
    private String role;
    private boolean enabled;
}
