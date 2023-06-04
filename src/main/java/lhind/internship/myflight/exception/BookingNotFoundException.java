package lhind.internship.myflight.exception;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(Long id) {
        super("Booking with id : " + id + " does not exist");
    }
}
