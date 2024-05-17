package personalfinancemanagement;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IncomeTracker implements ActionListener {
    private JFrame incomeTracker;
    private JLabel title, subTitle;
    private JButton addIncomeButton, viewIncomeButton, backButton;
    private String username;

    public IncomeTracker(String username) {
        this.username = username;
        initializeUI();
    }

    private void initializeUI() {
        createFrame();
        createLabels(); // Call createLabels before adding components
        createButtons();
        addComponents();
        incomeTracker.setVisible(true);
    }

    private void createFrame() {
        incomeTracker = new JFrame();
        incomeTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        incomeTracker.setSize(550, 700);
        incomeTracker.setResizable(false);
        incomeTracker.setLocationRelativeTo(null);
        incomeTracker.setLayout(null);

        backButton = createButton("Back", 20, 20, 100, 25, this::backButtonClicked);
    }

    private void createLabels() {
        title = createLabel("INCOME TRACKER", 180, 50, 20);
        subTitle = createLabel("Track Your Income Transactions Here", 140, 100, 15);
    }

    private JLabel createLabel(String text, int x, int y, int fontSize) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 300, 30);
        label.setFont(new Font("Arial", Font.BOLD, fontSize > 0 ? fontSize : 13));
        return label;
    }

    private void createButtons() {
        addIncomeButton = createButton("Add Income", 170, 210, 180, 30, this);
        viewIncomeButton = createButton("View Income", 170, 260, 180, 30, this);
    }

    private JButton createButton(String text, int x, int y, int width, int height, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.addActionListener(listener);
        return button;
    }

    private void addComponents() {
        incomeTracker.add(backButton);
        incomeTracker.add(addIncomeButton);
        incomeTracker.add(viewIncomeButton);
        incomeTracker.add(title);
        incomeTracker.add(subTitle);
    }

    private void backButtonClicked(ActionEvent e) {
        new dashboard(username); // Assuming Dashboard is the correct class name
        incomeTracker.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addIncomeButton)) {
            new AddIncome(username);
            incomeTracker.dispose();
        } 
        else if (e.getSource().equals(viewIncomeButton)) {
            new ViewIncome(username);
            incomeTracker.dispose();
        }
    }
}