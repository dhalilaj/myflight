package lhind.internship.myflight.controller;

import jakarta.validation.Valid;
import lhind.internship.myflight.exception.BookingNotFoundException;
import lhind.internship.myflight.exception.UserNotFoundException;
import lhind.internship.myflight.model.dto.*;
import lhind.internship.myflight.services.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/ofUser/{id}")
    public List<DisplayBookingsDto> findUserBookings(@PathVariable Long id) throws UserNotFoundException {
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
    public ResponseEntity<?> declineCancellation(@PathVariable Long id, @RequestBody ReasonDto reason) throws BookingNotFoundException {
        bookingService.decline(id, reason.getReason());
        return ResponseEntity.ok(new ResponseMsg("Booking Cancellation Declined!"));
    }

    @PostMapping
    public ResponseEntity<?> createBooking (@Valid @RequestBody CreateBookingRequest createBookingRequest)
    {
        bookingService.createBooking(createBookingRequest);
        return ResponseEntity.ok(new ResponseMsg("Booking Created successfully"));
    }


}
