package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Column(name = "address")
    private String address;
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Role> role;

    public User(String firstName, String middleName, String lastName, String password, String email, Integer phoneNumber, String address, Set<Role> role) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }

//    First name (Mandatory) • Middle name (Optional)
//    • Last name (Mandatory) • Email in email format (e.g., example@gmail.com) (M
//    andatory) (Unique) • Phone number (Optional) • Address (Optional) • Role (Mandatory)


}
