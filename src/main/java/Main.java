import com.github.javafaker.Faker;
import mypackage.*;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Location[] locations = new Location[5];

        locations[0] = new Location(LocationType.FRIENDLY, "Location1");
        locations[1] = new Location(LocationType.NEUTRAL, "Location2");
        locations[2] = new Location(LocationType.ENEMY, "Location3");
        locations[3] = new Location(LocationType.FRIENDLY, "Location4");
        locations[4] = new Location(LocationType.NEUTRAL, "Location5");

        // Friendly locations
        TreeSet<Location> friendlyLocations =
                Arrays.stream(locations)
                        .filter(location -> location.getType() ==  LocationType.FRIENDLY)
                        .collect(Collectors.toCollection(TreeSet::new));

        System.out.println("Friendly Locations:");
        friendlyLocations.forEach(System.out::println);

        // Enemy locations
        LinkedList<Location> enemyLocations =
                Arrays.stream(locations)
                        .filter(location -> location.getType() == LocationType.ENEMY)
                        .sorted(Comparator.comparing(Location::getType)
                                .thenComparing(Location::getName))
                        .collect(Collectors.toCollection(LinkedList::new));

        System.out.println("Enemy locations:");
        enemyLocations.forEach(System.out::println);

        // Random locations
        Location[] randomLocations = new Location[10];
        LocationType[] types = LocationType.values();
        Random rand = new Random();
        Faker faker = new Faker();

        for (int i = 0; i < randomLocations.length; i++) {
            randomLocations[i] = new Location(types[rand.nextInt(types.length)], faker.address().streetName());
        }

        System.out.println("Random Locations:");
        for (Location location : randomLocations) {
            System.out.println(location);
        }
    }
}
