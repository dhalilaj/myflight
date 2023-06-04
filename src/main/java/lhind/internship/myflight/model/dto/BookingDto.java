package lhind.internship.myflight.model.dto;

import lhind.internship.myflight.model.entity.Flight;
import lhind.internship.myflight.model.enums.BookingStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class BookingDto {

    private Long id;
    private BookingStatus status;
    private Date bookingDate;
    private Set<Flight> flights;

}
