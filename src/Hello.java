import javax.swing.*;

/** Hello World dans un JFrame */
public class Hello {
    /** La méthode principale crée une fenêtre avec un Jlabel "Hello World!" **/
    public static void main(String [] args) {
        JFrame f = new JFrame("Test Windows");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new JLabel("Hello World!"));
        f.pack();
        f.setVisible(true);
    }
}
