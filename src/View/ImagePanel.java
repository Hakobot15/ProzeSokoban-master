package View;

import Model.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ImagePanel extends JPanel implements ComponentListener, WindowStateListener{

    private Image img;
    private int spaceX;
    private JFrame frame;
    static int spaceY;
    private  String liczbaZyc;
    private  int czasGry;
    private String runingTime;

    public ImagePanel(Image img, JFrame frame, String filename, int DX,int DY) {
        spaceX = DX;
        spaceY = DY;
        Settings ustawienia= new Settings(filename);
        liczbaZyc =Integer.toString(ustawienia.getLives());
        czasGry = ustawienia.getTimeScale();
        this.frame = frame;
        this.img = img;
        setLayout(null);
        addComponentListener(this);
        frame.addWindowStateListener(this);
        runingTime = Integer.toString(czasGry);
        wyswietlanieCzasu();
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
                    Thread.sleep(1000);
                    czasGry--;
                }
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            }
        }
    });
    t.start();
}

    private void draw()
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
        spaceX = (int) r.width;
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
