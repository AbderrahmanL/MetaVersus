package com.tsp.heuristics;

import com.tsp.shared.City;
import com.tsp.shared.Tour;
import com.tsp.shared.Utility;
import com.tsp.shared.TourManager;

import java.util.Random;

public class SimulatedAnnealing {

    /**
     * Calculates the acceptance probability
     */
    public static double acceptanceProbability(int currentDistance, int newDistance, double temperature) {
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
    public static void startHeuristic() {


        Utility.readCities("src/com/tsp/shared/usa115475.tsp");

        if (TourManager.numberOfCities()>0){
            //Set initial temp
            double temp = 1000000;

            //Cooling rate
            double coolingRate = 0.003;

            //create random intial solution
            Tour currentSolution = new Tour();
            currentSolution.generateIndividual();

            System.out.println("Total distance of initial solution: " + currentSolution.getTotalDistance());

            // We would like to keep track if the best solution
            // Assume best solution is the current solution
            Tour best = new Tour(currentSolution.getTour());

            // Loop until system has cooled
            while (temp > 1) {
                // Create new neighbour tour
                Tour newSolution = new Tour(currentSolution.getTour());

                // Get random positions in the tour
                int tourPos1 = Utility.randomInt(0 , newSolution.tourSize());
                int tourPos2 = Utility.randomInt(0 , newSolution.tourSize());

                //to make sure that tourPos1 and tourPos2 are different
                while(tourPos1 == tourPos2) {tourPos2 = Utility.randomInt(0 , newSolution.tourSize());}

                // Get the coords at selected positions in the tour
                City citySwap1 = newSolution.getCity(tourPos1);
                City citySwap2 = newSolution.getCity(tourPos2);

                // Swap them
                newSolution.setCity(tourPos2, citySwap1);
                newSolution.setCity(tourPos1, citySwap2);

                // Get energy of solutions
                int currentDistance   = currentSolution.getTotalDistance();
                int neighbourDistance = newSolution.getTotalDistance();

                // Decide if we should accept the neighbour
                double rand = randomDouble();
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
