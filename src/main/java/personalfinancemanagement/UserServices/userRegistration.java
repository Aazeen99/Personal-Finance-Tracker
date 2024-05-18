package personalfinancemanagement.UserServices;

import java.awt.*;
import java.awt.event.*;
import java.security.NoSuchAlgorithmException;
import java.util.logging.*;
import javax.swing.*;
import personalfinancemanagement.Database.DatabaseManager;

class userRegistration implements ActionListener{
    JFrame registrationFrame;
    JLabel labelUsername, labelEmail, labelPassword, title;
    JTextField inputUsername, inputEmail;
    JPasswordField inputPassword;
    JButton registerButton, loginButton;
    JLabel labelLogin;
    
    userRegistration(){
        registrationFrame = new JFrame();
        
        title = new JLabel("Registration");
        title.setBounds(110,100,300,60);
        title.setFont(new Font("Arial", Font.BOLD, 50));
        registrationFrame.add(title);
        
        labelUsername = new JLabel("Username:");
        labelUsername.setBounds(80, 230, 100, 30);
        registrationFrame.add(labelUsername);

        inputUsername = new JTextField();
        inputUsername.setBounds(180, 230, 210, 30);
        registrationFrame.add(inputUsername);

        labelEmail = new JLabel("Email:");
        labelEmail.setBounds(80, 280, 100, 30);
        registrationFrame.add(labelEmail);

        inputEmail = new JTextField();
        inputEmail.setBounds(180, 280, 210, 30);
        registrationFrame.add(inputEmail);

        labelPassword = new JLabel("Password:");
        labelPassword.setBounds(80, 330, 100, 30);
        registrationFrame.add(labelPassword);

        inputPassword = new JPasswordField();
        inputPassword.setBounds(180, 330, 210, 30);
        registrationFrame.add(inputPassword);

        registerButton = new JButton("Register");
        registerButton.setBounds(220, 380, 100, 30);
        registrationFrame.add(registerButton);
        registerButton.addActionListener(this);
        
        loginButton = new JButton("Login");
        loginButton.setBounds(220, 480, 100, 30);
        registrationFrame.add(loginButton);
        loginButton.addActionListener(this);

        labelLogin = new JLabel("Already have an account?");
        labelLogin.setBounds(200, 430, 200, 30);
        registrationFrame.add(labelLogin);
        
        registrationFrame.setTitle("User Registration");
        registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registrationFrame.setSize(550, 700);
        registrationFrame.setResizable(false);
        registrationFrame.setLocationRelativeTo(null);
        registrationFrame.setLayout(null);
        
        registrationFrame.setVisible(true);
    }

    
    private static void showRegistrationDialog() {
        String[] options = {"Sign In", "Exit"};
        int choice = JOptionPane.showOptionDialog(null,
            "Registration Successful",
            "Success",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (choice == 0) {
            new userLogin();
            
        } else if (choice == 1) {
            System.exit(0);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(loginButton)){
            new userLogin();
            registrationFrame.dispose(); 
        }
        else if(e.getSource().equals(registerButton)){
            String username = inputUsername.getText();
            String email = inputEmail.getText();
            String password = String.valueOf(inputPassword.getPassword());
            
            //checking whether input is empty
            if(username.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(null, "You didn't enter username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            //hashing the inserted password
            try {
                String salt = userAuthentication.generateSalt();
                String hashedpassword = userAuthentication.hashPassword(password, salt);
                //password has been hashed now it needs to be stored in database 
                
                if(!userAuthentication.isValidEmail(email)){
                    JOptionPane.showMessageDialog(null, "Invalid e-mail.", "Error", JOptionPane.ERROR_MESSAGE);
                    inputUsername.setText("");
                    inputEmail.setText("");
                    inputPassword.setText("");
                }
                else{
                    if(DatabaseManager.insertUserData(username, email, hashedpassword,salt)){
                        System.out.println(salt);
                        showRegistrationDialog();
                        registrationFrame.dispose();
                }
            }
                
            } 
            catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(userRegistration.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }
}