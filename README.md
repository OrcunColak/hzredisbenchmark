# Goal
This little project aims to benchmark Redis and Hazelcast when they are used as Spring Cache stores.  
1. Start the related cache servers via the docker compose file
2. Run theSpringBoot application
3. Run the Apache Benchmark tool

# Running cache servers
```
cd docker-compose
docker-compose up
```


# Running the non-reactive application 
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
Redis : ab -n 100000 -c 50 http://localhost:8080/redisfindbyid/1
Hazelcast : ab -n 100000 -c 50 http://localhost:8080/hazelcastfindbyid/1
No Cache : ab -n 100000 -c 50 http://localhost:8080/findbyidnocache/1
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





