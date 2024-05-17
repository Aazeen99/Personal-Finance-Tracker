package personalfinancemanagement;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddIncome {
    private JFrame addIncomeFrame;
    private JLabel monthLabel, amountLabel, incomeSourceLabel;
    private JTextField inputAmount, inputSource;
    private JButton submitIncomeButton, backButton;
    private final String username;

    public AddIncome(String username) {
        this.username = username;
        initializeUI();
    }

    private void initializeUI() {
        createFrame();
        createLabels();
        createTextFields();
        createButtons();
        addComponents();
        addIncomeFrame.setVisible(true);
    }

    private void createFrame() {
        addIncomeFrame = new JFrame();
        addIncomeFrame.setTitle("Income Tracker");
        addIncomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addIncomeFrame.setSize(550, 700);
        addIncomeFrame.setResizable(false);
        addIncomeFrame.setLocationRelativeTo(null);
        addIncomeFrame.setLayout(null);
    }

    private void createLabels() {
        monthLabel = createLabel(getCurrentMonth(), 240, 200, 13);
        amountLabel = createLabel("Amount:", 100, 250, 0);
        incomeSourceLabel = createLabel("Income Source:", 100, 300, 0);
    }

    private JLabel createLabel(String text, int x, int y, int fontSize) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 200, 30);
        label.setFont(new Font("Arial", Font.BOLD, fontSize > 0 ? fontSize : 13));
        return label;
    }

    private void createTextFields() {
        inputAmount = createTextField(240, 250, 210);
        inputSource = createTextField(240, 300, 210);
    }

    private JTextField createTextField(int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 30);
        return textField;
    }

    private void createButtons() {
        backButton = createButton("Back", 20, 20, 100, 25, this::backButtonClicked);
        submitIncomeButton = createButton("Add Entry", 220, 380, 100, 30, this::submitIncomeButtonClicked);
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        return button;
    }

    private void addComponents() {
        addIncomeFrame.add(monthLabel);
        addIncomeFrame.add(amountLabel);
        addIncomeFrame.add(incomeSourceLabel);
        addIncomeFrame.add(inputAmount);
        addIncomeFrame.add(inputSource);
        addIncomeFrame.add(backButton);
        addIncomeFrame.add(submitIncomeButton);
    }

    private String getCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy");
        return dateFormat.format(calendar.getTime());
    }

    private void backButtonClicked(ActionEvent e) {
        new IncomeTracker(username);
        addIncomeFrame.dispose();
    }

    private void submitIncomeButtonClicked(ActionEvent e) {
        if (!inputAmount.getText().isEmpty() && !inputSource.getText().isEmpty()) {
            submitIncome();
        } else {
            JOptionPane.showMessageDialog(addIncomeFrame,
                "Both fields must contain data.",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitIncome() {
        String amount = inputAmount.getText();
        String description = inputSource.getText();

        int option = JOptionPane.showConfirmDialog(addIncomeFrame,
            "Add this entry?\nAmount: " + amount + "\nIncome Source: " + description,
            "Confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            System.out.println("Entry added: Amount - " + amount + ", Description - " + description);
            DatabaseManager.insertIncomeData(username, amount, description);
            inputAmount.setText("");
            inputSource.setText("");
        }

        addIncomeFrame.revalidate();
        addIncomeFrame.repaint();
    }
}