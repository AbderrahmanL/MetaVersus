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
			while((line = reader.readLine())!= null){

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
