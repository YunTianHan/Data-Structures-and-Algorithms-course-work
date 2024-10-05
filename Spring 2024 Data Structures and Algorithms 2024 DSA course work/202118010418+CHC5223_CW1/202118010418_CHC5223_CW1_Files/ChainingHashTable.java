public class ChainingHashTable {
    // The array of linked lists (chains) that make up the hash table
    private LinkedList[] table;
    // The number of key-value pairs in the hash table
    private int size;
    // The maximum capacity of each chain
    private static final int chain_Capacity = 8;
    // The load factor threshold for resizing the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    // Constructor for the ChainingHashTable class
    public ChainingHashTable(int table_Capacity, int chain_Capacity) {
        // Ensure the chain capacity is within the valid range
        if (chain_Capacity > 8) {
            chain_Capacity = 8;
            System.out.println("The capacity of the linked list of separate chaining should not exceed 8,it has been "
                    + "set to 8");
        }
        if (chain_Capacity < 1) {
            chain_Capacity = 1;
            System.out.println("The capacity of the linked list of separate chaining should not be less than 1,it has "
                    + "been set to 8");
        }
        if (table_Capacity < 1) {
            table_Capacity = 1;
            System.out.println("The capacity of the hash table should not be less than 1,it has been set to 1");
        }
        // Initialize the hash table array and each chain
        table = new LinkedList[table_Capacity];
        for (int i = 0; i < table_Capacity; i++) {
            table[i] = new LinkedList();
        }
        // Initialize the size of the hash table
        size = 0;
    }

    // Method to calculate the hash value of a key
    public int hash(String key) {
        int pieceSize = 4; // We're going to fold it in chunks of 4 characters
        int hash = 0;

        // Calculate the hash value by folding the key
        for (int i = 0; i < key.length(); i += pieceSize) {
            int piece = 0;
            for (int j = 0; j < pieceSize && i + j < key.length(); j++) {
                piece += key.charAt(i + j) * (i + j + 1); // Add weight to the character based on its position
            }
            hash += piece;
        }
        // Modulo the hash value by the length of the hash table to get the index
        return hash % table.length;
    }

    // Method to insert a key-value pair into the hash table
    public void insert(String key) {
        // Check for null key or value
        if (key == null ) {
            throw new IllegalArgumentException("Key or value is null");
        }
        // Check for valid key format
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Calculate the hash value (index) of the key
        int index = hash(key);
        // Insert the key-value pair into the appropriate chain
        table[index].insert(key);
        // Increase the size of the hash table
        size++;
        // Resize the hash table if the load factor threshold is exceeded
        if ((double) size / (table.length*chain_Capacity)> LOAD_FACTOR_THRESHOLD) {
            resize(2 * table.length);
        }
        // Print the load factor after insertion
        System.out.println("Load factor after insertion: " + loadFactor());
    }

    // Method to get the value associated with a key
    public int search(String key) throws Exception {
        // Check for null key
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // Check for valid key format
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Calculate the hash value (index) of the key
        int index = hash(key);
        // Search for the key in the appropriate chain
        Node node = table[index].access(key);
        // Throw an exception if the key is not found
        if (node == null) {
            throw new Exception("Key not found");
        }
        // Return the value associated with the key
        return hash(node.data);
    }

    // Method to delete a key-value pair by key
    public void delete(String key) throws Exception{
        // Check for null key
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // Check for valid key format
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Calculate the hash value (index) of the key
        int index = hash(key);
        // Delete the key-value pair from the appropriate chain
        table[index].delete(key);
        // Decrease the size of the hash table
        size--;
        // Print the load factor after deletion
        System.out.println("Load factor after deletion: " + loadFactor());
    }

    // Method to access all key-value pairs in the hash table
    public void accessAll() {
        // Print all key-value pairs in each chain
        for (int i = 0; i < table.length; i++) {
            System.out.print("ChainingHashTable: the " +i+ " chain: ");
            table[i].print();
        }
    }

    // Method to access all keys in the hash table
    public void accessKey() {
        // Print all keys in each chain
        for (LinkedList linkedList : table) {
            Node current = linkedList.head;
            while (current != null) {
                System.out.println("Key: " + current.data);
                current = current.next;
            }
        }
    }

    // Method to access all values in the hash table
    public void accessValue() {
        // Print all values in each chain
        for (LinkedList linkedList : table) {
            Node current = linkedList.head;
            while (current != null) {
                System.out.println("Value: " + hash(current.data));
                current = current.next;
            }
        }
    }

    // Method to calculate and return the load factor of the hash table
    public double loadFactor() {
        return (double) size / table.length;
    }

    // Method to resize the hash table
    private void resize(int capacity) {
        System.out.println("Hash table has been resized to " + capacity);
        // Create a new hash table with the given capacity
        LinkedList[] temp = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            temp[i] = new LinkedList();
        }
        // Rehash all key-value pairs from the old hash table into the new hash table
        for (LinkedList linkedList : table) {
            Node current = linkedList.head;
            while (current != null) {
                int index = hash(current.data);
                temp[index].insert(current.data);
                current = current.next;
            }
        }
        // Switch to the new hash table
        table = temp;
    }

    // Method to set the capacity of the hash table
    public void setM(int m) {
        // Create a new hash table with the given capacity
        LinkedList[] temp = new LinkedList[m];
        for (int i = 0; i < m; i++) {
            temp[i] = new LinkedList();
        }
        // Rehash all key-value pairs from the old hash table into the new hash table
        for (LinkedList linkedList : table) {
            Node current = linkedList.head;
            while (current != null) {
                int index = hash(current.data);
                temp[index].insert(current.data);
                current = current.next;
            }
        }
        // Switch to the new hash table
        table = temp;
    }

    // Method to get the capacity of the hash table
     public void getM() {
        System.out.println("The capacity of the linked list of separate chaining is " + table.length);
    }

    // Method to check if the key format is valid
    private boolean isValidKey(String key) {
        String regex = "^[a-zA-Z0-9]*$";
        return key.matches(regex);
    }

    // Method to clear the hash table
    public void clear() {
        // Clear each chain in the hash table
        for (LinkedList linkedList : table) {
            linkedList.clear();
        }
        // Reset the size of the hash table
        size = 0;
    }

    // Method to return the size of the hash table
    public int size() {
        return size;
    }
}