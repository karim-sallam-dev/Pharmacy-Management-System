import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class employyejframe  extends JFrame {

    public employyejframe() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout(5, 5));

        ////////////////////////////////leftPanel/////////////////////////////////////////////
        JPanel leftPanel = new JPanel(new GridLayout(7, 1, 10, 10));
        leftPanel.setBackground(new Color(9, 9, 8));
        leftPanel.setPreferredSize(new Dimension(200, 800));
        container.add(leftPanel, BorderLayout.WEST);



        JLabel label0 = new JLabel(" ",  JLabel.CENTER);
        leftPanel.add(label0);

        // Structure 1

        JLabel label1 = new JLabel("Add a product",  JLabel.CENTER);
        label1.setForeground(new Color(252, 247, 247));
        label1.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));
        leftPanel.add(label1);

        // Structure 2

        JLabel label2 = new JLabel("Edit a product", JLabel.CENTER);
        label2.setForeground(new Color(252, 247, 247));
        label2.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));

        leftPanel.add(label2);

        // Structure 3

        JLabel label3 = new JLabel("Delete a product",  JLabel.CENTER);
        label3.setForeground(new Color(252, 247, 247));
        label3.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));
        leftPanel.add(label3);

        // Structure 4

        JLabel label4 = new JLabel("Search for a product", JLabel.CENTER);
        label4.setForeground(new Color(252, 247, 247));
        label4.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));
        leftPanel.add(label4);



        JLabel label5 = new JLabel("Back to Home", JLabel.CENTER);
        label5.setForeground(new Color(255, 255, 255));
        label5.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));
        leftPanel.add(label5);



        label5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                Main_interface_page frame = new Main_interface_page();
                frame.setVisible(true);
            }
        });


        //////////////////////////////////rightPanel/////////////////////////////////////////////
        JPanel rightPanel = new JPanel(new GridLayout(1, 1, 0, 0));

        container.add(rightPanel, BorderLayout.CENTER);



              ///////////////////////////// وجهة منسدلة1 add a product


        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rightPanel.removeAll();
                JPanel q1 = new JPanel();
                q1.setBackground(new Color(255, 255, 255));
                rightPanel.add(q1);



                JPanel idPanel = new JPanel();
                idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.Y_AXIS));
                idPanel.setBackground(Color.WHITE);
                JLabel idLabel = new JLabel("ID:");
                JTextField idField = new JTextField(10);
                idPanel.add(idLabel);
                idPanel.add(idField);
                q1.add(idPanel);

                JPanel namePanel = new JPanel();
                namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
                namePanel.setBackground(Color.WHITE);
                JLabel nameLabel = new JLabel("Name:");
                JTextField nameField = new JTextField(10);
                namePanel.add(nameLabel);
                namePanel.add(nameField);
                q1.add(namePanel);

                JPanel pricePanel = new JPanel();
                pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));
                pricePanel.setBackground(Color.WHITE);
                JLabel priceLabel = new JLabel("Price:");
                JTextField priceField = new JTextField(10);
                pricePanel.add(priceLabel);
                pricePanel.add(priceField);
                q1.add(pricePanel);

                JPanel quantityPanel = new JPanel();
                quantityPanel.setLayout(new BoxLayout(quantityPanel, BoxLayout.Y_AXIS));
                quantityPanel.setBackground(Color.WHITE);
                JLabel quantityLabel = new JLabel("Quantity:");
                JTextField quantityField = new JTextField(10);
                quantityPanel.add(quantityLabel);
                quantityPanel.add(quantityField);
                q1.add(quantityPanel);


                JTextField imagePathField = new JTextField(20);
                imagePathField.setPreferredSize(new Dimension(200, 20));
                JLabel imageLabel = new JLabel();
                JButton selectButton = new JButton("Select Image");
                selectButton.setBackground(new Color(255, 255, 255));
                selectButton.setPreferredSize(new Dimension(120, 20));
                JPanel imagePanel = new JPanel();
                imagePanel.setLayout(new BoxLayout(imagePanel, BoxLayout.Y_AXIS));
                imagePanel.setBackground(Color.WHITE);
                imagePanel.add(selectButton);
                imagePanel.add(imagePathField);
                q1.add(imagePanel);

                q1.revalidate();


                selectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileChooser = new JFileChooser();
                        if (fileChooser.showOpenDialog(employyejframe.this) == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            imagePathField.setText(selectedFile.getAbsolutePath());

                            Image scaledImage = new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                            imageLabel.setIcon(new ImageIcon(scaledImage));

                            q1.add(imageLabel);
                            q1.revalidate();
                        }
                    }
                });



                JButton addButton = new JButton("Add");
                addButton.setBackground(new Color(240, 240, 240));
                addButton.setMaximumSize(new Dimension(80, 25));
                q1.add(addButton);

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Medicine m1 = new Medicine();
                        m1.setId(Integer.parseInt(idField.getText()));
                        m1.setName(nameField.getText());
                        m1.setPrice(Double.parseDouble(priceField.getText()));
                        m1.setQuantity(Integer.parseInt(quantityField.getText()));
                        String imagePath = imagePathField.getText();

                        idField.setText(null);
                        nameField.setText(null);
                        priceField.setText(null);
                        quantityField.setText(null);
                        imagePathField.setText(null);


                        try {


                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

                            PreparedStatement statement = connection.prepareStatement("INSERT INTO medicines (medicines_id, name, price, quantity, img) VALUES ('" + m1.getId() + "','" + m1.getName() + "','" + m1.getPrice() + "','" + m1.getQuantity() + "',?)");

                            File imageFile = new File(imagePath);
                            FileInputStream fis = new FileInputStream(imageFile);

                            statement.setBinaryStream(1, fis, (int) imageFile.length());

                            statement.executeUpdate();

                            fis.close();
                            statement.close();
                            connection.close();


                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        imageLabel.setIcon(null);


                    DefaultTableModel model = new DefaultTableModel();


                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Price");
                model.addColumn("Quantity");



                try

                    {


                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

                        PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines");

                        ResultSet resultSet = selectStatement.executeQuery();

                        while (resultSet.next()) {
                            model.addRow(new Object[]{resultSet.getInt("medicines_id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getInt("quantity")});
                        }


                        resultSet.close();
                        selectStatement.close();
                        connection.close();
                    } catch(
                    Exception ex)

                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                        JTable table = new JTable(model);
                        JScrollPane scrollPane = new JScrollPane(table);

                        scrollPane.setPreferredSize(new Dimension(700, 200));

                        JPanel tablePanel = new JPanel();
                        tablePanel.setLayout(new BorderLayout());
                        tablePanel.add(scrollPane, BorderLayout.CENTER);

                        q1.add(tablePanel, BorderLayout.CENTER);
                  q1.revalidate();

                }
                });





                revalidate();
                repaint();
            }
        });


