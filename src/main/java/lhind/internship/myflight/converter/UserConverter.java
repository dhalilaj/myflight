package lhind.internship.myflight.converter;

import jakarta.websocket.server.ServerEndpoint;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {
    public UserDto convertUserToDto (User user){
        UserDto convertedUserDto = new UserDto();
        convertedUserDto.setFirstName(user.getFirstName());
        convertedUserDto.setMiddleName(user.getMiddleName());
        convertedUserDto.setLastName(user.getLastName());
        convertedUserDto.setPassword(user.getPassword());
//        convertedUserDto.setRole(user.getRole());
        convertedUserDto.setAddress(user.getAddress());
        convertedUserDto.setEmail(user.getEmail());
        convertedUserDto.setPhoneNumber(user.getPhoneNumber());
        return convertedUserDto;
    }

    public User convertUserToEntity (UserDto userDto){
        User convertedUser = new User();
        convertedUser.setFirstName(userDto.getFirstName());
        convertedUser.setMiddleName(userDto.getMiddleName());
        convertedUser.setLastName(userDto.getLastName());
        convertedUser.setAddress(userDto.getAddress());
        convertedUser.setPassword(userDto.getPassword());
        convertedUser.setEmail(userDto.getEmail());
//        convertedUser.setRole(userDto.getRole());
        convertedUser.setPhoneNumber(userDto.getPhoneNumber());
        return convertedUser;
    }
}
