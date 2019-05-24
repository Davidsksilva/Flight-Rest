package com.projects.flightrest;

// Exception when not finding Flight Company by id
public class FlightCompanyNotFoundException extends RuntimeException {

    FlightCompanyNotFoundException(Long id) {
        super("Could not find flight company" + id);
    }
}