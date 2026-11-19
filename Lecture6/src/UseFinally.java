import java.sql.SQLException;

public class UseFinally {
    public static void main(String[] args) {
        try {
            System.out.println("Відкриття з'єднання з базою даних...");
            throw new SQLException("Помилка запиту до БД");
        } catch (SQLException e) {
            System.out.println("Помилка при доступі до бази даних.");
        } finally {
            System.out.println("З'єднання з базою даних закрито.");
        }
    }
}