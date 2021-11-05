package edu.iastate.cs228.hw1;

/**
 * @author Jacob Huseman
 */
public class NeighborState extends TownCell
{
	private Town plain;
	private int row;
	private int col;
	private State who;

	/**
	 * Creates an NeighborState
	 *
	 * @param p Town
	 * @param r rows
	 * @param c columns
	 */
	public NeighborState(Town p, int r, int c) 
	{
		super(p, r, c);
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * @return NeighborState
	 */
	@Override
	public State who() 
	{
		return who;
	}

	/**
	 * @return updated cell for next cycle
	 */
	@Override 
	public TownCell next(Town tNew) 
	{
		return new NeighborState(tNew, row, col);
	}

}
