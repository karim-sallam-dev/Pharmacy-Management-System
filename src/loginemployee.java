import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class loginemployee extends JFrame {

    public loginemployee() {

       BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);


        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        usernameLabel.setForeground(new Color(248, 48, 77));
        backgroundPanel.add(usernameLabel);
        usernameLabel.setBounds(50, 70, 100, 20);

        // Username Text Field
        JTextField usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
        backgroundPanel.add(usernameField);
        usernameField.setBounds(120, 70, 190, 20);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        passwordLabel.setForeground(new Color(248, 48, 77));
        backgroundPanel.add(passwordLabel);
        passwordLabel.setBounds(50, 120, 100, 20);

        // Password Field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
        backgroundPanel.add(passwordField);
        passwordField.setBounds(120, 120, 190, 20);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.PLAIN, 12));
        loginButton.setBackground(new Color(172, 183, 232));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

                    Statement statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

                    boolean isMatchFound = false;

                    while (resultSet.next()) {
                        String username = resultSet.getString(2);
                        int password = resultSet.getInt(5);

                        if (usernameField.getText().equals(username) && Integer.parseInt(new String(passwordField.getPassword())) == password) {
                            dispose();
                            employyejframe frame = new employyejframe();
                            frame.setVisible(true);
                            isMatchFound = true;
                            break;
                        }
                    }

                    if (!isMatchFound) {

                        JOptionPane.showMessageDialog(null, "The username or password you entered is incorrect. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }



                    resultSet.close();
                    statement.close();
                    connection.close();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(loginemployee.this, "Error connecting to database", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backgroundPanel.add(loginButton);
        loginButton.setBounds(120, 175, 90, 30);

        // Add Button
        JButton addButton = new JButton("Sign up");
        addButton.setFont(new Font("Arial", Font.PLAIN, 12));
        addButton.setBackground(new Color(172, 183, 232));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                addempolyee frame = new addempolyee();
                frame.setVisible(true);
            }
        });
        backgroundPanel.add(addButton);
        addButton.setBounds(220, 175, 90, 30);

        setContentPane(backgroundPanel);
        setTitle("Login Page");
        setSize(620, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("./assets/4.jpg")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
