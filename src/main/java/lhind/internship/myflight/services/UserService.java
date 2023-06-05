package lhind.internship.myflight.services;

import lhind.internship.myflight.model.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto userDto);

    UserDto findByEmail(String email);
    List<UserDto> findAll();

    void deleteUser (Long id);

    void updateUser (UserDto userDto);

}
