package com.example.demo.models.entities;
import com.example.demo.models.dto.forms.UserFormDTO;
import com.example.demo.models.dto.absolute.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String documentId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id", unique = true, nullable = false)
    private Address address;

    public User() {}

    public User(String name, String email, String password, String documentId){
        this.name = name;
        this.email = email;
        this.password = password;
        this.documentId = documentId;
    }

    public UserDTO toDTO() {
        return new UserDTO(id, name, email, documentId, address.toFormDTO());
    }

    public static User fromDTOForm(UserFormDTO userDTO) {
        User user = new User();
        user.name = userDTO.name();
        user.email = userDTO.email();
        user.documentId = userDTO.documentId();
        user.address = Address.fromFormDTO(userDTO.address());
        return user;
    }
}
