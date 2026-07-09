package com.armelsan.signalapp.controller;

import com.armelsan.signalapp.DTOs.UserCreateRequest;
import com.armelsan.signalapp.DTOs.UserResponse;
import com.armelsan.signalapp.model.Role; // Rol sınıfının model paketinde olduğunu varsayıyoruz
import com.armelsan.signalapp.model.User;
import com.armelsan.signalapp.service.UserService;
import com.armelsan.signalapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, Object> payload) {
        Long userId = Long.valueOf(payload.get("id").toString());
        String newPassword = payload.get("newPassword").toString();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        user.setPassword(newPassword);
        user.setRequiresPasswordChange(false);

        userRepository.save(user);

        return ResponseEntity.ok().body("{\"message\": \"Şifre başarıyla güncellendi\"}");
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest request) {
        // Gelen sadece-gerekli bilgileri (UserCreateRequest) alıp Entity'e çeviriyoruz
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());

        // String gelen Rolü Enum'a dönüştürüyoruz
        newUser.setRole(Role.valueOf(request.getRole()));
        newUser.setDepartment(request.getDepartment());

        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String email = credentials.get("email");
        String password = credentials.get("password");

        List<User> users = userRepository.findAll();

        for (User u : users) {
            if (u.getUsername().equals(username) &&
                    u.getEmail().equals(email) &&
                    u.getPassword().equals(password)) {

                UserResponse response = new UserResponse();
                response.setId(u.getId());
                response.setUsername(u.getUsername());
                response.setEmail(u.getEmail());
                response.setRole(u.getRole().name());
                response.setDepartment(u.getDepartment());
                response.setRequiresPasswordChange(u.isRequiresPasswordChange());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(401).body("{\"message\": \"Bilgiler hatalı\"}");
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        if (!userService.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body("Hata: Bu ID'ye sahip bir kullanıcı bulunamadı!");
        }

        userService.deleteUser(id);

        return ResponseEntity.ok("Kullanıcı başarıyla silindi.");
    }
}