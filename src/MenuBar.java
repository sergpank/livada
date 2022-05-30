import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        add(createAddRowColumItem());
        add(createMockMenuItem("Remove Row/Col"));
        add(createMockMenuItem("Max Harvest"));
        add(createMockMenuItem("Avg Harvest"));
        add(createMockMenuItem("Total Harvest"));
        add(createMockMenuItem("Lin Spec"));
        add(createMockMenuItem("K-trees sector"));
    }

    private JMenuItem createMockMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.addActionListener(e -> JOptionPane.showMessageDialog(null, text));
        return item;
    }

    private JMenuItem createAddRowColumItem() {
        JMenuItem item = new JMenuItem();
        item.setText("Add Row/Column");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Create Row/Col");

                Object[] options = {"Add row", "Add column", "Quit"};

                JPanel panel = new JPanel();
                panel.add(new JLabel("Enter number number of row or column that should be added"));
                JTextField textField = new JTextField(10);
                panel.add(textField);

                int result = JOptionPane.showOptionDialog(null,
                        panel, "Enter a Number",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        null);
                if (result == JOptionPane.YES_OPTION) {
                    System.out.println("Add row # " + textField.getText());
                } else if (result == JOptionPane.NO_OPTION) {
                    System.out.println("Add column # " + textField.getText());
                } else {
                    System.out.println("Cancel add row/col");
                }
            }
        });
        return item;
    }
}
