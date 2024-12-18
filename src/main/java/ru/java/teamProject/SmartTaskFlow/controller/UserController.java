package ru.java.teamProject.SmartTaskFlow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.dto.LoginDTO;
import ru.java.teamProject.SmartTaskFlow.dto.RegisterUserDTO;
import ru.java.teamProject.SmartTaskFlow.dto.UpdateProfileDTO;
import ru.java.teamProject.SmartTaskFlow.service.UserService;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    // Registration endpoint
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO registerDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid data provided.");
        }
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match.");
        }
        userService.registerUser(registerDTO);
        return ResponseEntity.ok("User registered successfully.");
    }

    // Authentication endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {
        boolean authenticated = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());
        if (!authenticated) {
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
        return ResponseEntity.ok("Login successful.");
    }

    // Update profile
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(Authentication authentication, @Valid @RequestBody UpdateProfileDTO profileDTO) {
        String email = authentication.getName();
        userService.updateProfile(email, profileDTO);
        return ResponseEntity.ok("Profile updated successfully.");
    }

    // Logout endpoint
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(Authentication authentication) {
        userService.logout(authentication.getName());
        return ResponseEntity.ok("Logout successful.");
    }

    // Delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
