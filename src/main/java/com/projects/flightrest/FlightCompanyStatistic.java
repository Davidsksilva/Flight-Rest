package com.projects.flightrest;

import lombok.Data;

@Data
public class FlightCompanyStatistic {

    private String company_name;
    private Long flight_count;

    FlightCompanyStatistic(String company_name, Long flight_count){
        this.company_name = company_name;
        this.flight_count = flight_count;
    }
}
