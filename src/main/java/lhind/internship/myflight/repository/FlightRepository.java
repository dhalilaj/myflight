package lhind.internship.myflight.repository;

import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.AirlineCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository <Flight,Long> {
    @Query(value = "select id from flight inner join booking_flight ON flight.id=booking_flight.flight_id where flight.id= :id group by flight.id", nativeQuery = true)
    Optional<Flight> findBookedFlight (Long id);


    @Query(value = "select user.* from user inner join booking on user.id = booking.booking_user inner join user_role on user.id = user_role.user_id inner join booking_flight on booking.id = booking_flight.booking_id where user_role.role_id = 2 and booking_flight.flight_id = :id", nativeQuery = true)
    List<User> findTravelerOfFlight (Long id);

    @Query(value = "SELECT u from User u " +
            "inner join u.bookings b " +
            "inner join u.role r " +
            "inner join b.flights f " +
            "where r.code = 'TRAVELLER' and f.id = :id")
    List<User> findTravelersOfFlight (Long id);


    @Query("SELECT e FROM Flight e WHERE (e.airlineCode = :airlineCode OR :airlineCode IS NULL) and e.origin = :origin and e.destination = :destination and e.flightDate = :flightDate")
    List<Flight> findFlightCustom (AirlineCode airlineCode, String origin, String destination, Date flightDate);


    void deleteById (Long id);

//    List<FlightDto> findAllByOriginAndDestinationAndFAndFlightDateAndAirlineCode(String origin, String destination, Date flightDate, AirlineCode airlineCode);

}
