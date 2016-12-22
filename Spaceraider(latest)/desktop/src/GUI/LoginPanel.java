package GUI;

//import Database.Database;

import Database.Database;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends BackGroundPanel {
    private JButton LoginButton;
    private JButton backButton;
    private JFrame frame;
    private double width;
    private double height;
    private JLabel label;
    private JLabel passwordLabel;
    private JTextField username;
    private JPasswordField password;
    private JLabel logo;
    private Database database = new Database();


    public LoginPanel(JFrame frame ,Database database) {
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

        LoginButton.addActionListener(e -> {
            String insertusername = username.getText();
            String insertpassword = new String(password.getPassword());
            boolean playersInDatabase = database.getUsername(insertusername);
            boolean passwordInDatabase = database.getPassword(insertusername, insertpassword);
            if(playersInDatabase && passwordInDatabase){
                int id = database.getId(insertusername);
                System.out.println(insertusername);
                MenuSelectorPanel menuSelectorPanel = new MenuSelectorPanel(frame, insertusername, id);
                frame.getContentPane().removeAll();
                frame.getContentPane().invalidate();
                frame.getContentPane().add(menuSelectorPanel);
                frame.getContentPane().revalidate();
            }
            else{
                JOptionPane.showMessageDialog(null, "Username or password are incorrect");
            }

        });
    }

    private void addComponents() {
        add(backButton);
        add(LoginButton);
        add(label);
        add(passwordLabel);
        add(username);
        add(password);
        add(logo);
    }

    private void initComponents() {
        backButton = new JButton("Back");
        backButton.setBounds((int) (width / 2) - (160 / 2) + 120, (int) (height / 2)+200, 160, 50);
        LoginButton = new JButton("Login");
        LoginButton.setBounds((int) (width / 2) - (160 / 2) - 120, (int) (height / 2)+200, 160, 50);
        label = new JLabel("", SwingConstants.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(0, 0, 0, 200));
        label.setText("Enter your username:");
        label.setBounds((int) (width / 3) - (160 / 2), (int) (height / 2) - 100, 420, 50);
        label.setFont(new Font("Verdana", 1, 20));
        label.setForeground(Color.white);
        passwordLabel = new JLabel("",SwingConstants.CENTER);
        passwordLabel.setOpaque(true);
        passwordLabel.setBackground(new Color(0, 0, 0, 200));
        passwordLabel.setText("Enter your password:");
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
    }

}
