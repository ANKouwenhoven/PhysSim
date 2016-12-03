package window;

import drawing.DrawGame;
import keyAdapter.PlayerKeyAdapter;
import keyAdapter.PlayerMouseAdapter;
import singleton.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Arnoud on 8-11-2016.
 */
public class GameWindow extends JFrame{

    private static int sizeX = 600;
    private static int sizeY = 600;
    //private static Game currentGame = new Game();
    private static DrawGame drawer = new DrawGame();
    private static GameWindow gameWindow;

    public GameWindow() {
        initUI();
    }

    /**
     * Initialize this window.
     */
    private void initUI() {

        setTitle("Vector TD");

        Game.getInstance().init();

        add(drawer);

        addKeyListener(new PlayerKeyAdapter());
        addMouseListener(new PlayerMouseAdapter());
        setFocusable(true);

        setSize(sizeX, sizeY);
        setResizable(false);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The starting method of the program
     * @param args
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            gameWindow = new GameWindow();
            gameWindow.setVisible(true);

            gameWindow.setCursor(gameWindow.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
        });
    }

    /**
     * @return - this Window's position on screen
     */
    public static Point getPos() {
        return gameWindow.getLocationOnScreen();
    }

    /**
     * @return - this Window's game
     */
    /*public static Game getGame(){
        return currentGame;
    }*/
}