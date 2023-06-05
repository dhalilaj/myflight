package lhind.internship.myflight.repository;

import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Booking;
import lhind.internship.myflight.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

}
