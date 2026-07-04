package com.example.demo.services;
import com.example.demo.models.dto.absolute.UserDTO;
import com.example.demo.models.dto.forms.UserFormDTO;
import com.example.demo.models.entities.Address;
import com.example.demo.models.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.exceptions.NotFound;
import com.example.demo.utils.exceptions.UserAlreadyExists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean alreadyEmailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean documentIdExists(String documentId) {
        return userRepository.findByDocumentId(documentId).isPresent();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("User not found with email: {}", email);
                return new NotFound("User not found with email: " + email);
            });
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> {
                log.error("User not found with id: {}", id);
                return new NotFound("User not found with id: " + id);
            });
    }

    @Transactional
    public long createUser(UserFormDTO user) {
        if (alreadyEmailExists(user.email()) || documentIdExists(user.documentId())) {
            throw new UserAlreadyExists("An user is already registered with this email or document id.");
        }
        User newUser = User.fromDTOForm(user);
        newUser.setPassword(passwordEncoder.encode(user.password()));
        userRepository.save(newUser);
        log.info("A new user has been created, {}", user.email());
        return newUser.getId();
    }

    @Transactional
    public UserFormDTO updateUser(Long id, UserFormDTO user){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        if (!user.email().equals(existingUser.getEmail()) && alreadyEmailExists(user.email())) {
            throw new IllegalArgumentException("An user is already registered with this email");
        }
        if (!user.documentId().equals(existingUser.getDocumentId()) && documentIdExists(user.documentId())) {
            throw new IllegalArgumentException("An user is already registered with this document ID");
        }
        existingUser.setName(user.name());
        existingUser.setEmail(user.email());
        existingUser.setPassword(passwordEncoder.encode(user.password()));
        existingUser.setDocumentId(user.documentId());

        Address updatedAddress = new Address(
                user.address().street(),
                user.address().number(),
                user.address().district(),
                user.address().city(),
                user.address().state(),
                user.address().zipCode()
        );
        existingUser.setAddress(updatedAddress);
        userRepository.save(existingUser);
        log.info("User with UUID {} has been updated.", id);
        return user;
    }

    public void deleteUser(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFound("User not found with id: " + id));
        userRepository.delete(existingUser);
        log.info("User with UUID {} has been deleted.", id);
    }
}
