package com.projects.flightrest;

import lombok.Data;

// Lombrok notation for auto-generated getters and setters
@Data
// Class to represent statistics of a Flight
public class FlightStatistic {

    private Long flight_count;
    private String flight_destination;
    private String flight_origin;
    private float flight_price;

    FlightStatistic(){

    }

}
