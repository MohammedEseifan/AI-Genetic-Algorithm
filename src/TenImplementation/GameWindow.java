package TenImplementation;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Mohammed on 24-Jan-17.
 */
public class GameWindow extends JComponent {

    MainGrid grid;

    public GameWindow(MainGrid grid) {
        this.grid=grid;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setBackground(new Color(114,114,114));
        grid.draw(0,0,getWidth(),getHeight(),20,g2);
    }
}
