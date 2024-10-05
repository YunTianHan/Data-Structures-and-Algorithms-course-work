public class LinearProbingHashTable {
    private int M = 20; // size of the hash table
    private int N = 0; // number of key-value pairs in the hash table
    private String[] keys; // key array
    private String[] values; // value array

    // Constructor to initialize the hash table,initial capacity of the array should not exceed 20.
    public LinearProbingHashTable() {
        if (M > 20) {
            M = 20;
            System.out.println("Initial capacity of the array should not exceed 20,it has been set to 20.");
        }
        if (M < 1) {
            M = 1;
            System.out.println("Initial capacity of the array should not be less than 1,it has been set to 1.");
        }
        keys = new String[M];
        values = new String[M];
    }

    // Hash function, converts string key into an array index
    public int hash(String key) {
        long hashValue = 0;
        int p = 31; // a prime number as base
        int m = M; // size of the hash table

        // Calculate the hash value of the key
        for (int i = 0; i < key.length(); i++) {
            hashValue = (p * hashValue + (long) key.charAt(i) * (i+1)) % m;
        }

        // Return the hash value as an index
        return (int) (hashValue % m);
    }

    // Method to insert a key-value pair
    public void insert(String key, String val) {
        // Check if key or value is null
        if (key == null || val == null) {
            throw new IllegalArgumentException("Key or value is null");
        }
        // Check if key format is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Resize the hash table if necessary
        if (N >= M / 2) resize(2 * M);
        int i;
        // Find the key and update its value
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            // Update the value if the key is found
            if (keys[i].equals(key)) {
                values[i] = val;
                return;
            }
        }
        // Insert the key-value pair
        keys[i] = key;
        values[i] = val;
        N++;
        // Print the load factor after insertion
        System.out.println("Load factor after insertion: " + loadFactor());
    }

    // Method to get the value associated with a key
    public String search(String key) {
        // Check if key is null
        if (key == null) {
            throw new IllegalArgumentException("Key is null");
        }
        // Check if key format is valid
        if (!isValidKey(key)) {
            throw new IllegalArgumentException("Key format is invalid");
        }
        // Find the key and return its associated value
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        // Return null if key is not found
        return null;
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
            i = (i + 1) % M;
        }
        keys[i] = null;
        values[i] = null;
        i = (i + 1) % M;
        // Redo all keys in the cluster
        while (keys[i] != null) {
            String keyToRedo = keys[i];
            String valToRedo = values[i];
            keys[i] = null;
            values[i] = null;
            N--;
            insert(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        // Resize the hash table if necessary
        if (N > 0 && N <= M / 8) resize(M / 2);
        // Print the load factor after deletion
        System.out.println("Load factor after deletion: " + loadFactor());
    }

    // Method to access all key-value pairs in the hash table
    public void access() {
        // Check if the hash table is empty
        if (N == 0) {
            System.out.println("Hash table is empty");
            return;
        }
        // Print all key-value pairs in the hash table
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                System.out.print("LinearProbingHashTable of index " + i +  " : ");
                System.out.print("(" + keys[i] + ", " + values[i] + ")");
                System.out.print(" Hash value: " + hash(keys[i]));
                System.out.println();
            }
        }
    }

    // Method to access all key in the hash table
    public void accessKey() {
        // Check if the hash table is empty
        if (N == 0) {
            System.out.println("Hash table is empty");
            return;
        }
        // Print all key in the hash table
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                System.out.println(keys[i]);
            }
        }
    }

    // Method to access all value in the hash table
    public void accessValue() {
        // Check if the hash table is empty
        if (N == 0) {
            System.out.println("Hash table is empty");
            return;
        }
        // Print all value in the hash table
        for (int i = 0; i < M; i++) {
            if (values[i] != null) {
                System.out.println(values[i]);
            }
        }
    }

    // Method to calculate and return the load factor of the hash table
    public double loadFactor() {
        return (double) N / M;
    }

    // Method to set the size of the hash table
    public void setM(int M) {
        this.M = M;
    }

    // Method to get the size of the hash table
    public int getM() {
        return M;
    }

    // Method to resize the hash table
    private void resize(int capacity) {
        System.out.println("Because the load factor is too high, the hash table has been resized to " + capacity);
        LinearProbingHashTable t = new LinearProbingHashTable();
        t.M = capacity;
        t.keys = new String[t.M];
        t.values = new String[t.M];
        // Rehash all keys in the old array
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.insert(keys[i], values[i]);
            }
        }
        // Switch to the new arrays
        keys = t.keys;
        values = t.values;
        M = t.M;
        System.out.println("Reload completed!");
    }

    // Method to check if the key format is valid
    private boolean isValidKey(String key) {
        String regex = "^[a-zA-Z0-9]*$";
        return key.matches(regex);
    }

    // Method to clear the hash table
    public void clear() {
        keys = new String[M];
        values = new String[M];
        N = 0;
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
        return search(key) != null;
    }

    // Method to check if the hash table contains a specific value
    public boolean containsValue(String value) {
        // Check if value is null
        if (value == null) {
            throw new IllegalArgumentException("Value is null");
        }
        // Check all values in the hash table
        for (int i = 0; i < M; i++) {
            if (values[i] != null && values[i].equals(value)) {
                return true;
            }
        }
        // Return false if value is not found
        return false;
    }
}