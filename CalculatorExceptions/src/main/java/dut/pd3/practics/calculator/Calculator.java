package dut.pd3.practics.calculator;

public class Calculator {

    private void validateInputs(double a, double b) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isInfinite(a) || Double.isInfinite(b)) {
            throw new InvalidInputException("Вхідні числа не можуть бути NaN або нескінченністю");
        }
    }

    public double add(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validateInputs(a, b);
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе");
        }
        return a / b;
    }

    public double sqrt(double a) throws InvalidInputException {
        if (Double.isNaN(a) || Double.isInfinite(a)) {
            throw new InvalidInputException("Вхідне число не може бути NaN або нескінченністю");
        }
        if (a < 0) {
            throw new InvalidInputException("Неможливо обчислити квадратний корінь з від'ємного числа: " + a);
        }
        return Math.sqrt(a);
    }
}