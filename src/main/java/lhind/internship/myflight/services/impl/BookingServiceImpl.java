package lhind.internship.myflight.services.impl;

import lhind.internship.myflight.converter.BookingConverter;
import lhind.internship.myflight.converter.UserConverter;
import lhind.internship.myflight.exception.BookingNotFoundException;
import lhind.internship.myflight.exception.CannotCancelBookingException;
import lhind.internship.myflight.exception.UserIdNotFoundExceptoin;
import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.Booking;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.BookingStatus;
import lhind.internship.myflight.repository.BookingRepository;
import lhind.internship.myflight.repository.UserRepository;
import lhind.internship.myflight.services.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    public UserRepository userRepository;
    public BookingRepository bookingRepository;
    public BookingConverter bookingConverter;
    public UserConverter userConverter;

    public BookingServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, BookingConverter bookingConverter, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.bookingConverter = bookingConverter;
        this.userConverter = userConverter;
    }

    @Override
    public List<BookingDto> findBookingByUserId(Long id) throws UserIdNotFoundExceptoin {

//        UserDto userDto = userRepository.findById(id).map(user -> userConverter.convertUserToDto(user)).orElseThrow(()-> new UserIdNotFoundExceptoin(id));
//                .collect(Collectors.toList());
        User user = userRepository.findById(id).orElseThrow(() -> new UserIdNotFoundExceptoin(id));
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
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));

        if (booking.getStatus().equals(BookingStatus.BOOKED)) {
            booking.setStatus(BookingStatus.CANCELLATION_REQUESTED);
            bookingRepository.save(booking);
        } else {
            throw new CannotCancelBookingException(id);
        }

    }

    @Override
    public void approve(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));

        if (booking.getStatus().equals(BookingStatus.CANCELLATION_REQUESTED)) {

                booking.setStatus(BookingStatus.CANCELLATOIN_APPROVED);
                bookingRepository.save(booking);

        } else {
            throw new CannotCancelBookingException(id);
        }
    }

    @Override
    public void decline(Long id) throws BookingNotFoundException {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));

        if (booking.getStatus().equals(BookingStatus.CANCELLATION_REQUESTED)) {

            booking.setStatus(BookingStatus.CANCELLATOIN_DECLINED);
            bookingRepository.save(booking);

        } else {
            throw new CannotCancelBookingException(id);
        }
    }
}
