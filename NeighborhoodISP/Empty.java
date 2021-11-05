package edu.iastate.cs228.hw1;

/**
 * @author Jacob Huseman
 */

public class Empty extends TownCell 
{

	/**
	 * Creates an empty
	 *
	 * @param p Town
	 * @param r rows
	 * @param c columns
	 */
	public Empty(Town p, int r, int c) 
	{
		super(p, r, c);
	}
	
	/**
	 * @return State type
	 */
	@Override
	public State who() 
	{
		return State.EMPTY;
	}

	/**
	 * @return updated cell for next cycle
	 */
	@Override
	public TownCell next(Town tNew) 
	{
		int[] nCensus = TownCell.nCensus;
		plain.grid[row][col].census(nCensus);
    
		if((nCensus[TownCell.EMPTY] + nCensus[TownCell.OUTAGE]) <= 1) 
		{
			return new Reseller(tNew, row, col);
		}
		return new Casual(tNew, row, col);
	}
}