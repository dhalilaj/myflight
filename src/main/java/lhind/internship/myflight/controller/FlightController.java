package lhind.internship.myflight.controller;

import jakarta.validation.Valid;
import lhind.internship.myflight.exception.FlightNotBookedException;
import lhind.internship.myflight.exception.FlightNotFoudException;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.dto.ResponseMsg;
import lhind.internship.myflight.model.dto.UserDto;
import lhind.internship.myflight.model.entity.User;
import lhind.internship.myflight.model.enums.AirlineCode;
import lhind.internship.myflight.services.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    public  FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto){
        this.flightService.createFlight(flightDto);
        return ResponseEntity.ok(new ResponseMsg("Flight added successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) throws FlightNotBookedException {
        flightService.deleteFlight(id);
        return ResponseEntity.ok(new ResponseMsg("Flight deleted!"));

    }

    @PutMapping
    public ResponseEntity<?> updateFlight(@Valid@RequestBody FlightDto flightDto) throws FlightNotBookedException, FlightNotFoudException {
        flightService.updateFlight(flightDto);
        return ResponseEntity.ok("Flight updated successfully!");
    }

    @GetMapping("/custom/{airlineCode}/{origin}/{destination}/{flightDate}")
    public List<FlightDto> customSearch (@PathVariable AirlineCode airlineCode, String origin, String destination, Date flightDate) throws RuntimeException{
        return  flightService.findFlightCustom( airlineCode,  origin,  destination,  flightDate);
    }

    @GetMapping("/travellers/{id}")
    public List<User> findTravelerOfFlight (@PathVariable Long id){
        return flightService.findTravelerOfFlight(id);
    }
}
