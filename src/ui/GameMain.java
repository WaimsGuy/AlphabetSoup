package ui;


import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hw3.AlphabetSoup;
import hw3.AlphabetUtil;

/**
 * Main class for a GUI for a game of AlphabetSoup sets up a 
 * GamePanel instance in a frame.
 */
public class GameMain
{
  /**
   * Size of grid for the game.
   */
  private static final int GRID_SIZE = 4;
  
  /**
   * Attempt to animate movement of tiles.
   */
  private static final boolean USE_ANIMATION = true;
  
  /**
   * Print lots of output to the console.
   */
  private static final boolean VERBOSE = true;
  
  /**
   * Tile size in pixels.
   */
  public static final int TILE_SIZE = 100; 

  /**
   * Font size for displaying score.
   */
  public static final int SCORE_FONT = 24; 

  /**
   * Size of the preview tile.
   */
  public static final int PREVIEW_SIZE = 30;
  
  /**
   * Colors for tiles.
   */
  public static final Color[] colors = {
      new Color(239, 245, 66), // pale yellow
      new Color(167, 245, 66), // pale green
      new Color(66, 242, 245), // cyan
      new Color(66, 185, 245), // light blue
      new Color(245, 203, 66), // pale orange
      new Color(245, 66, 242), // magenta
      new Color(194, 66, 245), // light purple
      new Color(66, 75, 245), // blue
      new Color(58, 168, 50), // green
      new Color(232, 51, 51), // bright red
      new Color(239, 242, 63), // bright yellow
      new Color(235, 7, 200),  // bright magenta
      Color.CYAN,              // bright cyan
      new Color(117, 45, 235), // purple
      new Color(165, 235, 45), // flourescent green
      Color.ORANGE, // bright orange

  };
  
  /**
   * Entry point.  Main thread passes control immediately
   * to the Swing event thread.
   * @param args not used
   */
  public static void main(String[] args)
  {
    final Random rand = new Random();
    final AlphabetUtil config = new AlphabetUtil();
    
    Runnable r = new Runnable()
    {
      public void run()
      {
        create(GRID_SIZE, config, rand, USE_ANIMATION, VERBOSE);
      }
    };
    SwingUtilities.invokeLater(r);
  }

  
  /**
   * Helper method for instantiating the components.  This
   * method should be executed in the context of the Swing
   * event thread only.
   */
  private static void create(int gridSize, AlphabetUtil config, Random rand, boolean useAnimation, boolean verbose)
  {
    // create a game 
    AlphabetSoup game = new AlphabetSoup(gridSize, config, rand);
    
    // create the two UI panels
    ScorePanel scorePanel = new ScorePanel();
    GamePanel panel = new GamePanel(game, scorePanel, useAnimation, verbose);
    
    // arrange the two panels vertically
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(panel);
    mainPanel.add(scorePanel);
    
    // put main panel in a window
    JFrame frame = new JFrame("Alphabet Soup!");
    frame.getContentPane().add(mainPanel);

    // give panels a nonzero size
    Dimension d = new Dimension(gridSize * GameMain.TILE_SIZE, gridSize * GameMain.TILE_SIZE);   
    panel.setPreferredSize(d);
    d = new Dimension(gridSize * GameMain.TILE_SIZE, GameMain.TILE_SIZE);
    scorePanel.setPreferredSize(d);
    frame.pack();
    
    // we want to shut down the application if the 
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
    panel.grabFocus();
    
    // rock and roll...
    frame.setVisible(true);
  }
  
  
  
  
}
