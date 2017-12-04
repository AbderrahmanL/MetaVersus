package com.tsp;

import com.tsp.heuristics.GA;
import com.tsp.heuristics.SimulatedAnnealing;
import com.tsp.shared.Chrono;

public class App {
    public static void main(String[] args) {
        Chrono chrono = new Chrono();
        chrono.start();
        //SimulatedAnnealing.startHeuristic();
        GA.startHeuristic();
        chrono.stop();
        System.out.println(chrono.getDureeSec()); // affichage en secondes la duree d'execution
    }
}
