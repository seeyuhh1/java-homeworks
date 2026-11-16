package dut.pd3.practics.practic_2.FinanceAnalyzer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Генерує гетери, сетери, toString(), equals() та hashCode()
@AllArgsConstructor // Генерує конструктор з усіма полями (date, amount, description)
@NoArgsConstructor // Генерує порожній конструктор
public class Transaction {
    private String date;
    private double amount;
    private String description;
}