/////////////////////// وجهة منسدلة 2 Edit a product////////
        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rightPanel.removeAll();
                JPanel q1 = new JPanel(new BorderLayout());
                q1.setBackground(new Color(255, 255, 255));
                rightPanel.add(q1);

                DefaultTableModel model = new DefaultTableModel();


                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Price");
                model.addColumn("Quantity");

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");
                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines");
                    ResultSet resultSet = selectStatement.executeQuery();

                    while (resultSet.next()) {
                        model.addRow(new Object[]{resultSet.getInt("medicines_id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getInt("quantity")});
                    }

                    resultSet.close();
                    selectStatement.close();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                JTable table = new JTable(model);
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                table.setDefaultRenderer(Object.class, renderer);


                JLabel idLabel = new JLabel("ID:");
                JTextField idField = new JTextField(5);
                JLabel nameLabel = new JLabel("New Name:");
                JTextField nameField = new JTextField(20);
                JLabel priceLabel = new JLabel("New Price:");
                JTextField priceField = new JTextField(10);
                JLabel quantityLabel = new JLabel("New Quantity:");
                JTextField quantityField = new JTextField(5);
                JButton editButton = new JButton("Edit Product");
                editButton.setBackground(new Color(240, 240, 240));

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.add(idLabel);
                buttonPanel.add(idField);
                buttonPanel.add(nameLabel);
                buttonPanel.add(nameField);
                buttonPanel.add(priceLabel);
                buttonPanel.add(priceField);
                buttonPanel.add(quantityLabel);
                buttonPanel.add(quantityField);
                buttonPanel.add(editButton);

                q1.add(new JScrollPane(table), BorderLayout.CENTER);
                q1.add(buttonPanel, BorderLayout.SOUTH);

                JPanel westPanel = new JPanel();
                westPanel.setBackground(new Color(252, 247, 247));
                westPanel.setPreferredSize(new Dimension(140, 0));
                q1.add(westPanel, BorderLayout.WEST);

                JPanel eastPanel = new JPanel();
                eastPanel.setBackground(new Color(255, 255, 255));
                eastPanel.setPreferredSize(new Dimension(140, 0));
                q1.add(eastPanel, BorderLayout.EAST);

                editButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(idField.getText());
                        String newName = nameField.getText();
                        double newPrice = Double.parseDouble(priceField.getText());
                        int newQuantity = Integer.parseInt(quantityField.getText());

                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");
                            PreparedStatement updateStatement = connection.prepareStatement("UPDATE medicines SET name=?, price=?, quantity=? WHERE medicines_id=?");
                            updateStatement.setString(1, newName);
                            updateStatement.setDouble(2, newPrice);
                            updateStatement.setInt(3, newQuantity);
                            updateStatement.setInt(4, id);
                            updateStatement.executeUpdate();
                            updateStatement.close();
                            connection.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                   //////////////////////////////////////////////////////////
                        rightPanel.removeAll();
                        JPanel q1 = new JPanel(new BorderLayout());
                        q1.setBackground(new Color(255, 255, 255));
                        rightPanel.add(q1);

                        DefaultTableModel model = new DefaultTableModel();


                        model.addColumn("ID");
                        model.addColumn("Name");
                        model.addColumn("Price");
                        model.addColumn("Quantity");

                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");
                            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines");
                            ResultSet resultSet = selectStatement.executeQuery();

                            while (resultSet.next()) {
                                model.addRow(new Object[]{resultSet.getInt("medicines_id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getInt("quantity")});
                            }

                            resultSet.close();
                            selectStatement.close();
                            connection.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        JTable table = new JTable(model);
                        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                        renderer.setHorizontalAlignment(SwingConstants.CENTER);
                        table.setDefaultRenderer(Object.class, renderer);


                        JLabel idLabel = new JLabel("ID:");
                        JTextField idField = new JTextField(5);
                        JLabel nameLabel = new JLabel("New Name:");
                        JTextField nameField = new JTextField(20);
                        JLabel priceLabel = new JLabel("New Price:");
                        JTextField priceField = new JTextField(10);
                        JLabel quantityLabel = new JLabel("New Quantity:");
                        JTextField quantityField = new JTextField(5);
                        JButton editButton = new JButton("Edit Product");
                        editButton.setBackground(new Color(240, 240, 240));

                        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        buttonPanel.add(idLabel);
                        buttonPanel.add(idField);
                        buttonPanel.add(nameLabel);
                        buttonPanel.add(nameField);
                        buttonPanel.add(priceLabel);
                        buttonPanel.add(priceField);
                        buttonPanel.add(quantityLabel);
                        buttonPanel.add(quantityField);
                        buttonPanel.add(editButton);

                        q1.add(new JScrollPane(table), BorderLayout.CENTER);
                        q1.add(buttonPanel, BorderLayout.SOUTH);

                        JPanel westPanel = new JPanel();
                        westPanel.setBackground(new Color(252, 247, 247));
                        westPanel.setPreferredSize(new Dimension(140, 0));
                        q1.add(westPanel, BorderLayout.WEST);

                        JPanel eastPanel = new JPanel();
                        eastPanel.setBackground(new Color(255, 255, 255));
                        eastPanel.setPreferredSize(new Dimension(140, 0));
                        q1.add(eastPanel, BorderLayout.EAST);

                        editButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                int id = Integer.parseInt(idField.getText());
                                String newName = nameField.getText();
                                double newPrice = Double.parseDouble(priceField.getText());
                                int newQuantity = Integer.parseInt(quantityField.getText());

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");
                                    PreparedStatement updateStatement = connection.prepareStatement("UPDATE medicines SET name=?, price=?, quantity=? WHERE medicines_id=?");
                                    updateStatement.setString(1, newName);
                                    updateStatement.setDouble(2, newPrice);
                                    updateStatement.setInt(3, newQuantity);
                                    updateStatement.setInt(4, id);
                                    updateStatement.executeUpdate();
                                    updateStatement.close();
                                    connection.close();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                //////////////////////////////////////////////////////////



                            }
                        });

                        revalidate();
                        repaint();

                   ////////////////////////////////////////////
                    }
                });

                revalidate();
                repaint();
            }
        });

