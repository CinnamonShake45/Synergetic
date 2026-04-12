package com.cinnamonshake.storefront.service;

import com.cinnamonshake.storefront.dto.UserRequest;
import com.cinnamonshake.storefront.entity.User;
import com.cinnamonshake.storefront.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String updateUser(String username, UserRequest req) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (req.getEmail() != null) {
            user.setEmail(req.getEmail());
        }

        if (req.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }

        userRepository.save(user);

        return "User updated successfully";
    }
}