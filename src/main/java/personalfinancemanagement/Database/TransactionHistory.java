package personalfinancemanagement.Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TransactionHistory {
    private final String username;

    public TransactionHistory(String username) {
        this.username = username;
    }

    public void displayTransactionHistory() {
        JFrame frame = new JFrame("Transaction History");
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Type", "Amount", "Description"}, 0);
        table.setModel(model);
        table.setDefaultEditor(Object.class, null);

        DatabaseManager.fetchTransactionHistory(username, model);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
