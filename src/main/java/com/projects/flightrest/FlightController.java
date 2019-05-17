package com.projects.flightrest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FlightController {

    private final FlightRepository flight_repo;
    private final FlightResourceAssembler flight_assembler;

    FlightController(FlightRepository flight_repo, FlightResourceAssembler flight_assembler) {
        this.flight_repo = flight_repo;
        this.flight_assembler = flight_assembler;
    }

    @GetMapping(value = "/voos", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Flight>> allFlights (@RequestParam(value = "origin", defaultValue = "all") String origin,
                                                   @RequestParam(value = "destination", defaultValue = "all") String destination,
                                                   @RequestParam(value = "available_seats", defaultValue = "0") int available_seats){

        List<Flight> flights;
        List<Resource<Flight>> flights_resource;

        if((destination.equals("all")) && (origin.equals("all"))){
            flights = flight_repo.findByAvailableSeats(available_seats);
        }
        else if(destination.equals("all")){
            flights = flight_repo.findByOriginAvailableSeats(origin,available_seats);
        }
        else if(origin.equals("all")){
            flights = flight_repo.findByDestinationAvailableSeats(destination, available_seats);
        }
        else{
            flights = flight_repo.findByOriginDestinationAvailableSeats(origin,destination, available_seats);
        }

        flights_resource = flights.stream()
                .map(flight_assembler::toResource)
                .collect(Collectors.toList());


        return new Resources<>(flights_resource,
                linkTo(methodOn(FlightController.class).allFlights(origin,destination,available_seats)).withSelfRel());
    }

    @GetMapping(value = "/voos/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Flight> oneFlight (@PathVariable Long id){

        Flight flight = flight_repo.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        return flight_assembler.toResource(flight);
    }

    @GetMapping(value = "/companhias/{id}/voos", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Flight>> allFlightsCompany (@PathVariable Long id){

        List<Flight> flights = flight_repo.findFlightsByCompany_Id(id);
        List<Resource<Flight>> flights_resource;

        flights_resource = flights.stream()
                .map(flight_assembler::toResource)
                .collect(Collectors.toList());


        return new Resources<>(flights_resource,
                linkTo(methodOn(FlightController.class).allFlightsCompany(id)).withSelfRel());
    }



    /*@PostMapping(value = "/companhias/{code}/voos", produces = "application/json; charset=UTF-8")
    ResponseEntity<?> newFlight(@RequestBody Flight flight) throws URISyntaxException{

        Resource<Flight> resource =
    }*/
}
