# Goal
This little project aims to benchmark Redis and Hazelcast when they are used as Spring Cache stores.  
1. Start the related cache server via the docker compose file
2. Change the profile in application.properties file to related cache server type
3. Run SpringBoot application
4. Run the Apache Benchmark tool

# Apache Benchmark Tool
You need to download the Apache Benchmark Tool  
For Windows see https://www.youtube.com/watch?v=hUZso9TpEes
Run the command below.   
- -n specifies number of requests
- -c specifies number of threads

```
ab -n 1000 -c 100 localhost:8080/findbyid/1
```
After executing the ab command **10** times the result of **Total median** is
- Redis : 23 ms  
- Hazelcast 23 ms

So they are roughly equal


