package com.example.oauth2demo.repository;

import com.example.oauth2demo.model.AuthProvider;
import com.example.oauth2demo.model.AuthProvider.ProviderType;
import com.example.oauth2demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long> {
    Optional<AuthProvider> findByProviderAndProviderUserId(ProviderType provider, String providerUserId);
    Optional<AuthProvider> findByUserAndProvider(User user, ProviderType provider);
}
