package lhind.internship.myflight.controller;

import jakarta.validation.Valid;
import lhind.internship.myflight.exception.UserNotFoundException;
import lhind.internship.myflight.model.dto.ResponseMsg;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserRepository userRepository;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return ResponseEntity.badRequest().body(new ResponseMsg("Error: User with this email already exists!"));
        }
        this.userService.createUser(userDto);
        return ResponseEntity.ok(new ResponseMsg("User added successfully!"));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers (){
        return ResponseEntity.ok(userService.findAll());
    }
//
//    @DeleteMapping("/{email}")
//    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email){
//        userService.deleteUserByEmail(email);
//        return ResponseEntity.ok(new ResponseMsg("User deleted"));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ResponseMsg("User deleted"));
    }

    @GetMapping("/{email}")
    public UserDto findByEmail(@PathVariable String email) throws UserNotFoundException {
        return userService.findByEmail(email);
    }

    @PutMapping
    public ResponseEntity<?> updateUser (@RequestBody UserDto userDto) throws UserNotFoundException{
        userService.updateUser(userDto);
        return  ResponseEntity.ok(new ResponseMsg("User updated successfully"));
    }

}
