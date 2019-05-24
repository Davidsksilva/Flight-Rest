package com.projects.flightrest;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
@Slf4j
// Class used to preload data into database
public class LoadDatabase {

    private static int getRandomIntegerBetweenRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    // Uncomment next line to enable preloading
    //@Bean
    CommandLineRunner initDatabase(PassengerRepository p_repo, FlightCompanyRepository fc_repo, FlightRepository f_repo) {
        return args -> {

            // Variables
            List<String> uf_list = Arrays.asList("AC", "AL", "AP", "AM", "BA", "CE",
                    "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RR", "RO",
                    "RJ", "RN", "RS", "SC", "SP", "SE", "TO");

            List<String> flight_companies_names = Arrays.asList("Azul Brazilian Airlines", "LATAM Airlines Group SA", "Avianca Brazil",
                    "Gol Transportes Aéreos", "Passaredo Linhas Aereas");

            List<String> flight_companies_codes = Arrays.asList("AD", "LA", "O6", "G3", "2Z");

            List<String> gender_list = Arrays.asList("Male", "Female", "Other");

            List<String> completed_routes = new ArrayList<>();

            Faker faker = new Faker(new Locale("pt"));

            int num_ufs = uf_list.size();

            // Flight Companies loop
            for (int i = 0; i < flight_companies_names.size(); i++) {

                // Random UF index
                int uf_index = getRandomIntegerBetweenRange(0, 25);

                // Generating Random entry values for Hotel entity
                int stars = getRandomIntegerBetweenRange(1, 5);
                String uf = uf_list.get(uf_index);
                String name = faker.company().name();

                // Create Flight Company entity
                FlightCompany company = new FlightCompany(flight_companies_names.get(i), flight_companies_codes.get(i));

                fc_repo.save(company);

                //Generating Random entry values for Bedroom entities
                int num_flights = getRandomIntegerBetweenRange(1, 20);

                // Flight loop
                for (int j = 0; j < num_flights; j++) {

                    int num_seats = getRandomIntegerBetweenRange(1, 25);
                    int price = getRandomIntegerBetweenRange(100, 200) * 10;

                    int num_taken_seats = getRandomIntegerBetweenRange(1, num_seats);
                    // Destinations loop
                    for (int w = 0; w < num_ufs;w++){

                        String orig = uf_list.get(w);

                        int dest_index = getRandomIntegerBetweenRange(0, num_ufs-1);

                        while (dest_index == w) {
                            dest_index = getRandomIntegerBetweenRange(0, num_ufs-1);
                        }
                        String dest = uf_list.get(dest_index);

                        int time = getRandomIntegerBetweenRange(5, 23);
                        String departure_time = time + ":00";

                        Flight flight = new Flight(orig, dest, departure_time, "Monday", price, num_seats, num_taken_seats, company);
                        Flight flight_back = new Flight(dest, orig, departure_time, "Friday", price, num_seats, num_taken_seats, company);

                        f_repo.save(flight);
                        f_repo.save(flight_back);

                        completed_routes.add(orig);

                        // Passengers loop
                        for (int k = 1; k <= num_taken_seats; k++) {

                            String passenger_name = faker.name().fullName();
                            int age = getRandomIntegerBetweenRange(20, 70);
                            int gender_index = getRandomIntegerBetweenRange(0, 2);
                            String gender = gender_list.get(gender_index);

                            Passenger passenger = new Passenger(passenger_name, age, gender, flight);
                            Passenger passenger_back = new Passenger(passenger_name, age, gender, flight_back);

                            p_repo.save(passenger);
                            p_repo.save(passenger_back);
                        }
                    }
                }
            }
        };
    }
}
