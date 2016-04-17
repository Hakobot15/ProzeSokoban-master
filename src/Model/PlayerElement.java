package Model;

/**
 * klasa zawierajaca model graficzny gracza
 */
public class PlayerElement extends AbstractElement {

    /**
     * Zmienna przechowujaca sciezke do grafiki skrzynki
     */
    private final static String SOURCE = "pic\\player.png";

    /**
     * Konstruktor
     * @param x wspolzedna wyswietlenia grafiki
     * @param y wspolzedna wyswietlenia grafiki
     */
    public PlayerElement(int x, int y) {
        /**
         * konstruktor odwolujacy sie do nadklasy AbstractElement
         * ustawia wspolzedne X i Y oraz przypisuje źródło klasie IMAGE
         */
        super(x, y, SOURCE);
    }
}
