package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import lhind.internship.myflight.model.enums.AirlineCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "flight")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "airline_code", nullable = false)@Enumerated
    private AirlineCode airlineCode;
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;
    @Column(name = "origin", nullable = false)
    private String origin;
    @Column(name = "destination", nullable = false)
    private String destination;
    @Column(name = "flight_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date flightDate;
    @Column(name = "departure_time", nullable = false)
    private Time departureTime;
    @Column(name = "aircraft_type")
    private String aircraftType;
    @Column(name = "seats_available")
    private Integer seatsAvailable;



//    Airline Code (Can be one of the following LH, OS, LX, EW) (Mandatory)
//    • Flight Number (Should start with the airline code followed by 3 numbers e.g., LH100)
//    (Mandatory) (Unique for each flight date meaning there could be only one LH100 flight on a specific date
//    however the flight number can recur on a different date)
//    • Origin (Airport three letter code e.g., TIA Tirana International Airport) (Mandatory)
//    • Destination (origin and destination should not be the same) (Mandatory)
//    • Flight Date (Should always be today’s date or a future date) (Mandatory) (Date)
//    • Departure Time (should always be in the future) (Mandatory) (Time)
//    • Aircraft Type (Optional)
//    • Total seats available for each booking class

}
