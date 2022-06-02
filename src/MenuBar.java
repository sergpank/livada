import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MenuBar extends JMenuBar {
    private DataTableModel tableModel;

    public MenuBar(DataTableModel tableModel) {
        this.tableModel = tableModel;
        JMenu menu = new JMenu("Меню");
        this.add(menu);

        menu.add(createAddRowColItem("Добавить"));
        menu.add(createRemoveRowColItem("Удалить"));
        menu.add(createMaxHarvestItem("Максимальный Урожай"));
        menu.add(createAverageHarvestItem("Средний Урожай"));
        menu.add(createCountHarvestItem("Общий урожай"));
        menu.add(createMockMenuItem("Lin Spec"));
        menu.add(createMockMenuItem("Сектор К деревьев"));
    }

    private JMenuItem createCountHarvestItem(String text) {
        JMenuItem item = new JMenuItem();
        item.setText(text);

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Count and Sort harvest");

                Map<String, Double> map = new HashMap<>();

                for (Data[] row : tableModel.getRowData()) {
                    for (Data d : row) {
                        Double totalTreeHarvest = map.containsKey(d.getTree()) ? map.get(d.getTree()) + d.getHarvest() : d.getHarvest();
                        map.put(d.getTree(), totalTreeHarvest);
                    }
                }
                Data[] data = new Data[map.keySet().size()];

                int pos = 0;
                for (Map.Entry<String, Double> entry : map.entrySet()) {
                    data[pos++] = new Data(entry.getKey(), entry.getValue());
                }

                for (int i = 0; i < data.length - 1; i++) {
                    for (int j = i + 1; j < data.length; j++) {
                        if (data[i].getHarvest() > data[j].getHarvest()) {
                            Data tmp = data[i];
                            data[i] = data[j];
                            data[j] = tmp;
                        }
                    }
                }

                StringBuilder sb = new StringBuilder();
                for (Data d : data) {
                    sb.append(d.toString()).append('\n');
                }

                JOptionPane.showMessageDialog(null,
                        sb.toString(),
                        "Общий урожай",
                        JOptionPane.PLAIN_MESSAGE);
            }
        });

        return item;
    }

    private JMenuItem createAverageHarvestItem(String text) {
        JMenuItem item = new JMenuItem();
        item.setText(text);

        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Avg harvest");

                JPanel panel = new JPanel();
                panel.add(new JLabel("Выберите вид дерева"));
                String[] trees = getUniqueTrees(tableModel.getRowData());
                JComboBox<String> comboBox = new JComboBox<>(trees);
                panel.add(comboBox);

                double harvest = 0.;
                int cnt = 0;

                int result = JOptionPane.showOptionDialog(null,
                        panel, "Определение средней урожайности дерева",
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
                            if (d.getTree().equals(tree)) {
                                harvest += d.getHarvest();
                                cnt++;
                            }
                        }
                    }
                } else {
                    System.out.println("Cancel avg cnt");
                }
                double avg = harvest / cnt;
                String msg = String.format("В среднем дерево [%s] дало %.2f центнеров урожая", comboBox.getSelectedItem(), avg);
                JOptionPane.showMessageDialog(null, msg);
            }
        });

        return item;
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
                    System.out.println("Cancel max search");
                }
                String msg = String.format("Самое урожайное дерево [%s] дало %.2f центнеров урожая", comboBox.getSelectedItem(), maxHarvest);
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
        });
        return item;
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
