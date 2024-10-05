// Node class represents the node of the list
public class Node {
    String key; // key of the node
    String value; // value of the node
    Node next; // reference to next node
    Node prev; // reference to previous node

    // Node constructor
    public Node(String key, String value) {
        this.key = key; // initialize key
        this.value = value; // initialize value
        this.next = null; // initialize next as null
        this.prev = null; // initialize prev as null
    }
}