package Model;

/**
 * Klasa przedstawiajaca model pojedycznego obiektu sciany
 */
public class WallElement extends AbstractElement {

    /**
     * Zmienna przechowujaca sciezke do grafiki skrzynki
     */
    private final static String SOURCE = "pic\\wall.png";

    /**
     * Konstrutor
     * @param x wspolzedna wyswietlenia grafiki
     * @param y wspolzedna wyswietlenia grafiki
     */
    public WallElement(int x, int y) {
        /**
         * konstruktor odwolujacy sie do nadklasy AbstractElement
         * ustawia wspolzedne X i Y oraz przypisuje źródło klasie IMAGE
         */
        super(x, y, SOURCE);
    }

}
