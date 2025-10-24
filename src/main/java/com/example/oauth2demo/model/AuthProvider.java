// package com.example.oauth2demo.model;

// import jakarta.persistence.*;
// import lombok.*;

// import java.time.LocalDateTime;

// @Entity
// @Table(name = "auth_providers")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class AuthProvider {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     // Many AuthProviders belong to one User
//     @ManyToOne
//     @JoinColumn(name = "user_id", nullable = false)
//     private User user;

//     @Enumerated(EnumType.STRING)
//     private ProviderType provider;

//     private String providerUserId;
//     private String providerEmail;

//     @Builder.Default
//     private LocalDateTime createdAt = LocalDateTime.now();

//     @Builder.Default
//     private LocalDateTime updatedAt = LocalDateTime.now();

//     // Enum for supported providers
//     public enum ProviderType {
//         GOOGLE, GITHUB
//     }
// }


package com.example.oauth2demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auth_providers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private ProviderType provider;

    private String providerUserId;
    private String providerEmail;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum ProviderType {
        GOOGLE, GITHUB
    }
}
