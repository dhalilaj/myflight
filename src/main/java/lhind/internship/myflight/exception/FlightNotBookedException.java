package lhind.internship.myflight.exception;

public class FlightNotBookedException extends RuntimeException{
    public FlightNotBookedException(Long id) {
        super("There is no booked flight with id : " + id );
    }
}
