package com.example.demo.services;

import com.example.demo.models.dto.absolute.TokenDTO;
import com.example.demo.models.dto.forms.LoginFormDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.exceptions.NotFound;
import com.example.demo.utils.exceptions.Unauthorized;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    final PasswordEncoder passwordEncoder;
    final UserRepository userRepository;
    final TokenService tokenService;

    public TokenDTO authenticate(LoginFormDTO data){
        User user = userRepository.findByEmail(data.email())
                .orElseThrow(() -> new NotFound("User not found"));
        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            throw new Unauthorized("Invalid password");
        }
        return new TokenDTO(tokenService.generateToken(user));
    }
}
