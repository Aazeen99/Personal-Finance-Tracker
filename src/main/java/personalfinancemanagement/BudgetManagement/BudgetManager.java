package personalfinancemanagement.BudgetManagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import personalfinancemanagement.UserServices.dashboard;

public class BudgetManager implements ActionListener {
    JLabel title;
    JFrame budgetManagerFrame;
    JButton addBudgetButton, viewBudgetButton, backButton;
    String username;

    public BudgetManager(String username) {
        this.username = username;
        budgetManagerFrame = new JFrame();
        
        title = new JLabel("Budget Tracking");
        title.setBounds(80,100,400,60);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        budgetManagerFrame.add(title);

        addBudgetButton = new JButton("Add Budget");
        addBudgetButton.setBounds(170, 210, 180, 30);
        budgetManagerFrame.add(addBudgetButton);
        addBudgetButton.addActionListener(this);

        viewBudgetButton = new JButton("View Budget");
        viewBudgetButton.setBounds(170, 260, 180, 30);
        budgetManagerFrame.add(viewBudgetButton);
        viewBudgetButton.addActionListener(this);

        backButton = new JButton("Back");
        backButton.setBounds(20, 20, 100, 25);
        budgetManagerFrame.add(backButton);
        backButton.addActionListener(this);
        
        budgetManagerFrame.setTitle("Budget Manager");
        budgetManagerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        budgetManagerFrame.setSize(550, 700);
        budgetManagerFrame.setResizable(false);
        budgetManagerFrame.setLocationRelativeTo(null);
        budgetManagerFrame.setLayout(null);

        budgetManagerFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addBudgetButton)) {
            new AddBudget(username);
        } 
        else if (e.getSource().equals(viewBudgetButton)) {
            new ViewBudget(username);
        }
        else if(e.getSource().equals(backButton)){
            new dashboard(username);
            budgetManagerFrame.dispose();
        }
    }
}
