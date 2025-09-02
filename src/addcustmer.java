import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addcustmer extends JFrame {

    public addcustmer() {

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);


        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setBounds(20, 40, 100, 20);
        backgroundPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 40, 200, 20);
        backgroundPanel.add(idField);


        JLabel creationDateLabel = new JLabel("Creation Date:");
        creationDateLabel.setBounds(20, 70, 100, 20);
        backgroundPanel.add(creationDateLabel);



        JTextField creationDateField = new JTextField();
        creationDateField.setBounds(120, 70, 200, 20);
        backgroundPanel.add(creationDateField);



        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 100, 100, 20);
        backgroundPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(120, 100, 200, 20);
        backgroundPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 130, 100, 20);
        backgroundPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(120, 130, 200, 20);
        backgroundPanel.add(lastNameField);

        JLabel adminNumberLabel = new JLabel("Access number");
        adminNumberLabel.setBounds(20, 160, 100, 20);
        backgroundPanel.add(adminNumberLabel);

        JTextField adminNumberField = new JTextField();
        adminNumberField.setBounds(120, 160, 200, 20);
        backgroundPanel.add(adminNumberField);

         JLabel adminPasswordLabel = new JLabel("Password:");
        adminPasswordLabel.setBounds(20, 190, 100, 20);
        backgroundPanel.add(adminPasswordLabel);

        JTextField  adminPasswordField = new JTextField();
        adminPasswordField.setBounds(120, 190, 200, 20);
        backgroundPanel.add(adminPasswordField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(20, 220, 100, 20);
        backgroundPanel.add(addressLabel);

        JTextField addressField = new JTextField();
        addressField.setBounds(120, 220, 200, 20);
        backgroundPanel.add(addressField);

        JButton addButton = new JButton("registration");
        addButton.setBackground(new Color(63, 86, 236));
        addButton.setBounds(120, 270, 100, 30);
        addButton.setForeground(new Color(9, 9, 8));

        JButton cancelButton = new JButton("cancel");
        cancelButton.setBackground(new Color(63, 86, 236));
        cancelButton.setBounds(230, 270, 95, 30);
        cancelButton.setForeground(new Color(9, 9, 8));


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

                    Statement statement = connection.createStatement();


                    Customer cut= new Customer();
                    cut.setId(Integer.parseInt((idField.getText())));
                    cut.setCreationDate(creationDateField.getText());
                    cut.setFirstName(firstNameField.getText());
                    cut.setLastName(lastNameField.getText());
                    cut.setCustomerNumber( Integer.parseInt((adminNumberField.getText())));
                    cut.setPassword(Integer.parseInt((adminPasswordField.getText())));
                    cut.setAddress(addressField.getText());

                     statement.executeUpdate("INSERT INTO customers (customers_id, frist_name, last_name, customer_number, customer_password, address) " +
                            "VALUES ('" + cut.getId() + "', '" + cut.getFirstName() + "', '" + cut.getLastName() + "', '" + cut.getCustomerNumber() + "', '" + cut.getPassword() + "', '" + cut.getAddress() + "')");

                    JOptionPane.showMessageDialog(addcustmer.this, "customer added successfully");

                    statement.close();
                    connection.close();
                    dispose();
                   

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addcustmer.this, "Error adding employee to database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backgroundPanel.add(addButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login f=new login();
                f.setVisible(true);
            }
        });
        backgroundPanel.add(cancelButton);
        setContentPane(backgroundPanel);
        setTitle("Add customer");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("./assets/13.jpg")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

}}
