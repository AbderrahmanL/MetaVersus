package com.tsp;

import com.tsp.heuristics.GA;
import com.tsp.heuristics.IHeuristic;
import com.tsp.heuristics.NearestNeighbor;
import com.tsp.heuristics.SimulatedAnnealing;
import com.tsp.shared.Chrono;

public class App {
    public static final int TabuSize =100;
    public static void main(String[] args) {
        Chrono chrono = new Chrono();
        IHeuristic heuristic = new NearestNeighbor();
        chrono.start();
        System.out.println("la solution: "+heuristic.startHeuristic().getTotalDistance());
        chrono.stop();
        System.out.println(chrono.getDureeSec()); // affichage en secondes la duree d'execution
//        int tab[][] = new int[TabuSize][2];
//        for (int i = 0 ; i<TabuSize ; i++){
//            for (int j = 0 ; j<2 ; j++)
//                tab[i][j] = i+j;
//        }
//
//        for (int i = 0 ; i<TabuSize ; i++){
//            for (int j = 0 ; j<2 ; j++)
//                System.out.print(tab[i][j]+" ");
//            System.out.println();
//        }
    }
}
