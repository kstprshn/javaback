package ru.java.teamProject.SmartTaskFlow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.teamProject.SmartTaskFlow.entity.User;
import ru.java.teamProject.SmartTaskFlow.repository.UserRepository;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(RegisterUserDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setNickname(registerDTO.getNickname());
        userRepository.save(user);
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email).orElse(null);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public void updateProfile(String email, UpdateProfileDTO profileDTO) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(profileDTO.getFirstName());
        user.setLastName(profileDTO.getLastName());
        user.setNickname(profileDTO.getNickname());
        if (profileDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(profileDTO.getPassword()));
        }
        userRepository.save(user);
    }

    public void logout(String email) {
        // Handle logout logic if necessary (e.g., invalidating tokens)
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
