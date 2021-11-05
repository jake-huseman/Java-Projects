package edu.iastate.cs228.hw1;

/**
 * 
 * @author Jacob Huseman
 *	Towncell class that determines the STATE of the cell
 *
 */
public abstract class TownCell 
{

	  protected Town plain;
	  protected int row;
	  protected int col;


	  // constants to be used as indices.
	  protected static final int RESELLER = 0;
	  protected static final int EMPTY = 1;
	  protected static final int CASUAL = 2;
	  protected static final int OUTAGE = 3;
	  protected static final int STREAMER = 4;

	  public static final int NUM_CELL_TYPE = 5;

	  //Use this static array to take census.
	  public static final int[] nCensus = new int[NUM_CELL_TYPE];

	  public TownCell(Town p, int r, int c) 
	  {
	    plain = p;
	    row = r;
	    col = c;
	  }

	  /**
	   * Checks all neighborhood cell types in the neighborhood. Refer to homework pdf for neighbor definitions (all
	   * adjacent neighbors excluding the center cell). Use who() method to get who is present in the neighborhood
	   */
	  public void census(int nCensus[]) 
	  {
	    // zero the counts of all customers
	    nCensus[RESELLER] = 0;
	    nCensus[EMPTY] = 0;
	    nCensus[CASUAL] = 0;
	    nCensus[OUTAGE] = 0;
	    nCensus[STREAMER] = 0;

	    for(int r = this.row - 1; r <= this.row + 1; r++) 
	    {
	      for(int c = this.col - 1; c <= this.col + 1; c++) 
	      {
	        // boundary conditions
	        if(r < 0 || r >= plain.getLength() || c < 0 || c >= plain.getWidth()) 
	        {
	          continue;
	        }
	        // skip the current cell
	        if(r == this.row && c == this.col) 
	        {
	          continue;
	        }
	        TownCell neighbor = plain.grid[r][c];
	        if(neighbor.who().equals(State.RESELLER)) 
	        {
	          nCensus[RESELLER]++;
	        } 
	        else if(neighbor.who().equals(State.EMPTY)) 
	        {
	          nCensus[EMPTY]++;
	        } 
	        else if(neighbor.who().equals(State.CASUAL)) 
	        {
	          nCensus[CASUAL]++;
	        } 
	        else if(neighbor.who().equals(State.OUTAGE)) 
	        {
	          nCensus[OUTAGE]++;
	        } 
	        else 
	        { // Streamer
	          nCensus[STREAMER]++;
	        }
	      }
	    }
	  }

	  /**
	   * Gets the identity of the cell.
	   *
	   * @return State
	   */
	  public abstract State who();

	  /**
	   * Determines the cell type in the next cycle.
	   *
	   * @param tNew: town of the next cycle
	   * @return TownCell
	   */
	  public abstract TownCell next(Town tNew);
	}
