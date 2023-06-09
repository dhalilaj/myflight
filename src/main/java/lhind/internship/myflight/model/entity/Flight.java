package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lhind.internship.myflight.model.enums.AirlineCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

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
    @Enumerated(EnumType.STRING)
    @Column(name = "airline_code") @NotNull
    private AirlineCode airlineCode;
    @Column(name = "flight_number") @NotNull
    private String flightNumber;
    @Column(name = "origin", length = 3) @NotNull
    private String origin;
    @Column(name = "destination", length = 3)@NotNull
    private String destination;
    @Column(name = "flight_date")@NotNull
    @Temporal(value = TemporalType.DATE)
    private Date flightDate;
    @Column(name = "departure_time")@NotNull
    private Time departureTime;
    @Column(name = "aircraft_type")
    private String aircraftType;
    @Column(name = "seats_available")
    private Integer seatsAvailable;

    @ManyToMany(mappedBy = "flights")
    private Set<Booking> bookings;
//    @ManyToMany(mappedBy = "flights")



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
