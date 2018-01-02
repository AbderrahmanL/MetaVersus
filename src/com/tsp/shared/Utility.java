package com.tsp.shared;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Utility {


	/**
	 * Computes and returns the Euclidean distance between two cities
	 */
	public static double distance(City city1, City city2){
		double xDistance = Math.abs(city1.getX() - city2.getX());
		double yDistance = Math.abs(city1.getY() - city2.getY());
		double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );

		return distance;
	}

	public static void readCities(String filePath){
		String line = "";
		String[] coords;
		try {
			FileReader fr = new FileReader(filePath);
			BufferedReader reader = new BufferedReader(fr);
//			while((line = reader.readLine())!= null){
			for(int i = 0; i<100 ; i++) {
				line = reader.readLine();
				coords = line.split(" ");
				if (!coords[0].matches("[0-9]*") )
					continue;

				City city = new City("city",Double.parseDouble( coords[1]), Double.parseDouble( coords[2]));
				TourManager.addCity(city);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
    * Get A neighbor solution
     */
	public static Tour getNeighbor(Tour tour){
		City citySwap1,citySwap2 ;
		int tourPos1 ,tourPos2 ;
		Tour neighbor = new Tour(tour.getTour());

		// Get random positions in the tour
		tourPos1 = Utility.randomInt(0 , neighbor.tourSize());
		tourPos2 = Utility.randomInt(0 , neighbor.tourSize());

		//to make sure that tourPos1 and tourPos2 are different
		while(tourPos1 == tourPos2) {tourPos2 = Utility.randomInt(0 , neighbor.tourSize());}

		// Get the coords at selected positions in the tour
		citySwap1 = neighbor.getCity(tourPos1);
		citySwap2 = neighbor.getCity(tourPos2);

		// Swap them
		neighbor.setCity(tourPos2, citySwap1);
		neighbor.setCity(tourPos1, citySwap2);

		return neighbor;
	}
	
	/**
	 * returns a random int value within a given range
	 * min inclusive .. max not inclusive
	 */ 
	public static int randomInt(int min , int max) {
		Random r = new Random();
		double d = min + r.nextDouble() * (max - min);
		return (int)d;
	}
}
