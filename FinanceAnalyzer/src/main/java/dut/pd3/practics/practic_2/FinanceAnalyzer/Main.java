package dut.pd3.practics.practic_2.FinanceAnalyzer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        // 1. Читання транзакцій (виклик статичного методу абстрактного класу)
        List<Transaction> transactions = TransactionCSVReader.readTransactionsFromUrl(filePath);

        // 2. Загальний баланс
        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        // 3. Кількість транзакцій за місяць
        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        // 4. 10 найбільших витрат
        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        // 5. Самостійна робота: найбільші і найменші витрати за період
        String startDate = "01-12-2023";
        String endDate = "31-12-2023";
        Optional<Transaction> maxExpense = TransactionAnalyzer.findMaxExpenseInPeriod(transactions, startDate, endDate);
        Optional<Transaction> minExpense = TransactionAnalyzer.findMinExpenseInPeriod(transactions, startDate, endDate);
        TransactionReportGenerator.printPeriodExtremesReport(startDate, endDate, maxExpense, minExpense);

        // 6. Самостійна робота: текстові звіти з візуалізацією '*'
        Map<String, Double> expensesByCategory = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printVisualExpensesReport("Сумарні витрати по категоріях", expensesByCategory);

        Map<String, Double> expensesByMonth = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        TransactionReportGenerator.printVisualExpensesReport("Сумарні витрати по місяцях", expensesByMonth);
    }
}
