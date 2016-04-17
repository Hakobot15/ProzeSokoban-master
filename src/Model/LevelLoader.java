package Model;

import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za stworzenie obiekt�w mapy
 * <p>
 * DEFAULT_PLAYER gracz w pliku mapy
 * DEFAULT_WALL �ciana w pliku mapy
 * DEFAULT_GOAL dziura na skrzynke w pliku mapy
 * DEFAULT_FLOOR podloga w pliku mapy
 * DEFAULT_CHEST skrzynia w pliku mapy
 * time czas na przejscie poziomu
 * map lista obiektow poziomu
 * mapWidth szerokosc mapy
 * mapHeight wysokosc mapy
 */
public class LevelLoader {
    /**
     * Zmienne przechowujace zmienne do odczytu mapy
     * konkretne symbole odpowiadaja za konkretny obiekt graficzny na mapie
     */
    private final static char DEFAULT_PLAYER = '@';
    private final static char DEFAULT_WALL = '#';
    private final static char DEFAULT_GOAL = 'o';
    private final static char DEFAULT_FLOOR = '_';
    private final static char DEFAULT_CHEST = '$';
    /**
     * zmienna przechowujaca czas na wykonanie celow mapy
     */
    private int time;
    /**
     * Lista przechowujaca wszystkie elemnty na mapie
     */
    private ArrayList<AbstractElement> map = new ArrayList<>();
    /**
     * zmienne przechowujace wysokosc i szerokosc mapy
     */
    private int mapWidth;
    private int mapHeight;
    /**
     * zmienne przechowujace polozenie gracza
     */
    private PlayerElement player;



    /**
     * @param fileName sciezka pliku z mapa
     */
    public LevelLoader(String fileName) {
        /**
         * Odczytanie pliku zawierajacego ustawienia mapy
         */
        DataFileReader file = new DataFileReader(fileName);
        time = Integer.parseInt(file.getDataList().get(0));
        mapHeight = file.getDataList().size() - 1 ;
        System.out.println("String ma dlugosc = "+  file.getDataList().size());
        char item;
        for (int i = 1; i < file.getDataList().size(); ++i) {
            /**
             * ustawienie wysokosci mapy na podstawie ilosc odczytanych wierszy
             */
            for (int j = 0; j < file.getDataList().get(i).length(); ++j) {
                /**
                 * Ustawienie szerokosc mapy na podstawie ilosc znakow w wierszu
                 */
                if(mapWidth < j+1) {mapWidth = j+1;}
                /**
                 * Odczytanie kolejnych znakow w wierszu
                 */
                item = file.getDataList().get(i).charAt(j);
                /**
                 * Jezeli odczytany znak to @  zostanie dodanie gracza do listy
                 */
                if (item == DEFAULT_PLAYER) // player
                {
                    player = new PlayerElement(j, i - 1);
                    map.add(new PlayerElement(j, i - 1));

                }
                /**
                 * Jezeli odczytany znak to # zostanie dodana sciana do listy
                 */
                else if (item == DEFAULT_WALL)// wall
                {
                    map.add(new WallElement(j, i - 1));
                }
                /**
                 * Jezeli odczytany znak to $  zostanie dodanie skrzynki do listy
                 */
                else if (item == DEFAULT_CHEST) // box
                {
                    map.add(new ChestElement(j, i - 1));
                }
                /**
                 * Jezeli odczytany znak to o  zostanie dodanie cel do listy
                 */
                else if (item == DEFAULT_GOAL) // goal-spot
                {
                    map.add(new GoalElement(j, i - 1));
                }
                /**
                 * Jezeli odczytany znak to _  zostanie dodanie podlogi do listy
                 */
                else if (item == DEFAULT_FLOOR) // floor
                {
                    map.add(new FloorElement(j, i - 1));
                }
            }
        }
    }

    /**
     * @return ArrayList zawierajacy obiekty z ktorych zbudowana jest mapa
     */
    public ArrayList<AbstractElement> getMap() {
        return map;
    }

    /**
     * @return Czas na przejscie mapy;
     */
    public int getTime() {
        return time;
    }

    /**
     * @return szeroko�c mapy
     */
    public int getMapWidth() {
        return mapWidth;
    }

    /**
     * @return wysoko�c mapy
     */
    public int getMapHeight() {
        return mapHeight;
    }

    public PlayerElement getPlayer() {
        return player;
    }
}

