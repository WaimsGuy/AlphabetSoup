import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import api.Direction;
import api.ShiftDescriptor;
import api.TileInfo;
import hw3.AlphabetSoup;
import hw3.AlphabetUtil;
import ui.ConsoleUI;

public class SimpleTests
{
  public static void main(String[] args)
  {
    
    // test setRowColumn
    AlphabetSoup g = new AlphabetSoup(5, new AlphabetUtil(), new Random(42));
    int[] arr = {1, 2, 3, 4, 5};
    System.out.println("Before:");
    ConsoleUI.printGrid(g);
    g.setRowColumn(arr, 2, Direction.DOWN);
    System.out.println("After:");
    ConsoleUI.printGrid(g);

    // test getRowColumn
    int[] result = g.getRowColumn(2, Direction.DOWN);
    System.out.println(Arrays.toString(result));
    System.out.println("Expected [1, 2, 3, 4, 5]");
    
    AlphabetUtil util = new AlphabetUtil();    
    
    // test get score for a tile
    int score = util.getScoreForOneTile(4);
    System.out.println(score);
    System.out.println("Expected 27");

    // test merge
    int canMerge = util.findMergedValue(5, 5);
    System.out.println(canMerge);
    System.out.println("Expected 6");
    
    canMerge = util.findMergedValue(2, 3);
    System.out.println(canMerge);
    System.out.println("Expected 0");
    
    // test doShift - examples from the pdf...
//  For arr = [1, 3, 3, 2], after doShift(arr), the array is [1, 4, 2, 0]
//  For arr = [1, 2, 1, 0], after doShift(arr), the array is [1, 2, 1, 0]
//  For arr = [7, 0, 3, 0, 2], after doShift(arr), the array is [7, 3, 0, 2, 0]
//  For arr = [2, 2, 2, 2], after doShift(arr), the array is [3, 2, 2, 0]C
//  For arr = [3, 0, 2, 2], after doShift(arr), the array is [3, 2, 2, 0]
//  For arr = [0, 0, 5, 0, 7, 7], after doShift(arr), the array is [0, 5, 0, 7, 7, 0]

    
    int[] test2 = {7, 0, 3, 0, 2};
    util.doShift(test2);
    System.out.println(Arrays.toString(test2));
    System.out.println("Expected [7, 3, 0, 2, 0]");
    
    int[] test = {1, 3, 3, 2};
    util.doShift(test);
    System.out.println(Arrays.toString(test));
    System.out.println("Expected [1, 4, 2, 0]");

    // try out shift
    g = new AlphabetSoup(4, new AlphabetUtil(), new Random(42));
    int[][] testGrid1 =
    {
        { 0, 2, 3, 1 },
        { 0, 1, 3, 2 },
        { 0, 2, 3, 0 },
        { 0, 1, 2, 0 } 
    };
    for (int row = 0; row < testGrid1.length; row += 1)
    {
      for (int col = 0; col < testGrid1[0].length; col += 1)
      {
        g.setCell(row, col, testGrid1[row][col]);
      }
    }
    System.out.println("Before: ");
    ConsoleUI.printGrid(g);
    g.shift(Direction.DOWN);
    System.out.println("After: ");
    ConsoleUI.printGrid(g);

    int[][] resultGrid1 =
      {
          { 0, 2, 0, 0 },
          { 0, 1, 3, 1 },
          { 0, 2, 4, 2 },
          { 0, 1, 2, 0 } 
      };
    System.out.println("Expected: ");
    ConsoleUI.printGrid(resultGrid1);
    System.out.println();
    
    // try getLastDirection using the grid above
    System.out.println(g.getLastDirection());
    System.out.println("Expected DOWN");
    System.out.println();
    
    // try setNewTile using the grid above
    TileInfo info = g.setNewTile();
    ConsoleUI.printGrid(g);
    System.out.println();
    
    // check the TileInfo and make sure it matches the new tile
    System.out.println("TileInfo: " + info);
    System.out.println();
    
    // returning ShiftDescriptors from doShift
    int[] test3 = {7, 0, 3, 0, 2};
    ArrayList<ShiftDescriptor> descriptors = util.doShift(test3);
    System.out.println(descriptors);
    System.out.println("Expected:");
    System.out.println("[Move c 2 to 1, Move b 4 to 3]");
    
    int[] test4 = {1, 3, 3, 2};
    descriptors = util.doShift(test4);
    System.out.println(descriptors);
    System.out.println("Expected:");
    System.out.println("[Merge c 2 to 1, Move b 3 to 2]");
    System.out.println();
    
    // returning descriptors from shift()
    g = new AlphabetSoup(4, new AlphabetUtil(), new Random(42));
    int[][] testGrid2 =
    {
        { 0, 2, 3, 1 },
        { 0, 1, 3, 2 },
        { 0, 2, 3, 0 },
        { 0, 1, 2, 0 } 
    };
    
    for (int row = 0; row < testGrid2.length; row += 1)
    {
      for (int col = 0; col < testGrid2[0].length; col += 1)
      {
        g.setCell(row, col, testGrid2[row][col]);
      }
    }

    System.out.println("Before: ");
    ConsoleUI.printGrid(g);
    ArrayList<ShiftDescriptor> moves = g.shift(Direction.DOWN);
    System.out.println("After: ");
    ConsoleUI.printGrid(g);

    System.out.println(moves);
    System.out.println("Expected: ");
    System.out.println("[Merge c 2 to 1 (column 2 DOWN),"); 
    System.out.println("Move c 3 to 2 (column 2 DOWN),");
    System.out.println("Move b 2 to 1 (column 3 DOWN),");
    System.out.println("Move a 3 to 2 (column 3 DOWN)]");
    
  }
}
