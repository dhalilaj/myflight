package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import lhind.internship.myflight.model.enums.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName code;

    public Role(RoleName code, Set<User> users) {
        this.code = code;
        this.users = users;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            }
    )
    private Set<User> users = new HashSet<>();

}
