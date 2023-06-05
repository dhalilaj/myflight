package lhind.internship.myflight.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lhind.internship.myflight.exception.UserNotFoundException;
import lhind.internship.myflight.model.dto.ResponseMsg;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Schema
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        this.userService.createUser(userDto);
        return ResponseEntity.ok(new ResponseMsg("User added successfully!"));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers (){
        return ResponseEntity.ok(userService.findAll());
    }

@PreAuthorize(value = "hasAnyRole('ADMIN')")

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ResponseMsg("User deleted"));
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping("/{email}")
    public UserDto findByEmail(@PathVariable String email) throws UserNotFoundException {
        return userService.findByEmail(email);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping
    public ResponseEntity<?> updateUser (@RequestBody UserDto userDto) throws UserNotFoundException{
        userService.updateUser(userDto);
        return  ResponseEntity.ok(new ResponseMsg("User updated successfully"));
    }

}
