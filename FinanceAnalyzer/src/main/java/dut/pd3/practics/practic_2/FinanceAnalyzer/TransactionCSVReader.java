package dut.pd3.practics.practic_2.FinanceAnalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    // 1. Основний метод: читання з URL та перетворення на транзакції
    public static List<Transaction> readTransactionsFromUrl(String urlString) {
        List<String> lines = readLinesFromUrl(urlString);
        return parseCsvLines(lines);
    }

    // Додатковий метод для локальних файлів (універсальність за завданням 1.2*)
    public static List<Transaction> readTransactionsFromFile(String filePath) {
        List<String> lines = readLinesFromFile(filePath);
        return parseCsvLines(lines);
    }

    // 2. Читання сирих рядків з URL (незалежно від формату даних)
    public static List<String> readLinesFromUrl(String urlString) {
        try (InputStream is = URI.create(urlString).toURL().openStream()) {
            return readLinesFromStream(is);
        } catch (IOException e) {
            System.err.println("Помилка при читанні з URL: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Читання сирих рядків з локального файлу
    public static List<String> readLinesFromFile(String filePath) {
        try (InputStream is = Files.newInputStream(Path.of(filePath))) {
            return readLinesFromStream(is);
        } catch (IOException e) {
            System.err.println("Помилка при читанні файлу: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Універсальне читання з будь-якого InputStream
    private static List<String> readLinesFromStream(InputStream inputStream) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        }
        return lines;
    }

    // 3. Обробка (парсинг) рядків формату CSV у список об'єктів Transaction
    public static List<Transaction> parseCsvLines(List<String> lines) {
        List<Transaction> transactions = new ArrayList<>();
        for (String line : lines) {
            // Пропускаємо рядок-заголовок, якщо він є у CSV
            if (line.startsWith("Дата") || line.startsWith("Date")) {
                continue;
            }
            String[] values = line.split(",");
            if (values.length >= 3) {
                String date = values[0].trim();
                double amount = Double.parseDouble(values[1].trim());
                String description = values[2].trim();
                transactions.add(new Transaction(date, amount, description));
            }
        }
        return transactions;
    }
}
