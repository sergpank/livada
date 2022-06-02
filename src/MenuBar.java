import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import java.util.TreeSet;

public class MenuBar extends JMenuBar {
    private DataTableModel tableModel;

    public MenuBar(DataTableModel tableModel) {
        this.tableModel = tableModel;
        JMenu menu = new JMenu("Меню");
        this.add(menu);

        menu.add(createAddRowColItem("Добавить"));
        menu.add(createRemoveRowColItem("Удалить"));
        menu.add(createMaxHarvestItem("Макс Урожай"));
        menu.add(createMockMenuItem("Средн Урожай"));
        menu.add(createMockMenuItem("Общий урожай"));
        menu.add(createMockMenuItem("Lin Spec"));
        menu.add(createMockMenuItem("Сектор К деревьев"));
    }

    private JMenuItem createMaxHarvestItem(String text) {
        JMenuItem item = new JMenuItem();
        item.setText(text);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Max harvest");

                JPanel panel = new JPanel();
                panel.add(new JLabel("Выберите вид дерева"));
                String[] trees = getUniqueTrees(tableModel.getRowData());
                JComboBox<String> comboBox = new JComboBox<>(trees);
                panel.add(comboBox);

                double maxHarvest = 0.;

                int result = JOptionPane.showOptionDialog(null,
                        panel, "Определение коррдинат самого урожайного дерева",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);
                if (comboBox.getSelectedItem() == null || comboBox.getSelectedItem().toString().trim().isEmpty()) {
                    System.out.println("Nothing was selected");
                    return;
                }
                if (result == JOptionPane.OK_OPTION) {
                    String tree = comboBox.getSelectedItem().toString();
                    System.out.println("Checking harvest for : " + tree);
                    for (Data[] row : tableModel.getRowData()) {
                        for (Data d : row) {
                            if (d.getTree().equals(tree) && d.getHarvest() > maxHarvest) {
                                maxHarvest = d.getHarvest();
                            }
                        }
                    }
                } else {
                    System.out.println("Cancel add row/col");
                }
                String msg = String.format("Саомое урожайное дерево [%s] дало %.2f центнеров урожая", comboBox.getSelectedItem(), maxHarvest);
                JOptionPane.showMessageDialog(null, msg);
            }
        });
        return item;
    }

    private String[] getUniqueTrees(Data[][] rowData) {
        Set<String> trees = new TreeSet<>();
        for (Data[] array : rowData) {
            for (Data data : array) {
                trees.add(data.getTree());
            }
        }
        return trees.toArray(new String[0]);
    }

    private JMenuItem createRemoveRowColItem(String text) {
        JMenuItem item = new JMenuItem();
        item.setText(text);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Remove Row/Col");
                Object[] options = {"Удалить строку", "Удалить колонку", "Отмена"};


                JPanel panel = new JPanel();
                panel.add(new JLabel("Введите номер строки или стоблца который должен быть удален"));
                JTextField textField = new JTextField(10);
                panel.add(textField);

                int result = JOptionPane.showOptionDialog(null,
                        panel, "Введите номер строки / столбца",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        null);
                if (textField.getText().trim().isEmpty()) {
                    return;

                }
                if (result == JOptionPane.YES_OPTION) {
                    int row = Integer.parseInt(textField.getText());
                    System.out.println("Remove row # " + row);
                    tableModel.removeRow(row - 1);
                } else if (result == JOptionPane.NO_OPTION) {
                    int column = Integer.parseInt(textField.getText());
                    System.out.println("Remove column # " + column);
                    tableModel.removeColumn(column - 1);
                } else {
                    System.out.println("Cancel add row/col");
                }
            }
        });    return item;

    }

    private JMenuItem createAddRowColItem(String text) {
        JMenuItem item = new JMenuItem();
        item.setText(text);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Row/Col");

                Object[] options = {"Добавить строку", "Добавить колонку", "Отмена"};

                JPanel panel = new JPanel();
                panel.add(new JLabel("Введите номер строки или стоблца который должен быть добавлен"));
                JTextField textField = new JTextField(10);
                panel.add(textField);

                int result = JOptionPane.showOptionDialog(null,
                        panel, "Введите номер строки / столбца",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        null);
                if (textField.getText().trim().isEmpty()) {
                    return;
                }
                if (result == JOptionPane.YES_OPTION) {
                    int row = Integer.parseInt(textField.getText());
                    System.out.println("Add row # " + row);
                    tableModel.addRow(row - 1);
                } else if (result == JOptionPane.NO_OPTION) {
                    int column = Integer.parseInt(textField.getText());
                    System.out.println("Add column # " + column);
                    tableModel.addColumn(column - 1);
                } else {
                    System.out.println("Cancel add row/col");
                }
            }
        });
        return item;
    }

    private JMenuItem createMockMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, text);
            }
        });
        return item;
    }
}
