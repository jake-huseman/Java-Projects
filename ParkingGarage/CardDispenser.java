package hw2;

/*
 * @ author jhuse
 */
public class CardDispenser {
	
	/*
	 * assigns TimeClock to clockInfo to access.
	 */
	private TimeClock clockInfo;
	
	/*
	 * Constructs CardDispenser with given clock
	 * @param
	 * givenClock
	 */
	public CardDispenser(TimeClock givenClock) {
		clockInfo = givenClock;
	}
	
	/*
	 * Takes a parkingCard
	 */
	public ParkingCard takeCard() {
		int x = clockInfo.getTime();
		ParkingCard takeCard = new ParkingCard(x);
		
		return takeCard;
	}
	
	
}
