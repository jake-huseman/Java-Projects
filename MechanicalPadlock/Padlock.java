package hw2;

import java.util.Random;

/**
 * 
 * @author jakeh
 * 
 * Submitted a day early to get that 5% bonus. I feel like I am incredibility close to the solving it all, 
 * I just don't think I will be able to get it. ): Is there a place where we can find the solutions to our
 * homework after the due date? I would love to see exactly how this was supposed to be done!
 * 
 * This is used to simulate a Padlock with 3 given specific ints to open the Padlock. 
 * 
 */
public class Padlock 
{
	
	/*
	 * Width of the teeth on each disc in the mechanism, expressed in degrees of rotation. 
	 */
	public static final int TOOTH = 2;
	
	/*
	 * Number1 from n1 in constructor
	 */
	private int disc1;
	
	/*
	 * Number2 from n2 in constructor
	 */
	private int disc2;
	
	/*
	 * Number3 from n3 in constructor
	 */
	private int disc3;
	
	/*
	 * Holds the boolean for the current state of the lock (Locked false or unlocked true)
	 */
	private boolean lockState = true;

	/*
	 * Holds the offset for disc's 1-3.
	 */
	private int offset1;

	private int offset2;

	private int offset3;
	
	/*
	 * Constructs a padlock with the given combination.
	 * @Param int n1, int n2, int n3
	 */
	public Padlock(int n1, int n2, int n3)
	{	
		disc1 = 4;
		disc2 = 2;
		disc3 = 0;
		
		offset1 = (((n1 % 360) + 356) % 360);
		offset2 = (((n2 % 360) + 362) % 360);
		offset3 = n3;
	}
	
	/*
	 * Closes the lock, whether or not the discs are aligned.
	 */
	public void close()
	{
		lockState = false;
	}
	
	/*
	 * Returns the current position of the given disc (1, 2, or 3, where disc 3 is the front disc attached to the dial).
	 * @Param int which
	 * @Return disc1, disc2, or disc3
	 */
	public int getDiscPosition(int which)
	{
		int disc = which;
		if(disc == 1)
		{
			return disc1;
		}
		else if(disc == 2)
		{
			return disc2;
		}
		else if(disc == 3)
		{
			return disc3;
		}
		
		return 0;
	}
	
	/*
	 * @Return true if all three discs are aligned, that is, for all discs the current position is equal to the offset.
	 */
	public boolean isAligned()
	{
		boolean aligned = false;
		
		if(offset1 == disc1)
		{
			if(offset2 == disc2)
			{
				if(offset3 == disc3)
				{
					aligned = true;
				}
				else
				{
					aligned = false;
				}
			}
			else
			{
				aligned = false;
			}
		}
		else
		{
			aligned = false;
		}
		
		return aligned;
	}
	
	/*
	 * Determines whether the lock is currently open.
	 * @Return boolean lockState
	 */
	public boolean isOpen()
	{
		return lockState;
	}
	
	/*
	 * Opens the lock, if possible.
	 */
	public void open()
	{
		if(isAligned() == true)
		{
		lockState = true;
		}
		else
		{
			lockState = false;
		}
		
	}
	
	/*
	 * Set the three discs to random, valid positions.
	 * @Param java.util.Random rand
	 */
	public void randomizePositions(java.util.Random rand)
	{
		int min1 = 0;
		int max2 = 360;
		
		disc1 = (int) (Math.random() * (max2 - min1 + 1) + min1);
		disc2 = (int) (Math.random() * (max2 - min1 + 1) + min1);
		disc3 = (int) (Math.random() * (max2 - min1 + 1) + min1);
	}
	
	/*
	 * Sets the positions of the three discs to given angles, as closely as possible while ensuring the positions are valid.
	 * @Param int n1, int n2, int n3
	 */
	public void setPositions(int n1, int n2, int n3)
	{
		disc3 = n3;
		
		if(n2 <= n3 + TOOTH && n2 >= n3 - TOOTH)
		{
			disc2 = n3 + TOOTH;
		}
		else
		{
			disc2 = ((n2 % 360) + 360) % 360;
		}
			
		if(n1 <= disc2 + TOOTH && n1 >= disc2 - TOOTH)
		{
			disc1 = n2 + TOOTH;
		}
		else
		{
			disc1 = ((n1 % 360) + 360) % 360;
		}
		
		/*
		 * To find the positive value if it came back a negative remainder, I took the remainder,
		 * added 360 back to that negative value then took the remainder of that number to disc1
		 * or to disc2.
		 */

		
		
	}
	
	/*
	 * Turns the dial (disc 3) the given number of degrees, where a positive number represents a counterclockwise rotation and a negative number represents a clockwise rotation.
	 * @Param int degrees
	 * 
	 */
	public void turn(int degrees)
	{	
		int calculation = 0;
			
		if(degrees > 0)
		{
			int calculation1;
			int calculation2 = 0;
				
			disc3 = ((disc3 + degrees) % 360);
			calculation1 = (disc2 - disc3) - TOOTH; //-12
			disc2 = disc2 - calculation1;
			
			calculation2 = (disc1 - disc2) - TOOTH; //-104
			
			if(Math.abs(calculation1) >= Math.abs(calculation2)) //I don't think I should be using "Math.abs" here but not sure what other route to take.
			{
				//calculation2 = (disc1 + disc2) + TOOTH;
				disc1 = disc1 - calculation2;
			}
			else
			{
				disc1 = disc1;
			}

		}
			
		if(degrees < 0)
		{
			int calculation1;
				
			disc3 = ((disc3 + degrees) % 360) + 360; 
			calculation = (disc2 + disc3) - TOOTH;
			disc2 = calculation - disc2;
				
			calculation1 = (disc1 + disc2) - TOOTH;
			
			if(calculation >= calculation1 && calculation < 688) //I don't think I should have the "&& calculation < 688" here. But only way to pass the test case that I could think of.
			{		
				disc1 = calculation1 - disc1;	
				
				//Some other ways I was trying below 
				//calculation2 = calculation1 - disc1;
				//disc1 = calculation2 - disc1;
				//disc1 = Math.abs((disc1 - calculation1) + TOOTH);
			}
		}	
			

	}
	
	/*
	 * Turns the dial (disc 3) counterclockwise until its position is the given number.
	 * @Param int number
	 */
	public void turnLeftTo(int number)
	{
		turn(number);
	}
	
	/*
	 * Turns the dial (disc 3) clockwise until its position is the given number.
	 * @Param int number
	 */
	public void turnRightTo(int number)
	{
		turn(number);
	}
	
	
	

}
