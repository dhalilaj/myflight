package lhind.internship.myflight.exception;

import lhind.internship.myflight.model.dto.BaseResponse;
import lhind.internship.myflight.model.dto.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.awt.print.Book;
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
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> originAndDestinationException() {
        logger.error("Destination Cannot be the Same as Origin!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Destination Cannot be the Same as Origin!"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(BookedFlightException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> bookedFlightException() {
        logger.error("This flight is already booked!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("This flight is already booked!"));
        return ResponseEntity.status(404).body(baseResponse);
    }


    @ExceptionHandler(FlightDateException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> flightDateException() {
        logger.error("Flight date Should always be today’s date or a future date!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Flight date Should always be today’s date or a future date!"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(SetLengthException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> setLengthException() {
        logger.error("Origin and Destination values should be at 3 letters length!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Origin and Destination values should be at 3 letters length!"));
        return ResponseEntity.status(404).body(baseResponse);
    }

    @ExceptionHandler(NoFlightAttachedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> noFlightAttachedException() {
        logger.error("Cannot create a booking without flights!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Cannot create a booking without flights!"));
        return ResponseEntity.status(404).body(baseResponse);
    }


    @ExceptionHandler(DepartureTimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<BaseResponse> departureTimeException() {
        logger.error("Departure time should always be in the future!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("Departure time should always be in the future!"));
        return ResponseEntity.status(404).body(baseResponse);
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
    public ResponseEntity<BaseResponse>  FlightNotBookedException() {
        logger.error("There is no booking for this flight!");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessages(List.of("There is no booking for this flight!"));
        return ResponseEntity.status(404).body(baseResponse);
    }

//    @ExceptionHandler(UserIdNotFoundExceptoin.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ResponseBody
//    public ResponseMsg UserIdNotFoundExceptoin() {
//        logger.error("User Does Not Exist");
//        return new ResponseMsg("User Does Not Exist");
//    }

    @ExceptionHandler(CannotCancelBookingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg CannotCancelBookingException() {
        logger.error("Booking Cannot Be Cancelled");
        return new ResponseMsg("Booking Cannot Be Cancelled");
    }

}
