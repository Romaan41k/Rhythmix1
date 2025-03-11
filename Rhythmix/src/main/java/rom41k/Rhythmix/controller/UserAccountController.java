package rom41k.Rhythmix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import rom41k.Rhythmix.database.entity.UserAccount;
import rom41k.Rhythmix.dto.UserAccountDTO;
import rom41k.Rhythmix.service.interfaces.UserAccountService;

@RestController
@RequestMapping("/accounts")
public class UserAccountController {
    private final UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/me")
    public ResponseEntity<Object> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized");
        }

        String email = authentication.getName();
        UserAccount account = userAccountService.getAccountByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserAccountDTO accountDTO = new UserAccountDTO(account.getEmail(), account.getRole().name(), account.isEnabled());

        return ResponseEntity.ok(accountDTO);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long id, @RequestBody String newPassword) {
        userAccountService.updatePassword(id, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        userAccountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully");
    }
}