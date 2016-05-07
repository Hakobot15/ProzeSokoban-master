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
	private ArrayList<ChestElement> chests = new ArrayList<>();
	private ArrayList<GoalElement> goals = new ArrayList<>();
	private ArrayList<WallElement> walls = new ArrayList<>();
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
	private String runingTime; // potrzebne tylko do obslugi watku
	/**
	 * parametr przechowujacy chec zmiany polozenie gracz - w poziomie
	 */
	private int dx;
	/**
	 * parametr przechowujacy chec zmiany polozenie gracz - w pionie
	 */
	private int dy;
	private boolean completed = false;
	private double mapTime = 100 ;
	/**
	 * przekazane okno gry, sluzy do ustalenia rozmiaru aktualnego okna + oblsuga Eventu maxymalizowani i minimalizowaniu okna
	 */
	private JFrame frame;
	PlayerElement player;

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
		map.addAll(level.getMap());  // dodanie list z level loader
		goals.addAll(level.getGoals());
		chests.addAll(level.getChests());
		walls.addAll(level.getWalls());
		player = level.getPlayer();
		mapHeight = level.getMapHeight();
		mapWidth = level.getMapWidth();
		spaceX = width / mapWidth;
		spaceY = (height-120) / mapHeight;
		this.setLayout(null);
		addComponentListener(this);
		frame.addWindowStateListener(this);
		frame.addKeyListener(this);
	}
	/**
	 * metoda rysujaca poziom
	 */
	public void paint(Graphics g) { // inaczej teraz rysuje bo rysuje podstawe a na niej inne obiekty
		for (int i = 0; i < map.size(); i++) {
			AbstractElement item = (AbstractElement) map.get(i);
			g.drawImage(item.getImage(), item.getX() * (int) spaceX, item.getY() * (int) spaceY, (int) spaceX, (int) spaceY, this);
		}
		for (int j = 0; j<chests.size();j++)
		{
			AbstractElement item2 = (AbstractElement) chests.get(j);
			g.drawImage(item2.getImage(), item2.getX()* (int) spaceX, item2.getY()*(int) spaceY, (int) spaceX, (int) (spaceY), this);
		}
		g.drawImage(player.getImage(), player.getX()*(int) spaceX, player.getY() * (int) spaceY, (int) spaceX, (int) spaceY, this);
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

	public void keyPressed(KeyEvent e) { // w tej metodzie bedziemy wykonywac ruch, bo jest najlatwiej

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -1;
			System.out.println("lewo");
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 1;
			System.out.println("prawo");
		}

		if (key == KeyEvent.VK_UP) {
			dy = -1;
			System.out.println("gora");
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 1;
			System.out.println("dol");
		}
		move();
	}
	public void move() {
		if (checkWall(player.getX() + dx, player.getY() + dy)) // sprawdzanie czy element na ktorych chcemy isc czy to nie sciana
			return; // to sciana - wychodzimy z metody
		if (checkChest(player.getX() + dx, player.getY() + dy) >= 0) { // sprawdzenie czy dany element to skrzynka
			if (checkWall(player.getX() + dx + dx, player.getY() + dy + dy)) // to jest skrzynka, trzeba sprawdzic czy kolejne pole to sciana
				return; // 2 pole to sciana wychodzimy z metody
			else if (checkChest(player.getX() + dx + dx, player.getY() + dy + dy) >= 0) // sprawdzenie czy drugi element to pudelko
				return; // tak to pudelko wychodzimy z metody
			else { // 1 pole to skrzynka 2 pole to pole neutralne
				chests.get(checkChest(player.getX() + dx, player.getY() + dy)).setXY(player.getX() + dx + dx, player.getY() + dy + dy); // ustawienie pozycji nowej skrzynki
				player.setXY(player.getX() + dx, player.getY() + dy); // ustawienie nowej pozycji gracza, nowa metoda ustawiajaca odrazu X i Y
				draw(); // repaint
				return; // ruszylismy sie to wychodzimy
			}
		}
		player.setXY(player.getX() + dx, player.getY() + dy); // 1 pole to pole neutralne
		draw(); // repaint

	}
	public boolean checkWall(int xw, int yw) // tutaj jest boolean bo tylko tyle nam trzeba
	{
		walls.size();
		WallElement dupa = new WallElement(xw,yw);
		for(int i = 0; i < walls.size();i++) // sprawdza czy nasz element to ktoras ze scian mapy
		{
			if(dupa.equals(walls.get(i))) // equals jest przeciazony
			return true;
		}
		return false;
	}
	public int checkChest(int xw, int yw ) // tutaj boolean nie wystarczy bo musimy wiedziec jeszcze ktora skrzyka jest na drodze
	{
		ChestElement dupa = new ChestElement(xw,yw);
		for(int i = 0; i < chests.size();i++)
		{
			if(dupa.equals(chests.get(i))) {
				return i; // zwraca pozycje skrzynki z list chests, musimy to wiedziec zeby moc zmodyfikowac ja podczas ruchu
			}
		}
		return -1;
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

	// potrzebne do rysowania
	private void draw() {
		this.repaint();

	}

public double getMapTime(){	return mapTime;}

}
