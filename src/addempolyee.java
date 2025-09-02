import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class addempolyee extends JFrame {

    public addempolyee() {

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);


        JLabel idLabel = new JLabel("Employee ID:");
        idLabel.setBounds(20, 20, 100, 20);
        backgroundPanel.add(idLabel);

        JTextField idField = new JTextField();
        idField.setBounds(120, 20, 200, 20);
        backgroundPanel.add(idField);

        JLabel creationDateLabel = new JLabel("Creation Date:");
        creationDateLabel.setBounds(20, 50, 100, 20);
        backgroundPanel.add(creationDateLabel);

        JTextField creationDateField = new JTextField();
        creationDateField.setBounds(120, 50, 200, 20);
        backgroundPanel.add(creationDateField);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(20, 80, 100, 20);
        backgroundPanel.add(firstNameLabel);

        JTextField firstNameField = new JTextField();
        firstNameField.setBounds(120, 80, 200, 20);
        backgroundPanel.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(20, 110, 100, 20);
        backgroundPanel.add(lastNameLabel);

        JTextField lastNameField = new JTextField();
        lastNameField.setBounds(120, 110, 200, 20);
        backgroundPanel.add(lastNameField);

        JLabel adminNumberLabel = new JLabel("Access number");
        adminNumberLabel.setBounds(20, 140, 100, 20);
        backgroundPanel.add(adminNumberLabel);

        JTextField adminNumberField = new JTextField();
        adminNumberField.setBounds(120, 140, 200, 20);
        backgroundPanel.add(adminNumberField);

        JLabel adminPasswordLabel = new JLabel("Password:");
        adminPasswordLabel.setBounds(20, 170, 100, 20);
        backgroundPanel.add(adminPasswordLabel);

        JTextField adminPasswordField = new JTextField();
        adminPasswordField.setBounds(120, 170, 200, 20);
        backgroundPanel.add(adminPasswordField);

        JLabel jobNameLabel = new JLabel("Job Name:");
        jobNameLabel.setBounds(20, 200, 100, 20);
        backgroundPanel.add(jobNameLabel);

        JTextField jobNameField = new JTextField();
        jobNameField.setBounds(120, 200, 200, 20);
        backgroundPanel.add(jobNameField);

        JButton addButton = new JButton("registration");
        addButton.setBackground(new Color(63, 86, 236));
        addButton.setBounds(120, 260, 100, 30);
        addButton.setForeground(new Color(9, 9, 8));

        JButton cancelButton = new JButton("cancel");
        cancelButton.setBackground(new Color(63, 86, 236));
        cancelButton.setBounds(230, 260, 95, 30);
        cancelButton.setForeground(new Color(9, 9, 8));



        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Establish connection
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

                    // Create statement
                    Statement statement = connection.createStatement();

                    // Retrieve data from text fields
                    Employee emp= new Employee();
                    emp.setId(Integer.parseInt((idField.getText())));
                    emp.setCreationDate( creationDateField.getText());
                    emp.setFirstName(firstNameField.getText());
                    emp.setLastName(lastNameField.getText());
                    emp.setCustomerNumber(Integer.parseInt((adminNumberField.getText())));
                    emp.setPassword(Integer.parseInt((adminPasswordField.getText())));
                    emp.setJobTitle(jobNameField.getText());
                    // Insert data into the database
                    statement.executeUpdate( "INSERT INTO employees (employees_id, frist_name, last_name, admin_number, admin_password, Job_name) " +
                            "VALUES ('" + emp.getId() + "', '" + emp.getFirstName() + "', '" + emp.getLastName() + "', '" + emp.getCustomerNumber() + "', '" + emp.getPassword() + "', '" + emp.getJobTitle() + "')");

                    // Show success message
                    JOptionPane.showMessageDialog(addempolyee.this, "Employee added successfully");

                    // Close connections and resources
                    statement.close();
                    connection.close();
                    dispose();
                    loginemployee frame1 =new loginemployee();
                    frame1.setVisible(true);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(addempolyee.this, "Error adding employee to database", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        backgroundPanel.add(addButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginemployee f=new loginemployee();
                f.setVisible(true);
            }
        });
        backgroundPanel.add(cancelButton);
        setContentPane(backgroundPanel);
        setTitle("Add Employee");
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

    }
}
