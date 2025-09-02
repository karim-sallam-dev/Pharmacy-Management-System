import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class invoice extends JFrame {

    public invoice(queues n) {
        setTitle("Invoice");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        container.setBackground(Color.WHITE);


        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");

        for (int i = 0; i < n.nItems; i++) {
            model.addRow(new Object[]{n.name[i], n.price[i], n.Quantity[i]});
        }

        JTable table = new JTable(model);

        table.setBackground(new Color(255, 255, 255));
        table.setForeground(Color.BLACK);

        table.setFont( new Font("Arial", Font.PLAIN, 12));

        JTableHeader header = table.getTableHeader();
        header.setFont( new Font("Arial", Font.PLAIN, 12));
        header.setForeground(Color.BLUE);

        table.setForeground(Color.BLACK);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);

        container.add(scrollPane, BorderLayout.CENTER);

        JLabel sumLabel = new JLabel("Total invoice: " + n.sum()+" EG");

        sumLabel.setForeground(new Color(0, 128, 0));
        sumLabel.setFont(new Font("Arial", Font.BOLD, 14));



        container.add(sumLabel, BorderLayout.SOUTH);

        sumLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sumLabel.setVerticalAlignment(SwingConstants.CENTER);

        setVisible(true);
    }
}