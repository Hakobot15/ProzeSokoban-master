package View;

import Model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ImagePanel extends JPanel implements ComponentListener, WindowStateListener{
    /**
     * Zmienna przechowujaca nasze tlo(obraz)
     */
    private Image img;
    /**
     * Zmienna sklaujac teksture obrazka w poziomie
     */
    private int spaceX;
    /**
     * Zmienna skalujaca teksture obrazka w pionie
     */
    static int spaceY;
    /**
     * Jest to JPanel na ktorym bedziemy wywolywac malowanie, lisnery
     */
    private JFrame frame;
    /**
     * Zmienna przechowujaca aktualnie posiadane zycia
     */
    private  String liczbaZyc;
    /**
     * Zmienna przechowujaca odczytany czas na mape
     */
    private  int czasGry;
    /**
     * Zmienna przechowujaca pozostaly czas na wykonanie celow mapy
     */
    private String runingTime;
    int tmpCzas = 1;

    public ImagePanel(Image img, JFrame frame, String filename, int DX,int DY) {
        spaceX = DX;
        spaceY = DY;
        Settings ustawienia= new Settings(filename);
        liczbaZyc =Integer.toString(ustawienia.getLives()); // odczytuje z pliku ile mamy zyc
        czasGry = ustawienia.getTimeScale(); // odcztuje czasu
        this.frame = frame;
        this.img = img;
        setLayout(null); // w sumie to nie wiem co to jest xD
        addComponentListener(this);
        frame.addWindowStateListener(this);
        runingTime = Integer.toString(czasGry);
        wyswietlanieCzasu(); // wyswietla czas gry
    }
private void wyswietlanieCzasu() {
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (czasGry >= 0) {
                    if (czasGry == 0)
                        runingTime = "Out Of Time!";
                    else {
                        runingTime = "Time left: " + Integer.toString(czasGry);
                    }
                    draw();
                    if(tmpCzas % 6 == 0 ) // timeout na 1 sekunde
                    {
                        czasGry--;
                        tmpCzas = 1;
                    }
                    else { tmpCzas++;}
                    if() // Tutaj trzeba zrobic zeby srawdzalo czy jest level ukonczony
                    Thread.sleep(20); //

                }
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            }
        }
    });
    t.start();
}

    private void draw() // dodalem to bo wywolanie repaint w watku to trzeba sie za duzo jebac
    {
        this.repaint();

    }

    public void paintComponent(Graphics g) {

        g.drawImage(img, 0, 0,spaceX, spaceY, this);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.GREEN);
        g.drawString(liczbaZyc,20,40);
        g.drawString(runingTime,200,40);
    }

    /**
     *
     * Event skalujący panel wyswietlajacy informacje
     */
    public void componentResized(ComponentEvent e) {
        Rectangle r = getBounds();
        spaceX = r.width;
    }
    public void componentHidden(ComponentEvent e) {
    }
    public void componentMoved(ComponentEvent e) {
    }
    public void componentShown(ComponentEvent e) {
    }

    /**
     * Przeciazenie metody z WindowStateListner
     * Wylicza nowe przeliczniki tekstur podczas maxymalizowania i minimalizowania okna
     */
    public void windowStateChanged(WindowEvent evt) {
        int oldState = evt.getOldState();
        int newState = evt.getNewState();

        if ((oldState & Frame.MAXIMIZED_BOTH) == 0 && (newState & Frame.MAXIMIZED_BOTH) != 0) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            spaceX = (int) screenSize.getWidth();

        } else if ((oldState & Frame.MAXIMIZED_BOTH) != 0 && (newState & Frame.MAXIMIZED_BOTH) == 0) {
            Rectangle r = frame.getBounds();
            spaceX = r.width;

        }

    }
}
