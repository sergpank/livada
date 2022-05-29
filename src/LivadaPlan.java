import javax.swing.*;
import java.awt.*;

public class LivadaPlan extends JPanel {

    public LivadaPlan() {
        super(new BorderLayout());

        String[] columns = new String[]{"Дерево", "Дерево", "Дерево"};

        String[][] data = new String[][] {
                {"Яблоко", "Вишня", "Груша"},
                {"Абрикос", "Вишня", "Персик"},
                {"Яблоко", "Вишня", "Яблоко"},
                {"Яблоко", "Яблоко", "Яблоко"}
        };

        JTable table = new JTable(data, columns);

        add(table, BorderLayout.CENTER);
    }
}
