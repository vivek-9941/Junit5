package org.fir.junit5;

import org.apache.catalina.util.ToStringUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

class PerformanceTest {
    void run() {
        int[] unorder = {1, 2, 3, 2, 32, 2466, 66, 243, 23, 2, 24, 24, 24, 42, 4, 24, 24, 24, 24, 24, 2111, 1, 4, 41, 34463, 4};
        Arrays.sort(unorder);
//
    }

    @Test
    void test() {
        assertTimeout(Duration.ofMillis(10), () -> {
            run();
        });
    }

    Calc c;
    @BeforeAll
    static void beforeall(){
        System.out.println("beforeall");
    }
    @BeforeEach//before each test this method is called

    void init() {
        System.out.println("before Each");
        c = new Calc();
    }

    @Test
    void add() {
//        Calc c = new Calc();
        int actual = c.add(1, 2);
        assertEquals(1 + 2, actual);
    }

    @Test
    void subTest() {
        int acutal = c.subtract(1, 2);
        assertEquals(1 - 2, acutal);
    }

    @Test
    void mulTest() {
        int acutal = c.multiply(1, 2);
        assertEquals(2, acutal);
    }

    @Test
    void divTest() {
        int acutal = c.divide(1, 2);
        assertEquals(1/2, acutal, "division is not working properly");//the message
        //will be executed only on failure of test
    }
    @AfterEach
    void closing(){
        System.out.println("closing each");
    }
}
