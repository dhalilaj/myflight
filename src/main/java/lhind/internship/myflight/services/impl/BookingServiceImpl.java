package lhind.internship.myflight.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import lhind.internship.myflight.converter.BookingConverter;
import lhind.internship.myflight.exception.*;
import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.dto.CreateBookingRequest;
import lhind.internship.myflight.model.dto.DisplayBookingsDto;
import lhind.internship.myflight.model.entity.Booking;
import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.BookingStatus;
import lhind.internship.myflight.repository.BookingRepository;
import lhind.internship.myflight.repository.FlightRepository;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.BookingService;
import lhind.internship.myflight.services.TokenService;
import org.apache.commons.lang3.StringUtils;
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
    private HttpServletRequest request;
    private TokenService tokenService;

    private FlightRepository flightRepository;


    public BookingServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, BookingConverter bookingConverter, HttpServletRequest request, TokenService tokenService, FlightRepository flightRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.bookingConverter = bookingConverter;
        this.request = request;
        this.tokenService = tokenService;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<DisplayBookingsDto> findBookingByUserId(Long id) throws UserNotFoundException {

        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

        List<DisplayBookingsDto> displayBookingsDtos = bookingRepository.findByUser(user).stream().map(DisplayBookingsDto::new).collect(Collectors.toList());
        return displayBookingsDtos;
    }

    @Override
    public void cancelBooking(Long id) throws BookingNotFoundException, NoPermissionException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());

        String headerAuth = request.getHeader("Authorization");
        String jwt = headerAuth.substring(7, headerAuth.length());
        String username = tokenService.extractUsername(jwt);

        User logedIn = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException());
        User bookingUser = booking.getUser();
        if (bookingUser.equals(logedIn)) {

            if (booking.getStatus().equals(BookingStatus.BOOKED)) {
                booking.setStatus(BookingStatus.CANCELLATION_REQUESTED);
                bookingRepository.save(booking);
            } else {
                throw new CannotCancelBookingException("Booking can not be cancelled because of invalid status: " + booking.getStatus().name());
            }
        } else {
            throw new NoPermissionException();
        }

    }

    @Override
    public void approve(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());

        if (booking.getStatus().equals(BookingStatus.CANCELLATION_REQUESTED)) {

            booking.setStatus(BookingStatus.CANCELLATOIN_APPROVED);
            bookingRepository.save(booking);

        } else {
            throw new CannotCancelBookingException("Booking can not be approved because of invalid status: " + booking.getStatus().name());
        }
    }

    @Override
    public void decline(Long id, String reason) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException());

        if (booking.getStatus().equals(BookingStatus.CANCELLATION_REQUESTED)) {
            if (StringUtils.isEmpty(reason)) {
                throw new CannotCancelBookingException("Reason for declining the cancellation is missing.");
            }
            booking.setStatus(BookingStatus.CANCELLATOIN_DECLINED);
            booking.setDeclineReason(reason);
            bookingRepository.save(booking);

        } else {
            throw new CannotCancelBookingException("Booking cancellation can not be declined because of invalid status: " + booking.getStatus().name());
        }
    }

    @Override
    public void createBooking(CreateBookingRequest createBookingRequest) {
        Booking booking = new Booking();
        Set<Flight> flights = new HashSet<>();


        if (createBookingRequest.getFlights().length <= 0) {
            throw new NoFlightAttachedException();
        } else {
            Arrays.stream(createBookingRequest.getFlights()).forEach(flightId -> {
                Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new FlightNotFoudException());
                LocalDate localDate = LocalDate.now();
                Date comparedt = (Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
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
