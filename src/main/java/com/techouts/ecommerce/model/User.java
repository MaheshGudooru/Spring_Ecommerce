package com.techouts.ecommerce.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NamedQuery(
        name = "User.findByEmail",
        query = "SELECT u FROM User u WHERE u.email = :email"
)

@NamedQuery(
    name = "User.findById",
    query = "SELECT u FROM User u WHERE u.id = :userId"
)

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "User name cannot be empty")
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Invalid e-mail format")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "Password field cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Column(name = "joined_date", nullable = false, updatable = false)
    private LocalDate joinedDate;

    @OneToOne(mappedBy = "userId", cascade = CascadeType.ALL)
    private Cart cart;

    public String getFormattedJoinedDate() {
        if (joinedDate == null)
            return "";
        return joinedDate.format (DateTimeFormatter.ofPattern ("MMMM dd, yyyy"));
    }

    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.joinedDate = LocalDate.now ();
    }

    public User(String email, String password) {

        this.email = email;
        this.password = password;
    }
}
