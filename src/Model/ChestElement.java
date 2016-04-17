package Model;

/**
 * Klasa przedstawiajaca model skrzyni
 */
public class ChestElement extends AbstractElement {
    /**
     * Zmienna przechowujaca sciezke do grafiki skrzynki
     */
    private final static String SOURCE = "pic\\chest.png";

    /**
     * Konstruktor
     * @param x wspolzedna wyswietlenia grafiki
     * @param y wspolzedna wyswietlenia grafiki
     */
    public ChestElement(int x, int y) {
        /**
         * konstruktor odwolujacy sie do nadklasy AbstractElement
         * ustawia wspolzedne X i Y oraz przypisuje źródło klasie IMAGE
         */
        super(x, y, SOURCE);
    }
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(!(obj instanceof ChestElement))
            return false;
        ChestElement s = (ChestElement) obj;
        if(this.getX() == s.getX() && this.getY() == s.getX())
            return true;
        return true;
    }

}
