package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lhind.internship.myflight.model.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking")

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    @NotNull@Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "decline_reason")
    private String declineReason;

    @NotNull
    @Column(name = "booking_date")
    @Temporal(value = TemporalType.DATE)
    private Date bookingDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="bookingUser", referencedColumnName = "id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "booking_flight", joinColumns = {
            @JoinColumn(name = "booking_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "flight_id", referencedColumnName = "id")
            }
    )
    private Set<Flight> flights;


}
