package com.projects.flightrest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PassengerController {

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final PassengerResourceAssembler passengerResourceAssembler;

    /**
     * Constructor method.
     * @param passengerRepository
     * @param flightRepository
     * @param passengerResourceAssembler
     */
    PassengerController(PassengerRepository passengerRepository, FlightRepository flightRepository, PassengerResourceAssembler passengerResourceAssembler){

        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.passengerResourceAssembler = passengerResourceAssembler;
    }

    /**
     * Endpoint to list Passengers of a Flight.
     * @param idFlight the Flight id.
     * @return
     */
    @GetMapping(value = "/voos/{idFlight}/passageiros", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Passenger>> allFlightPassengers(@PathVariable Long idFlight){

        List<Passenger> passengers = passengerRepository.findPassengersByFlight_Id(idFlight);
        List<Resource<Passenger>> passengers_resource;

        passengers_resource = passengers.stream()
                .map(passengerResourceAssembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(passengers_resource,
                linkTo(methodOn(PassengerController.class).allFlightPassengers(idFlight)).withSelfRel());
    }

    /**
     * Endpoint to a single Passenger of a Flight.
     * @param idFlight the Flight id.
     * @param idPassenger the Passenger id.
     * @return
     */
    @GetMapping(value = "/voos/{idFlight}/passageiros/{idPassenger}", produces = "application/json; charset=UTF-8")
    public Resource<Passenger> oneFlightPassenger(@PathVariable("idFlight") Long idFlight,
                                         @PathVariable("idPassenger") Long idPassenger){

        Passenger passenger = passengerRepository.findPassengerByIdAndFlight_Id(idPassenger,idFlight);

        return passengerResourceAssembler.toResource(passenger);
    }

    /**
     * Endpoint to a single Flight.
     * @param newPassenger the Passenger entity to be included.
     * @param idFlight the Flight id.
     * @return
     * @throws URISyntaxException
     */
    @PostMapping( value = "/voos/{idFlight}", produces = "application/json; charset=UTF-8")
    ResponseEntity<?> newPassenger(@RequestBody Passenger newPassenger, @PathVariable Long idFlight) throws URISyntaxException {
        Flight flight = flightRepository.findById(idFlight)
                .orElseThrow(() -> new FlightNotFoundException(idFlight));

        int available_seats = flight.getNum_seats() - flight.getTaken_seats();

        if(available_seats > 1){
            flight.setTaken_seats(flight.getTaken_seats()+1);
            newPassenger.setFlight(flight);
            flightRepository.save(flight);
            Resource<Passenger> resource = passengerResourceAssembler.toResource(passengerRepository.save(newPassenger));

            return ResponseEntity
                    .created(new URI(resource.getId().expand().getHref()))
                    .body(resource);
        }
        else{
            return ResponseEntity
                    .created(new URI("/voos/" + idFlight))
                    .body(null);
        }


    }

    /**
     * Endpoint to delete a Passenger by id.
     * @param idFlight the Flight id.
     * @param idPassenger the Passenger id.
     * @return
     */
    @DeleteMapping (value = "/voos/{idFlight}/passageiros/{idPassenger}", produces = "application/json; charset=UTF-8")
    ResponseEntity<?> deletePassenger(@PathVariable("idFlight") Long idFlight, @PathVariable("idPassenger") Long idPassenger){
        passengerRepository.deleteById(idFlight);

        return ResponseEntity.noContent().build();

    }



}


