import Model.FloorElement;
import Model.WallElement;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created by DDcreo on 2016-04-13.
 */

public class mainTesty {

    public static void main(String[] args)
    { // Zmienilem troche maina dziala bez zarzutu
            EventQueue.invokeLater(() -> {
               JFrame frame = new View.MainFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setTitle("Sokoban");
           });

    }
}




