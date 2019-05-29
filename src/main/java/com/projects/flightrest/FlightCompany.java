package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class FlightCompany {

    private @Id @GeneratedValue Long id;

    private String name;
    private String code;

    public FlightCompany(){

    }

    public FlightCompany(String name, String code){

        this.name = name;
        this.code = code;
    }
}
