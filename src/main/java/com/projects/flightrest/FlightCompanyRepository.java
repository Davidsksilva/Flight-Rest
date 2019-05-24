package com.projects.flightrest;

import org.springframework.data.jpa.repository.JpaRepository;

// Flight Company repository
public interface FlightCompanyRepository extends JpaRepository<FlightCompany, Long> {
}
