package rom41k.Rhythmix.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import rom41k.Rhythmix.database.entity.User;
import rom41k.Rhythmix.dto.UserDTO;
import rom41k.Rhythmix.service.interfaces.UserService;
import rom41k.Rhythmix.util.UserMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> ResponseEntity.ok(UserMapper.convertToDto(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> allUsers() {
        List<UserDTO> users = userService.allUsers().stream()
                .map(UserMapper::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return ResponseEntity.ok(userService.updateUser(id, updatedUser));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserByEmail(Authentication authentication) {
        String email = authentication.getName();
        User user = userService.getUserByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        userService.loadPlaylistsAndTracks(user.getId());

        return ResponseEntity.ok(UserMapper.convertToDto(user));
    }
}