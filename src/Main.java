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
        //Create and set up the window.
        JFrame frame = new JFrame("Livada");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        LivadaPlan livadaPlan = new LivadaPlan();
        livadaPlan.setOpaque(true);
        frame.setContentPane(livadaPlan);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}