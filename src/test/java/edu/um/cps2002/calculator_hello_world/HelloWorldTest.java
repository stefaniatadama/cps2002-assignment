package edu.um.cps2002.calculator_hello_world;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class HelloWorldTest {

    HelloWorld helloWorld;

    @Before
    public void setup(){
        helloWorld = new HelloWorld();
    }

    @Test
    public void testSimpleGetMessage(){

//        //Setup
//        HelloWorld helloWorld = new HelloWorld();

        //Exercise
        String message = helloWorld.getMessage();

        //Verify
        assertEquals("Hello, World!", message);

//        //Teardown
//        helloWorld = null;
    }

    @Test
    public void testGetMessage(){

//        //Setup
//        HelloWorld helloWorld = new HelloWorld();

        //Exercise
        String message = helloWorld.getMessage("Luke");

        //Verify
        assertEquals("Hello, Luke!", message);

//        //Teardown
//        helloWorld = null;
    }

    @Test
    public void testGetMessage2(){

        //Exercise
        String message = helloWorld.getMessage2("Luke");

        //Verify
        assertEquals("Hello, Luke!", message);

    }

    @Test
    public void testGetMessage2Null(){

        //Exercise
        String message = helloWorld.getMessage2(null);

        //Verify
        assertEquals("Hello, World!", message);

    }

    @Test
    public void testGetMessage2William(){

        //Exercise
        String message = helloWorld.getMessage2("William");

        //Verify
        assertEquals("Hello, your majesty!", message);

    }

    @Test
    public void testFibonacciBaseCase(){

        //Exercise
        int second = helloWorld.fibonacci(1);

        //Verify
        assertEquals(1, second);
    }

    @Test
    public void testFibonacciRecursion(){

        //Exercise
        int fib10 = helloWorld.fibonacci(10);

        //Verify
        assertEquals(55, fib10);
    }

    @After
    public void teardown(){
        helloWorld = null;
    }
}
