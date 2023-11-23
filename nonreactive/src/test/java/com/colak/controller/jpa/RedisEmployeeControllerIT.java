package com.colak.controller.jpa;

import com.colak.model.jpa.Employee;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.List;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedisEmployeeControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Container
    @ServiceConnection
    private static final RedisContainer REDIS_CONTAINER =
            new RedisContainer(DockerImageName.parse("redis:latest"))
                    .withExposedPorts(6379);

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
            .withUsername("testUser")
            .withPassword("testSecret")
            .withDatabaseName("testDatabase");

    private static final GenericContainer<?> HAZELCAST = new GenericContainer<>(DockerImageName.parse("hazelcast/hazelcast:5.3.0-slim"));

    @Container
    @ServiceConnection
    private static final MongoDBContainer MONGO = new MongoDBContainer(DockerImageName.parse("mongo"));

    @BeforeAll
    static void beforeAll () {
        List<String> portBindings = new ArrayList<>();
        portBindings.add("5701:5701"); // hostPort:containerPort
        HAZELCAST.setPortBindings(portBindings);
        HAZELCAST.start();
    }
    @Test
    void testRedisFindById() {
        String url = "/redisfindbyid/{userId}";
        Integer userId = 1;
        Employee actual = testRestTemplate.getForObject(url, Employee.class, userId);
        Employee expected = new Employee(1L, "employee1", "lastname1");
        Assertions.assertEquals(expected, actual);
    }

}
