package personalfinancemanagement.BudgetManagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import personalfinancemanagement.Database.DatabaseManager;

public class ViewBudget {
    JFrame viewBudgetFrame;
    JTable budgetTable;
    String username;

    ViewBudget(String username) {
        this.username = username;
        viewBudgetFrame = new JFrame();

        DefaultTableModel model = DatabaseManager.viewBudget(username);

        budgetTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(budgetTable);
        viewBudgetFrame.add(scrollPane, BorderLayout.CENTER);

        viewBudgetFrame.setTitle("View Budget");
        viewBudgetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewBudgetFrame.setSize(400, 300);
        viewBudgetFrame.setLocationRelativeTo(null);
        viewBudgetFrame.setVisible(true);
    }
}
