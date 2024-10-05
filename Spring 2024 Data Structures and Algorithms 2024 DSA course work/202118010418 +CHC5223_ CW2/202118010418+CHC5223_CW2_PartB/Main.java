import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Network network = new Network(8);
        network.readNetworkFromFile("network.txt");
        System.out.println("-----------------");
        System.out.println("List：");
        network.printStations();
        System.out.println("-----------------");
        System.out.println("Matrix：");
        network.printMatrix();
        System.out.println("-----------------");
        network.breadthFirstTraversal("DeMasia");
        System.out.println("-----------------");
        network.depthFirstTraversal("DeMasia");
        System.out.println("-----------------");
        network.dijkstraShortestPath("DeMasia", "Freeldrode");
    }
}