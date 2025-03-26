package mypackage;

public class CustomEdge {
    private final Location source;
    private final Location target;
    private double time;
    private double probability;

    public CustomEdge(Location source, Location target, double time, double probability) {
        this.source = source;
        this.target = target;
        this.time = time;
        this.probability = probability;
    }

    public Location getSource() {
        return source;
    }

    public Location getTarget() {
        return target;
    }

    public double getTime() {
        return time;
    }

    public double getProbability() {
        return probability;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s | Time: %.2f, Prob: %.2f", source, target, time, probability);
    }
}

