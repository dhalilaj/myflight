package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Role;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> userConverter.convertUserToDto(user))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<UserDto> findByEmail(String email) {
//        return userRepository.findUserByEmail(email).stream()
//                .map(user -> userConverter.convertUserToDto(user))
//                .collect(Collectors.toList());
//    }

    public void createUser(String firstName, String middleName, String lastName,  String password, String email, Integer phoneNumber, String address,  Set<Role> role) {
        User user = new User(firstName, middleName, lastName, password, email, phoneNumber, address, role);
        this.userRepository.save(user);
    }}

//    public User updateUser (String email, String firstName, String middleName, String lastName,  String password, Integer phoneNumber, String address,  Set<Role> role) {
//        User user = userRepository.findUserByEmail(email).sream()
//                .map(userDto -> userConverter.convertUserToEntity(userDto))
//                .collect(Collectors.toList());
//    }
