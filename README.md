# Spring reactive and its performances

Several tests were done to compare Spring reactive against Spring MVC and JDBC. You can find some of the Gatling reports in the 
`/reports` directory. The different reports are in directories named after the input parameters in the following format : 
`[users]-[requests]-[?minDelay?]-[?maxDelay?]-[ramp | atonce]`, for instance `1000-4-100-200-ramp` represents a test with 1000 users 
requesting 4 times with a min delay of 100ms and a max delay of 200ms with a ramp up.    

This branch uses these versions:
- Spring Boot 2.6.1
- Spring Boot starter data R2DBC 2.6.1
    - Spring data R2DBC 1.4.0
    - R2DBC pool 0.8.7
- R2DBC PostgreSQL driver 0.8.10 (with PostgreSQL 12.x)

## Testing the applications

Before launching a Gatling test, you need to launch the related Spring Boot application:
- `./gradlew reactive:webflux:bootrun`
- `./gradlew reactive:r2dbc:bootrun`
- `./gradlew reactive:mongo:bootrun`
- `./gradlew mvc:bootrun` 

Then you can launch a test with the command `./gradlew gatling-test:loadTest -Dsimulation=WebFluxSimulation`. 
The command accepts these parameters:
- `simulation`: the name of the gatling simulation to launch, can be one of `R2dbcSimulation`, `WebFluxSimulation`, `RMongoSimulation`, `MvcSimulation` and `PostgreSimulation`
- `users`: the number of users used in the simulation, default to 1
- `requests`: the number of requests a user will perform, default to 1
- `minDelay`: for simulations testing primarily a web component, the minimum delay in ms that will be applied when retrieving a resource
- `maxDelay`: for simulations testing primarily a web component, the maximum delay in ms that will be applied when retrieving a resource
- `ramp`: a boolean stating whether the simulation uses a ramp-up strategy, default to true; if false use the at-once strategy
- `rampUpTime`: the duration of the ramp-up in ms, default to 20ms 
