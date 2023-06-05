package lhind.internship.myflight.exception;

import lhind.internship.myflight.model.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> userNotFoundException() {
        logger.error("User Does Not Exist");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("User Does Not Exist"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(FlightNotFoudException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> flightDoesNotExistException() {
        logger.error("Flight Does Not Exist");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight Does Not Exist"));
        return ResponseEntity.status(404).body(baseResponse);
    }
    @ExceptionHandler(OriginAndDestinationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse> originAndDestinationException() {
        logger.error("Destination Cannot be the Same as Origin!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Destination Cannot be the Same as Origin!"));
        return ResponseEntity.status(400).body(baseResponse);
    }

    @ExceptionHandler(BookedFlightException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse> bookedFlightException() {
        logger.error("This flight is already booked!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("This flight is already booked!"));
        return ResponseEntity.status(400).body(baseResponse);
    }


    @ExceptionHandler(FlightDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse> flightDateException() {
        logger.error("Flight date Should always be today’s date or a future date!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight date Should always be today’s date or a future date!"));
        return ResponseEntity.status(400).body(baseResponse);
    }

    @ExceptionHandler(SetLengthException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse> setLengthException() {
        logger.error("Origin and Destination values should be at 3 letters length!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Origin and Destination values should be at 3 letters length!"));
        return ResponseEntity.status(400).body(baseResponse);
    }

    @ExceptionHandler(NoFlightAttachedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse> noFlightAttachedException() {
        logger.error("Cannot create a booking without flights!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Cannot create a booking without flights!"));
        return ResponseEntity.status(400).body(baseResponse);
    }


    @ExceptionHandler(DepartureTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse> departureTimeException() {
        logger.error("Departure time should always be in the future!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Departure time should always be in the future!"));
        return ResponseEntity.status(400).body(baseResponse);
    }



    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> bookingNotFoundException() {
        logger.error("Booking not found!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Booking not found!"));
        return ResponseEntity.status(404).body(baseResponse);
    }
    @ExceptionHandler(FlightNotBookedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse>  flightNotBookedException() {
        logger.error("There is no booking for this flight!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("There is no booking for this flight!"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(CannotCancelBookingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse>  cannotCancelBookingException(CannotCancelBookingException ex) {
        logger.error("Booking Cannot Be Cancelled!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of(ex.getMessage()));
        return ResponseEntity.status(400).body(baseResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse>  emailAlreadyExistsException() {
        logger.error("User with this email already exists!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("User with this email already exists!"));
        return ResponseEntity.status(400).body(baseResponse);
    }
    @ExceptionHandler(NoPermissionException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseEntity<BaseResponse>  noPermissionException() {
        logger.error("No permissions to perform this action.");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("No permissions to perform this action."));
        return ResponseEntity.status(403).body(baseResponse);
    }


    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<BaseResponse>  usernameAlreadyExistsException() {
        logger.error("User with this username already exists!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("User with this username already exists!"));
        return ResponseEntity.status(400).body(baseResponse);
    }

}
