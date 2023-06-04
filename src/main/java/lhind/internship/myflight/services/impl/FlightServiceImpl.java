package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.FlightConverter;
import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.FlightNotBookedException;
import lhind.internship.myflight.exception.FlightNotFoudException;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.enums.AirlineCode;
import lhind.internship.myflight.repository.BookingRepository;
import lhind.internship.myflight.repository.FlightRepository;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.FlightService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    private final UserConverter userConverter;

    public FlightServiceImpl(FlightRepository flightRepository, FlightConverter flightConverter, UserRepository userRepository, BookingRepository bookingRepository, UserConverter userConverter) {
        this.flightRepository = flightRepository;
        this.flightConverter = flightConverter;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.userConverter = userConverter;
    }

    @Override
    public void createFlight(FlightDto flightDto) {
        Random random = new Random();
        int randomNumber = random.nextInt(900) + 100;

        Flight flight = new Flight();
        flight.setFlightDate(flightDto.getFlightDate());
        flight.setFlightNumber(flightDto.getAirlineCode() + String.valueOf(randomNumber));
        flight.setAircraftType(flightDto.getAircraftType());
        flight.setAirlineCode(flightDto.getAirlineCode());
        flight.setOrigin(flightDto.getOrigin());
        flight.setDestination(flightDto.getDestination());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setSeatsAvailable(flightDto.getSeatsAvailable());
        flight.setId(flightDto.getId());

        this.flightRepository.save(flight);

    }

    @Override
    public void deleteFlight(Long id) throws FlightNotBookedException {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new FlightNotFoudException(id));
        if (flight.getBookings().size() > 0) {
            throw new FlightNotBookedException(id);
        } else {
            flightRepository.deleteById(id);
        }

    }

    @Override
    public void updateFlight(FlightDto flightDto) throws FlightNotFoudException, FlightNotBookedException {
        Flight flight = flightRepository.findById(flightDto.getId()).orElseThrow(() -> new FlightNotFoudException(flightDto.getId()));

        if (flight.getBookings().size() > 0) {
            Flight booked = flightRepository.findById(flightDto.getId()).orElseThrow(() -> new FlightNotBookedException(flightDto.getId()));
            booked.setDepartureTime(flightDto.getDepartureTime());
            flightRepository.save(booked);
        } else {
            Flight notBooked = flightRepository.findById(flightDto.getId()).orElseThrow(() -> new FlightNotBookedException(flightDto.getId()));
            notBooked.setFlightDate(flightDto.getFlightDate());
            notBooked.setFlightNumber(flightDto.getFlightNumber());
            notBooked.setAircraftType(flightDto.getAircraftType());
            notBooked.setOrigin(flightDto.getOrigin());
            notBooked.setDestination(flightDto.getDestination());
            notBooked.setAirlineCode(flightDto.getAirlineCode());
            notBooked.setDepartureTime(flightDto.getDepartureTime());
            notBooked.setSeatsAvailable(flightDto.getSeatsAvailable());
            flightRepository.save(notBooked);
        }
    }

    @Override
    public List<FlightDto> findFlightCustom(AirlineCode airlineCode, String origin, String destination, Date flightDate) {
        List<FlightDto> customSearch = flightRepository.findFlightCustom(airlineCode, origin, destination, flightDate).stream().map(flight -> flightConverter.convertFlightToDto(flight)).collect(Collectors.toList());
        if (customSearch.isEmpty()) {
            throw new RuntimeException("Flight does not exist");
        } else {
            return customSearch;
        }
    }

    @Override
    public List<UserDto> findTraveller(Long id) {
//        FlightDto flightDto = flightRepository.findById(id).stream().map(flight -> flightConverter.convertFlightToDto(flight)).findAny().orElseThrow(() -> new FlightNotBookedException(id));
//
//        bookingRepository.f
//        userRepository.findById()
        return null;
    }

    @Override
    public List<UserDto> findTravelerOfFlight(Long id) {


//        List<User> userList = flightRepository.findTravelerOfFlight(id);
//        List<UserDto> userDtos = flightRepository.findTravelerOfFlight(id).stream()
//                .map(user -> userConverter.convertUserToDto(user)).collect(Collectors.toList());
//
////        User user= userRepository.findById(flightRepository.findTravelerOfFlight(id)).orElseThrow(() -> new UserIdNotFoundExceptoin(flightRepository.findTravelerOfFlight(id)));
//
////        List<UserDto> userDtos= userRepository.findById(flightRepository.findTravelerOfFlight(id)).stream().map(user -> userConverter.convertUserToDto(user)).collect(Collectors.toList());
//        if (userDtos.isEmpty()){
//            throw new RuntimeException("");
//        }
//        else {return userDtos;}
        return null;
    }

}
