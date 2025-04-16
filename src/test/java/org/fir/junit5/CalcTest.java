package org.fir.junit5;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
class CalcTest {

    Calc c = new Calc();

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
        assertEquals(1, acutal, "division is not working properly");//the message
        //will be executed only on failure of test
    }

    @Test
    void arrtest() {
//        try{
//
//        int []expectedarr = {1,2,4,5,5};
//            System.out.println(actualarr[0]);
//        }
//        catch(Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
        //now to check if the proper exception is throw
        int[] actualarr = {};
        //pass executable i.e function as second param
        assertThrows(IndexOutOfBoundsException.class, () -> {
            System.out.println(actualarr[0]);
        });
        //this pass as it thorws indexoutofboundexception


    }
}