package com.projects.flightrest;

import lombok.Data;


@Data
public class FlightStatistic {

    private Long flight_count;
    private String flight_destination;
    private String flight_origin;
    private float flight_price;

    FlightStatistic(){

    }

}
