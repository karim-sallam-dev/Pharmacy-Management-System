import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customerjframe extends JFrame {

    public Customerjframe() {
        setTitle("Customer");
        setSize(1600, 825);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Dimension itemSize = new Dimension(200, 200);

        JPanel medicinePanel = new JPanel(new GridLayout(2, 3, 5, 5));

        try {


            // Connect to the database
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

            // Prepare the SELECT statement to retrieve data from the database
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines");

            // Execute the SELECT statement
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next()) {
                JPanel medicineItemPanel = new JPanel(new BorderLayout());
                medicineItemPanel.setPreferredSize(itemSize);
                medicineItemPanel.setBackground(Color.WHITE);

                byte[] imageData = resultSet.getBytes("img");
                ImageIcon imageIcon = new ImageIcon(imageData);
                Image image = imageIcon.getImage().getScaledInstance(200, 300, Image.SCALE_SMOOTH);

                JLabel imageLabel = new JLabel(new ImageIcon(image));
                medicineItemPanel.add(imageLabel, BorderLayout.NORTH);

                JPanel textPanel = new JPanel(new GridLayout(2, 1)); // Changed grid layout to accommodate two labels
                textPanel.setBackground(Color.WHITE);

                JLabel idLabel = new JLabel("ID: " + resultSet.getInt("medicines_id"));
                JLabel nameLabel = new JLabel(resultSet.getString("name"));
                JLabel priceLabel = new JLabel("Price: " + resultSet.getDouble("price") + " EG");

                
                textPanel.add(idLabel);
                textPanel.add(nameLabel);
                textPanel.add(priceLabel);

                medicineItemPanel.add(textPanel, BorderLayout.CENTER);

                medicinePanel.add(medicineItemPanel);
            }

            // Close resources
            resultSet.close();
            selectStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(Customerjframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(medicinePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buyingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel idLabel2 = new JLabel("ID:");
        JTextField idField2 = new JTextField(20);
        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField(20);
        JButton buyingButton = new JButton("Purchase");
        buyingButton.setBackground(Color.orange);


        buyingPanel.add(idLabel2);
        buyingPanel.add(idField2);
        buyingPanel.add(quantityLabel);
        buyingPanel.add(quantityField);
        buyingPanel.add(buyingButton);




        rightPanel.add(buyingPanel, BorderLayout.NORTH);

        getContentPane().add(rightPanel);
        queues n = new queues(150);
        JButton invoiceButton = new JButton("Invoice");
        buyingPanel.add(invoiceButton);
        invoiceButton.setBackground(Color.orange);

        buyingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int medicineID = Integer.parseInt(idField2.getText());
                    int quantity = Integer.parseInt(quantityField.getText());


                    idField2.setText(null);
                    quantityField.setText(null);




                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");


                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines WHERE medicines_id = ?");
                    selectStatement.setInt(1, medicineID);


                    ResultSet resultSet = selectStatement.executeQuery();

                    if (resultSet.next()) {
                        int id = resultSet.getInt("medicines_id");
                        String name = resultSet.getString("name");
                        double price = resultSet.getDouble("price");
                        int q = resultSet.getInt("quantity");

                        if (q >= quantity) {
                            q = q - quantity;

                            PreparedStatement updateStatement = connection.prepareStatement("UPDATE medicines SET quantity = ? WHERE medicines_id = ?");
                            updateStatement.setInt(1, q);
                            updateStatement.setInt(2, medicineID);

                             updateStatement.executeUpdate();
                            n.insert(id, name, price, quantity);
                        } else {
                            JOptionPane.showMessageDialog(Customerjframe.this, "The product is not available in the specified quantity: " , "Error", JOptionPane.WARNING_MESSAGE);

                        }


                    }


                    resultSet.close();
                    selectStatement.close();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Customerjframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }







                revalidate();
                repaint();
            }
        });

        invoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                invoice invoicePage = new invoice(n);
                invoicePage.setVisible(true);
            }
        });



    }
}
