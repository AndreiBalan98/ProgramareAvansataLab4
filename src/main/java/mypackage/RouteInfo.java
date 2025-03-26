package mypackage;

import java.util.List;

public class RouteInfo {
    private Location source;
    private Location target;
    private double totalTime;
    private List<Location> route;

    public RouteInfo(Location source, Location target, double totalTime, List<Location> route) {
        this.source = source;
        this.target = target;
        this.totalTime = totalTime;
        this.route = route;
    }

    public Location getSource() {
        return source;
    }

    public Location getTarget() {
        return target;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public List<Location> getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Time: " + totalTime + " | Route: " + route;
    }
}
