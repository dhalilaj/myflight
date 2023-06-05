package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.FlightConverter;
import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.*;
import lhind.internship.myflight.model.dto.DisplayUser;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.enums.AirlineCode;
import lhind.internship.myflight.repository.FlightRepository;
import lhind.internship.myflight.services.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;



    public FlightServiceImpl(FlightRepository flightRepository, FlightConverter flightConverter) {
        this.flightRepository = flightRepository;
        this.flightConverter = flightConverter;
    }

    @Override
    public void createFlight(FlightDto flightDto) {
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;

        Flight flight = new Flight();
        flight.setFlightNumber(flightDto.getAirlineCode() + String.valueOf(randomNumber));

        flight.setId(flightDto.getId());
        flight.setAircraftType(flightDto.getAircraftType());
        flight.setAirlineCode(flightDto.getAirlineCode());

        if (flightDto.getOrigin().equals(flightDto.getDestination())) {
            throw new OriginAndDestinationException();
        } else {
            if (flightDto.getOrigin().length() != 3) {
                throw new SetLengthException();
            } else {
                flight.setOrigin(flightDto.getOrigin());
            }
            if (flightDto.getDestination().length() != 3) {
                throw new SetLengthException();
            } else {
                flight.setDestination(flightDto.getDestination());
            }
        }

        LocalDate localDate = LocalDate.now();
        LocalDate flightDt = flightDto.getFlightDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (localDate.equals(flightDt) || flightDt.isAfter(localDate)) {
            flight.setFlightDate(flightDto.getFlightDate());
        } else {
            throw new FlightDateException();
        }

        LocalTime currentTime = LocalTime.now();
        LocalTime timeToCompare = flightDto.getDepartureTime().toLocalTime();

        if (timeToCompare.isAfter(currentTime)) {
            flight.setDepartureTime(flightDto.getDepartureTime());
        } else {
            throw new DepartureTimeException();
        }
        flight.setSeatsAvailable(flightDto.getSeatsAvailable());

        this.flightRepository.save(flight);

    }

    @Override
    public void deleteFlight(Long id) throws BookedFlightException , FlightNotFoudException {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoudException());
        if (flight.getBookings().size() > 0) {
            throw new BookedFlightException();
        } else {
            flightRepository.deleteById(id);
        }

    }

    @Override
    public void updateFlight(FlightDto flightDto) throws FlightNotFoudException, BookedFlightException {
        Flight flight = flightRepository.findById(flightDto.getId()).orElseThrow(() -> new FlightNotFoudException());

        if (flight.getBookings().size() > 0) {
            flight.setDepartureTime(flightDto.getDepartureTime());
            flightRepository.save(flight);
        } else {

            flight.setFlightDate(flightDto.getFlightDate());
            flight.setFlightNumber(flightDto.getFlightNumber());
            flight.setAircraftType(flightDto.getAircraftType());
            flight.setOrigin(flightDto.getOrigin());
            flight.setDestination(flightDto.getDestination());
            flight.setAirlineCode(flightDto.getAirlineCode());
            flight.setDepartureTime(flightDto.getDepartureTime());
            flight.setSeatsAvailable(flightDto.getSeatsAvailable());

            flightRepository.save(flight);
        }
    }

    @Override
    public List<FlightDto> findFlightCustom(AirlineCode airlineCode, String origin, String destination, Date flightDate) {
        List<FlightDto> customSearch = flightRepository.findFlightCustom(airlineCode, origin, destination, flightDate).stream().map(flight -> flightConverter.convertFlightToDto(flight)).collect(Collectors.toList());
        if (customSearch.isEmpty()) {
            throw new FlightNotFoudException();
        } else {
            return customSearch;
        }
    }

    @Override
    public List<DisplayUser> findTravelerOfFlight(Long id) {

        return flightRepository.findTravelersOfFlight(id).stream().map(DisplayUser::new).collect(Collectors.toList());

    }

}
