import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }

        //Create and set up the window.
        JFrame frame = new JFrame("Livada");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DataReader dataReader = new DataReader();

        String[][] trees = dataReader.readTrees("Derevia.in");
        double[][] harvest = dataReader.readHarvest("Urojai.in", trees.length);

        Data[][] data = dataReader.mergeData(trees, harvest);

        //Create and set up the content pane.
        LivadaPlan livadaPlan = new LivadaPlan(data);
        livadaPlan.setOpaque(true);
        frame.setContentPane(livadaPlan);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}