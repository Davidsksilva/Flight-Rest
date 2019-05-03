package com.projects.flightrest;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class FlightCompanyController {

    private final FlightCompanyRepository fc_repo;

    FlightCompanyController(FlightCompanyRepository fc_repo){

        this.fc_repo = fc_repo;
    }

    @GetMapping( value = "/companhias", produces = "application/json; charset=UTF-8")
    public List<FlightCompany> allFlightCompanies(){

        List<FlightCompany> companies = fc_repo.findAll();


        return companies;
    }
}
