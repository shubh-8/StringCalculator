package test.java.com.incubyte;

import main.java.com.incubyte.StringCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private final StringCalculator calc = new StringCalculator();

    @Test
    void emptyStringReturnsZero() {
        assertEquals(0, calc.add(""));
    }

    @Test
    void singleNumber() {
        assertEquals(7, calc.add("7"));
    }

    @Test
    void twoNumbers() {
        assertEquals(6, calc.add("1,5"));
    }

    @Test
    void anyAmountOfNumbers() {
        assertEquals(10, calc.add("1,2,3,4"));
    }

    @Test
    void supportsNewlines() {
        assertEquals(6, calc.add("1\n2,3"));
    }

    @Test
    void supportsCustomDelimiterSimple() {
        assertEquals(3, calc.add("//;\n1;2"));
    }

    @Test
    void supportsCustomDelimiterAnyLength_NoBracketsNeeded() {
        assertEquals(10, calc.add("//--\n2--3--5")); // still within “single custom delimiter” rule
    }

    @Test
    void throwsOnNegatives_listsAll() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> calc.add("1,-2,3,-5"));
        assertEquals("negative numbers not allowed -2,-5", ex.getMessage());
    }
}
