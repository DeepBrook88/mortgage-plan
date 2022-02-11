# Mortgage Plan
coding exercise for Crosskey trainee

## Installation

### run locally
run 
* `mvnw install`
* `mvnw spring-boot:run`
* accessed at localhost:8080

### run in docker container
run
* `mvnw install -Pproduction`
* `docker build -t mortgage-docker .`
* `docker run -ti -p 8090:8080 mortgage-docker`
* accessed at localhost:8090