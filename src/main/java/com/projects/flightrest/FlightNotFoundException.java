package com.projects.flightrest;

public class FlightNotFoundException extends RuntimeException{

    FlightNotFoundException(Long id){
        super("Could not find flight " + id);
    }
}
