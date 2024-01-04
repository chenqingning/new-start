package com.ratelimiting;

import com.eureka.EurekaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@SpringBootTest
@ComponentScan("com.*")
class RateLimitingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    EurekaTest eurekaTest;

    @Test
    void testEurreka() {
        eurekaTest.getInstance2();
    }

}
