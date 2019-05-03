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
    public Resources<Resource<Flight>> allFlights (@RequestParam(value = "origin", defaultValue = "all") String origin, @RequestParam(value = "destiny", defaultValue = "all") String destiny ){

        List<Flight> flights;
        List<Resource<Flight>> flights_resource;
        if((destiny.equals("all")) && (origin.equals("all"))){
            flights = flight_repo.findAll();
        }
        else if(destiny.equals("all")){
            flights = flight_repo.findFlightsByOrigin(origin);
        }
        else if(origin.equals("all")){
            flights = flight_repo.findFlightsByDestiny(destiny);
        }
        else{
            flights = flight_repo.findFlightsByOriginAndDestiny(origin,destiny);
        }

        flights_resource = flights.stream()
                .map(flight_assembler::toResource)
                .collect(Collectors.toList());


        return new Resources<>(flights_resource,
                linkTo(methodOn(FlightController.class).allFlights(origin,destiny)).withSelfRel());
    }

    @GetMapping(value = "/voos/{id}", produces = "application/json; charset=UTF-8")
    public Resource<Flight> oneFlight (@PathVariable Long id){

        Flight flight = flight_repo.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));

        return flight_assembler.toResource(flight);
    }

    @GetMapping(value = "/companhias/{code}/voos", produces = "application/json; charset=UTF-8")
    public Resources<Resource<Flight>> allFlightsCompany (@PathVariable int code){

        List<Flight> flights = flight_repo.findFlightsByCompany_Code(code);
        List<Resource<Flight>> flights_resource;

        flights_resource = flights.stream()
                .map(flight_assembler::toResource)
                .collect(Collectors.toList());


        return new Resources<>(flights_resource,
                linkTo(methodOn(FlightController.class).allFlightsCompany(code)).withSelfRel());
    }

    /*@PostMapping(value = "/companhias/{code}/voos", produces = "application/json; charset=UTF-8")
    ResponseEntity<?> newFlight(@RequestBody Flight flight) throws URISyntaxException{

        Resource<Flight> resource =
    }*/
}
