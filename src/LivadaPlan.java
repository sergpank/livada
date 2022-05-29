import javax.swing.*;
import java.awt.*;

public class LivadaPlan extends JPanel {

    public LivadaPlan(Data[][] data) {
        super(new BorderLayout());

        String[] header = createHeader(data[0].length);

        JTable table = new JTable(new DataTableModel(data, header));
        JScrollPane scrollPane = new JScrollPane(table);

        JTable rowTable = new RowNumberTable(table);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());

        add(scrollPane, BorderLayout.CENTER);
    }

    private String[] createHeader(int length) {
        String[] header = new String[length];
        for (int i = 1; i <= length; i++) {
            header[i - 1] = i + "";
        }
        return header;
    }
}
