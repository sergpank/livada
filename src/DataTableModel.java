import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

    private Data[][] rowData;
    private String[] columnNames;

    public DataTableModel(Data[][] rowData, String[] columnNames) {
        this.rowData = rowData;
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
