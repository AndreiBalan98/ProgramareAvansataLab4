package mypackage;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.shortestpath.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class Robot {
    private Graph<Location, CustomEdge> graph;
    private Graph<Location, DefaultWeightedEdge> probGraph;
    private Map<DefaultWeightedEdge, Double> probabilities;
    private Location currentLocation;

    public void setGraph(Graph<Location, CustomEdge> graph) {
        this.graph = graph;
    }

    public void setProbGraph(Graph<Location, DefaultWeightedEdge> probGraph, HashMap<DefaultWeightedEdge, Double> probabilities) {
        this.probGraph = probGraph;
        this.probabilities = probabilities;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public ArrayList<GraphPath<Location, DefaultWeightedEdge>> findSafestPaths() {
        FloydWarshallShortestPaths<Location, DefaultWeightedEdge> floydWarshall = new FloydWarshallShortestPaths<>(probGraph);

        Set<Location> nodes = probGraph.vertexSet();
        ArrayList<GraphPath<Location, DefaultWeightedEdge>> paths = new ArrayList<>();

        for (Location source : nodes) {
            for (Location target : nodes) {
                if (!source.equals(target)) {
                    var path = floydWarshall.getPath(source, target);
                    if (path != null) {
                        paths.add(path);
                        double maxProb = getMaxProbability(path, probabilities);
                        System.out.println("Cel mai sigur drum de la " + source + " la " + target + ": " + path);
                        System.out.println("Probabilitate maximă: " + maxProb);
                        System.out.println("--------------------------");
                    }
                }
            }
        }

        return paths;
    }

    public List<RouteInfo> findShortestPaths() {
        DijkstraShortestPath<Location, CustomEdge> dijkstra = new DijkstraShortestPath<>(graph);
        List<RouteInfo> routes = new ArrayList<>();

        System.out.println("\n=== Shortest Paths from " + currentLocation + " ===");
        for (Location target : graph.vertexSet()) {
            if (!target.equals(currentLocation)) {
                GraphPath<Location, CustomEdge> path = dijkstra.getPath(currentLocation, target);

                if (path == null) {
                    System.out.printf("To %s -> Time: ∞ (No path available)\n", target);
                    routes.add(new RouteInfo(currentLocation, target, Double.POSITIVE_INFINITY, null));
                } else {
                    List<Location> route = path.getVertexList();
                    double totalTime = path.getWeight();

                    System.out.printf("To %s -> Time: %.2f | Route: %s\n", target, totalTime, formatRoute(route));
                    routes.add(new RouteInfo(currentLocation, target, totalTime, route));
                }
            }
        }

        return routes;
    }

    private double getMaxProbability(GraphPath<Location, DefaultWeightedEdge> path, Map<DefaultWeightedEdge, Double> probabilities) {
        double maxProb = 1.0;
        for (DefaultWeightedEdge edge : path.getEdgeList()) {
            maxProb *= probabilities.get(edge);
        }
        return maxProb;
    }

    private String formatRoute(List<Location> route) {
        StringBuilder sb = new StringBuilder();
        for (Location loc : route) {
            sb.append(loc).append(" -> ");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 4);
        }
        return sb.toString();
    }
}
