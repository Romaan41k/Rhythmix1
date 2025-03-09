package rom41k.Rhythmix.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class VerifyUserDto {
    private String email;
    private String verificationCode;
}