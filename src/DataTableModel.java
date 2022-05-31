import javax.swing.*;
import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

    private Data[][] rowData;
    private String[] columnNames;

    public DataTableModel(Data[][] rowData, String[] columnNames) {
        this.rowData = rowData;
        this.columnNames = columnNames;
    }

    public Data[][] getRowData() {
        return rowData;
    }

    public void addColumn(int i) {
        if (i > rowData[0].length) {
            String message = String.format("Невозможно добавить столбец %d так как в таблце всего %d колонок", i, rowData[0].length);
            JOptionPane.showMessageDialog(null, message, "Неправильный индекс столбца", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Data[][] newRowData = new Data[rowData.length][rowData[0].length + 1];
        for (int row = 0; row < rowData.length; row++) {
            for (int col = 0; col < rowData[0].length + 1; col++) {
                if (col == i) {
                    newRowData[row][col] = new Data("", 0.);
                } else if (col > i) {
                    newRowData[row][col] = rowData[row][col - 1];
                } else {
                    newRowData[row][col] = rowData[row][col];
                }
            }
        }
        rowData = newRowData;

        String[] newColumnNames = new String[columnNames.length + 1];
        for (int col = 0; col < columnNames.length + 1; col++) {
            newColumnNames[col] = col + 1 + "";
        }
        columnNames = newColumnNames;

        this.fireTableDataChanged();
        this.fireTableStructureChanged();
    }

    public void removeColumn(int i) {

    }

    public void addRow(int i) {

    }

    public void removeRow(int i) {

    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return rowData.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return rowData[row][col];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        rowData[row][col] = new Data(value.toString());
        fireTableCellUpdated(row, col);
    }
}
