package View;

import Model.Settings;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DDcreo on 2016-05-14.
 */
public class MyRunnable implements Runnable {
    /**
     * Zmienna przechowujaca panel gry
     */
    private GameAreaPanel tmpCurrentArea;
    /**
     * Zmienna przechowujaca panel wynikow
     */
    private GameStatePanel tmpCurrentState;
    /**
     * Zmienna potrzebna do odczytywania listy map
     */
    private BufferedReader tmpReader;
    /**
     * zmiennap przechowujaca ekran gry
     */
    private JFrame frame;
    /**
     * Zmienna przechowujaca odczytany w danej chwili nazwe mapy
     */
    private String tmp = null;
    /**
     * Zmienne przechowujaca standardowe wymiary mapy
     */
    private int DEFAULT_WIDTH;
    private int DEFAULT_HIGHT;
    /**
     * Zmienna przechowujaca nazwe pliku w ktorym sa nazwy mapy
     */
    private String mapNames;
    /**
     * Zmienna informujaca ktora to mapa
     */
    private int licznik = 0;
    /**
     * Zmienna przechowujaca aktualny wynik
     */
    private int score = 0;
    /**
     * Zmienna przechowujaca ilosc zyc jakie nam pozostaly
     */
    private int liczbaZyc;
    /**
     * Przeliczniki do punktacji
     */
    private int przelicznikCzasu;
    private int przelicznikPunktow;

    public MyRunnable(JFrame frame, int DEFAULT_WIDTH, int DEFAULT_HIGHT, String mapNames)
    {
        this.mapNames = mapNames;
        this.frame = frame;
        this.DEFAULT_HIGHT = DEFAULT_HIGHT;
        this.DEFAULT_WIDTH = DEFAULT_WIDTH;
        Settings ustawienia= new Settings("Ustawienia.txt");
        liczbaZyc = ustawienia.getLives(); // odczytuje z pliku ile mamy zyc
        przelicznikCzasu = ustawienia.getTimeScale();
        przelicznikPunktow = ustawienia.getPointScale();
        try {
            tmpReader = new BufferedReader(new FileReader(mapNames));
            if ((tmp = tmpReader.readLine()) != null) {
                tmpCurrentState = new GameStatePanel(frame, tmp, liczbaZyc);
                tmpCurrentArea = new GameAreaPanel(tmp, frame, DEFAULT_WIDTH, DEFAULT_HIGHT);
            } else return;
        } catch (IOException ex)
        {
            ex.printStackTrace();
        } finally {
            try{
                tmpReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void run() {
        try {
            while(Thread.currentThread().isAlive()) {

                if (tmpCurrentArea.getCompleted() == true) {
                    wywolanieMapy();
                }
                if(tmpCurrentState.getPanel().getCompleted())
                {
                    liczbaZyc--;
                    if(liczbaZyc<0) {
                        Thread.currentThread().interrupt();
                        return;
                    }

                    rebootMapy();
                }
                Thread.sleep(20);
            }
        } catch (InterruptedException exp) {
            Thread.currentThread().interrupt();
        }

    }

    /**
     * Ponownie wczytanie tego samego poziomu
     */
    public void rebootMapy()
    {
        try {
            tmpReader = new BufferedReader(new FileReader(mapNames));
            for (int i = 0; i<licznik;i++)
            {
                tmpReader.readLine();
            }
            if ((tmp = tmpReader.readLine()) != null ) {
                frame.remove(tmpCurrentArea);
                frame.revalidate();
                frame.remove(tmpCurrentState.getPanel());
                frame.revalidate();
                tmpCurrentState = new GameStatePanel( frame, tmp, liczbaZyc);
                frame.revalidate();
                tmpCurrentArea = new GameAreaPanel(tmp, frame, DEFAULT_WIDTH, DEFAULT_HIGHT);
                frame.revalidate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                tmpReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Wczytanie nowego poziomu mapy
     */
    public void wywolanieMapy() {
       // score += tmpCurrentState.panel.getCzasGry()*tmpCurrentArea.get;
        try {
            tmpReader = new BufferedReader(new FileReader(mapNames));
            for (int i = 0; i<=licznik;i++)
            {
                tmpReader.readLine();
            }
            if ((tmp = tmpReader.readLine()) != null ) {
                tmpCurrentState.getPanel().setCompleted();
                licznik++;
                frame.remove(tmpCurrentArea);
                frame.remove(tmpCurrentState);
                tmpCurrentState = new GameStatePanel(frame, tmp, liczbaZyc);
                tmpCurrentArea = new GameAreaPanel(tmp, frame, DEFAULT_WIDTH, DEFAULT_HIGHT);
                frame.revalidate();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                tmpReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
