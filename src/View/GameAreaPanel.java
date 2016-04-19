package View;

import Model.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * klasa zawierajaca panel w ktorym toczyc sie bedzie rozgrywka
 *
 */
public class GameAreaPanel extends JPanel implements ComponentListener, WindowStateListener, KeyListener {
	/**
	 * lista przechowujaca elemnty do wyswietlenia na mapie
	 */
	private ArrayList<AbstractElement> map = new ArrayList();
	/**
	 * zmiena przechowujaca gracza - potrzebna do ustalenia aktualnej pozycji gracza
	 */
	private PlayerElement player;
	private String tmpElement = "@";
	/**
	 * zmienne przechowujace ilosc kolumn mapy
	 */
	private int mapWidth;
	/**
	 * Zmienna przechowujac ilosc wierszy mapy
	 */
	private int mapHeight;
	/**
	 * Zmienna skalujaca tekstury w poziomoe
	 */
	private double spaceX;
	/**
	 * Zmienna skalujaca tekstury w pionie
	 */
	private double spaceY;

	private int dx;
	private int dy;
	private boolean completed = false;

	/**
	 * przekazane okno gry, sluzy do ustalenia rozmiaru aktualnego okna + oblsuga Eventu maxymalizowani i minimalizowaniu okna
	 */
	private JFrame frame;

	/**
	 * Konstruktor
	 *
	 * @param fileName sciezka dostepu do pliku z poziomem gry
	 * @parm frame przekazuje okna aby mozna bylo obsluzyc event oraz do obliczen zbalansowania tekstur
	 */
	public GameAreaPanel(String fileName, JFrame frame, int width, int height) {
		this.frame = frame;
		LevelLoader level;
		level = new LevelLoader(fileName);
		map.addAll(level.getMap());
		mapHeight = level.getMapHeight();
		mapWidth = level.getMapWidth();
		this.setLayout(null);
		addComponentListener(this);
		frame.addWindowStateListener(this);
		frame.addKeyListener(this);
		spaceX = width / mapWidth;
		spaceY = (height-120) / mapHeight;
		player = level.getPlayer();
	}

	/**
	 * metoda rysujaca poziom
	 */
	public void paint(Graphics g) {
		for (int i = 0; i < map.size(); i++) {
			AbstractElement item = (AbstractElement) map.get(i);
			g.drawImage(item.getImage(), item.getX() * (int) spaceX, item.getY() * (int) spaceY, (int) spaceX, (int) spaceY, this);
		}
	}

	/**
	 * przeciazenie metody z ComponentListner
	 * Wyliczna nowe przeliczniki tekstur
	 */
	public void componentResized(ComponentEvent e) {
		Rectangle r = getBounds();
		spaceY = (r.height) / mapHeight;
		spaceX = r.width / mapWidth;
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
			spaceX = (screenSize.getWidth() - 60) / mapWidth;
			spaceY = (screenSize.getHeight() - 50) / mapHeight;
		} else if ((oldState & Frame.MAXIMIZED_BOTH) != 0 && (newState & Frame.MAXIMIZED_BOTH) == 0) {
			Rectangle r = frame.getBounds();
			spaceX = (r.width - 20) / mapWidth;
			spaceY = (r.height - 50) / mapHeight;
		}

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
		}

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public void keyTyped(KeyEvent e) {
	}
}