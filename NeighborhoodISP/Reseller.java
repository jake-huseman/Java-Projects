package edu.iastate.cs228.hw1;

/**
 * @author Jacob Huseman Also provide appropriate comments for this class
 */

public class Reseller extends TownCell 
{

	/**
	 * Creates a Reseller
	 *
	 * @param p
	 * @param r
	 * @param c
	 */
	public Reseller(Town p, int r, int c) 
	{
		super(p, r, c);
	}

	/**
	 * @return State type
	 */
	@Override
	public State who() 
	{
		return State.RESELLER;
	}

	/**
	 * @return updated cell for next cycle
	 */
	@Override
	public TownCell next(Town tNew) 
	{
		int[] nCensus = TownCell.nCensus;
		plain.grid[row][col].census(nCensus);
    
		if((nCensus[TownCell.CASUAL] <= 3) || (nCensus[TownCell.EMPTY] >= 3)) 
		{
			return new Empty(tNew, row, col);
		}
		return new Reseller(tNew, row, col);
	}
}
