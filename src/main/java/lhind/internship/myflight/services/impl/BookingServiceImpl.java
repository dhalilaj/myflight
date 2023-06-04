package lhind.internship.myflight.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import lhind.internship.myflight.converter.BookingConverter;
import lhind.internship.myflight.converter.FlightConverter;
import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.*;
import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.dto.CreateBookingRequest;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Booking;
import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.BookingStatus;
import lhind.internship.myflight.repository.BookingRepository;
import lhind.internship.myflight.repository.FlightRepository;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.BookingService;
import lhind.internship.myflight.services.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private UserRepository userRepository;
    private BookingRepository bookingRepository;
    private BookingConverter bookingConverter;
    private UserConverter userConverter;
    private FlightConverter flightConverter;
    private HttpServletRequest request;
    private TokenService tokenService;

    private FlightRepository flightRepository;


    public BookingServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, BookingConverter bookingConverter, UserConverter userConverter, FlightConverter flightConverter, HttpServletRequest request, TokenService tokenService, FlightRepository flightRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.bookingConverter = bookingConverter;
        this.userConverter = userConverter;
        this.flightConverter = flightConverter;
        this.request = request;
        this.tokenService = tokenService;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<BookingDto> findBookingByUserId(Long id) throws UserNotFoundException {

//        UserDto userDto = userRepository.findById(id).map(user -> userConverter.convertUserToDto(user)).orElseThrow(()-> new UserIdNotFoundExceptoin(id));
//                .collect(Collectors.toList());
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
//        UserDto userDto = userConverter.convertUserToDto(user);

        List<BookingDto> bookingDto = bookingRepository.findByUser(user).stream().map(booking -> bookingConverter.convertBookingToDto((booking))).collect(Collectors.toList());
        return bookingDto;
    }

    @Override
    public List<UserDto> findTravelerOfFlight(Long id) {
//        userRepository.findByUsername(bookingRepository.findTravelerOfFlight(id));
        bookingRepository.findTravelerOfFlight(id);

        return null;
    }

    @Override
    public void cancelBooking(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());

        if (booking.getStatus().equals(BookingStatus.BOOKED)) {
            booking.setStatus(BookingStatus.CANCELLATION_REQUESTED);
            bookingRepository.save(booking);
        } else {
            throw new CannotCancelBookingException();
        }

    }

    @Override
    public void approve(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());

        if (booking.getStatus().equals(BookingStatus.CANCELLATION_REQUESTED)) {

            booking.setStatus(BookingStatus.CANCELLATOIN_APPROVED);
            bookingRepository.save(booking);

        } else {
            throw new CannotCancelBookingException();
        }
    }

    @Override
    public void decline(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());

        if (booking.getStatus().equals(BookingStatus.CANCELLATION_REQUESTED)) {

            booking.setStatus(BookingStatus.CANCELLATOIN_DECLINED);
            bookingRepository.save(booking);

        } else {
            throw new CannotCancelBookingException();
        }
    }

    @Override
    public void createBooking(CreateBookingRequest createBookingRequest) {
        Booking booking = new Booking();
        Set<Flight> flights = new HashSet<>();


        if (createBookingRequest.getFlights().length <= 0) {
            throw new NoFlightAttachedException();
        } else {
//            List<Flight> flights = createBookingRequest.getFlights().stream().toList();
            Arrays.stream(createBookingRequest.getFlights()).forEach(flightId -> {
                Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoudException());
                LocalDate localDate = LocalDate.now();
                Date comparedt = (Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
//                LocalDate flightDt = flight.getFlightDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (localDate.equals(flight.getFlightDate()) || flight.getFlightDate().after(comparedt)) {
                    flights.add(flight);
                } else {
                    throw new FlightDateException();
                }
            });
        }
        booking.setFlights(flights);
        booking.setStatus(BookingStatus.BOOKED);
        LocalDate localDate = LocalDate.now();
        booking.setBookingDate(Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = tokenService.extractUsername(jwt);

        booking.setUser(userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException()));

        bookingRepository.save(booking);
    }
}
