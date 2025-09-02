import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main_interface_page extends JFrame {
    public Main_interface_page() {
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridLayout(2, 1, 0, 0));

        JPanel l1 = new JPanel(new BorderLayout());
        l1.setBackground(new Color(0, 0, 0, 0));

        backgroundPanel.add(l1);

        JLabel text1 = new JLabel("Welcome to Delta Pharmacy ");
        text1.setHorizontalAlignment(SwingConstants.CENTER);
        text1.setFont(new Font("Tahoma", Font.BOLD, 20));
        l1.add(text1, BorderLayout.CENTER);

        JLabel text2 = new JLabel("Choose the login mode first ");
        text2.setHorizontalAlignment(SwingConstants.CENTER);
        text2.setFont(new Font("Tahoma", Font.BOLD, 14));
        l1.add(text2, BorderLayout.SOUTH);

        JPanel l2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 10));
        l2.setBackground(new Color(0, 0, 0, 0));
        backgroundPanel.add(l2);


        JButton Customers = new JButton("Customer destination");
        Customers.setPreferredSize(new Dimension(240, 90));
        Customers.setBackground(new Color(59, 107, 229));
        Customers.setForeground(new Color(16, 14, 14));
        Customers.setFont(new Font("Arial", Font.BOLD, 18));


        JButton employees = new JButton("Staff destination ");
        employees.setPreferredSize(new Dimension(240, 90));
        employees.setBackground(new Color(59, 107, 229));
        employees.setForeground(new Color(16, 14, 14));
        employees.setFont(new Font("Arial", Font.BOLD, 18));



        l2.add(Customers);
        l2.add(employees);

        Customers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login frame1 = new login();
                frame1.setVisible(true);
            }
        });
        employees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginemployee frame1 = new loginemployee();
                frame1.setVisible(true);
            }
        });

        setContentPane(backgroundPanel);

        setTitle("Delta Pharmacy");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 825);
        setLocationRelativeTo(null);
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            backgroundImage = new ImageIcon(getClass().getResource("./assets/1.jpg")).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }


}
