package logic;

import ui.DataTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LinSpecAction implements ActionListener {

    private DataTableModel tableModel;

    public LinSpecAction(DataTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<Integer> indexes = new ArrayList<>();
        Data[][] rowData = tableModel.getRowData();
        for (int i = 0; i < rowData.length; i++) {
            int cnt = 0;
            for (int j = 0; j < rowData[i].length - 1; j++) {
                if (rowData[i][j].getTree().equals(rowData[i][j + 1].getTree())) {
                    cnt++;
                }
            }
            if (cnt == rowData[i].length - 1) {
                indexes.add(i);
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("LinSpec.txt"));
             BufferedReader br = new BufferedReader(new FileReader("Derevia.in"))) {
            int cnt = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if (indexes.contains(cnt - 1)) {
                    bw.write(line);
                    bw.newLine();
                }
                cnt ++;
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        JOptionPane.showMessageDialog(null, "Сдедующие строки были скопированы из Derevia.in в LinSpec.txt : " + indexes);
    }
}
