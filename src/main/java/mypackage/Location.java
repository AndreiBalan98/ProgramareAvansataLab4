package mypackage;

public class Location implements Comparable<Location> {
    private LocationType locationType;
    private String locationName;

    public Location(LocationType locationType, String locationName) {
        this.locationType = locationType;
        this.locationName = locationName;
    }

    public int compareTo(Location otherLocation) {
        return this.locationName.compareTo(otherLocation.locationName);
    }

    public String toString() {
        return locationName +  " (" + locationType.toString() + ")";
    }

    public LocationType getType() {
        return locationType;
    }

    public String getName() {
        return locationName;
    }
}
