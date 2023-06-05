package lhind.internship.myflight.services;

import lhind.internship.myflight.exception.BookingNotFoundException;
import lhind.internship.myflight.exception.CannotCancelBookingException;
import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.dto.CreateBookingRequest;
import lhind.internship.myflight.model.dto.DisplayBookingsDto;
import lhind.internship.myflight.model.dto.UserDto;

import java.util.List;

public interface BookingService {
    List<DisplayBookingsDto> findBookingByUserId(Long id);

    void cancelBooking (Long id);

    void approve(Long id) throws BookingNotFoundException, CannotCancelBookingException;

    void decline (Long id, String reason) throws BookingNotFoundException, CannotCancelBookingException;

    void createBooking (CreateBookingRequest createBookingRequest);

}
