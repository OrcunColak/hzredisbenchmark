# Goal
This little project aims to benchmark Redis and Hazelcast when they are used as Spring Cache stores.  
1. Start the related cache server via the docker compose file
2. Change the profile in application.properties file to related cache server type
3. Run SpringBoot application
4. Run the Apache Benchmark tool

# Running the non-reactive application 
Change the application.properties to set cache type as Redis or Hazelcast 
```
cd nonreactive
mvn clean spring-boot:run
```
# Docker Environment
CPUs : 8  
Total Memory: 2.847GiB

# Apache Benchmark Tool
You need to download the Apache Benchmark Tool  
For Windows see https://www.youtube.com/watch?v=hUZso9TpEes
Run the command below.   
- -n specifies number of requests
- -c specifies number of threads. 
```
ab -n 100000 -c 50 http://localhost:8080/findbyid/1
```
You can also visit http://localhost:8080/actuator/metrics/cache.gets to see cache statistics

# Results for the non-reactive application
| Cache | Total Median (ms) | Peak live threads on Spring Server |
|-|-------------------|------------------------------------|
| Redis | 12                | 91                                 |
| Hazelcast| 12                | 105                                |
| Hazelcast with TPC enabled| 12                | 106                                |

So they are roughly equal

# Results for the reactive application
| Cache | Total Median (ms) | Peak live threads on Spring Server |
|-|-------------------|------------------------------------|
| Redis | 12                | 73                                 |





