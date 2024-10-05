// LinkedList class represents the doubly linked list
public class LinkedList {
    Node head; // head of the list
    int size; // size of the list

    // LinkedList constructor
    public LinkedList() {
        this.head = null; // initialize head as null
        this.size = 0; // initialize size as 0
    }

    // Method to insert a new node at the front of the list
    public void insert(String key, String value) {
        Node newNode = new Node(key, value); // create a new node
        newNode.next = head; // point new node's next to head
        if (head != null) {
            head.prev = newNode; // if head is not null, point head's prev to new node
        }
        head = newNode; // make new node as head
        size++; // increment size
    }

    // Method to delete a node with the given index
    public void delete(int index) throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty"); // throw exception if list is empty
        }

        if (index < 0 || index >= size) {
            throw new Exception("Index out of bounds"); // throw exception if index is out of bounds
        }

        Node temp = head; // start from head
        for (int i = 0; i < index; i++) {
            temp = temp.next; // move to next node
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next; // if prev is not null, point its next to temp's next
        } else {
            head = temp.next; // if prev is null, make temp's next as head
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev; // if next is not null, point its prev to temp's prev
        }
        size--; // decrement size
    }


    // Method to delete a node at a particular point
    public void delete(Node node) throws Exception{
        if (isEmpty()) {
            throw new Exception("List is empty"); // throw exception if list is empty
        }

        if (node.prev != null) {
            node.prev.next = node.next; // if prev is not null, point its next to node's next
        } else {
            head = node.next; // if prev is null, make node's next as head
        }

        if (node.next != null) {
            node.next.prev = node.prev; // if next is not null, point its prev to node's prev
        }
        size--; // decrement size
    }

    // Method to delete a node with the given key
    public void delete(String key) throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty"); // throw exception if list is empty
        }

        Node temp = head; // start from head
        while (temp != null) {
            if (temp.key.equals(key)) { // if key is found
                if (temp.prev != null) {
                    temp.prev.next = temp.next; // if prev is not null, point its next to temp's next
                } else {
                    head = temp.next; // if prev is null, make temp's next as head
                }

                if (temp.next != null) {
                    temp.next.prev = temp.prev; // if next is not null, point its prev to temp's prev
                }
                size--; // decrement size
                return; // return after deletion
            }
            temp = temp.next; // move to next node
        }

        throw new Exception("Element not found"); // throw exception if element is not found
    }

    // Method to return node at the given index
    public Node access(int index) throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty"); // throw exception if list is empty
        }

        if (index < 0 || index >= size) {
            throw new Exception("Index out of bounds"); // throw exception if index is out of bounds
        }

        Node temp = head; // start from head
        for (int i = 0; i < index; i++) {
            temp = temp.next; // move to next node
        }

        return temp; // return the data of the node
    }

    // Method to access a node with the given key
    public Node access(String key) throws Exception {
        if (isEmpty()) {
            throw new Exception("List is empty"); // throw exception if list is empty
        }
        Node temp = head; // start from head
        while (temp != null) {
            if (temp.key.equals(key)) { // if key is found
                return temp; // return the node
            }
            temp = temp.next; // move to next node
        }
        return null; // return null if key is not found
    }


    // Method to print the list
    public void print() {
        Node temp = head; // start from head
        while (temp != null) {
            System.out.print("(" + temp.key + ", " + temp.value + ") "); // print the data
            temp = temp.next; // move to next node
        }
        System.out.println(); // print new line
    }

    // Method to check if the list is empty
    public boolean isEmpty() {
        return size == 0; // return true if head is null
    }

    // Method to return the size of the list
    public int size() {
        return size; // return the size
    }

    // Method to clear the list
    public void clear() {
        head = null; // make head as null
        size = 0; // make size as 0
    }
}