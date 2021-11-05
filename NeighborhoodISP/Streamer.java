package edu.iastate.cs228.hw1;

/**
 * @author Jacob Huseman Also provide appropriate comments for this class
 */

public class Streamer extends TownCell 
{

	/**
	 * Creates an Streamer
	 *
	 * @param p
	 * @param r
	 * @param c
	 */

	public Streamer(Town p, int r, int c) 
	{
    super(p, r, c);
	}

	/**
	 * @return State type
	 */
	@Override
	public State who() 
	{
    return State.STREAMER;
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
    else if(nCensus[TownCell.RESELLER] > 0) 
    {
    	return new Outage(tNew, row, col);
    } 
    else if(nCensus[TownCell.OUTAGE] > 0) 
    {
    	return new Empty(tNew, row, col);
    }
    return new Streamer(tNew, row, col);
	}
}
