# Mortgage Plan
coding exercise, crosskey
## Installation
### run locally
run 
* `mvnw install`
### run in docker container
run
* `mvnw install -Pproduction`
* `docker build -t mortgage-docker .`
* `docker run -ti -p 8090:8080 mortgage-docker`