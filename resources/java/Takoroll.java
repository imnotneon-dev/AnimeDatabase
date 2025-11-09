import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Takoroll {
    
    private final String DB_URL = "jdbc:mysql://localhost:3306/takoroll"; // ???
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "to be filled";
    private static Connection connection = null;

    public static void main(String[] args) {
        System.out.println("Welcome to Takoroll!");
    }

    public Connection connectToDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to Takoroll database successfully!");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            System.getLogger(Takoroll.class.getName()).log(System.Logger.Level.ERROR, "Database connection error", e);
        }
        return connection;
    }

    public Connection geConnection() {
        return connection;
    }
}