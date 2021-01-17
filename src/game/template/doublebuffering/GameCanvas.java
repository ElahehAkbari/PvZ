/*** In The Name of Allah ***/
package game.template.doublebuffering;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.*;
import game.template.bufferstrategy.GameState;

/**
 * The canvas on which the rendering is performed.
 * This structure uses the old conventional double-buffering method.
 * A more modern approach is to use the BufferStrategy class.
 * For more information on how to use BufferStrategy check out:
 *    http://docs.oracle.com/javase/tutorial/extra/fullscreen/bufferstrategy.html
 *    http://docs.oracle.com/javase/8/docs/api/java/awt/image/BufferStrategy.html
 */
public class GameCanvas extends JPanel {

    public static final int GAME_HEIGHT = 772;
    public static final int GAME_WIDTH = 1010;
    private final Image background;
    private final Image peaShooterCard;
    private final Image sunFlowerCard;
    private final Image cherryBombCard;
    private final Image wallNutCard;

    private int y, x, flag;

    private BufferedImage bufferedScreen;
    private Graphics2D bufferedGraphics;

    public GameCanvas() {
        super(null);
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        //
        // Other initializations ...
        //
        // Create the offscreen buffer for offline drawing;
        bufferedScreen = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        // Get the graphics of the offscreen buffer;
        bufferedGraphics = (Graphics2D) bufferedScreen.createGraphics();
        background = new ImageIcon(".\\PVS Design Kit\\images\\mainBG.png").getImage();
        peaShooterCard = new ImageIcon(".\\PVS Design Kit\\images\\Cards\\card_peashooter.png").getImage();
        sunFlowerCard = new ImageIcon(".\\PVS Design Kit\\images\\Cards\\card_sunflower.png").getImage();
        cherryBombCard = new ImageIcon(".\\PVS Design Kit\\images\\Cards\\card_cherrybomb.png").getImage();
        wallNutCard = new ImageIcon(".\\PVS Design Kit\\images\\Cards\\card_wallnut.png").getImage();
    }

    /**
     * The paint method of this component.
     */
    @Override
    public void paintComponent(Graphics g) {
        // Call super class's paint
        super.paintComponent(g);
        // Now, draw the offscreen image to the screen like a normal image.
        g.drawImage(background, 2,30, null);
        g.drawImage(peaShooterCard, 110, 38, null);
        g.drawImage(sunFlowerCard, 110+peaShooterCard.getWidth(null), 38, null);
        g.drawImage(cherryBombCard, 110+2*peaShooterCard.getWidth(null), 38, null);
        g.drawImage(wallNutCard, 110+3*peaShooterCard.getWidth(null), 38, null);
    }


    /**
     * The method to perform renderings of game elements.
     */
    public void render(GameState state) {
        //
        // Draw all game elements according to the game state
        //  on the offscreen image (using 'bufferedGraphics') ...
        //
        // Then finally:
        EventQueue.invokeLater(doRepaint);
    }

    /**
     * This can be much more cleaner using member reference (Java-8 syntax):
     *    Runnable doRepaint = this::repaint;
     * or to use lambda expressions (again from Java-8 syntax):
     *    Runnable doRepaint = () -> { repaint(); };
     * but I left it as is, so students won't get confused.
     */
    private final Runnable doRepaint = new Runnable() {
        @Override
        public void run() {
            repaint();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();
        }
    };

    /**
     * This is required for good double-buffering.
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
}