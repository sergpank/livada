import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    private DataTableModel tableModel;

    public MenuBar(DataTableModel tableModel) {
        this.tableModel = tableModel;
        JMenu menu = new JMenu("Меню");
        this.add(menu);

        menu.add(createAddRowColItem());
        menu.add(createMockMenuItem("Удалить"));
        menu.add(createMockMenuItem("Макс Урожай"));
        menu.add(createMockMenuItem("Средн Урожай"));
        menu.add(createMockMenuItem("Общий урожай"));
        menu.add(createMockMenuItem("Lin Spec"));
        menu.add(createMockMenuItem("Сектор К деревьев"));
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

    private JMenuItem createAddRowColItem() {
        JMenuItem item = new JMenuItem();
        item.setText("Добавить");
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
                    tableModel.addRow(row);
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
}
