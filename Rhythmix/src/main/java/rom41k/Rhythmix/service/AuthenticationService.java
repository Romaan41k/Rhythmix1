package rom41k.Rhythmix.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rom41k.Rhythmix.database.entity.User;
import rom41k.Rhythmix.database.entity.UserAccount;
import rom41k.Rhythmix.database.enums.Role;
import rom41k.Rhythmix.dto.LoginUserDto;
import rom41k.Rhythmix.dto.RegisterUserDto;
import rom41k.Rhythmix.dto.VerifyUserDto;
import rom41k.Rhythmix.exception.AccountNotVerifiedException;
import rom41k.Rhythmix.repository.UserAccountRepository;
import rom41k.Rhythmix.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Value("${email.template-path}")
    private String emailTemplatePath;

    public UserAccount signup(RegisterUserDto input) {
        if (userAccountRepository.findByEmail(input.email()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(input.email());
        userAccount.setPassword(passwordEncoder.encode(input.password()));
        userAccount.setCreatedAt(LocalDateTime.now());
        userAccount.setEnabled(false);
        userAccount.setVerificationCode(generateVerificationCode());
        userAccount.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        userAccount.setRole(Role.valueOf(input.role()));

        userAccountRepository.save(userAccount);

        User user = new User();
        user.setName(input.username());
        user.setAccount(userAccount);
        userRepository.save(user);

        sendVerificationEmail(userAccount);

        return userAccount;
    }

    public UserAccount authenticate(LoginUserDto input) {
        UserAccount userAccount = userAccountRepository.findByEmail(input.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!userAccount.isEnabled()) {
            throw new AccountNotVerifiedException("Account not verified. Please verify your account.");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.email(), input.password())
        );

        return userAccount;
    }

    public void verifyUser(VerifyUserDto input) {
        UserAccount userAccount = userAccountRepository.findByEmail(input.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userAccount.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification code has expired");
        }
        if (!userAccount.getVerificationCode().equals(input.verificationCode())) {
            throw new RuntimeException("Invalid verification code");
        }

        userAccount.setEnabled(true);
        userAccount.setVerificationCode(null);
        userAccount.setVerificationCodeExpiresAt(null);
        userAccountRepository.save(userAccount);
    }

    public void resendVerificationCode(String email) {
        UserAccount userAccount = userAccountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userAccount.isEnabled()) {
            throw new RuntimeException("Account is already verified");
        }

        userAccount.setVerificationCode(generateVerificationCode());
        userAccount.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        sendVerificationEmail(userAccount);
        userAccountRepository.save(userAccount);
    }

    private void sendVerificationEmail(UserAccount userAccount) {
        String verificationCode = userAccount.getVerificationCode();
        String htmlMessage = readEmailTemplate().replace("${verificationCode}", verificationCode);

        try {
            emailService.sendVerificationEmail(userAccount.getEmail(), "Account Verification", htmlMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }
    }

    private String readEmailTemplate() {
        try {
            Path path = Path.of(emailTemplatePath);
            return Files.readString(path);
        } catch (IOException e) {
            throw new RuntimeException("Error reading email template file", e);
        }
    }

    private String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }
}