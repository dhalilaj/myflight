package lhind.internship.myflight.services;

import lhind.internship.myflight.model.dto.DisplayUser;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.enums.AirlineCode;

import java.util.Date;
import java.util.List;

public interface FlightService {
    void createFlight (FlightDto flightDto);
    void deleteFlight (Long id);

    void updateFlight (FlightDto flightDto);

    List<FlightDto> findFlightCustom ( AirlineCode airlineCode, String origin, String destination, Date flightDate);

    List<DisplayUser> findTravelerOfFlight (Long id);


}
