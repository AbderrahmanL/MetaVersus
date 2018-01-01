package com.tsp.heuristics;

import com.tsp.shared.City;
import com.tsp.shared.Tour;
import com.tsp.shared.Utility;
import com.tsp.shared.TourManager;

import java.util.Random;

public class SimulatedAnnealing implements IHeuristic{

	 //Set initial temp
    static double temp = 9999999;

    //Cooling rate
    static double coolingRate = 0.000003;
    
    /**
     * Calculates the acceptance probability
     */
    public static double acceptanceProbability(double currentDistance, double newDistance, double temperature) {
        // If the new solution is better, accept it
        if (newDistance < currentDistance) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((currentDistance - newDistance) / temperature);
    }

    /**
     * this method returns a random number n such that
     * 0.0 <= n <= 1.0
     */
    public static double randomDouble()
    {
        Random r = new Random();
        return r.nextInt(1000) / 1000.0;
    }

    /**
    * The heuristic main algorithm implementation
    */
    public void startHeuristic() {


        Utility.readCities("src/com/tsp/shared/usa115475.tsp");

        if (TourManager.numberOfCities()>0){

            //create random intial solution
            Tour currentSolution = new Tour();
            currentSolution.generateRandom();

            System.out.println("Total distance of initial solution: " + currentSolution.getTotalDistance());

            // We would like to keep track if the best solution
            // Assume best solution is the current solution
            Tour best = new Tour(currentSolution.getTour());
            Tour newSolution = null;
            int tourPos1 ,tourPos2 ;
            double currentDistance,neighbourDistance;
            City citySwap1,citySwap2 ;
            double rand ;
            // Loop until system has cooled
            new Thread() {

				@Override
				public void run() {
					while(temp>1) {
					System.out.println(temp);
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
            	
            }.start();
            while (temp > 1) {
                // Create new neighbour tour
                newSolution = new Tour(currentSolution.getTour());

                // Get random positions in the tour
                tourPos1 = Utility.randomInt(0 , newSolution.tourSize());
                tourPos2 = Utility.randomInt(0 , newSolution.tourSize());

                //to make sure that tourPos1 and tourPos2 are different
                while(tourPos1 == tourPos2) {tourPos2 = Utility.randomInt(0 , newSolution.tourSize());}

                // Get the coords at selected positions in the tour
                citySwap1 = newSolution.getCity(tourPos1);
                citySwap2 = newSolution.getCity(tourPos2);

                // Swap them
                newSolution.setCity(tourPos2, citySwap1);
                newSolution.setCity(tourPos1, citySwap2);

                // Get energy of solutions
                currentDistance   = currentSolution.getTotalDistance();
                neighbourDistance = newSolution.getTotalDistance();

                // Decide if we should accept the neighbour
                rand = randomDouble();
                if (acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
                    currentSolution = new Tour(newSolution.getTour());
                }

                // Keep track of the best solution found
                if (currentSolution.getTotalDistance() < best.getTotalDistance()) {
                    best = new Tour(currentSolution.getTour());
                }

                // Cool system
                temp *= 1 - coolingRate;
            }

            System.out.println("Final solution distance: " + best.getTotalDistance());
        }


    }
}
