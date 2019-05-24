package com.projects.flightrest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Flight repository
public interface FlightRepository extends JpaRepository<Flight, Long> {


    List<Flight> findFlightsByCompany_Id(Long code);

    @Query("select f from Flight f where f.origin = ?1 and f.destination = ?2 and (f.num_seats - f.taken_seats) >= ?3")
    List<Flight> findByOriginDestinationAvailableSeats(String origin, String destiny, int available_seats);

    @Query("select f from Flight f where (f.num_seats - f.taken_seats) >= ?1")
    List<Flight> findByAvailableSeats(int available_seats);

    @Query("select f from Flight f where f.origin = ?1 and (f.num_seats - f.taken_seats) >= ?2")
    List<Flight> findByOriginAvailableSeats(String origin,int available_seats);

    @Query("select f from Flight f where f.destination = ?1 and(f.num_seats - f.taken_seats) >= ?2")
    List<Flight> findByDestinationAvailableSeats(String destination,int available_seats);

    @Query("select count(f) from Flight f where f.company =?1")
    Long countCompanyFlights(FlightCompany flight_id);

    @Query("select count(f) from Flight f where f.destination =?1")
    Long countFlightsPerDestination(String destination);

    @Query("select count(f) from Flight f where f.origin =?1")
    Long countFlightsPerOrigin(String origin);

    @Query("select count(f) from Flight f where f.destination =?1 and f.company = ?2")
    Long countFlightsPerDestinationPerCompany(String destination, FlightCompany company);

    @Query("select count(f) from Flight f where f.origin =?1 and f.company = ?2")
    Long countFlightsPerOriginPerCompany(String origin, FlightCompany company);


}

