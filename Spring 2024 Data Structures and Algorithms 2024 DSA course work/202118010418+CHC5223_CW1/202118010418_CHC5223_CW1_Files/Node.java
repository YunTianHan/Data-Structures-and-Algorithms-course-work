// Node class represents the node of the list
public class Node {
    String data; // data of the node
    Node next; // reference to next node
    Node prev; // reference to previous node

    // Node constructor
    public Node(String key) {
        this.data = key; // initialize data
        this.next = null; // initialize next as null
        this.prev = null; // initialize prev as null
    }
}