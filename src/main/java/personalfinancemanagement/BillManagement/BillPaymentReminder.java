package personalfinancemanagement.BillManagement;

import personalfinancemanagement.Database.DatabaseManager;
import javax.swing.*;
import java.awt.*;

public class BillPaymentReminder {
    JFrame billPaymentFrame;
    JLabel titleLabel, billNameLabel, amountLabel, dueDateLabel;
    JTextField billNameField, amountField, dueDateField;
    JButton submitButton, backButton;
    private final String username;
    
    BillPaymentReminder(String username) {
        this.username = username;
        billPaymentFrame = new JFrame("Bill Payment Reminder");
        billPaymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billPaymentFrame.setSize(550, 700);
        billPaymentFrame.setResizable(false);
        billPaymentFrame.setLocationRelativeTo(null);
        billPaymentFrame.setLayout(null);
        
        titleLabel = new JLabel("Bill Payment Reminder");
        titleLabel.setBounds(150, 50, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        billPaymentFrame.add(titleLabel);
        
        billNameLabel = new JLabel("Bill Name:");
        billNameLabel.setBounds(100, 120, 100, 30);
        billPaymentFrame.add(billNameLabel);
        
        billNameField = new JTextField();
        billNameField.setBounds(250, 120, 200, 30);
        billPaymentFrame.add(billNameField);
        
        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(100, 180, 100, 30);
        billPaymentFrame.add(amountLabel);
        
        amountField = new JTextField();
        amountField.setBounds(250, 180, 200, 30);
        billPaymentFrame.add(amountField);
        
        dueDateLabel = new JLabel("Due Date (dd-mm-yyyy):");
        dueDateLabel.setBounds(50, 240, 200, 30);
        billPaymentFrame.add(dueDateLabel);
        
        dueDateField = new JTextField();
        dueDateField.setBounds(250, 240, 200, 30);
        billPaymentFrame.add(dueDateField);
        
        submitButton = new JButton("Submit");
        submitButton.setBounds(170, 300, 100, 30);
        submitButton.addActionListener(e -> submitBill());
        billPaymentFrame.add(submitButton);
        
        backButton = new JButton("Back");
        backButton.setBounds(290, 300, 100, 30);
        backButton.addActionListener(e -> {
            billPaymentFrame.dispose();
            new billManagement(username);
        });
        billPaymentFrame.add(backButton);
        
        billPaymentFrame.setVisible(true);
    }
    
    private void submitBill() {
        String billName = billNameField.getText().trim();
        String amount = amountField.getText().trim();
        String dueDate = dueDateField.getText().trim();

        if (billName.isEmpty() || amount.isEmpty() || dueDate.isEmpty()) {
            JOptionPane.showMessageDialog(billPaymentFrame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!isValidDateFormat(dueDate)) {
            JOptionPane.showMessageDialog(billPaymentFrame, "Invalid date format. Please use dd-mm-yyyy.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (DatabaseManager.insertBill(username, billName, amount, dueDate)) {
                JOptionPane.showMessageDialog(billPaymentFrame, "Bill added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(billPaymentFrame, "Failed to add bill.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        billNameField.setText("");
        amountField.setText("");
        dueDateField.setText("");
    }

    private boolean isValidDateFormat(String date) {
        String regex = "\\d{2}-\\d{2}-\\d{4}";
        return date.matches(regex);
    }
}
