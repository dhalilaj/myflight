package lhind.internship.myflight.model.dto;

import jakarta.validation.constraints.NotNull;
import lhind.internship.myflight.model.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter@Setter
public class LoadUser {
    private Long id;
    private String username;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    private Integer phoneNumber;
    private String address;

}
