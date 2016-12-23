package com.spaceraider.GUI;

//import Database.Database;



import com.spaceraider.Database.Database;

import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends BackGroundPanel {
    private JButton registerButton;
    private JButton backButton;
    private JFrame frame;
    private double width;
    private double height;
    private JLabel label;
    private JLabel passwordLabel;
    private JTextField username;
    private JPasswordField password;
    private JTextField email;
    private JLabel emailLabel;
    private JLabel logo;
    private Database database;

    public RegisterPanel(JFrame frame,Database database) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        setPreferredSize(new Dimension((int) width, (int) height));
        setLayout(null);
        this.frame = frame;
        initComponents();
        addComponents();
        actionListener();
        this.database = database;
    }

    private void actionListener() {
        backButton.addActionListener(e -> {
            StartPanel playerPanel = new StartPanel(frame);
            frame.getContentPane().removeAll();
            frame.getContentPane().invalidate();
            frame.getContentPane().add(playerPanel);
            frame.getContentPane().revalidate();
        });

        registerButton.addActionListener(e -> {
            String insertemail = email.getText();
            String insertName = username.getText();
            String insertPassword = new String(password.getPassword());
            if (!database.checkEmailAvailable(insertemail)) {

                JOptionPane.showMessageDialog(null, "Email is in use");
            }else{
                if(!database.checkUserAvailable(insertName)){
                    JOptionPane.showMessageDialog(null, "Username is in use");
                }
                else{
                    database.addUser(insertemail,insertName, insertPassword,0);
                    LoginPanel loginPanel = new LoginPanel(frame,database);
                    frame.getContentPane().removeAll();
                    frame.getContentPane().invalidate();
                    frame.getContentPane().add(loginPanel);
                    frame.getContentPane().revalidate();
                }

            }


        });
    }

    private void addComponents() {
        add(backButton);
        add(registerButton);
        add(label);
        add(passwordLabel);
        add(username);
        add(password);
        add(logo);
        add(email);
        add(emailLabel);
    }

    private void initComponents() {
        backButton = new JButton("Back");
        backButton.setBounds((int) (width / 2) - (160 / 2) + 120, (int) (height / 2)+200, 160, 50);
        registerButton = new JButton("Register");
        registerButton.setBounds((int) (width / 2) - (160 / 2) - 120, (int) (height / 2) + 200, 160, 50);
        label = new JLabel("", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 200));
        label.setText("Enter your new username:");
        label.setBounds((int) (width / 3) - (160 / 2), (int) (height / 2) - 100, 420, 50);
        label.setFont(new Font("Verdana", 1, 20));
        label.setForeground(Color.white);
        passwordLabel = new JLabel("",SwingConstants.CENTER);
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(new Color(0, 0, 0, 200));
        passwordLabel.setText("Enter your new password:");
        passwordLabel.setBounds((int) (width / 3) - (160 / 2), (int) (height / 2), 420, 50);
        passwordLabel.setFont(new Font("Verdana", 1, 20));
        passwordLabel.setForeground(Color.white);
        username = new JTextField();
        username.setBounds((int) (width / 2) + 30, (int) (height / 2) - 100, 300, 50);
        password = new JPasswordField();
        password.setBounds((int) (width / 2) + 30, (int) (height / 2), 300, 50);;
        logo = new JLabel();
        logo.setBounds((int) (width / 2) - (400 / 2), 25, 600, 200);
        logo.setIcon(new ImageIcon("src/resources/arcade-font-writer.png"));
        emailLabel = new JLabel("",SwingConstants.CENTER);
        emailLabel.setOpaque(true);
        emailLabel.setBackground(new Color(0, 0, 0, 200));
        emailLabel.setText("Enter your email-address:");
        emailLabel.setBounds((int) (width / 3) - (160 / 2), (int) (height / 2)-200, 420, 50);
        emailLabel.setFont(new Font("Verdana", 1, 20));
        emailLabel.setForeground(Color.white);
        email = new JTextField();
        email.setBounds((int) (width / 2) + 30, (int) (height / 2) - 200, 300, 50);
    }

}
