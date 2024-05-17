package personalfinancemanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewIncome {
    private JFrame viewIncomeFrame;
    private JTable incomeTable;
    private JScrollPane scrollPane;
    private JButton backButton;
    private String username;

    public ViewIncome(String username) {
        this.username = username;
        initializeUI();
    }

    private void initializeUI() {
        createFrame();
        createTable();
        createScrollPane();
        createButton();
        addComponents();
        fetchAndDisplayIncome();
        viewIncomeFrame.setVisible(true);
    }

    private void createFrame() {
        viewIncomeFrame = new JFrame();
        viewIncomeFrame.setTitle("Income Tracker");
        viewIncomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewIncomeFrame.setSize(550, 400);
        viewIncomeFrame.setResizable(false);
        viewIncomeFrame.setLocationRelativeTo(null);
        viewIncomeFrame.setLayout(null);
    }

    private void createTable() {
        incomeTable = new JTable();
    }

    private void createScrollPane() {
        scrollPane = new JScrollPane(incomeTable);
        scrollPane.setBounds(20, 20, 500, 300);
    }

    private void createButton() {
        backButton = new JButton("Back");
        backButton.setBounds(20, 330, 100, 25);
        backButton.addActionListener(this::backButtonClicked);
    }

    private void addComponents() {
        viewIncomeFrame.add(scrollPane);
        viewIncomeFrame.add(backButton);
    }

    private void fetchAndDisplayIncome() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Amount", "Source"}, 0);
        DatabaseManager.fetchIncomeData(username, model);
        incomeTable.setModel(model);
    }

    private void backButtonClicked(ActionEvent e) {
        new IncomeTracker(username);
        viewIncomeFrame.dispose();
    }
}