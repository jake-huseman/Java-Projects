package hw2;
/*
 * author @jhuse
 */

/*
 * model of a PayStation in MU parking garage. 
 */
public class PayStation {
	/*
	 * assigns TimeClock to clockInfo to access.
	 */
	private TimeClock clockInfo;
	/*
	 * Total Amount of payments initialzed to 0.
	 */
	private double totalPayments = 0;
	/*
	 * assigning new Parkingcard to currentParkingCard to access.
	 */
	private ParkingCard currentParkingCard;
	/*
	 * boolean cardInserted to show if card is inserted into the pay machine or not.
	 */
	private boolean cardInserted;
	/*
	 * double amountDue to hold the total amount due at time of paying.
	 */
	private double amountDue;
	
	/*
	 * Constructs a new PayStation with the givenClock.
	 * @param givenClock
	 */
	public PayStation(TimeClock givenClock) {
		clockInfo = givenClock;
		totalPayments = 0.0;
		
	}
	
	/*
	 * inserts a new card with given ParkingCard t.
	 * @param t
	 * 
	 */
	public void insertCard(ParkingCard t) {
		cardInserted = true;
		currentParkingCard = t;
	}
	
	/*
	 * gets CurrentCard or returns null if no card.
	 * @return
	 * currentParkingcard
	 */
	public ParkingCard getCurrentCard() {
		if(cardInserted == true) {
			return currentParkingCard;
		}
		else { return null; 
		}
	}
	
	/*
	 * decides if card is inserted and inProgress or not.
	 * @return
	 * true if in progress false if not.
	 */
	public boolean inProgress() {
		if(cardInserted == true) {
			return true;
		} 
		else { return false;
		}
	}
	
	/*
	 * gets paymentDue
	 * @return
	 * amountDue 
	 */
	public double getPaymentDue() {
		if(cardInserted == true) {
			amountDue = RateUtil.calculateCost(clockInfo.getTime() - currentParkingCard.getPaymentTime() - currentParkingCard.getStartTime());
		}
			return amountDue;
		}
	
	/*
	 * makes payment
	 */
	public void makePayment() {
		
	}
	
	/*
	 * ejects card
	 */
	public void ejectCard() {
		cardInserted = false;	
	}
	
	/*
	 * up's totalPayments by 1 each time payment is made.
	 */
	public double getTotalPayments() {
		return totalPayments += 1;
	}

}
