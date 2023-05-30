package lhind.internship.myflight.repository;

import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDto findUserByEmail(String email);
}
