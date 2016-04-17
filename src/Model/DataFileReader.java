package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasa wczytujaca dane z plikow i zapisujaca je w ArrayList
 */
public class DataFileReader {
    /**
     * lista przechowujaca stringi odczytane z pliku .txt
     */
    private ArrayList<String> dataList;

    /**
     * Konstruktor
     * @param fileName ścieżka pliku txt, który ma być odczytywany
     */
    public DataFileReader(String fileName) {
        /**
         * Utworzenie klasy BufferedReader i nadanie jej warotsic NULL, będzie służyć do odczytywania stringów z pliku .txt
         */
        BufferedReader reader = null;
        /**
         * parametr tmp będzie przechowywał tymczasowo odczytaną linie tekstu z pliku .txt
         */
        String tmp = null;
        /**
         * Tymczasowa lista przechowując odczytane linijki z pliki
         */
        ArrayList<String> tmpList = new ArrayList<String>();

        try {
            /**
             * Otwieramy plik z określonej ściezki
             */
            reader = new BufferedReader(new FileReader(fileName));
            /**
             * dodawanie kolejnych lini tekstu do mementu wykrycia braku tekstu
             */
            while ((tmp = reader.readLine()) != null) {
                tmpList.add(tmp);
            }
            /**
             * wyłapanie wyjątku, gdy nie uda się otworzy pliku
             */
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * przekazanie warotsci z listy tymczasowej do listy glownej
         *
         */
        dataList = tmpList;
    }

    /**
     *
     * @return zwraca lista z odczytanymi stringami
     */
    public ArrayList<String> getDataList() {
        return dataList;
    }
}
