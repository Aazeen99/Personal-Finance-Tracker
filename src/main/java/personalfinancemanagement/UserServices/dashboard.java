package personalfinancemanagement.UserServices;

import personalfinancemanagement.BillManagement.*;
import personalfinancemanagement.IncomeManagement.*;
import personalfinancemanagement.Database.*;
import personalfinancemanagement.BudgetManagement.*;
import personalfinancemanagement.UserServices.*;
import personalfinancemanagement.ExpenseManagement.expenseTracker;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class dashboard implements ActionListener{
    JFrame dashboard;
    JLabel title;
    JButton incomeTracking, expenseTracking, budgetTracking, billpayment, transactionHistory, logout;
    String username;
    
    public dashboard(String username){
        this.username = username;
        dashboard = new JFrame();
        
        title = new JLabel("Dashboard");
        title.setBounds(130,100,300,60);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        dashboard.add(title);
        
        incomeTracking = new JButton("Income Tracker");
        incomeTracking.setBounds(170, 210, 180, 30);
        dashboard.add(incomeTracking);
        incomeTracking.addActionListener(this);
        
        expenseTracking = new JButton("Expense Tracker");
        expenseTracking.setBounds(170, 260, 180, 30);
        dashboard.add(expenseTracking);
        expenseTracking.addActionListener(this);
        
        budgetTracking = new JButton("Budget Tracker");
        budgetTracking.setBounds(170, 310, 180, 30);
        dashboard.add(budgetTracking);
        budgetTracking.addActionListener(this);
        
        billpayment = new JButton("Bill Management");
        billpayment.setBounds(170, 360, 180, 30);
        dashboard.add(billpayment);
        billpayment.addActionListener(this);
        
        transactionHistory = new JButton("Transaction History");
        transactionHistory.setBounds(170, 410, 180, 30);
        dashboard.add(transactionHistory);
        transactionHistory.addActionListener(this);
        
        logout = new JButton("Logout");
        logout.setBounds(20, 20, 100, 25);
        dashboard.add(logout);
        logout.addActionListener(this);
        
        dashboard.setTitle("User Login");
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboard.setSize(550, 700);
        dashboard.setResizable(false);
        dashboard.setLocationRelativeTo(null);
        dashboard.setLayout(null);
        
        dashboard.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(logout)){
            new userLogin();
            dashboard.dispose();
        }
        else if(e.getSource().equals(incomeTracking)){
            new IncomeTracker(username);
        }
        else if(e.getSource().equals(expenseTracking)){
            new expenseTracker(username);
        }
        else if(e.getSource().equals(transactionHistory)){
            TransactionHistory history = new TransactionHistory(username);
            history.displayTransactionHistory();
        }
        else if(e.getSource().equals(budgetTracking)){
            new BudgetManager(username);
        }
        else if(e.getSource().equals(billpayment)){
            new billManagement(username);
        }
    }
}
