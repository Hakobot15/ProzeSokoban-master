package View;

import Model.LevelLoader;


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
    private int spaceY;
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
    /**
     * zmienna potrzebna do odliczania czasu;
     */
    private int counter = 1;
    /**
     * Zmienna przechowujaca warunek skonczenia planszy
     */
    private boolean completed = false;


    public ImagePanel(Image img, JFrame frame, int DX,int DY, String tmp, int liczbaZyc) {
        this.liczbaZyc = Integer.toString(liczbaZyc);
        LevelLoader level;
        level = new LevelLoader(tmp);
        czasGry = level.getTime();
        spaceX = DX;
        spaceY = DY;
        this.frame = frame;
        this.img = img;
        setLayout(null);
        addComponentListener(this);
        frame.addWindowStateListener(this);
        runingTime = Integer.toString(czasGry)+1;
        wyswietlanieCzasu(); // wyswietla czas gry
        this.setPreferredSize(new Dimension(DX,DY)); // ustalanie wielkosc
        frame.getContentPane().add(this,BorderLayout.NORTH); // ustalanie pozycji
    }

    /**
     * Wątek który odlicza czas i go wyświetla
     */
    private void wyswietlanieCzasu() {
    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                while (czasGry >= 0) {
                    if(completed) {
                    Thread.currentThread().interrupt();
                    }
                    if (czasGry == 0) {
                        runingTime = "Out Of Time!";
                        completed = true;
                    }
                    else {
                        runingTime = "Time left: " + Integer.toString(czasGry);
                    }
                    frame.repaint();
                    Thread.sleep(20);
                    if(counter%50==0) {
                        czasGry--;
                        counter++;
                    }
                    else counter++;
                }
            } catch (InterruptedException exp) {
                Thread.currentThread().interrupt();
            }
        }
    });
    t.start();
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

    /**
     * metoda ustalajaca status ukonczenia mapy
     */
    public void setCompleted(){ completed = true;}

    /**
     *
     * @return zwraca status ukonczenia mapy
     */
    public boolean getCompleted(){return completed;}
}
