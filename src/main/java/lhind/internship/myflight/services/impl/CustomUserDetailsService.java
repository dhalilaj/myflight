package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(this::toUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private UserDetails toUserDetails(lhind.internship.myflight.model.entity.User user) {
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().stream().map(role -> role.getCode().name()).toArray(String[]::new))
                .build();
    }

}
