package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;

// Lombrok notation for auto-generated getters and setters
@Data
// JPA notation to inform that the class is also an entity
@Entity
public class FlightCompany {

    // Auto Generated Id for Flight Company entity
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
