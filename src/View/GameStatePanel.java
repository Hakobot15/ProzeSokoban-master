package View;
import javax.swing.*;
import java.awt.*;



/**
 * klasa zawierajaca Panel z informacjaim o rozgrywce
 */
public class GameStatePanel extends JPanel {
    private final static int DEFAULT_SPACEY = 60;
    private final static int DEFAULT_SPACEX = 520;

    public GameStatePanel(String filename, JFrame frame) {
        ImagePanel panel = new ImagePanel(new ImageIcon("pic\\background.jpg").getImage(),frame, filename,DEFAULT_SPACEX, DEFAULT_SPACEY);
        panel.setPreferredSize(new Dimension(DEFAULT_SPACEX,DEFAULT_SPACEY));
        frame.getContentPane().add(panel,BorderLayout.NORTH);

    }
}