package com.tsp.shared;
import java.util.ArrayList;

/*
* Holds and keeps track of the cities of a tour
*/


public class TourManager {

    // Holds our cities
    private static ArrayList<City> destinationCities = new ArrayList<City>();

    /**
	 * Adds a destination city
	 */
	public static void addCity(City city) {
		destinationCities.add(city);
	}

	/**
	 * returns a city given its index
	 */
	public static City getCity(int index){
		return (City)destinationCities.get(index);
	}

	/**
	 * Returns the number of destination cities 
	 */
	public static int numberOfCities(){
		return destinationCities.size();
	}
    
}
