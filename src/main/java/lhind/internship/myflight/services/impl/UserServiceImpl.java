package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.model.dto.CreateUserRequest;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Role;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.RoleName;
import lhind.internship.myflight.repository.RoleRepository;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    private final RoleRepository roleRepository;

    PasswordEncoder encoder;


    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(user -> userConverter.convertUserToDto(user))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<UserDto> findAllByEmail(String email) {
//        return userRepository.findUserByEmail(email).stream()
//                .map(user -> userConverter.convertUserToDto(user))
//                .collect(Collectors.toList());
//    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {

        Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode(RoleName.TRAVELLER).get()));

        User user = new User(createUserRequest.getEmail(), encoder.encode(createUserRequest.getPassword()), roles);

        this.userRepository.save(user);
    }

//    public User updateUser(String email, String firstName, String middleName, String lastName, String password, Integer phoneNumber, String address, Set<Role> role) {
//        User user = userRepository.findUserByEmail(email).sream()
//                .map(userDto -> userConverter.convertUserToEntity(userDto))
//                .collect(Collectors.toList());
//        return user;
//    }
}