package com.projects.flightrest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Passenger repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findPassengersByFlight_Id(Long id);

    Passenger findPassengerByIdAndFlight_Id(Long id_p, Long id_f);
}
