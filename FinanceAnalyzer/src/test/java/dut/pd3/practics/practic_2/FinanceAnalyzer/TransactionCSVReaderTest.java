package dut.pd3.practics.practic_2.FinanceAnalyzer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TransactionCSVReaderTest {

    @Test
    public void testParseCsvLines() {
        List<String> rawLines = Arrays.asList(
                "Дата,Сума,Категорія",
                "05-12-2023,-450,Сільпо",
                "10-12-2023,8000,Зарплата"
        );

        List<Transaction> transactions = TransactionCSVReader.parseCsvLines(rawLines);

        Assertions.assertEquals(2, transactions.size(), "Має бути розпізнано 2 транзакції (заголовок ігнорується)");

        Transaction first = transactions.get(0);
        Assertions.assertEquals("05-12-2023", first.getDate());
        Assertions.assertEquals(-450.0, first.getAmount());
        Assertions.assertEquals("Сільпо", first.getDescription());
    }
}