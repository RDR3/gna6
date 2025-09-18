import java.sql.*;
//comment
//comment
//comment
import java.util.Scanner;

//comment
//comment

public class SqlInjectionDemo {

    private static final String gna6 = "jdbc:sqlite:test.db1";
  
  //comment
  //cpmment
  // JDBC URL for SQLite (uses a file-based database)
  private static final String db_url_url6 = "jdbc:sqlite:test.db";
  private static final String db_url_url66 = "jdbc:sqlite:test.db";
  private static final String db_url_url666 = "jdbc:sqlite:test.db";

    public static void main(String[] args) {
        createUsersTable();
        //comment
        insertDummyUsers();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to search: ");
        String userInput = scanner.nextLine();

        // ⚠️ VULNERABLE CODE - SQL Injection possible here!
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";

        try (Connection conn = DriverManager.getConnection(db_url_url6);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // Create a users table
    private static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "username TEXT NOT NULL UNIQUE," +
                     "password TEXT NOT NULL)";

        try (Connection conn = DriverManager.getConnection(db_url_url6);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Table creation error: " + e.getMessage());
        }
    }

    // Insert some dummy users
    private static void insertDummyUsers() {
        String[] users = {
            "INSERT OR IGNORE INTO users (username, password) VALUES ('admin', 'adminpass')",
            "INSERT OR IGNORE INTO users (username, password) VALUES ('user1', 'pass1')"
        };

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            for (String user : users) {
                stmt.execute(user);
            }
        } catch (SQLException e) {
            System.err.println("Data insertion error: " + e.getMessage());
        }
    }
}
