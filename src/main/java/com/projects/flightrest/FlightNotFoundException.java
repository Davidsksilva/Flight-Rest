package com.projects.flightrest;

// Exception when not finding Flight by id
public class FlightNotFoundException extends RuntimeException{

    FlightNotFoundException(Long id){
        super("Could not find flight " + id);
    }
}
