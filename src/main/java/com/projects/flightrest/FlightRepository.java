package com.projects.flightrest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findFlightsByOriginAndDestination(String origin, String destiny);

    List<Flight> findFlightsByCompany_Id(Long code);

    List<Flight> findFlightsByDestination(String dest);

    List<Flight> findFlightsByOrigin(String orig);

    Flight findFlightByIdAndCompany_Id(Long id_f, Long id_fc);

    @Query("select f from Flight f where f.origin = ?1 and f.destination = ?2 and (f.num_seats - f.taken_seats) >= ?3")
    List<Flight> findByOriginDestinationAvailableSeats(String origin, String destiny, int available_seats);

    @Query("select f from Flight f where (f.num_seats - f.taken_seats) >= ?1")
    List<Flight> findByAvailableSeats(int available_seats);

    @Query("select f from Flight f where f.origin = ?1 and (f.num_seats - f.taken_seats) >= ?2")
    List<Flight> findByOriginAvailableSeats(String origin,int available_seats);

    @Query("select f from Flight f where f.destination = ?1 and(f.num_seats - f.taken_seats) >= ?2")
    List<Flight> findByDestinationAvailableSeats(String destination,int available_seats);
}
