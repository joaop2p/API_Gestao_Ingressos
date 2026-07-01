package com.example.demo.services;
import com.example.demo.models.dto.UserFormDTO;
import com.example.demo.models.entities.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean alreadyExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public User createUser(UserFormDTO user) {
        if (alreadyExists(user.email())) {
                throw new IllegalArgumentException("Já existe um usuário cadastrado com esse email.");
        }
        User newUser = new User(
                user.email(),
                user.name(),
                user.password()
        );
        userRepository.save(newUser);
        log.info("A new user has been created, {}", user.email());
        return newUser;
    }
}
