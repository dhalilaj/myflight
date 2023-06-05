package lhind.internship.myflight.exception;

public class CannotCancelBookingException extends RuntimeException{
    public CannotCancelBookingException(String message) {
        super(message);
    }

}
