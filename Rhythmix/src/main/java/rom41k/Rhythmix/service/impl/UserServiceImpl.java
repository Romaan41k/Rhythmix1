package rom41k.Rhythmix.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rom41k.Rhythmix.database.entity.User;
import rom41k.Rhythmix.repository.UserRepository;
import rom41k.Rhythmix.service.interfaces.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> allUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(updatedUser.getName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void loadPlaylistsAndTracks(Long userId) {
        User user = entityManager.find(User.class, userId);
        if (user != null) {
            user.getPlaylists().size();
            user.getTracks().size();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByAccount_Email(email);
    }

}