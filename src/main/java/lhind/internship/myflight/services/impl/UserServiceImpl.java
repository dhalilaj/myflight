package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.EmailAlreadyExistsException;
import lhind.internship.myflight.exception.UserNotFoundException;
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
        return userRepository.findAll().stream().map(user -> userConverter.convertUserToDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        userRepository.delete(user);
    }

    @Override
    public void createUser(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

//        User user = new User();
        User user = userConverter.convertUserToEntity(userDto);

//        user.setUsername(userDto.getUsername());
//        user.setPassword(encoder.encode(userDto.getPassword()));
//        user.setEmail(userDto.getEmail());
//        user.setFirstName(userDto.getFirstName());
//        user.setMiddleName(userDto.getMiddleName());
//        user.setLastName(userDto.getLastName());
//        user.setAddress(userDto.getAddress());
//        user.setPhoneNumber(userDto.getPhoneNumber());

        Set<Role> roles = new HashSet<>(Arrays.asList(roleRepository.findByCode(RoleName.TRAVELLER).get()));

        user.setRole(roles);

        this.userRepository.save(user);
    }

    @Override
    public UserDto findByEmail(String email) throws UserNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException());
        UserDto userDto = userConverter.convertUserToDto(user);
        return userDto;
    }

    public void updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException());

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setMiddleName(userDto.getMiddleName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setRole(userDto.getRole());
        existingUser.setUsername(userDto.getUsername());
        existingUser.setAddress(userDto.getAddress());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(existingUser);
    }
}