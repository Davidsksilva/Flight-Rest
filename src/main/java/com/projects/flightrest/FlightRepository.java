package com.projects.flightrest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findFlightsByOriginAndDestiny(String origin, String destiny);

    List<Flight> findFlightsByCompany_Code(int code);

    List<Flight> findFlightsByDestiny(String dest);

    List<Flight> findFlightsByOrigin(String orig);

    Flight findFlightByIdAndCompany_Id(Long id_f, Long id_fc);
}
