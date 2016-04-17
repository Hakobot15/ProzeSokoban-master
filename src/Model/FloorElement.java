package Model;

/**
 * Model podlogi po ktorej bedize sie poruszal gracz
 */
public class FloorElement extends AbstractElement {
    /**
     * Zmienna przechowujaca sciezke do grafiki skrzynki
     */
    private final static String SOURCE = "pic\\floor.png";

    /**
     * Konstruktor
     *
     * @param x wspolzedna wyswietlenia grafiki
     * @param y wspolzedna wyswietlenia grafiki
     */
    public FloorElement(int x, int y) {
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
        if(!(obj instanceof FloorElement))
            return false;
        FloorElement s = (FloorElement) obj;
        if(this.getX() == s.getX() && this.getY() == s.getX())
            return true;
        return true;
    }
}

