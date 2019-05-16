package com.projects.flightrest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class FlightResourceAssembler implements ResourceAssembler<Flight, Resource<Flight>>{
    @Override
    public Resource<Flight> toResource(Flight flight){

        return new Resource<>(flight,
                linkTo(methodOn(FlightController.class).oneFlight(flight.getId())).withSelfRel(),
                linkTo(methodOn(FlightController.class).allFlights("all","all",0)).withRel("flights"));
    }
}