public class LinearProbingHashTable {
    private int capacity; // size of the hash table
    private int size = 0; // number of key-value pairs in the hash table
    private String[] keys; // key array
    private int[] values; // hashValue array

    // Constructor to initialize the hash table,initial capacity of the array should not exceed 20.
    public LinearProbingHashTable(int capacity) {
        if (capacity > 20) {
            this.capacity = 20;
            System.out.println("Initial capacity of the array should not exceed 20, the capacity has been set to 20");
        }
        if (capacity < 1) {
            this.capacity = 0;
            System.out.println("Initial capacity of the array should not be less than 1, the capacity has been set to 0");
        }
        this.capacity = capacity;
        keys = new String[capacity];
        values = new int[capacity];
    }

    // Hash function, converts string key into an array index
    public int hash(String key) {
        long hashValue = 0;
        int p = 31; // a prime number as base
        int m = capacity; // size of the hash table

        // Calculate the hash value of the key
        for (int i = 0; i < key.length(); i++) {
            hashValue = (p * hashValue + (long) key.charAt(i) * (i+1)) % m;
        }

        // Return the hash value as an index
        return (int) (hashValue % m);
    }

    // Method to insert a key-value pair
    public void insert(String key) {
        // Check if key or value is null
        if (key == null) {
            throw new IllegalArgumentException("Key or value is null");
        }
        // Check if key format is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Resize the hash table if necessary
        if (size >= capacity / 2) resize(2 * capacity);
        int i;
        // Find the key and update its value
        for (i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            // Update the value if the key is found
            if (keys[i].equals(key)) {
                values[i]++;
                return;
            }
        }
        // Insert the key-value pair
        keys[i] = key;
        values[i] = hash(key);
        size++;
        // Print the load factor after insertion
        System.out.println("Load factor after insertion: " + loadFactor());
    }

    // Method to get the value associated with a key
    public int search(String key) {
        // Check if key is null
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // Check if key format is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Find the key and return its associated value
        for (int i = hash(key); keys[i] != null; i = (i + 1) % capacity) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        // Return null if key is not found
        return -1;
    }

    // Method to delete a key-value pair by key
    public void delete(String key) {
        // Check if key is null
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // Check if key format is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Do nothing if the key is not found
        if (!containsKey(key)) return;
        int i = hash(key);
        // Find the key and delete it
        while (!key.equals(keys[i])) {
            i = (i + 1) % capacity;
        }
        keys[i] = null;
        values[i] = 0;
        i = (i + 1) % capacity;
        // Redo all keys in the cluster
        while (keys[i] != null) {
            String keyToRedo = keys[i];
            int valueToRedo = values[i];
            keys[i] = null;
            values[i] = 0;
            size--;
            insert(keyToRedo);
            values[hash(keyToRedo)] = valueToRedo;
            i = (i + 1) % capacity;
        }
        size--;
        // Resize the hash table if necessary
        if (size > 0 && size <= capacity / 8) resize(capacity / 2);
        // Print the load factor after deletion
        System.out.println("Load factor after deletion: " + loadFactor());
    }

    // Method to access all key-value pairs in the hash table
    public void access() {
        // Check if the hash table is empty
        if (size == 0) {
            System.out.println("Hash table is empty");
            return;
        }
        // Print all key-value pairs in the hash table
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                System.out.print("LinearProbingHashTable of index " + i +  " : ");
                System.out.print("(" + keys[i] + ", " + values[i] + ")");;
                System.out.println();
            }
        }
    }

    // Method to access all key in the hash table
    public void accessKey() {
        // Check if the hash table is empty
        if (size == 0) {
            System.out.println("Hash table is empty");
            return;
        }
        // Print all key in the hash table
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                System.out.println(keys[i]);
            }
        }
    }

    // Method to calculate and return the load factor of the hash table
    public double loadFactor() {
        return (double) size / capacity;
    }

    // Method to set the size of the hash table
    public void setCapacity(int M) {
        this.capacity = M;
    }

    // Method to get the size of the hash table
    public int getCapacity() {
        return capacity;
    }

    // Method to resize the hash table
    private void resize(int capacity) {
        System.out.println("Because the load factor is too high, the hash table has been resized to " + capacity);
        // Create a new hash table with the given capacity
        String[] tempKeys = new String[capacity];
        int[] tempValues = new int[capacity];
        for (int i = 0; i < this.capacity; i++) {
            if (keys[i] != null) {
                String key = keys[i];
                int value = values[i];
                int j;
                for (j = hash(key); tempKeys[j] != null; j = (j + 1) % capacity) {
                    if (tempKeys[j].equals(key)) {
                        tempValues[j]++;
                        break;
                    }
                }
                tempKeys[j] = key;
                tempValues[j] = value;
            }
        }
        // Switch to the new hash table
        keys = tempKeys;
        values = tempValues;
        this.capacity = capacity;
        System.out.println("Reload completed!");
    }

    // Method to check if the key format is valid
    private boolean isValidKey(String key) {
        String regex = "^[a-zA-Z0-9]*$";
        return key.matches(regex);
    }

    // Method to clear the hash table
    public void clear() {
        keys = new String[capacity];
        values = new int[capacity];
        size = 0;
    }

    // Method to check if the hash table contains a specific key
    public boolean containsKey(String key) {
        // Check if key is null
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // Check if key format is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Return true if key is found
        return search(key) != -1;
    }

    // Method to check if the hash table contains a specific value
    public boolean containsValue(int value) {
        // Check if value is negative
        if (value < 0) {
            throw new IllegalArgumentException("Value is negative");
        }
        // Return true if value is found
        for (int i = 0; i < capacity; i++) {
            if (values[i] == value) {
                return true;
            }
        }
        return false;
    }
}