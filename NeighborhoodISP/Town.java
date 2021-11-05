package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Jacob Huseman
 *
 */
public class Town 
{

	private int length;
	private int width;  //Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed. This constructor does not
	 * populate each cell of the grid (but should assign a 2D array to it).
	 *
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) 
	{
		this.length = length;
	    this.width = width;
	    this.grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file. Please see that it simple throws
	 * FileNotFoundException exception instead of catching it. Ensure that you close any resources (like file or scanner)
	 * which is opened in this function.
	 *
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException 
	{
		File file = new File(inputFileName);
	    Scanner input = new Scanner(file);
	    int rows = input.nextInt();
	    int cols = input.nextInt();
	    this.length = rows;
	    this.width = cols;
	    this.grid = new TownCell[rows][cols];
	    
	    for(int row = 0; row < rows; row++) 
	    {
	    	for(int col = 0; col < cols; col++) 
	    	{
	    		String now = input.next();
	    		
	    		switch(now.charAt(0)) 
	    		{
	    			case 'R':
	    				grid[row][col] = new Reseller(this, row, col);
	    				break;
	            
	    			case 'E':
	    				grid[row][col] = new Empty(this, row, col);
	    				break;
	            
	    			case 'C':
	    				grid[row][col] = new Casual(this, row, col);
	    				break;
	            
	    			case 'O':
	    				grid[row][col] = new Outage(this, row, col);
	    				break;
	            
	    			default: // case 'S'
	    				grid[row][col] = new Streamer(this, row, col);
	    				break;
	    		}
	    	}
	    }
	    input.close();
	}

	/**
	 * Returns width of the grid.
	 *
	 * @return
	 * 
	 */
	public int getWidth() 
	{
		return width;
	}

	/**
	 * Returns length of the grid.
	 *
	 * @return
	 */
	public int getLength() 
	{
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object: Casual, Empty, Outage,
	 * Reseller OR Streamer
	 */
	public void randomInit(int seed) 
	{
		Random generator = new Random(seed);
	    
		for(int row = 0; row < this.length; row++) 
	    {
			for(int col = 0; col < this.width; col++) 
			{
				int newRandomValue = generator.nextInt(5);
				TownCell cell;
	        
				if(newRandomValue == TownCell.RESELLER) 
				{
					cell = new Reseller(this, row, col);
				} 
				else if(newRandomValue == TownCell.EMPTY) 
				{
					cell = new Empty(this, row, col);
				} 
				else if(newRandomValue == TownCell.CASUAL) 
				{
					cell = new Casual(this, row, col);
				} 
				else if(newRandomValue == TownCell.STREAMER) 
				{
					cell = new Streamer(this, row, col);
				} 
				else 
				{
					cell = new Outage(this, row, col);
				}
				
				this.grid[row][col] = cell;
			}
	    }
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell type. Each letter should be separated
	 * either by a single space or a tab. And each row should be in a new line. There should not be any extra line between
	 * the rows.
	 */
	@Override
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		for(int row = 0; row < this.length; row++) 
	    {
			for(int col = 0; col < this.width; col++) 
			{
				if(grid[row][col].who().equals(State.RESELLER)) 
				{
					s.append("R");
				} 
				else if(grid[row][col].who().equals(State.EMPTY)) 
				{
					s.append("E");
				} 
				else if(grid[row][col].who().equals(State.CASUAL)) 
				{
					s.append("C");
				} 
				else if(grid[row][col].who().equals(State.OUTAGE)) 
				{
					s.append("O");
				} 
				else 
				{
					s.append("S");
				}
				if(col < this.width - 1) 
				{
					s.append(" ");
				}
			}
			if(row < this.length - 1) 
			{
				s.append("\n");
			}
	    }
		return s.toString();
	}
}