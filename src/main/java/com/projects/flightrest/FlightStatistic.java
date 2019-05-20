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

    FlightStatistic(Long flight_count, String flight_destination, String flight_origin, float flight_price){
        this.flight_count= flight_count;
        this.flight_destination = flight_destination;
        this.flight_origin = flight_origin;
    }


}
