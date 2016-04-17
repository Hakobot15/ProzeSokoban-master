package View;

import Controller.Move;
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
	public GameAreaPanel(String fileName, JFrame frame) {
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
		spaceX = (frame.getBounds().width - 20) / mapWidth;
		spaceY = (frame.getBounds().height - 50) / mapHeight;
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
		spaceY = r.height / mapHeight;
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

		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -1;
			System.out.println("Chce isc w gore");
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
		move();
		repaint();

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

	public void move() {
		int tmpX = player.getX();
		int tmpY = player.getY();
		player.setX(player.getX() + dx);
		player.setY(player.getY() + dy);
		WallElement tmpWa = new WallElement(tmpX, tmpY);
		FloorElement tmpFl = new FloorElement(tmpX, tmpY);
		ChestElement tmpCh = new ChestElement(tmpX, tmpY);
		GoalElement tmpGo = new GoalElement(tmpX, tmpY);
		for (int i = 0; i < map.size(); i++) {
			if (tmpWa.equals(map.get(i))) { // jezeli sciana to sie nie moze ruszyc
				System.out.println(tmpWa.getX()+tmpWa.getY()+" "+ map.get(i).getX()+map.get(i).getY());
				System.out.println("Jestem w scianie");
				break;
			}
			if (tmpFl.equals(map.get(i))) { // jezelie podloga to sie porusza
				System.out.println("wszedlem do floora");
				for (int j = 0; j < map.size(); j++) {
					if (map.get(j) instanceof PlayerElement) {
						System.out.println("znalazlo gracza");
						map.set(i, player);
						if (tmpElement.equals("_")) {
							map.set(j, tmpFl);
							tmpElement = "_";
							System.out.println("Jestem w podflorze");
							break;
						}
						if (tmpElement.equals("@")) {
							map.set(j, tmpCh);
							tmpElement = "_";
							break;
						}
						if (tmpElement.equals("o")) {
							map.set(j, tmpGo);
							tmpElement = "_";
							break;
						}
					}
				}
			}
			if (tmpGo.equals(map.get(i))) { // jezeli cel
				for (int j = 0; i < map.size(); i++) {
					if (map.get(j) instanceof PlayerElement)
						map.set(i, player);
					if (tmpElement.equals("_")) {
						map.set(j, tmpFl);
						tmpElement = "o";
						break;
					}
					if (tmpElement.equals("@")) {
						map.set(j, tmpCh);
						tmpElement = "o";
						break;
					}
					if (tmpElement.equals("o")) {
						map.set(j, tmpGo);
						tmpElement = "o";
						break;
					}

				}
			}
			if (tmpCh.equals(map.get(i))) {
				for (int j = 0; i < map.size(); i++) {
					if (map.get(j) instanceof PlayerElement)
						map.set(i, player);
					if (tmpElement.equals("_")) {
						map.set(j, tmpFl);
						tmpElement = "@";
						break;
					}
					if (tmpElement.equals("@")) {
						map.set(j, tmpCh);
						tmpElement = "@";
						break;
					}
					if (tmpElement.equals("o")) {
						map.set(j, tmpGo);
						tmpElement = "@";
						break;
					}

				}
			}
		}
	}
}