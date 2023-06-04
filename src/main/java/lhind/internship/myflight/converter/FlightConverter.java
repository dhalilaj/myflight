package lhind.internship.myflight.converter;

import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.entity.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightConverter {
    public FlightDto convertFlightToDto(Flight flight) {
        FlightDto convertedFlightDto = new FlightDto();
        convertedFlightDto.setId(flight.getId());
        convertedFlightDto.setFlightDate(flight.getFlightDate());
        convertedFlightDto.setFlightNumber(flight.getFlightNumber());
        convertedFlightDto.setAircraftType(flight.getAircraftType());
        convertedFlightDto.setOrigin(flight.getOrigin());
        convertedFlightDto.setDestination(flight.getDestination());
        convertedFlightDto.setAirlineCode(flight.getAirlineCode());
        convertedFlightDto.setDepartureTime(flight.getDepartureTime());
        convertedFlightDto.setSeatsAvailable(flight.getSeatsAvailable());
        return convertedFlightDto;
    }

    public Flight convertFlightToEntity (FlightDto flightDto) {
        Flight convertedFlight = new Flight();
        convertedFlight.setFlightDate(flightDto.getFlightDate());
        convertedFlight.setFlightNumber(flightDto.getFlightNumber());
        convertedFlight.setAircraftType(flightDto.getAircraftType());
        convertedFlight.setOrigin(flightDto.getOrigin());
        convertedFlight.setDestination(flightDto.getDestination());
        convertedFlight.setAirlineCode(flightDto.getAirlineCode());
        convertedFlight.setDepartureTime(flightDto.getDepartureTime());
        convertedFlight.setSeatsAvailable(flightDto.getSeatsAvailable());
        return convertedFlight;
    }

}