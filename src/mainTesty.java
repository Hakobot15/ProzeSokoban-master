import javax.swing.*;
import java.awt.*;

/**
 * Created by DDcreo on 2016-04-13.
 */
public class mainTesty {

    public static void main(String[] args)
    {

            EventQueue.invokeLater(() -> {
                JFrame frame = new View.MainFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Sokoban");
            });

    }
}
