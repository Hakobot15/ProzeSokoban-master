package View;
import javax.swing.*;
import java.awt.*;



/**
 * klasa zawierajaca Panel z informacjaim o rozgrywce
 */
public class GameStatePanel extends JPanel {
    /**
     * Zmiena przechowujaca standardowa wysokosc panelu
     */
    private final static int DEFAULT_SPACEY = 60;
    /**
     * Zmienna przechowujaca standardowa szerokosc panelu
     */
    private final static int DEFAULT_SPACEX = 520;
    /**
     * Tlo naszego panelu wynikow
     */
    private ImagePanel panel;

    public GameStatePanel(JFrame frame, String tmp, int liczbaZyc) {
        panel = new ImagePanel(new ImageIcon("pic\\background.jpg").getImage(),frame,DEFAULT_SPACEX, DEFAULT_SPACEY, tmp, liczbaZyc );


    }

    /**
     *
     * @return Zwraca nasze tlo
     */
    public ImagePanel getPanel(){return panel;}
}