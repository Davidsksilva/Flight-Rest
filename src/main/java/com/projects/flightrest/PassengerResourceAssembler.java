package com.projects.flightrest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class PassengerResourceAssembler implements ResourceAssembler<Passenger,Resource<Passenger>> {
    @Override
    public Resource<Passenger> toResource(Passenger passenger){

        return new Resource<>(passenger,
                linkTo(methodOn(PassengerController.class).oneFlightPassenger(passenger.getFlight().getId(),passenger.getId())).withSelfRel(),
                linkTo(methodOn(PassengerController.class).allFlightPassengers(passenger.getFlight().getId())).withRel("flight passengers"));
    }
}
