package rom41k.Rhythmix.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rom41k.Rhythmix.database.entity.UserAccount;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
    Optional<UserAccount> findByEmail(String email);
    Optional<UserAccount> findByVerificationCode(String verificationCode);
}
