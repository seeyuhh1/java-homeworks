import java.sql.SQLException;

public class DatabaseManager {
    public static void main(String[] args) {
        try {
            connectToDatabase();
        } catch (SQLException e) {
            System.out.println("Помилка підключення до бази даних.");
        }
    }

    public static void connectToDatabase() throws SQLException {
        throw new SQLException("Не вдалося підключитися до сервера");
    }
}