package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
    void deleteUser(Long id);
}
