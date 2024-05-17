package personalfinancemanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class billManagement implements ActionListener {
    JFrame billManagementFrame;
    JLabel titleLabel;
    JButton addBillReminder, viewBills;
    String username;
    
    billManagement(String username) {
        this.username = username;
        billManagementFrame = new JFrame();
        
        titleLabel = new JLabel("Bill Management");
        titleLabel.setBounds(150, 100, 250, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        billManagementFrame.add(titleLabel);
        
        addBillReminder = new JButton("Add Bill Reminder");
        addBillReminder.setBounds(170, 210, 180, 30);
        billManagementFrame.add(addBillReminder);
        addBillReminder.addActionListener(this);
        
        viewBills = new JButton("View Bills");
        viewBills.setBounds(170, 260, 180, 30);
        billManagementFrame.add(viewBills);
        viewBills.addActionListener(this);
        
        billManagementFrame.setTitle("Bill Management");
        billManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billManagementFrame.setSize(550, 700);
        billManagementFrame.setResizable(false);
        billManagementFrame.setLocationRelativeTo(null);
        billManagementFrame.setLayout(null);
        
        billManagementFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addBillReminder)) {
            new BillPaymentReminder(username);
            billManagementFrame.dispose();
        } else if (e.getSource().equals(viewBills)) {
            // Implement view bills functionality
            displayBills(username);
        }
    }
    
    private void displayBills(String username) {
        
        JFrame viewBillFrame;
        JTable billTable;
    
        viewBillFrame = new JFrame();

        DefaultTableModel model = DatabaseManager.viewBills(username);

        billTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(billTable);
        viewBillFrame.add(scrollPane, BorderLayout.CENTER);

        viewBillFrame.setTitle("View Bills");
        viewBillFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        viewBillFrame.setSize(400, 300);
        viewBillFrame.setLocationRelativeTo(null);
        viewBillFrame.setVisible(true);    }

}
