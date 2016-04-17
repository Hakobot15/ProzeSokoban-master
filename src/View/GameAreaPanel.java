package View;

import Model.AbstractElement;
import Model.LevelLoader;
import Model.PlayerElement;
import com.sun.java.accessibility.util.AWTEventMonitor;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;




/**
 * klasa zawierajaca panel w ktorym toczyc sie bedzie rozgrywka
 *
 */
public class GameAreaPanel extends JPanel implements ComponentListener, WindowStateListener {
	/**
	 * lista przechowujaca elemnty do wyswietlenia na mapie
	 */
	private ArrayList<AbstractElement> map = new ArrayList();
	/**
	 * zmiena przechowujaca gracza
	 */
	private PlayerElement player;
	/**
	 * zmienne przechowujace wysokosc i szerokosc
	 */
	private int mapWidth;
	private int mapHeight;
	/**
	 * Kacper Wpisz tutaj bo nie wiem co to dokladnie jest
	 */
	private double spaceX = 50;
	private double spaceY = 50;
	private boolean completed = false;

	/**
	 * Konstruktor
	 *
	 * @param fileName sciezka dostepu do pliku z poziomem gry
	 */
	public GameAreaPanel(String fileName, JFrame frame) {
		/**
		 * Odczytanie ustawien mapy
		 */
		LevelLoader level;
		level = new LevelLoader(fileName);
		/**
		 * Dodanie elementow odczytanie mapy do listy
		 */
		map.addAll(level.getMap());
		/**
		 * Odczytanie wysokosci i szerokosc mapy
		 */
		mapHeight = level.getMapHeight();
		mapWidth = level.getMapWidth();
		this.setLayout(null);
		addComponentListener(this);
		frame.addWindowStateListener(this);

	}

	/**
	 * metoda rysujaca poziom
	 */

	public void paint(Graphics g) {
		for (int i = 0; i < map.size(); i++) {
			AbstractElement item = (AbstractElement) map.get(i);
			g.drawImage(item.getImage(), item.getX() * (int)spaceX, item.getY() * (int)spaceY, (int)spaceX, (int)spaceY, this);
		}
	}

	/**
	 * przeciazenie metody z ComponentListner
	 * Wyliczna nowe przeliczniki do rysowania
     */
	public void componentResized(ComponentEvent e) {
		Rectangle r = getBounds();
		spaceY = r.height/mapHeight;
		spaceX = r.width/mapWidth;
	}


	public void componentHidden(ComponentEvent e) {}
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}

	public void windowStateChanged(WindowEvent evt)
	{
		int oldState = evt.getOldState();
		int newState = evt.getNewState();

		if ((oldState & Frame.MAXIMIZED_BOTH) == 0 && (newState & Frame.MAXIMIZED_BOTH) != 0) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			spaceX = screenSize.getWidth();
			spaceY = screenSize.getHeight();
		} else if ((oldState & Frame.MAXIMIZED_BOTH) != 0 && (newState & Frame.MAXIMIZED_BOTH) == 0) {
			Rectangle r = getBounds();
			spaceY = r.height/mapHeight;
			spaceX = r.width/mapWidth;
		}

	}
}