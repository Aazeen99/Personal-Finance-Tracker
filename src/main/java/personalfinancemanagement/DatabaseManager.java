package personalfinancemanagement;

import java.security.NoSuchAlgorithmException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/personalfinancedb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Method to establish a database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    public static boolean verifyLogin(String username, String password) throws NoSuchAlgorithmException {
        String selectPasswordSaltQuery = "SELECT password, salt FROM users WHERE username = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectPasswordSaltQuery)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPasswordFromDatabase = resultSet.getString("password");
                    String salt = resultSet.getString("salt");

                    // Hash the entered password using the same salt
                    String hashedEnteredPassword = userAuthentication.hashPassword(password, salt);

                    // Compare the hashed password with the hashed password stored in the database
                    return hashedEnteredPassword.equals(hashedPasswordFromDatabase);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertUserData(String username, String email, String password, String salt) {
        String insertUserQuery = "INSERT INTO users (username, email, password, salt) VALUES (?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserQuery)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, salt);

            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("User data inserted successfully.");
                return true;
            } else {
                System.out.println("User data insertion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insertIncomeData(String username, String amount, String description) {
        String insertIncomeQuery = "INSERT INTO income (username, amount, description) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertIncomeQuery)) {

            pstmt.setString(1, username);
            pstmt.setString(2, amount);
            pstmt.setString(3, description);

            pstmt.executeUpdate();

            System.out.println("Income entry added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fetchTransactionHistory(String username, DefaultTableModel model) {
        String fetchTransactionQuery = "SELECT amount, description, null AS category FROM income WHERE username = ? UNION ALL SELECT amount, null AS description, category FROM expense WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(fetchTransactionQuery)) {

            stmt.setString(1, username);
            stmt.setString(2, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("description") != null ? "Income" : "Expense";
                    String description = rs.getString("description");
                    String category = rs.getString("category");
                    model.addRow(new Object[]{type, rs.getDouble("amount"), type.equals("Income") ? description : category});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void insertBudgetData(String username, String category, String amount) {
    String insertBudgetQuery = "INSERT INTO budget (username, category, amount) VALUES (?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(insertBudgetQuery)) {

        pstmt.setString(1, username);
        pstmt.setString(2, category);
        pstmt.setString(3, amount);

        pstmt.executeUpdate();

        System.out.println("Budget entry added to the database.");
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public static DefaultTableModel viewBudget(String username) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Category");
        model.addColumn("Set Percentage");

        String fetchBudgetQuery = "SELECT category, amount FROM budget WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(fetchBudgetQuery)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    double amount = rs.getDouble("amount");
                    String category = rs.getString("category");
                    double percentage = (amount / 100.0) * 100.0; // Calculate percentage
                    model.addRow(new Object[]{category, percentage});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
    
    public static void insertExpenseData(String username, double amount, String category, String paymentMethod) {
        String insertExpenseQuery = "INSERT INTO expense (username, amount, category, payment_method) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertExpenseQuery)) {

            pstmt.setString(1, username);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, category);
            pstmt.setString(4, paymentMethod);

            pstmt.executeUpdate();

            System.out.println("Expense entry added to the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DefaultTableModel viewExpense(String username) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Amount");
        model.addColumn("Category");
        model.addColumn("Payment Method");

        String fetchExpenseQuery = "SELECT amount, category, payment_method FROM expense WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(fetchExpenseQuery)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    double amount = rs.getDouble("amount");
                    String category = rs.getString("category");
                    String paymentMethod = rs.getString("payment_method");
                    model.addRow(new Object[]{amount, category, paymentMethod});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
    }
    
    public static boolean insertBill(String username, String billName, String amount, String dueDate) {
    String insertBillQuery = "INSERT INTO bills (username, bill, amount, duedate) VALUES (?, ?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(insertBillQuery)) {

        pstmt.setString(1, username);
        pstmt.setString(2, billName);
        pstmt.setString(3, amount);
        pstmt.setString(4, dueDate);

        int rowsInserted = pstmt.executeUpdate();

        if (rowsInserted > 0) {
            System.out.println("Bill inserted successfully.");
            return true;
        } else {
            System.out.println("Failed to insert bill.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
    
    public static DefaultTableModel viewBills(String username) {
       DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Bill");
        model.addColumn("Amount");
        model.addColumn("Due Date");

        String fetchBillQuery = "SELECT bill, amount, duedate FROM bills WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(fetchBillQuery)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String bill = rs.getString("bill");
                    String amount = rs.getString("amount");
                    String dueDate = rs.getString("duedate");
                    model.addRow(new Object[]{bill, amount, dueDate});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return model;
   }

    public static void fetchIncomeData(String username, DefaultTableModel model) {
    String fetchIncomeQuery = "SELECT amount, description FROM income WHERE username = ?";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(fetchIncomeQuery)) {

        stmt.setString(1, username);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                double amount = rs.getDouble("amount");
                String description = rs.getString("description");
                model.addRow(new Object[]{amount, description});
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
}