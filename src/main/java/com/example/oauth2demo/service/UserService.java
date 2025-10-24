// // package com.example.oauth2demo.service;

// // import com.example.oauth2demo.model.User;
// // import com.example.oauth2demo.repository.UserRepository;
// // import org.springframework.security.oauth2.core.user.OAuth2User;
// // import org.springframework.stereotype.Service;

// // import java.util.Optional;

// // @Service
// // public class UserService {

// //     private final UserRepository userRepository;

// //     public UserService(UserRepository userRepository) {
// //         this.userRepository = userRepository;
// //     }

// //     public User processOAuthPostLogin(OAuth2User oAuth2User, String provider) {
// //         String email = oAuth2User.getAttribute("email");
// //         String name = oAuth2User.getAttribute("name");
// //         String avatar = oAuth2User.getAttribute(provider.equals("google") ? "picture" : "avatar_url");

// //         if (email == null) {
// //             if ("github".equals(provider) && oAuth2User.getAttribute("login") != null) {
// //                 email = oAuth2User.getAttribute("login") + "@github.com";
// //             } else {
// //                 throw new RuntimeException("Email not found from " + provider);
// //             }
// //         }

// //         Optional<User> optionalUser = userRepository.findByEmail(email);
// //         User user;
// //         if (optionalUser.isPresent()) {
// //             user = optionalUser.get();
// //             user.setDisplayName(name);
// //             user.setAvatarUrl(avatar);
// //         } else {
// //             user = new User();
// //             user.setEmail(email);
// //             user.setDisplayName(name);
// //             user.setAvatarUrl(avatar);
// //             user.setProvider(provider);
// //         }
// //         return userRepository.save(user);
// //     }
// // }


// package com.example.oauth2demo.service;

// import com.example.oauth2demo.model.User;
// import com.example.oauth2demo.model.AuthProvider;
// import com.example.oauth2demo.model.AuthProvider.ProviderType;
// import com.example.oauth2demo.repository.UserRepository;
// import com.example.oauth2demo.repository.AuthProviderRepository;
// import org.springframework.stereotype.Service;
// import org.springframework.security.oauth2.core.user.OAuth2User;

// import java.time.LocalDateTime;
// import java.util.Optional;

// @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final AuthProviderRepository authProviderRepository;

//     // Constructor injection
//     public UserService(UserRepository userRepository, AuthProviderRepository authProviderRepository) {
//         this.userRepository = userRepository;
//         this.authProviderRepository = authProviderRepository;
//     }

//     public void processOAuthPostLogin(OAuth2User oAuth2User, String providerName) {
//         ProviderType provider = ProviderType.valueOf(providerName.toUpperCase());

//         String email = oAuth2User.getAttribute("email");
//         String providerUserId = oAuth2User.getName(); // usually the "id" field
//         String displayName = oAuth2User.getAttribute("name");
//         String avatarUrl = oAuth2User.getAttribute("picture");

//         // Fallback for GitHub
//         if (email == null && provider == ProviderType.GITHUB) {
//             String login = oAuth2User.getAttribute("login");
//             email = login + "@github.com";
//         }

//         Optional<AuthProvider> authOpt = authProviderRepository.findByProviderAndProviderUserId(provider, providerUserId);

//         User user;
//         if (authOpt.isPresent()) {
//             // Existing user
//             user = authOpt.get().getUser();
//             user.setDisplayName(displayName);
//             user.setAvatarUrl(avatarUrl);
//             user.setUpdatedAt(LocalDateTime.now());
//         } else {
//             // New user
//             user = userRepository.findByEmail(email).orElse(User.builder()
//                     .email(email)
//                     .displayName(displayName)
//                     .avatarUrl(avatarUrl)
//                     .createdAt(LocalDateTime.now())
//                     .build());

//             AuthProvider authProvider = AuthProvider.builder()
//                     .user(user)
//                     .provider(provider)
//                     .providerUserId(providerUserId)
//                     .providerEmail(email)
//                     .createdAt(LocalDateTime.now())
//                     .build();

//             user.getAuthProviders().add(authProvider);
//         }

//         userRepository.save(user);
//     }
// }


package com.example.oauth2demo.service;

import com.example.oauth2demo.model.AuthProvider;
import com.example.oauth2demo.model.User;
import com.example.oauth2demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void processOAuthPostLogin(OAuth2User oAuth2User, String provider) {
        String email = oAuth2User.getAttribute("email");
        Optional<User> existUser = userRepository.findByEmail(email);

        if (existUser.isEmpty()) {
            // Create new user
            User newUser = User.builder()
                    .email(email)
                    .displayName(oAuth2User.getAttribute("name"))
                    .avatarUrl(oAuth2User.getAttribute("picture"))
                    .build();

            // Add auth provider
            AuthProvider authProvider = AuthProvider.builder()
                    .provider(provider.equalsIgnoreCase("google") ? AuthProvider.ProviderType.GOOGLE : AuthProvider.ProviderType.GITHUB)
                    .providerUserId(oAuth2User.getName())
                    .providerEmail(email)
                    .user(newUser)
                    .build();

            newUser.setAuthProviders(java.util.List.of(authProvider));

            userRepository.save(newUser);
        } else {
            // Optional: update existing user info
            User user = existUser.get();
            user.setDisplayName(oAuth2User.getAttribute("name"));
            user.setAvatarUrl(oAuth2User.getAttribute("picture"));
            userRepository.save(user);
        }
    }
}
