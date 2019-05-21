package com.projects.flightrest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FlightCompanyController {

    private final FlightCompanyRepository fc_repo;
    private final FlightRepository flight_repo;

    FlightCompanyController(FlightCompanyRepository fc_repo, FlightRepository flight_repo){

        this.fc_repo = fc_repo;
        this.flight_repo = flight_repo;
    }

    @GetMapping( value = "/companhias", produces = "application/json; charset=UTF-8")
    public List<FlightCompany> allFlightCompanies(){

        List<FlightCompany> companies = fc_repo.findAll();


        return companies;
    }

    // Statistics
    @GetMapping(value = "/companhias/estatistica", produces = "application/json; charset=UTF-8")
    public List<FlightCompanyStatistic> allCompaniesStatistics(){

        List<FlightCompany> companies = fc_repo.findAll();

        List<FlightCompanyStatistic> companies_statistics = new ArrayList<FlightCompanyStatistic>();

        for(int i = 0;i < companies.size(); i++){

            Long flightCount = flight_repo.countCompanyFlights(companies.get(i));
            FlightCompanyStatistic stats = new FlightCompanyStatistic(companies.get(i).getName(),flightCount, companies.get(i).getId());

            companies_statistics.add(stats);
        }

        return companies_statistics;
    }

}
