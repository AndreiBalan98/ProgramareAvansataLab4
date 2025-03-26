import com.github.javafaker.Faker;
import mypackage.*;
import mypackage.GraphMap;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // COMPULSORY
        /*// Generate locations
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
        enemyLocations.forEach(System.out::println);*/

        // HOMEWORK
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

        // Fastest routes
        GraphMap myMap = new GraphMap(20, 40);
        System.out.println();
        myMap.printMap();
        Robot myRobot = new Robot();
        myRobot.setGraph(myMap.getGraph());
        myRobot.setCurrentLocation(myMap.getLocations()[rand.nextInt(myMap.getLocations().length)]);
        List<RouteInfo> routes = myRobot.findShortestPaths();

        // Grouped locations
        Map<LocationType, List<Location>> groupedLocations = Arrays.stream(myMap.getLocations()).collect(Collectors.groupingBy(Location::getType));
        System.out.println("\nGrouped Locations:");
        groupedLocations.forEach((type, locationsOfType) -> {
            System.out.println(type + ":");
            locationsOfType.forEach(location -> System.out.println("  - " + location.getName()));
        });

        // Grouped routes
        Map<LocationType, List<RouteInfo>> groupedRoutes = routes.stream()
                .collect(Collectors.groupingBy(route -> route.getTarget().getType()));

        groupedRoutes.forEach((type, routeList) -> {
            System.out.println("\n=== Fastest Routes to " + type + " Locations ===");

            routeList.stream()
                    .sorted(Comparator.comparingDouble(RouteInfo::getTotalTime))
                    .forEach(route -> System.out.println("To " + route.getTarget() + " -> Time: " + route.getTotalTime()));
        });
    }
}
