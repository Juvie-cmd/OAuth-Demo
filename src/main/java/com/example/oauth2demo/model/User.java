// // package com.example.oauth2demo.model;

// // import jakarta.persistence.*;
// // import lombok.Data;

// // @Entity
// // @Table(name = "users")
// // @Data
// // public class User {

// //     @Id
// //     @GeneratedValue(strategy = GenerationType.IDENTITY)
// //     private Long id;

// //     @Column(unique = true, nullable = false)
// //     private String email;

// //     private String displayName;
// //     private String avatarUrl;

// //     @Column(length = 500)
// //     private String bio;

// //     private String provider; // "google" or "github"
// // }


// package com.example.oauth2demo.model;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Entity
// @Data
// @NoArgsConstructor
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String displayName;
//     private String avatarUrl;
//     private String email;
//     private String provider;
//     private String bio;
// }


package com.example.oauth2demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String displayName;
    private String avatarUrl;
    private String bio;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AuthProvider> authProviders;
}
