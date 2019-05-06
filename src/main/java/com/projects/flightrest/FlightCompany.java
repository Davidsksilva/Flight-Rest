package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class FlightCompany {

    private @Id @GeneratedValue (strategy = GenerationType.SEQUENCE) Long id;
    private String name;
    private String code;

    public FlightCompany(){

    }

    public FlightCompany(String name, String code){

        this.name = name;
        this.code = code;
    }
}
