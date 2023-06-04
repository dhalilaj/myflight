package lhind.internship.myflight.model.dto;

import jakarta.validation.constraints.NotNull;
import lhind.internship.myflight.model.enums.AirlineCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class FlightDto {

    private Long id;
    @NotNull
    private AirlineCode airlineCode;
    @NotNull
    private String flightNumber;
    @Length(min = 3, max = 3)
    @NotNull
    private String origin;
    @Length(min = 3, max = 3)
    @NotNull
    private String destination;
    @NotNull
    private Date flightDate;
    @NotNull
    private Time departureTime;
    private String aircraftType;
    private Integer seatsAvailable;
}
