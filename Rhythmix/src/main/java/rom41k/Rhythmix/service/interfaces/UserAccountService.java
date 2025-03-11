package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.UserAccount;

import java.util.Optional;

public interface UserAccountService {
    Optional<UserAccount> getAccountByEmail(String email);
    void updatePassword(Long id, String newPassword);
    void deleteAccount(Long id);
}