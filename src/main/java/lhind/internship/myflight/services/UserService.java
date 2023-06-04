package lhind.internship.myflight.services;

import lhind.internship.myflight.model.dto.CreateUserRequest;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.User;

import java.util.List;

public interface UserService {

    List<UserDto> findAllByEmail(String email);

    void createUser(UserDto userDto);

    UserDto findByEmail(String email);
    List<UserDto> findAll();

    void deleteUserByEmail (String email);

    void deleteUser (Long id);

    void updateUser (UserDto userDto);

}
