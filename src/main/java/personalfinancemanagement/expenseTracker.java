package personalfinancemanagement;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class expenseTracker {
    public JFrame mainFrame;
    private String username;

    public expenseTracker(String username) {
        this.username = username;
        initializeMainFrame();
    }

    private void initializeMainFrame() {
        mainFrame = new JFrame("Expense Tracker");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(550, 700);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setLayout(null);

        JButton addExpenseButton = new JButton("Add Expense");
        addExpenseButton.setBounds(170, 210, 180, 30);
        addExpenseButton.addActionListener(e -> openAddExpenseFrame());

        JButton viewExpenseButton = new JButton("View Expenses");
        viewExpenseButton.setBounds(170, 260, 180, 30);
        viewExpenseButton.addActionListener(e -> displayExpenses());

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 100, 25);
        backButton.addActionListener(e -> {
            new dashboard(username);
            mainFrame.dispose();
        });

        mainFrame.add(addExpenseButton);
        mainFrame.add(viewExpenseButton);
        mainFrame.add(backButton);
        mainFrame.setVisible(true);
    }

    private void openAddExpenseFrame() {
        JFrame addExpenseFrame = new JFrame("Add Expense");
        addExpenseFrame.setSize(550, 700);
        addExpenseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addExpenseFrame.setResizable(false);
        addExpenseFrame.setLocationRelativeTo(null);
        addExpenseFrame.setLayout(null);

        JLabel titleLabel = new JLabel("EXPENSE TRACKER");
        titleLabel.setBounds(180, 50, 200, 30);
        addExpenseFrame.add(titleLabel);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(100, 200, 80, 30);
        addExpenseFrame.add(amountLabel);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(100, 250, 80, 30);
        addExpenseFrame.add(categoryLabel);

        JLabel paymentMethodLabel = new JLabel("Payment Method:");
        paymentMethodLabel.setBounds(100, 300, 120, 30);
        addExpenseFrame.add(paymentMethodLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(240, 200, 210, 30);
        addExpenseFrame.add(amountField);

        JTextField categoryField = new JTextField();
        categoryField.setBounds(240, 250, 210, 30);
        addExpenseFrame.add(categoryField);

        JTextField paymentMethodField = new JTextField();
        paymentMethodField.setBounds(240, 300, 210, 30);
        addExpenseFrame.add(paymentMethodField);

        JButton backButton = new JButton("Back");
        backButton.setBounds(20, 20, 100, 25);
        backButton.addActionListener(e -> {
            addExpenseFrame.dispose();
            mainFrame.setVisible(true);
        });
        addExpenseFrame.add(backButton);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(220, 380, 100, 30);
        saveButton.addActionListener(e -> {
            if (amountField.getText().isEmpty() || categoryField.getText().isEmpty() || paymentMethodField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(addExpenseFrame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(addExpenseFrame, "Are you sure you want to add this expense?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    try {
                        double amount = Double.parseDouble(amountField.getText());
                        String category = categoryField.getText();
                        String paymentMethod = paymentMethodField.getText();
                        DatabaseManager.insertExpenseData(username, amount, category, paymentMethod);
                        addExpenseFrame.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(addExpenseFrame, "Invalid amount format.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        addExpenseFrame.add(saveButton);

        addExpenseFrame.setVisible(true);
    }

    private void displayExpenses() {
        JFrame frame = new JFrame("Expense History");
        JTable table = new JTable();
        DefaultTableModel model = DatabaseManager.viewExpense(username);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(mainFrame);
        frame.setVisible(true);
    }
}
