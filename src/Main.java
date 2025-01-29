import java.sql.*;
import java.util.Scanner;

public class Main {
    private static String dbUrl = "jdbc:postgresql://localhost:2007/postgres";
    private static String dbUser = "postgres";
    private static String dbPassword = "7";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nChoose an action:");
                System.out.println("1. Change database connection");
                System.out.println("2. View all subjects");
                System.out.println("3. View all users");
                System.out.println("4. Add a subject");
                System.out.println("5. Delete a subject");
                System.out.println("6. Add a user");
                System.out.println("7. Delete a user");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> changeDatabaseConnection(scanner);
                    case 2 -> withConnection(Main::viewAllSubjects);
                    case 3 -> withConnection(Main::viewAllUsers);
                    case 4 -> withConnection(conn -> addSubject(conn, scanner));
                    case 5 -> withConnection(conn -> deleteSubject(conn, scanner));
                    case 6 -> withConnection(conn -> addUser(conn, scanner));
                    case 7 -> withConnection(conn -> deleteUser(conn, scanner));
                    case 8 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
    }

    private static void changeDatabaseConnection(Scanner scanner) {
        System.out.print("Enter new database URL: ");
        dbUrl = scanner.nextLine();
        System.out.print("Enter new database username: ");
        dbUser = scanner.nextLine();
        System.out.print("Enter new database password: ");
        dbPassword = scanner.nextLine();
        System.out.println("Database connection settings updated.");
    }

    private static void withConnection(DatabaseOperation operation) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            operation.execute(connection);
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void viewAllSubjects(Connection connection) throws SQLException {
        String query = "SELECT * FROM Subject";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Subjects:");
            while (resultSet.next()) {
                System.out.printf("ID: %d, Name: %s, Description: %s, Credits: %d, Active: %b%n",
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("credits"),
                        resultSet.getBoolean("active"));
            }
        }
    }

    private static void viewAllUsers(Connection connection) throws SQLException {
        String query = "SELECT * FROM \"user\"";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("Users:");
            while (resultSet.next()) {
                System.out.printf("ID: %d, Username: %s, Email: %s, Role: %s, Created At: %s%n",
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("role"),
                        resultSet.getTimestamp("created_at"));
            }
        }
    }

    private static void addSubject(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter subject name: ");
        String name = scanner.nextLine();
        System.out.print("Enter subject description: ");
        String description = scanner.nextLine();
        System.out.print("Enter subject credits: ");
        int credits = scanner.nextInt();
        System.out.print("Is the subject active (true/false): ");
        boolean active = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline

        String query = "INSERT INTO Subject (name, description, credits, active) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, credits);
            preparedStatement.setBoolean(4, active);
            preparedStatement.executeUpdate();
            System.out.println("Subject added successfully.");
        }
    }

    private static void deleteSubject(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter subject ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String query = "DELETE FROM Subject WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Subject deleted successfully.");
            } else {
                System.out.println("No subject found with the given ID.");
            }
        }
    }

    private static void addUser(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter role: ");
        String role = scanner.nextLine();

        String query = "INSERT INTO \"user\" (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, role);
            preparedStatement.executeUpdate();
            System.out.println("User added successfully.");
        }
    }

    private static void deleteUser(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        String query = "DELETE FROM \"user\" WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with the given username.");
            }
        }
    }

    @FunctionalInterface
    interface DatabaseOperation {
        void execute(Connection connection) throws SQLException;
    }
}
