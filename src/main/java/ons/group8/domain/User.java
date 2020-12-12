package ons.group8.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Email field is mandatory")
    @NotNull
    @NotEmpty
    private String email;

    @NotBlank(message="First Name field is mandatory")
    @NotNull
    @NotEmpty
    private String firstName;

    @NotBlank(message="Last name field is manadatory")
    @NotNull
    @NotEmpty
    private String lastName;

    @NotBlank(message="Password field is mandatory")
    @NotNull
    @NotEmpty
    private String password;

    @NotBlank(message="User role field is mandatory")
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")) // this means that by adding this, when a role is added to the hash then it will be added to the user_role table with the user
    private Set<Role> roles = new HashSet<>();


    public User(String email, String password, String firstName, String lastName) {
        this(null, email, firstName, lastName, password, new HashSet<>());
    }

    public void addRole(Role role) {
        roles.add(role);
    }
}

