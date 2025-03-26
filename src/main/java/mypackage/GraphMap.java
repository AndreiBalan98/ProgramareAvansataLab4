package mypackage;

import com.github.javafaker.Faker;
import com.sun.tools.jconsole.JConsoleContext;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.swing.*;
import java.util.Random;

public class GraphMap {
    private Location[] locations;
    private CustomEdge[] connections;
    private Graph<Location, CustomEdge> graph;

    public GraphMap(int numberOfLocations, int numberOfConnections) {
        locations = new Location[numberOfLocations];
        connections = new CustomEdge[numberOfConnections];
        graph = new DefaultDirectedGraph<>(CustomEdge.class);

        randomPopulateMap(numberOfLocations, numberOfConnections);
    }

    public void randomPopulateMap(int numberOfLocations, int numberOfConnections) {
        LocationType[] types = LocationType.values();
        Random rand = new Random();
        Faker faker = new Faker();

        for (int i = 0; i < numberOfLocations; i++) {
            locations[i] = new Location(types[rand.nextInt(types.length)], faker.address().streetName());
            graph.addVertex(locations[i]);
        }

        for (int i = 0; i < numberOfConnections; i++) {
            Location source = locations[rand.nextInt(numberOfLocations)];
            Location target = locations[rand.nextInt(numberOfLocations)];

            if (!source.equals(target) && graph.getEdge(source, target) == null) {
                double time = 1 + rand.nextDouble() * 10;
                double probability = rand.nextDouble();

                CustomEdge edge = new CustomEdge(source, target, time, probability);
                connections[i] = edge;
                graph.addEdge(source, target, edge);
            } else {
                i--;
            }
        }
    }

    public void printMap() {
        System.out.println("=== MAP GRAPH ===\n");

        System.out.println("Locations:");
        for (int i = 0; i < locations.length; i++) {
            System.out.printf("[%d] %s\n", i, locations[i]);
        }

        System.out.println("\nConnections:");
        for (CustomEdge edge : connections) {
            if (edge != null) {
                System.out.printf("[%s] ──(Time: %.2f | Prob: %.2f)──> [%s]\n",
                        edge.getSource(), edge.getTime(), edge.getProbability(), edge.getTarget());
            }
        }
    }

    public Graph<Location, CustomEdge> getGraph() {
        return graph;
    }

    public Location[] getLocations() {
        return locations;
    }
}