package Model;

/**
 * Model docelowego miejsca w ktore gracz powinien ustawic skrzynie
 */
public class GoalElement extends AbstractElement {

    /**
     * Zmienna przechowujaca sciezke do grafiki skrzynki
     */
    private final static String SOURCE = "pic\\goal.png";

    /**
     * @param x wspolzedna wyswietlenia grafiki
     * @param y wspolzedna wyswietlenia grafiki
     */
    public GoalElement(int x, int y) {
        /**
         * konstruktor odwolujacy sie do nadklasy AbstractElement
         * ustawia wspolzedne X i Y oraz przypisuje źródło klasie IMAGE
         */
        super(x, y, SOURCE);
    }

}
