package Model;

/**
 * Klasa przedstawiajaca model skrzyni
 */
public class ChestElement extends AbstractElement {
    /**
     * Zmienna przechowujaca sciezke do grafiki skrzynki
     */
    private final static String SOURCE = "pic\\chest.gif";
    private final static String MARK = "$";
    private int moveX = 0;
    private int moveY =0;

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

    /**
     * Przeciazenie metody equals
     */
    public boolean equals(Object obj) // Kacper nie wiem czy to Ci sie przyda, ja tego nie wykorzystuje poki co
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(!(obj instanceof ChestElement))
            return false;
        ChestElement s = (ChestElement) obj;
        if(this.getX() == s.getX() && this.getY() == s.getY())
            return true;
        return false;
    }

    /**
     *
     * @param obj obiekt ktory porównujemy
     * @return wzraca czy dane obiekty maja maja te same wspolrzedne
     */
    public boolean equalsXY(GoalElement obj)
    {
        if(this.getX() == obj.getX() && this.getY() == obj.getY())
            return true;
        else return false;
    }

    public void setMoveX(int moveX){
        this.moveX = moveX;
    }
    public void setMoveY(int moveY)
    {
        this.moveY = moveY;
    }

    public int getMoveX(){return moveX;}
    public int getMoveY(){return moveY;}
}
