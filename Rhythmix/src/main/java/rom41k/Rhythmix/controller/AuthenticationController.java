package rom41k.Rhythmix.controller;

import rom41k.Rhythmix.dto.LoginUserDto;
import rom41k.Rhythmix.dto.RegisterUserDto;
import rom41k.Rhythmix.dto.VerifyUserDto;
import rom41k.Rhythmix.database.entity.UserAccount;
import rom41k.Rhythmix.responses.ApiResponse;
import rom41k.Rhythmix.responses.LoginResponse;
import rom41k.Rhythmix.service.AuthenticationService;
import rom41k.Rhythmix.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserAccount>> register(@RequestBody RegisterUserDto registerUserDto) {
        UserAccount registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(new ApiResponse<>(registeredUser));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserAccount authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse(jwtToken, jwtService.getExpirationTime());
        return ResponseEntity.ok(new ApiResponse<>(loginResponse));
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<Map<String, String>>> verifyUser(@RequestBody VerifyUserDto verifyUserDto) {
        authenticationService.verifyUser(verifyUserDto);
        return ResponseEntity.ok(ApiResponse.message("Account verified successfully"));
    }

    @PostMapping("/resend")
    public ResponseEntity<ApiResponse<Map<String, String>>> resendVerificationCode(@RequestParam String email) {
        authenticationService.resendVerificationCode(email);
        return ResponseEntity.ok(ApiResponse.message("Verification code resent"));
    }
}