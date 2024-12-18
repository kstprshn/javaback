package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.dto.RegisterUserDTO;
import ru.java.teamProject.SmartTaskFlow.dto.UpdateProfileDTO;

public interface UserService {
    void registerUser(RegisterUserDTO registerDTO);
    boolean authenticateUser(String email, String password);
    void updateProfile(String email, UpdateProfileDTO profileDTO);
    void logout(String email);
    void deleteUser(Long userId);
}
