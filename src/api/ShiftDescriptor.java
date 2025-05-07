package api;


/**
 * A ShiftDescriptor object describes the motion of one tile (or two tiles in the case
 * of a merge) in the AlphabetSoup game.  A move or merge is represented
 * by the starting index of the tile being moved within a one-dimensional array.
 * In addition, it is possible to also specify a Direction and a row or column
 * index, so that the move could be interpreted as taking place in a two-dimensional
 * grid.  (For example, given a ShiftDescriptor with startIndex 3, endIndex 2,
 * rowOrColumn 0 and direction DOWN, interpreted in a 4 x 4 grid it would
 * correspond to a move from (0, 0) to (1, 0).)
 */
public class ShiftDescriptor
{
  /**
   * Starting index of the tile being moved.
   */  
  private final int startIndex;
  
  /**
   * Indicates whether this is a merge move or not.
   */
  private final boolean merged;
  
  /**
   * Current value in the tile to be moved.
   */
  private final int value;
  
  /**
   * Value of tile after merge (same as original when not a merge).
   */
  private final int newValue;
  
  /**
   * Row or column in the grid (represents a row if direction
   * is LEFT or RIGHT, represents a column if direction is UP or DOWN).
   */
  private int rowOrColumn;
  
  /**
   * Direction of the move in the grid.
   */
  private Direction dir;

  /**
   * Constructs a single-tile move from startIndex to startIndex - 1. Initially the
   * Direction is null and row/column is -1.  Clients should call setDirection
   * to fill these values in.
   * @param startIndex
   *   starting index of the tile within an array
   * @param value
   *   current value of the tile
   */
  public ShiftDescriptor(int startIndex, int value)
  {
    this.startIndex = startIndex;
    this.value = value;
    this.newValue = value;
    merged = false;
    dir = null;
    rowOrColumn = -1;
  }
  
  /**
   * Constructs a merge move from startIndex into startIndex - 1. Initially the
   * Direction is null and row/column is -1.  Clients should call setDirection
   * to fill these values in.
   * @param startIndex
   *   starting index of the moving tile within an array
   * @param currentValue
   *   current value of the tiles being merged
   * @param newValue
   *   new value after merge
   */
  public ShiftDescriptor(int startIndex, int currentValue, int newValue)
  {
    this.startIndex = startIndex;
    this.value = currentValue;
    this.newValue = newValue;
    merged = true;
    dir = null;
    rowOrColumn = -1;
  }

  /**
   * Sets a direction and row/column index for interpreting this move within a grid.
   * @param givenRowOrColumn
   *   row or column index
   * @param givenDirection
   *   direction to set
   */
  public void setDirection(int givenRowOrColumn, Direction givenDirection)
  {
    dir = givenDirection;
    rowOrColumn = givenRowOrColumn;
  }

  /**
   * Returns the old index of the first (or only) tile represented by this move.
   * @return
   *   index of first tile
   */
  public int getOldIndex()
  {
    return startIndex;
  }

  /**
   * Returns the new index of the tile or tiles represented by this move.
   * @return
   *   new index for move
   */
  public int getNewIndex()
  {
    return startIndex - 1;
  }

  /**
   * Determines whether this is a merge move or a single tile move.
   * @return
   *   true if this is a merge move, false otherwise
   */
  public boolean isMerged()
  {
    return merged;
  }

  /**
   * Returns a direction for interpreting this move in a 2D grid.
   * @return
   *   direction for this move, or null if none has been set
   */
  public Direction getDirection()
  {
    return dir;
  }

  /**
   * Returns a row or column index for interpreting this move in a 2D grid.
   * @return
   *   row or column index, or -1 if none has been set
   */
  public int getRowOrColumn()
  {
    return rowOrColumn;
  }

  /**
   * Returns the current (old) value of the tile or tiles represented by this move.
   * @return
   *   value of tiles in this move
   */
  public int getValue()
  {
    return value;
  }

  /**
   * Returns the new value of the tile represented by this move.
   * @return
   *   new value of moved or merged tile
   */
  public int getNewValue()
  {
    return newValue;
  }
  
  /**
   * Determines whether this object is equal to the given object.
   * @return
   *   true if the given object is a ShiftDescriptor and all attributes are 
   *   the same as those in this ShiftDescriptor
   */
  public boolean equals(Object obj)
  {
    if (obj == null || obj.getClass() != this.getClass())
    {
      return false;
    }
    ShiftDescriptor other = (ShiftDescriptor) obj;

    return (startIndex == other.startIndex &&
        value == other.value &&
        newValue == other.newValue &&
        rowOrColumn == other.rowOrColumn &&
        dir == other.dir &&
        merged == other.merged);      
  }
  
  /**
   * Returns a string description of this move.
   * @return
   *   a string description of this move
   */
  public String toString()
  {
    // values 1, 2, 3, ... should display as 'a', 'b', 'c', ...
    String toDisplay = "" + (char) ('a' + value - 1);

    String rowAndDirection = "";
    if (rowOrColumn >= 0 && dir != null)
    {
      if (dir == Direction.UP || dir == Direction.DOWN)
      {
        rowAndDirection = " (column " + rowOrColumn + " " + dir + ")";
      }
      else if (dir == Direction.LEFT || dir == Direction.RIGHT)
      {
        rowAndDirection = " (row " + rowOrColumn + " " + dir + ")";
      }

    }
    
    if (merged)
    {
      return "Merge " + toDisplay + " " + startIndex + " to " + (startIndex - 1) + rowAndDirection;
    }
    else
    {
      return "Move " + toDisplay + " " + startIndex + " to " + (startIndex - 1)  + rowAndDirection;
    }
  }
  
  
  
}