package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public User getProfile(Authentication authentication, @RequestHeader("Authorization") String token) throws Exception {
        String idToken = token.replace("Bearer ", "");
        FirebaseToken decoded = FirebaseAuth.getInstance().verifyIdToken(idToken);

        String uid = decoded.getUid();
        String name = decoded.getName() != null ? decoded.getName() : "NoName";
        String email = decoded.getEmail() != null ? decoded.getEmail() : uid + "@example.com";

        return userService.getOrCreateUser(uid, name, email);
    }

    @GetMapping("/test")
    public String test() {
        return "Public endpoint working!";
    }
}
