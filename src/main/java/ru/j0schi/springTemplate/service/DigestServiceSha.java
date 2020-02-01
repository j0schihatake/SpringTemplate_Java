package ru.j0schi.springTemplate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DigestServiceSha implements DigestService {
    private final PasswordEncoder passwordEncoder;

    public String hash(String pass) {
        String password;
        password = passwordEncoder.encode(pass);
        return password;
    }
}
