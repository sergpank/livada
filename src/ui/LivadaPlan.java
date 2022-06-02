package ui;

import javax.swing.*;
import java.awt.*;

import logic.Data;

public class LivadaPlan extends JPanel {

    private DataTableModel tableModel;

    public LivadaPlan(Data[][] data) {
        super(new BorderLayout());

        String[] header = createHeader(data[0].length);

        tableModel = new DataTableModel(data, header);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JTable rowTable = new RowNumberTable(table);
        scrollPane.setRowHeaderView(rowTable);
        scrollPane.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());
        add(scrollPane, BorderLayout.CENTER);
    }

    public DataTableModel getTableModel() {
        return tableModel;
    }

    private String[] createHeader(int length) {
        String[] header = new String[length];
        for (int i = 1; i <= length; i++) {
            header[i - 1] = i + "";
        }
        return header;
    }
}
