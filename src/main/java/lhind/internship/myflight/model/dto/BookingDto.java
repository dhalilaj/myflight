package lhind.internship.myflight.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BookingDto {

    private Long id;
    private String status;
    private Date bookingDate;

}
