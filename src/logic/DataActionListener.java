package logic;

import ui.DataTableModel;

import java.awt.event.ActionListener;

public abstract class DataActionListener  implements ActionListener {

    protected DataTableModel tableModel;

    public DataActionListener(DataTableModel tableModel) {
        this.tableModel = tableModel;
    }
}
