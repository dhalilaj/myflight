package lhind.internship.myflight.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lhind.internship.myflight.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@JsonSerialize

@Getter
@Setter
public class DisplayUser {

    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Integer phoneNumber;
    private String address;

    public DisplayUser() {
    }

    public DisplayUser(User user) {
        this.setUsername(user.getUsername());
        this.setFirstName(user.getFirstName());
        this.setMiddleName(user.getMiddleName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setAddress(user.getAddress());
        this.setPhoneNumber(user.getPhoneNumber());
        this.setPhoneNumber(user.getPhoneNumber());

    }
}
