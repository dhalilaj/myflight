package lhind.internship.myflight.converter;

import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.entity.Booking;


public class BookingConverter {
    public BookingDto convertBookingToDto(Booking booking) {
        BookingDto convertedBookingDto = new BookingDto();
        convertedBookingDto.setBookingDate(booking.getBookingDate());
        convertedBookingDto.setStatus(booking.getStatus());
        return convertedBookingDto;
    }

    public Booking convertBookingEntity(BookingDto bookingDto) {
        Booking convertedBookingEntity = new Booking();
        convertedBookingEntity.setBookingDate(bookingDto.getBookingDate());
        convertedBookingEntity.setStatus(bookingDto.getStatus());
        return convertedBookingEntity;
    }

}
