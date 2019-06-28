# [TBA] Flight REST API

Part of the projet Travel Booking App (TBA),a backend to register flight companies, flights and passangers.

Travel Booking App (TBA): constitutes a project to gain familiarity with the tecnology stack [Spring](https://spring.io/) and [React](https://reactjs.org/). Where the user is able to book a travel package including Flight and Hotel room from a web page grown with React, consuming and feeding multiple REST API's, utilizing the [MySQL](https://www.mysql.com/) relational database. Includes a dashboard page using [ECharts](https://echarts.baidu.com/).

Refer to the main [repository](https://github.com/Davidsksilva/travel-booking-app).

## Getting Started

To run te project, follow the next steps.

### Prerequisites

Prerequisites to run the project:

- Gradle 4.10.2;
- Java 11;
- Deploy of the database including in [this](https://github.com/Davidsksilva/travel-booking-app) repo.

### Executing

On the first run, its possible to préload some data. To do this, on the package **com.projects.flighrest** and class **LoadDatabase** remove the comment on the line *@Bean*:

```Java
 //@Bean
    CommandLineRunner initDatabase(PassengerRepository p_repo, FlightCompanyRepository fc_repo, FlightRepository f_repo)
```

And in **application.properties** change the hibernate mode to **create**:

```properties
spring.jpa.hibernate.ddl-auto=create
```

To run the backend:

```bash
git clone https://github.com/Davidsksilva/Travel-Booking-App.git
cd /Flight-Rest/
./gradlew bootRun
```
