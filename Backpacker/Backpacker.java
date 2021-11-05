package hw1;

/*
 * @author jakeh
 */

/*
 * backpacker model to show current backpackers statics.
 */

public class Backpacker {
	
	public static final int SYMPATHY_FACTOR = 30;
	private int currentFund;
	private String currentLocation;
	private int numberOfNights;
	private static int numberOfNightsWoMoney = 0;
	private static int numberOfNightsInT = 0;
	private String currentJournal;
	private int currentLocationCost;
	private static int postCardCost = 4;
	private int totalCards;
	private int noOfCards;
	
	/*
	 * backpacker constructor.
	 */
	public Backpacker(int initialFunds, Location initialLocation) {
		currentFund = initialFunds;
		currentLocation = initialLocation.getName();
	}
	
	/*
	 * returns current location.
	 */
	public String getCurrentLocation() {
		
		return currentLocation;
	}
	
	/*
	 * backpackers current funds.
	 */
	public int getCurrentFunds() {
		
		return currentFund; //Returns the amount of money the backpacker currently has.
	}
	
	/*
	 * backpackers current journal.
	 */
	public String getJournal() {
		if(numberOfNights == 0) {
			
			currentJournal = currentLocation + "(start)";
		}
		else {
			currentJournal = currentJournal + "," + currentLocation + "(" + numberOfNights + ")";
		}
		return currentJournal;
		//Returns the backpacker's journal. The journal is a string of comma-separated
		//values of the form locationName(number_of_nights) containing the cities visited
		//by the backpacker, in the order visited, along with the number of nights spent in
		//each. The first value always has the form locationname(start) for the starting
		//location. For example, if a backpacker starts in Paris, spends 5 nights in Rome,
		//and then spends 2 nights in Prague, the journal string would be:
		//Paris(start),Rome(5),Prague(2)
	}
	
	/*
	 * returns if backpacker is SOL.
	 */
	public boolean isSOL() {
		boolean sol = false;
			if (postCardCost > currentFund) {
				sol = true;
			} else {
				sol = false;
		}
		return sol;  //Returns true if the backpacker does not have enough money to send a postcard from the current location.
	}
	
	/*
	 * returns total nights in the train station.
	 */
	public int getTotalNightsInTrainStation() {
		numberOfNightsInT = numberOfNightsInT + numberOfNightsWoMoney;
		return numberOfNightsInT; //Returns the number of nights the backpacker has spent in a train station when visiting a location without
		//enough money for lodging.
	}
	
	/*
	 * sends the backpacker to a location.
	 */
	public void visit(Location c, int numNights) {
		currentLocation = c.getName();
		numberOfNights = numNights;
		currentLocationCost = c.lodgingCost();
		
		if(currentLocationCost * numberOfNights > currentFund) {
			int possible_nights = currentFund / currentLocationCost;
			numberOfNightsWoMoney = numberOfNights - possible_nights;
			currentFund = currentFund - possible_nights * currentLocationCost;
		}
		else {
			currentFund = currentFund - numberOfNights * currentLocationCost;
		}
		//Simulates a visit by this backpacker to the given location for the given number of
		//nights. The backpacker's funds are reduced based on the number of nights of
		//lodging purchased. When the funds are not sufficient for numNights nights of
		//lodging in the location, the number of nights spent in the train station is updated
		//accordingly. The journal is updated by appending a comma, the location name,
		//and the number of nights in parentheses.
	}
	
	/*
	 * sends given number of postcards home.
	 */
	public void sendPostcardsHome(int howMany) {
			int cost = postCardCost * noOfCards;
			int max_cards = currentFund / postCardCost;
			
			if(max_cards < noOfCards) {
				cost = postCardCost * max_cards;
				totalCards = totalCards + max_cards;
			}
			else
			{
				totalCards = totalCards + noOfCards;
			}
			currentFund = currentFund - cost;
		//Sends the given number of postcards, if possible, reducing the backpacker's funds
		//appropriately and increasing the count of postcards sent. If there is not enough
		//money, sends as many postcards as possible without allowing the funds to go
		//below zero.
	}
	
	/*
	 * calls home for money.
	 */
	public void callHomeForMoney() {
		int homeMoney = totalCards * SYMPATHY_FACTOR;
		currentFund = currentFund + homeMoney;
		//Increases the backpacker's funds by an amount equal to SYMPATHY_FACTOR 
		//times the number of postcards sent since the last call to this method, and resets the
		//count of the number of postcards sent back to zero.
	}

}
