package lhind.internship.myflight.model.dto;

import lhind.internship.myflight.model.entity.Booking;
import lhind.internship.myflight.model.enums.BookingStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
@NoArgsConstructor
public class DisplayBookingsDto {
    private Long id;
    private BookingStatus status;
    private Date bookingDate;

    public DisplayBookingsDto(Booking booking){
        this.setId(booking.getId());
        this.setStatus(booking.getStatus());
        this.setBookingDate(booking.getBookingDate());
    }
}
