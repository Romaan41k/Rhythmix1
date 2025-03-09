package rom41k.Rhythmix.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RegisterUserDto {
    private String email;
    private String password;
    private String username;
    private String role;
}
