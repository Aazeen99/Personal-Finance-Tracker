package personalfinancemanagement.BudgetManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import personalfinancemanagement.Database.DatabaseManager;

public class AddBudget {
    JFrame addBudgetFrame;
    JLabel titleLabel;
    JComboBox<String>[] categoryDropDowns;
    JTextField[] amountFields;
    JButton submitButton;
    String[] categories = {"Groceries", "Bills", "Entertainment", "Others"};
    String username;

    AddBudget(String username) {
        this.username = username;
        addBudgetFrame = new JFrame();

        titleLabel = new JLabel("Set Budget");
        titleLabel.setBounds(200, 20, 150, 20);
        addBudgetFrame.add(titleLabel);

        categoryDropDowns = new JComboBox[4];
        amountFields = new JTextField[4];

        int startY = 60;

        for (int i = 0; i < 4; i++) {
            final int index = i; // Declare final variable inside loop
            categoryDropDowns[i] = new JComboBox<>(categories);
            categoryDropDowns[i].setBounds(50, startY, 120, 25);
            categoryDropDowns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JComboBox<String> selectedDropDown = (JComboBox<String>) e.getSource();
                    String selectedCategory = (String) selectedDropDown.getSelectedItem();
                    for (int j = 0; j < 4; j++) {
                        if (j != index) { // Use index instead of i
                            categoryDropDowns[j].removeItem(selectedCategory);
                        }
                    }
                }
            });
            addBudgetFrame.add(categoryDropDowns[i]);

            amountFields[i] = new JTextField();
            amountFields[i].setBounds(200, startY, 100, 25);
            addBudgetFrame.add(amountFields[i]);

            startY += 50;
        }


        submitButton = new JButton("Submit");
        submitButton.setBounds(200, startY, 100, 30);
        submitButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int totalAmount = 0;
            for (int i = 0; i < 4; i++) {
                if (!amountFields[i].getText().isEmpty()) {
                    totalAmount += Integer.parseInt(amountFields[i].getText());
                    DatabaseManager.insertBudgetData(username, (String) categoryDropDowns[i].getSelectedItem(), amountFields[i].getText());
                }
            }
            if (totalAmount != 100) {
                JOptionPane.showMessageDialog(addBudgetFrame, "Total amount should be 100");
            } else {
                // Code to save the budget details
                addBudgetFrame.dispose();
            }
        }
    });

        addBudgetFrame.add(submitButton);

        addBudgetFrame.setTitle("Add Budget");
        addBudgetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addBudgetFrame.setSize(400, 500);
        addBudgetFrame.setResizable(false);
        addBudgetFrame.setLayout(null);
        addBudgetFrame.setLocationRelativeTo(null);

        addBudgetFrame.setVisible(true);
    }
}
