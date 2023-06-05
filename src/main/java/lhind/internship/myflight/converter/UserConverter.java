package lhind.internship.myflight.converter;

import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    public UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto convertUserToDto (User user){
        UserDto convertedUserDto = new UserDto();
        convertedUserDto.setId(user.getId());
        convertedUserDto.setFirstName(user.getFirstName());
        convertedUserDto.setMiddleName(user.getMiddleName());
        convertedUserDto.setLastName(user.getLastName());
//        convertedUserDto.setRole(user.getRole().stream().map(role -> role.getCode()));
        convertedUserDto.setRole(user.getRole());
        convertedUserDto.setUsername(user.getUsername());
        convertedUserDto.setAddress(user.getAddress());
        convertedUserDto.setEmail(user.getEmail());
        convertedUserDto.setPhoneNumber(user.getPhoneNumber());
        return convertedUserDto;
    }

    public User convertUserToEntity (UserDto userDto){
        User convertedUser = new User();
        convertedUser.setUsername(userDto.getUsername());
        convertedUser.setFirstName(userDto.getFirstName());
        convertedUser.setMiddleName(userDto.getMiddleName());
        convertedUser.setLastName(userDto.getLastName());
        convertedUser.setAddress(userDto.getAddress());
        convertedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        convertedUser.setEmail(userDto.getEmail());
//        convertedUser.setRole(userDto.getRole());
        convertedUser.setPhoneNumber(userDto.getPhoneNumber());
        return convertedUser;
    }
}
