package dut.pd3.practics.calculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator();

        try {
            System.out.print("Введіть перше число: ");
            double num1 = Double.parseDouble(scanner.nextLine());

            System.out.print("Введіть операцію (+, -, *, /, sqrt): ");
            String operation = scanner.nextLine().trim();

            double result;

            if (operation.equals("sqrt")) {
                result = calculator.sqrt(num1);
                System.out.println("Результат: " + result);
            } else {
                System.out.print("Введіть друге число: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                switch (operation) {
                    case "+":
                        result = calculator.add(num1, num2);
                        break;
                    case "-":
                        result = calculator.subtract(num1, num2);
                        break;
                    case "*":
                        result = calculator.multiply(num1, num2);
                        break;
                    case "/":
                        result = calculator.divide(num1, num2);
                        break;
                    default:
                        throw new InvalidInputException("Невідома операція: " + operation);
                }
                System.out.println("Результат: " + result);
            }

        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Помилка вводу: потрібно ввести коректне число.");
        } catch (ArithmeticException e) {
            System.out.println("Арифметична помилка: " + e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println("Помилка даних: " + e.getMessage());
        } finally {
            System.out.println("Завершення обробки запиту.");
            scanner.close();
        }
    }
}