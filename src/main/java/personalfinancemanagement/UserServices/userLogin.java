package personalfinancemanagement.UserServices;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import personalfinancemanagement.Database.DatabaseManager;

class userLogin implements ActionListener{
    JFrame loginFrame;
    JLabel labelUsername, labelPassword, title;
    JTextField inputUsername;
    JPasswordField inputPassword;
    JButton registerButton, loginButton;
    JLabel registrationLabel;
    
    userLogin(){
        loginFrame = new JFrame();
        
        title = new JLabel("Login");
        title.setBounds(195,100,300,60);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        loginFrame.add(title);
        
        labelUsername = new JLabel("Username:");
        labelUsername.setBounds(80, 230, 100, 30);
        loginFrame.add(labelUsername);

        inputUsername = new JTextField();
        inputUsername.setBounds(180, 230, 210, 30);
        loginFrame.add(inputUsername);

        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(80, 280, 100, 30);
        loginFrame.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setBounds(180, 280, 210, 30);
        loginFrame.add(inputPassword);

        registerButton = new JButton("Register");
        registerButton.setBounds(220, 440, 100, 30);
        loginFrame.add(registerButton);
        registerButton.addActionListener(this);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(220, 340, 100, 30);
        loginFrame.add(loginButton);
        loginButton.addActionListener(this);

        registrationLabel = new JLabel("New User?");
        registrationLabel.setBounds(235, 390, 200, 30);
        loginFrame.add(registrationLabel);
        
        loginFrame.setTitle("User Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(550, 700);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setLayout(null);
        
        loginFrame.setVisible(true);
    }

    private static void showIncorrectCredentialsDialog() {
            JOptionPane.showMessageDialog(null,
            "Incorrect Username or Password. Re-enter.",
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(registerButton)){
            new userRegistration();
            loginFrame.dispose();
        }
        else if(e.getSource().equals(loginButton)){
            String username = inputUsername.getText();
            String password = String.valueOf(inputPassword.getPassword());
            
            try {
                if(DatabaseManager.verifyLogin(username, password)){
                    new dashboard(username);
                    
                    loginFrame.dispose();
                }
                else{
                    showIncorrectCredentialsDialog();
                    inputUsername.setText("");
                    inputPassword.setText("");
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(userLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
