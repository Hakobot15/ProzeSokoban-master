package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
 *
 * Glowna ramka programu
 *
 */
public class MainFrame extends JFrame {
	/**
	 * Zmienna przechowujaca tytul gry
	 */
	private final static String TITLE = "Sokoban v1.0.0";
	/**
	 * Zmienna przechowujaca standardowa szerokosc okna gry
	 */
	private final static int DEFAULT_WIDTH = 520;
	/**
	 * Zmienna przechowujaca standardowa wysokosc okna gry
	 */
	private final static int DEFAULT_HIGHT = 570;
	private String mapNames = "listaMap.txt";
	private Action saveAction;
	private Action newGameAction;
	private GameAreaPanel areaPanel;
	private GameStatePanel statePanel;
	/**
	 * Bezparametrowy konstruktor tworzacy glowna ramke aplikacji
	 */
	public MainFrame() {
		setTitle(TITLE);
		setVisible(true);
		setBounds(new Rectangle(DEFAULT_WIDTH, DEFAULT_HIGHT));
		makeMenu();
		this.setResizable(true);
		newGameStart();
	}


	/**
	 * uruchomienie nowej gry
	 */
	// Do new Game doodalem this(potrzebny do wylapywania watku, i dodalem wysokosc i szerokosc, potrzebne do ustalanie wielkosc tekstur
	private void newGameStart() {
			Thread t = new Thread(new MyRunnable(this, DEFAULT_WIDTH, DEFAULT_HIGHT, mapNames) {
			});
			t.start();

	}
	/**
	 * Method creating mainMenu
	 */
	private void makeMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menuGame = new JMenu("Gra");

		menuGame.add(new AbstractAction("Nowa gra") {
			public void actionPerformed(ActionEvent event) {
				newGameStart();
			}
		});

		menuGame.add(new AbstractAction("Najlepsze wyniki") {
			public void actionPerformed(ActionEvent event) {
				String fileName = "info\\highScore.txt";
				StringBuilder instrukcjaGry = new StringBuilder();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(fileName));
					String tmp = null;
					while ((tmp = reader.readLine()) != null) {

						instrukcjaGry.append(tmp);
						instrukcjaGry.append("\n");

					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				JFrame frameInfo = new JFrame();
				JOptionPane.showMessageDialog(frameInfo, instrukcjaGry, "Sokoban - Top10", 1);
			}
		});

		menuGame.add(new AbstractAction("Wyjscie") {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		JMenu menuSettings = new JMenu("Ustawienia");
		ButtonGroup levelSettings = new ButtonGroup();
		JRadioButtonMenuItem easyLevel = new JRadioButtonMenuItem("Poziom latwy");
		easyLevel.setSelected(true);
		JRadioButtonMenuItem hardLevel = new JRadioButtonMenuItem("Poziom trudny");
		levelSettings.add(easyLevel);
		levelSettings.add(hardLevel);
		ButtonGroup networkSettings = new ButtonGroup();
		JRadioButtonMenuItem online = new JRadioButtonMenuItem("Online");
		JRadioButtonMenuItem offline = new JRadioButtonMenuItem("Offline");
		offline.setSelected(true);

		menuSettings.add(easyLevel);
		menuSettings.add(hardLevel);
		menuSettings.addSeparator();
		menuSettings.add(online);
		menuSettings.add(offline);

		JMenu menuHelp = new JMenu("Pomoc");
		menuHelp.add(new AbstractAction("Instrukcja") {
			public void actionPerformed(ActionEvent event) {
				String fileName = "info\\info.txt";
				StringBuilder instrukcjaGry = new StringBuilder();
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(fileName));
					String tmp = null;
					while ((tmp = reader.readLine()) != null) {

						instrukcjaGry.append(tmp);
						instrukcjaGry.append("\n");

					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				JFrame frameInfo = new JFrame();
				JOptionPane.showMessageDialog(frameInfo, instrukcjaGry, "Sokoban - Info", 1);
			}
		});

		menuBar.add(menuGame);
		menuBar.add(menuSettings);
		menuBar.add(menuHelp);

		this.setJMenuBar(menuBar);

	}

}