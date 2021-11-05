package hw1;
	
public class Backpacker {
	public static final int SYMPATHY_FACTOR = 30;
	public static int current_fund;
    public static int initial_fund;
    public static int number_of_nights;
    public static int number_of_nights_wo_money_t = 0;
    public static int number_of_nights_wo_money = 0;
    public static String current_city;
    public static String current_journal;
    public boolean journey_started = false;
    public int current_city_cost;
    public static int paris_postcard_cost = 4;
    public static int total_cards;
	
    
    
    public static void main(String[] args) {

    }
	
	public Backpacker(int initialFunds, City initialCity) {
		current_fund = initialFunds;
		current_city = initialCity.city_name;
		current_city_cost = initialCity.city_cost;
		number_of_nights = 0;
	}
	
	public String getCurrentCity() {
		return current_city;
	}

	public int getCurrentMoney() {
		return current_fund;
	}
	
	public String getJournal() {
		if(number_of_nights == 0) {
			
			current_journal = current_city + "(start)";
		}
		else {
			current_journal = current_journal + "," + current_city + "(" + number_of_nights + ")";
		}
		return current_journal;
	}
	
	public boolean isSOL() {
		boolean sol = false;
		
		if(current_city == "Paris") {
			if (paris_postcard_cost > current_fund) {
				sol = true;
			} else {
				sol = false;
			}
		}
		return sol;
	}
	
	public int getNightsInStation() {
		number_of_nights_wo_money_t = number_of_nights_wo_money_t + number_of_nights_wo_money;
		return number_of_nights_wo_money_t;
	}
	
	public void visit(City city, int no_of_nights) {
		current_city = city.city_name;
		number_of_nights = no_of_nights;
		current_city_cost = city.city_cost;
		
		if(current_city_cost * no_of_nights > current_fund) {
			int possible_nights = current_fund / current_city_cost;
			number_of_nights_wo_money = number_of_nights - possible_nights;
			current_fund = current_fund - possible_nights * city.city_cost;
		}
		else {
			current_fund = current_fund - number_of_nights *city.city_cost;
		}
	}
	
	
	public void sendPostcardsHome(int no_of_cards) {
		if(current_city == "Paris") {
			int cost = paris_postcard_cost * no_of_cards;
			int max_cards = current_fund / paris_postcard_cost;
			
			if(max_cards < no_of_cards) {
				cost = paris_postcard_cost * max_cards;
				total_cards = total_cards + max_cards;
			}
			else
			{
				total_cards = total_cards + no_of_cards;
			}
			current_fund = current_fund - cost;
			}
	}

	public void callHomeForMoney() {
		int home_money = total_cards * SYMPATHY_FACTOR;
		current_fund = current_fund + home_money;
	}

}
