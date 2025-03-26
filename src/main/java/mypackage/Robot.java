package mypackage;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private Graph<Location, CustomEdge> graph;
    private Location currentLocation;

    public void setGraph(Graph<Location, CustomEdge> graph) {
        this.graph = graph;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
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
