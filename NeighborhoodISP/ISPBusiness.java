package edu.iastate.cs228.hw1;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jacob Huseman 
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */

public class ISPBusiness 
{

  /**
   * Returns a new Town object with updated grid value for next billing cycle.
   *
   * @param tOld: old/current Town object.
   * @return: New town object.
   */
	public static Town updatePlain(Town tOld) 
	{
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
    
		for(int row = 0; row < tNew.getLength(); row++) 
		{
			for(int col = 0; col < tNew.getWidth(); col++) 
			{
				tNew.grid[row][col] = tOld.grid[row][col].next(tNew);
			}
		}
		return tNew;
	}

  /**
   * Returns the profit for the current state in the town grid.
   *
   * @param town
   * @return
   */
	public static int getProfit(Town town) //percentage not correct.
	{
		int casualCustomersCount = 0;
    
		for(int row = 0; row < town.getLength(); row++) 
		{
			for(int col = 0; col < town.getWidth(); col++) 
			{
				if(town.grid[row][col].who().equals(State.CASUAL)) 
				{
					casualCustomersCount++;
				}
			}
		}
		return casualCustomersCount;
	}


  /**
   * Main method. Interact with the user and ask if user wants to specify elements of grid via an input file (option: 1)
   * or wants to generate it randomly (option: 2).
   * 
   * Depending on the user choice, create the Town object using respective constructor and if user choice is to populate
   * it randomly, then populate the grid here.
   * 
   * Finally: For 12 billing cycle calculate the profit and update town object (for each cycle). Print the final profit
   * in terms of %. You should print the profit percentage with two digits after the decimal point:  Example if profit
   * is 35.5600004, your output should be:
   * 
   * 35.56%
   * 
   * Note that this method does not throw any exception, so you need to handle all the exceptions in it.
   *
   * @param args
   */
	public static void main(String[] args) 
	{
		try 
		{
			Scanner scanner = null;
			scanner = new Scanner(System.in);
			
			System.out.println("Simulator of an ISP counting profit.");
			System.out.println("Please enter: 1(File Input), 2(Random), or 3(Exit)");
			
			int selection = scanner.nextInt();
			Town town;
      
			switch(selection) 
			{
				case 1:
					System.out.print("Please enter File name: ");
					String fileName = scanner.next();
					town = new Town(fileName);
					break;
					
				case 2:
					System.out.print("Provide rows, cols and seed integer separated by spaces: ");
					int rows = scanner.nextInt();
					int cols = scanner.nextInt();
					int seed = scanner.nextInt();
					town = new Town(rows, cols);
					town.randomInit(seed);
					break;
					
				default:
					throw new IllegalArgumentException("Wrong option selected: " + selection);
			}
			
			int currentRun = 0;
			float profitInYearlyBillingCycle = 0;
			float maxPossibleProfit = town.getLength() * town.getWidth();
			
			while(currentRun < 12) 
			{
				int profit = getProfit(town);
				
				System.out.println(currentRun == 0 ? "Start:" : "After iter: " + currentRun);
				System.out.println(town.toString());
				System.out.println("Profit: " + profit);
				
				profitInYearlyBillingCycle += profit;
				town = updatePlain(town);
				currentRun++;
			}
			float profitPercentage = 100 * profitInYearlyBillingCycle / maxPossibleProfit; // does not output correct percentage. I am confused on what I am calculating over the cycle
			System.out.printf("%.2f%%", profitPercentage);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
}