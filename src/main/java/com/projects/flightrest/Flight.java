package com.projects.flightrest;

import lombok.Data;

import javax.persistence.*;

// Lombrok notation for auto-generated getters and setters
@Data
// JPA notation to inform that the class is also an entity
@Entity
public class Flight {

    // Auto Generated Id for Flight entity
    private @Id @GeneratedValue Long id;

    private String code;
    private String origin;
    private String destination;
    private String departure_time;
    private String flight_day;
    private int num_seats;
    private float price;
    private int taken_seats;

    // A Flight Company has many flights
    @ManyToOne
    FlightCompany company;

    public Flight(){

    }

    public Flight(String origin, String destination, String departure_time, String flight_day, float price, int num_seats,int taken_seats, FlightCompany company){

        this.origin= origin;
        this.destination= destination;
        this.departure_time= departure_time;
        this.flight_day = flight_day;
        this.price = price;
        this.company = company;
        this.num_seats = num_seats;
        this.taken_seats = taken_seats;

        // Generating a code for the flight mixing origin, destination, the day of the flight and the time of departure
        this.code = this.company.getCode() + origin.substring(0,2) + destination.substring(0,2) + flight_day.substring(0,2).toUpperCase() + departure_time.substring(0,2);
    }
}