/////////////////// وجهة منسدلة3 Delete a product///////////////
        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rightPanel.removeAll();
                JPanel q1 = new JPanel();
                q1.setBackground(new Color(230, 204, 255));
                rightPanel.add(q1);

                DefaultTableModel model = new DefaultTableModel();


                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Price");
                model.addColumn("Quantity");

                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");
                    PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines");
                    ResultSet resultSet = selectStatement.executeQuery();

                    while (resultSet.next()) {
                        model.addRow(new Object[]{resultSet.getInt("medicines_id"), resultSet.getString("name"), resultSet.getDouble("price"), resultSet.getInt("quantity")});
                    }

                    resultSet.close();
                    selectStatement.close();
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                JLabel idLabel = new JLabel("ID:");
                JTextField idField = new JTextField(5);
                idField.setHorizontalAlignment(JTextField.CENTER);

                JButton editButton = new JButton("Delete Product");
                editButton.setBackground(new Color(240, 240, 240));
                editButton.setHorizontalAlignment(SwingConstants.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.add(idLabel);
                buttonPanel.add(idField);
                buttonPanel.add(editButton);

                JTable table = new JTable(model);
                q1.setLayout(new BorderLayout());
                q1.add(new JScrollPane(table), BorderLayout.CENTER);
                q1.add(buttonPanel, BorderLayout.SOUTH);

                JPanel westPanel = new JPanel();
                westPanel.setBackground(new Color(241, 229, 91));
                westPanel.setPreferredSize(new Dimension(140, 0));
                q1.add(westPanel, BorderLayout.WEST);

                JPanel eastPanel = new JPanel();
                eastPanel.setBackground(new Color(241, 229, 91));
                eastPanel.setPreferredSize(new Dimension(140, 0));
                q1.add(eastPanel, BorderLayout.EAST);

                editButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(idField.getText());

                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");
                            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM medicines WHERE medicines_id=?");
                            deleteStatement.setInt(1, id);
                            deleteStatement.executeUpdate();
                            deleteStatement.close();
                            connection.close();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        JOptionPane.showMessageDialog(employyejframe.this, "product deleted " );

                    }
                });

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }





                revalidate();
                repaint();

                //////////////////////////////////////////////////////

                /////////////////////////////////////////////////////
            }
        });


