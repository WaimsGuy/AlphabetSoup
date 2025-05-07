package ui;
import java.util.Scanner;

import api.Direction;
import hw3.AlphabetSoup;
import hw3.AlphabetUtil;

/**
 * Ultra-simple UI for the AlphabetSoup game.
 * 
 * This version does not use the ShiftDescriptor objects returned
 * from the shift method.
 */
public class ConsoleUI
{
  public static void main(String[] args)
  {
    run();
  }
  
  
  
  public static void run()
  {
    Scanner in = new Scanner(System.in);
    AlphabetSoup g = new AlphabetSoup(4, new AlphabetUtil());
    printGrid(g);
    
    while (true)
    {
      System.out.println("Enter a, d, w, or s: ");
      String s = in.nextLine();
      if (s.startsWith("a"))
      {
        g.shift(Direction.LEFT);
      }
      else if (s.startsWith("d"))
      {
        g.shift(Direction.RIGHT);
      }
      else if (s.startsWith("w"))
      {
        g.shift(Direction.UP);
      }
      else if (s.startsWith("s"))
      {
        g.shift(Direction.DOWN);
      }
      printGrid(g);     

      System.out.println("Press ENTER for new tile...");      
      s = in.nextLine();
      g.setNewTile();  
      printGrid(g);
    }

  }

  
  public static void printGrid(AlphabetSoup game)
  {
    System.out.println("---------------");
    for (int row = 0; row < game.getSize(); row += 1)
    {
      for (int col = 0; col < game.getSize(); col += 1)
      {
        String toDisplay = "-";
        int value = game.getCell(row, col);
        if (value > 0)
        {
          // values 1, 2, 3, ... should display as 'a', 'b', 'c', ...
          toDisplay = "" + (char) ('a' + value - 1);
        }
        System.out.printf("%4s", toDisplay);
      }
      System.out.println();
    }
    System.out.println("---------------");
  }

  public static void printGrid(int[][] grid)
  {
    System.out.println("---------------");
    for (int row = 0; row < grid.length; row += 1)
    {
      for (int col = 0; col < grid[0].length; col += 1)
      {
        String toDisplay = "-";
        int value = grid[row][col];
        if (value > 0)
        {
          // values 1, 2, 3, ... should display as 'a', 'b', 'c', ...
          toDisplay = "" + (char) ('a' + value - 1);
        }
        System.out.printf("%4s", toDisplay);
      }
      System.out.println();
    }
    System.out.println("---------------");
  }

}
