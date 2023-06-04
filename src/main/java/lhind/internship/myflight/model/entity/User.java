package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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

    @Column(name = "username")
    private String username;
    @Column(name = "first_name")@NotNull
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")@NotNull
    private String lastName;
    @Column(name = "password")
    private String password;
    @Column(name = "email", unique = true)@NotNull
    private String email;
    @Column(name = "phone_number")
    private Integer phoneNumber;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,  fetch = FetchType.LAZY )
    private Set<Booking> bookings;

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

    public User(String password, String email, Set<Role> role) {
        this.password = password;
        this.email = email;
        this.role = role;
    }

    //    First name (Mandatory) • Middle name (Optional)
//    • Last name (Mandatory) • Email in email format (e.g., example@gmail.com) (M
//    andatory) (Unique) • Phone number (Optional) • Address (Optional) • Role (Mandatory)


}