////////////////// وجهة منسدلة4 Search for a product
        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rightPanel.removeAll();
                JPanel q1 = new JPanel();
                q1.setBackground(new Color(255, 255, 255));
                rightPanel.add(q1);


                JLabel idLabel = new JLabel("Enter Product ID:");
                JTextField idField = new JTextField(5);
                JButton searchButton = new JButton("Search");
                searchButton.setBackground(new Color(240, 240, 240));

                q1.add(idLabel);
                q1.add(idField);
                q1.add(searchButton);

                DefaultTableModel model = new DefaultTableModel();

                model.addColumn("ID");
                model.addColumn("Name");
                model.addColumn("Price");
                model.addColumn("Quantity");



                searchButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int id = Integer.parseInt(idField.getText());

                        try {


                            // Connect to the database
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/pharmacy", "root", "123456");

                            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM medicines WHERE medicines_id = ?");
                            selectStatement.setInt(1, id);

                            ResultSet resultSet = selectStatement.executeQuery();



                            while (resultSet.next()) {
                                int foundId = resultSet.getInt("medicines_id");
                                String name = resultSet.getString("name");
                                double price = resultSet.getDouble("price");
                                int quantity = resultSet.getInt("quantity");

                                model.addRow(new Object[]{foundId, name, price, quantity});
                            }

                            // Close resources
                            resultSet.close();
                            selectStatement.close();
                            connection.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(employyejframe.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                });
                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);
                q1.add(scrollPane);

                revalidate();
                repaint();
            }
        });







        /////////////////////////////////////////////
        setTitle("employyes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 825);
        setLocationRelativeTo(null);

    }


}
