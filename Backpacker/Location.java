package hw1;
/**
 * @author jakeh
 */

/**
 * Location model to show where and how long our backpacker can stay at a location.
 */

public class Location {
	
	public String locationName = "";
	public int cost = 0;
	public static final double RELATIVE_COST_OF_POSTCARD = 0.05;
	
	/*
	 * constructor taking in given name and cost of lodging. 
	 */
	
	public Location(String givenName, int givenLodgingCost) {
		locationName = givenName;
		cost = givenLodgingCost;
		
		}
	
	/*
	 * return name of location
	 */
	
	public String getName() {
		
		return locationName; //locations name
	}
	
	/*
	 * returns cost of lodging.
	 */
	
	public int lodgingCost() {
		
		return cost; //return locations lodging cost per night
	}
	
	/*
	 * returns the cost to send a post card.
	 */
	
	public int costToSendPostcard() {
		
		return (int)(Math.round(cost * RELATIVE_COST_OF_POSTCARD)); //Returns the cost to send a postcard from this location.
		//The value is a percentage of the lodging cost specified by the constant RELATIVE_COST_OF_POSTCARD, rounded to the nearest integer.
	}
	
	/*
	 * returns the max length backpacker can stay at location.
	 */
	
	public int maxLengthOfStay(int funds) {
		int stay = 0;
		stay = (int)funds/cost;
		return stay; //Returns the number of nights of lodging in this location that a backpacker could afford with the given amount of money.
	}
	
	/*
	 * returns number of postcards backpacker can afford to send from current location.
	 */
	
	public int maxNumberOfPostcards(int funds) {
		int postCards;
		postCards = (int)funds/4;
		return postCards; //Returns the number of postcards that a backpacker could afford to send from this location with the given amount of money.
	}

}
