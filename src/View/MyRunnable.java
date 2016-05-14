package View;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by DDcreo on 2016-05-14.
 */
public class MyRunnable implements Runnable {
    private GameAreaPanel tmpCurrentArea;
    private GameStatePanel tmpCurrentState;
    private BufferedReader tmpReader;
    private JFrame Main;
    String tmp = null;
    private int DEFAULT_WIDTH;
    private int DEFAULT_HIGHT;
    private String mapNames;
    private int licznik = 0;
    private int a = 1;
    private String ustawienia = "Ustawienia.txt";

    public MyRunnable(JFrame Main, int DEFAULT_WIDTH, int DEFAULT_HIGHT, String mapNames)
    {
        this.mapNames = mapNames;
        this.Main = Main;
        this.DEFAULT_HIGHT = DEFAULT_HIGHT;
        this.DEFAULT_WIDTH = DEFAULT_WIDTH;
        try {
            tmpReader = new BufferedReader(new FileReader(mapNames));
            if ((tmp = tmpReader.readLine()) != null) {
                tmpCurrentState = new GameStatePanel(ustawienia, Main, tmp);
                tmpCurrentArea = new GameAreaPanel(tmp, Main, DEFAULT_WIDTH, DEFAULT_HIGHT);
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
            while(a>0) {
                if (tmpCurrentArea.getCompleted() == true) {
                    wywolanieMapy();
                }
                if(tmpCurrentState.panel.getCompleted())
                {
                    rebootMapy();
                }
                Thread.sleep(20);
            }
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        }

    }
    public void rebootMapy()
    {
        try {
            tmpReader = new BufferedReader(new FileReader(mapNames));
            for (int i = 0; i<licznik;i++)
            {
                tmpReader.readLine();
            }
            if ((tmp = tmpReader.readLine()) != null ) {
                Main.remove(tmpCurrentArea);
                Main.revalidate();
                Main.remove(tmpCurrentState.panel);
                Main.revalidate();
                tmpCurrentState = new GameStatePanel(ustawienia, Main, tmp);
                Main.revalidate();
                tmpCurrentArea = new GameAreaPanel(tmp, Main, DEFAULT_WIDTH, DEFAULT_HIGHT);
                Main.revalidate();
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
    public void wywolanieMapy() {
        try {
            tmpReader = new BufferedReader(new FileReader(mapNames));
            for (int i = 0; i<=licznik;i++)
            {
                tmpReader.readLine();
            }
            if ((tmp = tmpReader.readLine()) != null ) {
                tmpCurrentState.panel.setCompleted();
                licznik++;
                Main.remove(tmpCurrentArea);
                Main.remove(tmpCurrentState);
                tmpCurrentState = new GameStatePanel(ustawienia, Main, tmp);
                tmpCurrentArea = new GameAreaPanel(tmp, Main, DEFAULT_WIDTH, DEFAULT_HIGHT);
                Main.revalidate();
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
