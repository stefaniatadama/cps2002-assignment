package um.edu.cps2002;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calc;

    @Before
    public void setup(){
        calc = new Calculator();
    }

    @Test
    public void testAdd(){
        int sum = calc.add(2, 3);
        assertEquals(5, sum);
    }

    @Test
    public void testSubtract(){
        int difference = calc.subtract(2, 3);
        assertEquals(-1, difference);
    }

    @Test
    public void testMultiply(){
        int product = calc.multiply(2, 3);
        assertEquals(6, product);
    }

    @Test
    public void testDivide(){
        int quotient = calc.divide(6, 2);
        assertEquals(3, quotient);
    }

    @Test
    public void testDivideByZero(){
        int quotient = calc.divide(6, 0);
        assertEquals(-999, quotient);
    }

    @Test
    public void testExponentiate(){
        int exp = calc.exponentiate(2, 3);
        assertEquals(8, exp);
    }

    @Test
    public void testExpZeroToZero(){
        int exp = calc.exponentiate(0, 0);
        assertEquals(-999, exp);
    }

    @Test
    public void testChooseBaseCase(){
        int choose = calc.choose(5, 0);
        assertEquals(1, choose);
    }

    @Test
    public void testChooseRecursion(){
        int choose = calc.choose(7, 2);
        assertEquals(21, choose);
    }

    @After
    public void teardown(){
        calc = null;
    }
}
