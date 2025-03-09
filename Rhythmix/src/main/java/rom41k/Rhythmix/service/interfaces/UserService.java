package rom41k.Rhythmix.service.interfaces;

import rom41k.Rhythmix.database.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> allUsers();
    Optional<User> getUserById(Long id);
    User updateUser(Long id, User updatedUser);
    void loadPlaylistsAndTracks(Long userId);
    Optional<User> getUserByEmail(String email);
}
