package dut.pd3.practics.practic_2.FinanceAnalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction transaction1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction transaction2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction transaction3 = new Transaction("03-01-2023", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);

        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction transaction1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction transaction2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction transaction3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2, transaction3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb, "Кількість транзакцій за лютий неправильна");
        Assertions.assertEquals(1, countMar, "Кількість транзакцій за березень неправильна");
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2024", 5000.0, "Зарплата"), // дохід, має ігноруватися
                new Transaction("02-01-2024", -100.0, "Кава"),
                new Transaction("03-01-2024", -500.0, "Продукти"),
                new Transaction("04-01-2024", -3000.0, "Оренда"),
                new Transaction("05-01-2024", -200.0, "Аптека"),
                new Transaction("06-01-2024", -1500.0, "Одяг"),
                new Transaction("07-01-2024", -50.0, "Проїзд"),
                new Transaction("08-01-2024", -800.0, "Бензин"),
                new Transaction("09-01-2024", -400.0, "Кіно"),
                new Transaction("10-01-2024", -2500.0, "Ремонт"),
                new Transaction("11-01-2024", -600.0, "Подарунки"),
                new Transaction("12-01-2024", -1200.0, "Техніка")
        );

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(10, topExpenses.size(), "Має повертатися рівно 10 найбільших витрат");
        Assertions.assertEquals(-3000.0, topExpenses.get(0).getAmount(), "Першою має бути найбільша витрата (-3000.0)");
        Assertions.assertEquals(-100.0, topExpenses.get(9).getAmount(), "Десятою має бути витрата -100.0 (а -50.0 має відсіятися)");
    }
}