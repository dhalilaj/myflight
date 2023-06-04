package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.FlightConverter;
import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.*;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.AirlineCode;
import lhind.internship.myflight.repository.BookingRepository;
import lhind.internship.myflight.repository.FlightRepository;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.FlightService;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

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
    public List<User> findTravelerOfFlight(Long id) {

        List<User> userList = flightRepository.findTravelerOfFlight(id);
//        List<UserDto> userDtos = flightRepository.findTravelerOfFlight(id).stream()
//                .map(userConverter::convertUserToDto).collect(Collectors.toList());

//        User user= userRepository.findById(flightRepository.findTravelerOfFlight(id)).orElseThrow(() -> new UserIdNotFoundExceptoin(flightRepository.findTravelerOfFlight(id)));

//        List<UserDto> userDtos= userRepository.findById(flightRepository.findTravelerOfFlight(id)).stream().map(user -> userConverter.convertUserToDto(user)).collect(Collectors.toList());
        if (userList.isEmpty()) {
            throw new RuntimeException("No Users have Booked this Flight");
        } else {
            return userList;
        }
//        return null;
    }

}
