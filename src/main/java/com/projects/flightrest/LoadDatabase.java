package com.projects.flightrest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(PassengerRepository c_repo, FlightCompanyRepository ca_repo, FlightRepository v_repo){
        return args ->{
            FlightCompany companhia = new FlightCompany("Azul",10);
            Flight flight = new Flight("PB","SP","15:00","Friday",500,100,companhia);
            Passenger passenger = new Passenger("David",21,"Male", flight);
            log.info("Preloading " + ca_repo.save(companhia));
            log.info("Preloading " + v_repo.save(flight));
            log.info("Preloading " + c_repo.save(passenger));
        };
    }
}
