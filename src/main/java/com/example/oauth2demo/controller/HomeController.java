package com.example.oauth2demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
            "googleLoginUrl", "/oauth2/authorization/google",
            "githubLoginUrl", "/oauth2/authorization/github"
        );
    }

} // <-- Make sure this closing brace exists
