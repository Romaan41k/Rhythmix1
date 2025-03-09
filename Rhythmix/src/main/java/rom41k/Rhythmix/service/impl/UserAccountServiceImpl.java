package rom41k.Rhythmix.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rom41k.Rhythmix.database.entity.UserAccount;
import rom41k.Rhythmix.repository.UserAccountRepository;
import rom41k.Rhythmix.service.interfaces.UserAccountService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<UserAccount> getAccountByEmail(String email) {
        return userAccountRepository.findByEmail(email);
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        userAccountRepository.findById(id).ifPresentOrElse(account -> {
            account.setPassword(passwordEncoder.encode(newPassword));
            userAccountRepository.save(account);
        }, () -> {
            throw new RuntimeException("User not found with id: " + id);
        });
    }

    @Override
    public void deleteAccount(Long id) {
        if (userAccountRepository.existsById(id)) {
            userAccountRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
