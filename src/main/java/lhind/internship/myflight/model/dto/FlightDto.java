package lhind.internship.myflight.model.dto;

import lhind.internship.myflight.model.enums.AirlineCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class FlightDto {

    private Long id;
    private AirlineCode airlineCode;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date flightDate;
    private Time departureTime;
    private String aircraftType;
    private Integer seatsAvailable;
}
