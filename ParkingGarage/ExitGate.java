package hw2;

/*
 * @author jhuse
 */
public class ExitGate {
	
	/*
	 * Assigns TimeClock to clockInfo to access.
	 */
	private TimeClock clockInfo;
	
	/*
	 * Assigns ParkingCard to card to access.
	 */
	private ParkingCard card;
	
	/*
	 * Initializes numOfExits to 0.
	 */
	private int numOfExits = 0;
	
	/*
	 * constructs an ExitGate with givenClock
	 * @param
	 * givenClock
	 */
	public ExitGate(TimeClock givenClock) {
		clockInfo = givenClock;
	}
	
	/*
	 * boolean to insert a card.
	 * @param
	 * ParkingCard c
	 */
	public boolean insertCard(ParkingCard c) {
		card = c;
		if(card.getPaymentTime() == 0) {
			return false;
		}
		if(clockInfo.getTime() - card.getPaymentTime() <= 15) {
			return true;
		} 
		else { return false;
		}
	}
	
	/*
	 * Counts the number of times an exit has been made.
	 */
	public int getExitCount() {
		numOfExits += 1;
		return numOfExits;
	}
	
	
}
