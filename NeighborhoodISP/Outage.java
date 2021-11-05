package edu.iastate.cs228.hw1;

/**
 * @author Jacob Huseman 
 */

public class Outage extends TownCell 
{

	/**
	 * Creates an Outage
	 *
	 * @param p
	 * @param r
	 * @param c
	 */

	public Outage(Town p, int r, int c) 
	{
		super(p, r, c);
	}

	/**
	 * @return State type
	 */
	@Override
	public State who() 
	{
		return State.OUTAGE;
	}

	/**
	 * @return updated cell for next cycle
	 */
	@Override
	public TownCell next(Town tNew) 
	{
		return new Empty(tNew, row, col);
	}
}
