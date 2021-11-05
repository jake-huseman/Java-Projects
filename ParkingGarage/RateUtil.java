package hw2;
/*
 * @author jhuse
 */
public class RateUtil {
	
	/*
	 * time allowed to exit garage after paying
	 */
	public static final int EXIT_TIME_LIMIT = 15;
	
	/*
	 * cost of parking
	 */
	private static double cost;
	
	/*
	 * private RateUtil to call
	 */
	private RateUtil() {
		
	}
	
	/*
	 * calculates cost of parking with given minutes
	 * @param
	 * minutes
	 * @return
	 * cost
	 */
	public static double calculateCost(int minutes) {
		if(minutes <= 30) {
			cost = 1;
		}
		else if(minutes > 30 && minutes <= 60) {
			cost = 2;
		}
		else if(minutes > 60 && minutes <= 120) {
			cost = 3.5;
		}
		else if(minutes > 120 && minutes <= 180) {
			cost = 5;
		}
		else if(minutes > 180 && minutes <= 240) {
			cost = 6.5;
		}
		else if(minutes > 240 && minutes <= 300) {
			cost = 8;
		}
		else if(minutes > 300 && minutes <= 360) {
			cost = 9.25;
		}
		else if(minutes > 360 && minutes <= 420) {
			cost = 10.5;
		}
		else if(minutes > 420 && minutes <= 480) {
			cost = 11.75;
		}
		else if(minutes > 480 && minutes <= 1440) {
				cost = 13;
		}
		else { cost = (minutes / 1440) * 13.00 + calculateCost(minutes % 1440);
		}
		return cost;
	}
}
