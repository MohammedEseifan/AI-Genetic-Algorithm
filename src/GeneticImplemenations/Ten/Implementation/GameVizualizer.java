package GeneticImplemenations.Ten.Implementation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mohammed on 24-Jan-17.
 */
public class GameVizualizer implements ActionListener {

    private MainGrid grid;
    GameWindow gameWindow;

    public GameVizualizer(MainGrid grid, int windowSize) {
        this.grid = grid;
        JFrame window = new JFrame("Ten");
        window.setSize(windowSize, windowSize);

        gameWindow = new GameWindow(grid);
        window.add(gameWindow);
        window.setLocation(100, 100);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);

        Timer t = new Timer(30, this);
        t.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameWindow.repaint();
    }
}
