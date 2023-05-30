package lhind.internship.myflight.model.dto;

import lhind.internship.myflight.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private String address;
    private Set<Role> role;
}
