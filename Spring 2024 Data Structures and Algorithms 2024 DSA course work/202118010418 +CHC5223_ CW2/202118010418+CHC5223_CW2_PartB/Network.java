import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Network {
    final double NO_LINK = Double.MAX_VALUE;
    int numStations;
    double[][] distance;
    StationInfo[] stations;

    public Network(int capacity) {
        numStations = 0;
        distance = new double[capacity][capacity];
        stations = new StationInfo[capacity];
        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                if (i == j) {
                    distance[i][j] = NO_LINK;
                } else {
                    distance[i][j] = NO_LINK;
                }
            }
        }
    }

    public void readNetworkFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split(" ");
            if (words[0].equals("\"station\"")) {
                String name = words[1];
                int xPos;
                int yPos;
                try {
                    xPos = Integer.parseInt(words[2]);
                    yPos = Integer.parseInt(words[3]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid coordinates for station: " + name);
                }
                if (findStationIndex(name) != -1) {
                    throw new IllegalArgumentException("Duplicate station: " + name);
                }
                if (numStations >= stations.length) {
                    throw new IllegalArgumentException("Exceeded capacity of stations");
                }
                stations[numStations] = new StationInfo(name, xPos, yPos);
                numStations++;
            } else if (words[0].equals("\"link\"")) {
                String name1 = words[1];
                String name2 = words[2];
                double dist;
                try {
                    dist = Double.parseDouble(words[3]);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid distance for link: " + name1 + " - " + name2);
                }
                int index1 = findStationIndex(name1);
                int index2 = findStationIndex(name2);
                if (index1 == -1 || index2 == -1) {
                    throw new IllegalArgumentException("Invalid link: " + name1 + " - " + name2);
                }
                if (distance[index1][index2] != NO_LINK || distance[index2][index1] != NO_LINK) {
                    throw new IllegalArgumentException("Duplicate link: " + name1 + " - " + name2);
                }
                distance[index1][index2] = dist;
                distance[index2][index1] = dist;
            }
        }
        reader.close();
    }

    private int findStationIndex(String name) {
        for (int i = 0; i < numStations; i++) {
            if (stations[i].getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void printStations() {
        System.out.printf("%-20s %-10s %-10s\n", "Name", "X Position", "Y Position");
        for (int i = 0; i < numStations; i++) {
            StationInfo station = stations[i];
            System.out.printf("%-20s %-10d %-10d\n", station.getName(), station.getxPos(), station.getyPos());
        }
    }

    public void printMatrix() {
        // Determine the maximum length of station names
        int maxNameLength = 0;
        for (int i = 0; i < numStations; i++) {
            maxNameLength = Math.max(maxNameLength, stations[i].getName().length());
        }

        // Print column labels
        System.out.print(String.format("%" + (maxNameLength + 2) + "s", ""));
        for (int i = 0; i < numStations; i++) {
            System.out.printf("%" + (maxNameLength + 2) + "s", stations[i].getName());
        }
        System.out.println();

        // Print rows
        for (int i = 0; i < numStations; i++) {
            // Print row label
            System.out.printf("%-" + (maxNameLength + 2) + "s", stations[i].getName());

            // Print row data
            for (int j = 0; j < numStations; j++) {
                if (distance[i][j] == NO_LINK) {
                    System.out.printf("%" + (maxNameLength + 2) + "s", "NO LINK");
                } else {
                    System.out.printf("%" + (maxNameLength + 2) + ".2f", distance[i][j]);
                }
            }
            System.out.println();
        }
    }

    public ListInt breadthFirstTraversal(String start) {
        StationInfo startStation = stations[findStationIndex(start)];
        QueueInt queue = new QueueInt(numStations);
        ListInt visited = new ListInt(numStations);

        queue.addToBack(findStationIndex(startStation.getName()));

        System.out.println("Breadth First Traversal");
        System.out.println("Started: " + startStation.getName());

        while (queue.getSize() != 0) {
            StationInfo station = stations[queue.removefromFront()];

            if (!visited.contains(findStationIndex(station.getName()))) {
                visited.append(findStationIndex(station.getName()));
                System.out.print("->" + station.getName());

                for (int i = 0; i < numStations; i++) {
                    if (distance[findStationIndex(station.getName())][i] != NO_LINK && !visited.contains(i)) {
                        queue.addToBack(i);
                    }
                }
            }
        }
        System.out.println();
        return visited;
    }

    public ListInt depthFirstTraversal(String start) {
        StationInfo startStation = stations[findStationIndex(start)];
        StackInt stack = new StackInt(numStations);
        ListInt visited = new ListInt(numStations);

        stack.push(findStationIndex(startStation.getName()));

        System.out.println("Depth First Traversal");
        System.out.println("Started: " + startStation.getName());

        while (stack.getSize() != 0) {
            StationInfo station = stations[stack.pop()];

            if (!visited.contains(findStationIndex(station.getName())))  {
                visited.append(findStationIndex(station.getName()));
                System.out.print("->" + station.getName());
                for (int i = 0; i < numStations; i++) {
                    if (distance[findStationIndex(station.getName())][i] != NO_LINK && !visited.contains(i)){
                        stack.push(i);
                    }
                }
            }
        }
        System.out.println();
        return visited;
    }

    public void dijkstraShortestPath(String start, String end) {
        // Find the index of the start and end stations
        int startIdx = findStationIndex(start);
        int endIdx = findStationIndex(end);

        // Initialize the distances array with NO_LINK (infinity)
        double[] distances = new double[numStations];
        for (int i = 0; i < numStations; i++) {
            distances[i] = NO_LINK;
        }
        // Set the distance from the start station to itself as 0
        distances[startIdx] = 0;

        // Initialize the previous array to keep track of the path
        int[] previous = new int[numStations];
        for (int i = 0; i < numStations; i++) {
            previous[i] = -1;
        }

        // Initialize the visited array to keep track of which stations have been visited
        boolean[] visited = new boolean[numStations];
        for (int i = 0; i < numStations; i++) {
            visited[i] = false;
        }

        // Initialize the open array to keep track of which stations are open for visiting
        boolean[] open = new boolean[numStations];
        for (int i = 0; i < numStations; i++) {
            open[i] = false;
        }

        // Mark the start station as open
        open[startIdx] = true;

        // Initialize the iterations counter
        int iterations = 0;

        // While the end station has not been visited
        while (!visited[endIdx]) {
            // Find the open station with the smallest distance
            double minDist = NO_LINK;
            int minIdx = -1;
            for (int i = 0; i < numStations; i++) {
                if (open[i] && distances[i] < minDist) {
                    minDist = distances[i];
                    minIdx = i;
                }
            }
            // If no such station exists, break the loop
            if (minIdx == -1) {
                break;
            }
            // Mark the station as visited and not open
            open[minIdx] = false;
            visited[minIdx] = true;

            // If the end station has been visited, break the loop
            if (minIdx == endIdx) {
                break;
            }
            // Otherwise, update the distances to the neighboring stations
            else {
                for (int i = 0; i < numStations; i++) {
                    if (distance[minIdx][i] != NO_LINK && !visited[i]) {
                        double newDist = distances[minIdx] + distance[minIdx][i];
                        if (newDist < distances[i]) {
                            distances[i] = newDist;
                            previous[i] = minIdx;
                            open[i] = true;
                        }
                    }
                }
            }
            // Increment the iterations counter
            iterations++;
        }

        // If no path was found, print a message
        if (distances[endIdx] == NO_LINK) {
            System.out.println("No path found from " + start + " to " + end);
        }
        // Otherwise, print the shortest path and its distance
        else {
            System.out.println("Dijkstra Shortest Path");
            System.out.println("Shortest path from " + start + " to " + end + ":");
            int idx = endIdx;
            StackInt stack = new StackInt(numStations);
            // Push the stations on the shortest path onto a stack
            while (idx != -1) {
                stack.push(idx);
                idx = previous[idx];
            }
            // Pop the stations from the stack and print them
            while (stack.getSize() != 0) {
                System.out.print(stations[stack.pop()].getName());
                if (stack.getSize() != 0) {
                    System.out.print("->");
                }
            }
            System.out.println();
            System.out.println("Distance: " + distances[endIdx]);
        }
        // Print the number of iterations
        System.out.println("Number of iterations: " + iterations);
    }
}


