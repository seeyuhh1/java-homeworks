package dut.pd3.practics.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testBasicOperations() throws InvalidInputException {
        Assertions.assertEquals(15.0, calculator.add(10.0, 5.0));
        Assertions.assertEquals(5.0, calculator.subtract(10.0, 5.0));
        Assertions.assertEquals(50.0, calculator.multiply(10.0, 5.0));
        Assertions.assertEquals(2.0, calculator.divide(10.0, 5.0));
        Assertions.assertEquals(4.0, calculator.sqrt(16.0));
    }

    @Test
    void testDivisionByZero() {
        ArithmeticException exception = Assertions.assertThrows(
                ArithmeticException.class,
                () -> calculator.divide(10.0, 0.0)
        );
        Assertions.assertEquals("Ділення на нуль неможливе", exception.getMessage());
    }

    @Test
    void testInvalidStringInputParsing() {
        Assertions.assertThrows(
                NumberFormatException.class,
                () -> Double.parseDouble("abc")
        );
    }

    @Test
    void testCustomInvalidInputException() {
        Assertions.assertThrows(
                InvalidInputException.class,
                () -> calculator.sqrt(-9.0)
        );

        Assertions.assertThrows(
                InvalidInputException.class,
                () -> calculator.add(Double.NaN, 5.0)
        );

        Assertions.assertThrows(
                InvalidInputException.class,
                () -> calculator.multiply(Double.POSITIVE_INFINITY, 2.0)
        );
    }
}