package lhind.internship.myflight.controller;

import jakarta.validation.Valid;
import lhind.internship.myflight.exception.BookedFlightException;
import lhind.internship.myflight.exception.FlightNotFoudException;
import lhind.internship.myflight.model.dto.DisplayUser;
import lhind.internship.myflight.model.dto.FlightDto;
import lhind.internship.myflight.model.dto.ResponseMsg;
import lhind.internship.myflight.model.enums.AirlineCode;
import lhind.internship.myflight.services.FlightService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<?> createFlight(@Valid @RequestBody FlightDto flightDto){
        this.flightService.createFlight(flightDto);
        return ResponseEntity.ok(new ResponseMsg("Flight added successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable Long id) throws BookedFlightException {
        flightService.deleteFlight(id);
        return ResponseEntity.ok(new ResponseMsg("Flight deleted!"));

    }

    @PutMapping
    public ResponseEntity<?> updateFlight(@Valid@RequestBody FlightDto flightDto) throws BookedFlightException, FlightNotFoudException {
        flightService.updateFlight(flightDto);
        return ResponseEntity.ok("Flight updated successfully!");
    }

    @GetMapping("/customSearch")
    public List<FlightDto> customSearch (@RequestParam String airlineCode, @RequestParam String origin, @RequestParam String destination, @RequestParam String flightDate) throws RuntimeException, ParseException {
        return  flightService.findFlightCustom(StringUtils.isNotEmpty(airlineCode) ? AirlineCode.valueOf(airlineCode) : null,  origin,  destination,  new SimpleDateFormat("yyyy-MM-dd").parse(flightDate));
    }

    @GetMapping("/travellers/{id}")
    public List<DisplayUser> findTravelerOfFlight (@PathVariable Long id){
        return flightService.findTravelerOfFlight(id);
    }
}
