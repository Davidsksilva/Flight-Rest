package com.projects.flightrest;

import lombok.Data;

@Data
public class FlightCompanyStatistic {

    private String company_name;
    private Long flight_count;
    private Long company_id;

    FlightCompanyStatistic(String company_name, Long flight_count, Long company_id){
        this.company_name = company_name;
        this.flight_count = flight_count;
        this.company_id = company_id;
    }
}
