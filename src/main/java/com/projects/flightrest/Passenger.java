package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Passenger {

    // Auto Generated Id for Flight entity
    private @Id @GeneratedValue Long id;

    private String name;
    private int age;
    private String gender;

    @ManyToOne
    private Flight flight;

    public Passenger(){

    }

    public Passenger(String name, int age, String gender, Flight flight){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.flight = flight;

    }
}
