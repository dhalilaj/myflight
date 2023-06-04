package lhind.internship.myflight.exception;

import lhind.internship.myflight.model.dto.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg tripDoesNotExistException() {
        logger.error("User Does Not Exist");
        return new ResponseMsg("User Does Not Exist");
    }

    @ExceptionHandler(FlightNotFoudException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg flightDoesNotExistException() {
        logger.error("Flight Does Not Exist");
        return new ResponseMsg("Flight Does Not Exist");
    }


    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg BookingNotFoundException() {
        logger.error("Booking not found");
        return new ResponseMsg("Booking not found");
    }

    @ExceptionHandler(FlightNotBookedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg FlightNotBookedException() {
        logger.error("This Is Not a Booked Flight");
        return new ResponseMsg("This Is Not a Booked Flight");
    }

    @ExceptionHandler(UserIdNotFoundExceptoin.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg UserIdNotFoundExceptoin() {
        logger.error("User Does Not Exist");
        return new ResponseMsg("User Does Not Exist");
    }

    @ExceptionHandler(CannotCancelBookingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg CannotCancelBookingException() {
        logger.error("Booking Cannot Be Cancelled");
        return new ResponseMsg("Booking Cannot Be Cancelled");
    }

}
