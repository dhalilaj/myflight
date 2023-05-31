package lhind.internship.myflight.model.dto;

import lhind.internship.myflight.model.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingDto {

    private Long id;
    private BookingStatus status;
    private Date bookingDate;

}
