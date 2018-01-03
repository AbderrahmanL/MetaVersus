package com.tsp.heuristics;

import com.tsp.shared.City;
import com.tsp.shared.Tour;
import com.tsp.shared.TourManager;
import com.tsp.shared.Utility;

import java.util.ArrayList;

public class NearestNeighbor implements IHeuristic {

    public Tour findShortestTour(Tour cities){
        ArrayList<City> shortest = new ArrayList<>();
        System.out.println("------------------------------------------------");
        System.out.println("Initial Tour distance ==> "+ cities.getTotalDistance());
        System.out.println("------------------------------------------------");
        City currentCity = cities.getTour().get((int)(cities.getTour().size()*Math.random()));
        update(cities, currentCity ,shortest);
        while (cities.getTour().size() >= 1){
            currentCity = getNextCity(cities.getTour() , currentCity);
            update(cities,currentCity, shortest);
        }
        return new Tour(shortest);
    }

    private City getNextCity(ArrayList<City> cities,City city){
        return cities.stream().min((city1,city2) -> {
            int flag = 0;
            if (Utility.distance(city1,city) < Utility.distance(city2,city)) flag = -1;
            else if (Utility.distance(city1,city) > Utility.distance(city2,city)) flag = 1;
            return flag;
        }).get();
    }

    private void update(Tour cities, City startCity, ArrayList<City> shortest) {
        shortest.add(startCity);
        cities.getTour().remove(startCity);
    }

    @Override
    public Tour startHeuristic() {
        Utility.readCities("src/com/tsp/shared/usa115475.tsp");
        Tour initial = new Tour(TourManager.getCitiesList());
        return findShortestTour(initial);
    }
}
