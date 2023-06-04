package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.UserIdNotFoundExceptoin;
import lhind.internship.myflight.exception.UserNotFoundException;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Role;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.RoleName;
import lhind.internship.myflight.repository.FlightRepository;
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
        return userRepository.findAll().stream().map(user -> userConverter.convertUserToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("NotFound"));
        userRepository.delete(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundExceptoin(id));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> findAllByEmail(String email) {
        return userRepository.findAllByEmail(email).stream().map(user -> userConverter.convertUserToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void createUser(UserDto userDto) {


        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setAddress(userDto.getAddress());
        user.setPhoneNumber(userDto.getPhoneNumber());
        Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode(RoleName.TRAVELLER).get()));
        user.setRole(roles);

        this.userRepository.save(user);
    }

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
        UserDto userDto = userConverter.convertUserToDto(user);


//        return userRepository.findByEmail(email).map(userConverter::convertUserToDto).orElseThrow(UserNotFoundException::new);
        return userDto;
    }

    public void updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserIdNotFoundExceptoin(userDto.getId()));

        user.setFirstName(userDto.getFirstName());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setRole(userDto.getRole());
        user.setUsername(userDto.getUsername());
        user.setAddress(userDto.getAddress());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(user);
    }
}