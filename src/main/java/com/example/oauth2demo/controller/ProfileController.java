package com.example.oauth2demo.controller;

import com.example.oauth2demo.model.User;
import com.example.oauth2demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.ServletException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;

    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal OAuth2User oauthUser) {
        String email = oauthUser.getAttribute("email");
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping("/profile")
    public User updateProfile(@AuthenticationPrincipal OAuth2User oauthUser,
                              @RequestBody Map<String, String> updates) {
        String email = oauthUser.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setDisplayName(updates.getOrDefault("displayName", user.getDisplayName()));
        user.setBio(updates.getOrDefault("bio", user.getBio()));

        return userRepository.save(user);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();
    }
}
