package lhind.internship.myflight.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateUserRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Set<String> role;
}
