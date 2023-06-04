package lhind.internship.myflight.repository;

import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Booking;
import lhind.internship.myflight.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUser(User user);

    @Query(value = "Select user.username from user inner join user_role on user.id= user_role.user_id inner join booking on booking.booking_user = user.id inner join booking_flight\n" + "on booking.id=booking_flight.booking_id where booking_flight.flight_id=:id and user_role.role_id=2", nativeQuery = true)
    public List<UserDto> findTravelerOfFlight(Long id);

//    Select distinct booking.booking_user user_id, booking.id booking_id, booking_flight.flight_id
//    from booking
//    inner join user_role on booking.booking_user= user_role.user_id
//    inner join booking_flight on booking.id=booking_flight.booking_id
//    where user_role.role_id=2
//    and booking_flight.flight_id=1;

}
