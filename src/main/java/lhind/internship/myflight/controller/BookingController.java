package lhind.internship.myflight.controller;

import lhind.internship.myflight.exception.BookingNotFoundException;
import lhind.internship.myflight.exception.UserIdNotFoundExceptoin;
import lhind.internship.myflight.model.dto.BookingDto;
import lhind.internship.myflight.model.dto.ResponseMsg;
import lhind.internship.myflight.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    public final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/ofUser/{id}")
    public List<BookingDto> findUserBookings(@PathVariable Long id) throws UserIdNotFoundExceptoin {
        return bookingService.findBookingByUserId(id);
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> requestCancellation(@PathVariable Long id) throws BookingNotFoundException {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok(new ResponseMsg("Booking Cancellation Request Sent!"));
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveCancellation(@PathVariable Long id) throws BookingNotFoundException {
        bookingService.approve(id);
        return ResponseEntity.ok(new ResponseMsg("Booking Cancellation Approved!"));
    }

    @PutMapping("/decline/{id}")
    public ResponseEntity<?> declineCancellation(@PathVariable Long id, @RequestBody String reasom) throws BookingNotFoundException {
//        String message = msg;
        bookingService.decline(id);
        return ResponseEntity.ok(new ResponseMsg(reasom));

    }


}
