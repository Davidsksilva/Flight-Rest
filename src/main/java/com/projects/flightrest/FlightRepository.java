package com.projects.flightrest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findFlightsByOriginAndDestination(String origin, String destiny);

    List<Flight> findFlightsByCompany_Id(Long code);

    List<Flight> findFlightsByDestination(String dest);

    List<Flight> findFlightsByOrigin(String orig);

    Flight findFlightByIdAndCompany_Id(Long id_f, Long id_fc);
}
