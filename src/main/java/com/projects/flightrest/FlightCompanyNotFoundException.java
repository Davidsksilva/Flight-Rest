package com.projects.flightrest;

public class FlightCompanyNotFoundException extends RuntimeException {

    FlightCompanyNotFoundException(Long id) {
        super("Could not find flight company" + id);
    }
}