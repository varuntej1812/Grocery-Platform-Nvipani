package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getOrCreateUser(String uid, String name, String email) {
        return userRepository.findById(uid)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                            .uid(uid)
                            .name(name)
                            .email(email)
                            .build()
                ));
    }
}
