package lhind.internship.myflight.repository;

import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    List<User> findAllByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
