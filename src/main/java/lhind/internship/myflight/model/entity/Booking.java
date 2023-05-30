package lhind.internship.myflight.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NotNull
    @Column(name = "status")
    private String status;
    @NotNull
    @Column(name = "booking_date")
    private Date bookingDate;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="bookingUser", referencedColumnName = "id")
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "booking_flight", joinColumns = {
            @JoinColumn(name = "booking_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "flight_id", referencedColumnName = "id")
            }
    )
    private Set<Flight> flights;


}
