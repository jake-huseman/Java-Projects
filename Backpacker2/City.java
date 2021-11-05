package hw1;
import java.lang.Math;
public class City {
	String name = "";
	int cost = 0;
	public int city_cost;
	public String city_name;
	public static final double POSTCARD_COST = 0.05;
	
	
	public City(String s, int f) {
		city_name = s;
		city_cost = f;
	}

	public String getCityName() {
		return city_name;
	}

	public int hostelCost() {
		return city_cost;
	}

	public int postcardCost() {
		return (int)(Math.round(city_cost*POSTCARD_COST));
	}
	
	public int nightsStay(int moneyAvailable) {
		int temp = 0;
		temp=(int)moneyAvailable/city_cost;
		return temp;
	}

	public int numPostcards(int moneyAvailable) {
		int temp;
		temp = (int)moneyAvailable/4;
		return temp;
	}


}
