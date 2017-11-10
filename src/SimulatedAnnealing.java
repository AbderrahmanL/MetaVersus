 
 
public class SimulatedAnnealing {
    
    public static void main(String[] args) {
       
         City city = new City("city",60, 200);
         TourManager.addCity(city);
         City city2 = new City("city2",180, 200);
         TourManager.addCity(city2);
         City city3 = new City("city3",80, 180);
         TourManager.addCity(city3);
         City city4 = new City("city4",140, 180);
         TourManager.addCity(city4);
         City city5 = new City("city5",20, 160);
         TourManager.addCity(city5);
         City city6 = new City("city6",100, 160);
         TourManager.addCity(city6);
         City city7 = new City("city7",200, 160);
         TourManager.addCity(city7);
         City city8 = new City("city8",140, 140);
         TourManager.addCity(city8);
         City city9 = new City("city9",40, 120);
         TourManager.addCity(city9);
         City city10 = new City("city10",100, 120);
         TourManager.addCity(city10);
         City city11 = new City("city11",180, 100);
         TourManager.addCity(city11);
         City city12 = new City("city12",60, 80);
         TourManager.addCity(city12);
         City city13 = new City("city13",120, 80);
         TourManager.addCity(city13);
         City city14 = new City("city14",180, 60);
         TourManager.addCity(city14);
         City city15 = new City("city",20, 40);
         TourManager.addCity(city15);
         City city16 = new City("city15",100, 40);
         TourManager.addCity(city16);
         City city17 = new City("city16",200, 40);
         TourManager.addCity(city17);
         City city18 = new City("city17",20, 20);
         TourManager.addCity(city18);
         City city19 = new City("city18",60, 20);
         TourManager.addCity(city19);
         City city20 = new City("city19",160, 20);
         TourManager.addCity(city20);
        
        //Set initial temp
        double temp = 1000000;

        //Cooling rate
        double coolingRate = 0.000003;

        //create random intial solution
        Tour currentSolution = new Tour();
        currentSolution.generateIndividual();
        
        System.out.println("Total distance of initial solution: " + currentSolution.getTotalDistance());
        System.out.println("Tour: " + currentSolution);

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

            // Get the cities at selected positions in the tour
            City citySwap1 = newSolution.getCity(tourPos1);
            City citySwap2 = newSolution.getCity(tourPos2);

            // Swap them
            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);
            
            // Get energy of solutions
            int currentDistance   = currentSolution.getTotalDistance();
            int neighbourDistance = newSolution.getTotalDistance();

            // Decide if we should accept the neighbour
            double rand = Utility.randomDouble();
            if (Utility.acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
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
        System.out.println("Tour: " + best);
    }
}
