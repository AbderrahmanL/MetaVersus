package com.tsp.shared;

import java.util.ArrayList;
import java.util.Collections;

/*
* Models a traveling salesman tour
* Stores a candidate tour through all cities
*/

public class Tour{

    //to hold a tour of cities
    private ArrayList<City> tour = new ArrayList<City>();
    
    //we assume initial value of distance is 0
    private double fitness = 0;
    private int distance = 0;
    
    //Constructor
    //starts an empty tour
    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            tour.add(null);
        }
    }
    
    //another Constructor
    //starts a tour from another tour
    @SuppressWarnings("unchecked")
	public Tour(ArrayList<City> tour){
        this.tour = (ArrayList<City>) tour.clone();
    }
    
    /**
      Returns tour information
     */
    public ArrayList<City> getTour(){
        return tour;
    }
     
    /**
     * Creates a random tour (i.e. individual or candidate solution)
     */
    public void generateRandom() {
        // Loop through all our destination cities and add them to our tour
        for (int cityIndex = 0; cityIndex < TourManager.numberOfCities(); cityIndex++) {
          setCity(cityIndex, TourManager.getCity(cityIndex));
        }
        // Randomly reorder the tour
        Collections.shuffle(tour);
    }

    public void generateWithNearestNeighbor(){

    }

    /**
     * Returns a city from the tour given the city's index
     */
    public City getCity(int index) {
        return tour.get(index);
    }

    /**
     * Sets a city in a certain position within a tour
     */
    public void setCity(int index, City city) {
        tour.set(index, city);
        // If the tour has been altered we need to reset the fitness and distance
        distance = 0;
    }

    // Gets the tours fitness
    public double getFitness() {
        if (fitness == 0) {
            fitness = 1/(double)getTotalDistance();
        }
        return fitness;
    }

    /**
     * Computes and returns the total distance of the tour
     */
    public double getTotalDistance(){
    	if (distance == 0) {
            int tourDistance = 0;
            // Loop through our tour's cities
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                // Get city we're traveling from
                City fromCity = getCity(cityIndex);
                // City we're traveling to
                City destinationCity;
                // Check we're not on our tour's last city, if we are set our
                // tour's final destination city to our starting city
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }
                else{
                    destinationCity = getCity(0);
                }                
                // Get the distance between the two cities
                tourDistance += Utility.distance(fromCity, destinationCity); 
            }
            distance = tourDistance;
        }
        return distance;
    }

    /**
     * Get number of cities on our tour
     */
    public int tourSize() {
        return tour.size();
    }

    // Check if the tour contains a city
    public boolean containsCity(City city){
        return tour.contains(city);
    }

    @Override
    /**
     * To print out a list of all the cities in the tour
     */
    public String toString() {
        String s = getCity(0).getCityName();
        for (int i = 1; i < tourSize(); i++) {
            s += " -> " + getCity(i).getCityName();
        }
        return s;
    }
}
    
