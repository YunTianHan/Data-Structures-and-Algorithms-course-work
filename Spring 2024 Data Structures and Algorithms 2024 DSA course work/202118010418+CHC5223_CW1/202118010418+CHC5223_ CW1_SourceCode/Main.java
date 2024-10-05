public class Main {
    public static void main(String[] args) throws Exception {
        // Create an instance of LinearProbingHashTable and ChainingHashTable
        LinearProbingHashTable lpht = new LinearProbingHashTable();
        ChainingHashTable cht = new ChainingHashTable();

        // Set the capacity of the hash table to a small value so that collisions are easy to occur.
        lpht.setM(5); // Set the capacity to a small value
        cht.setM(5); // Set the capacity to a small value

        // Verify that each of the hash functions is working well.
        System.out.println("---------------------------------");
        System.out.println("Verify that each of the hash functions is working well");
        System.out.println("(LinearProbingHashTable)Hash value of abc4321: " + lpht.hash("abc4321"));
        System.out.println("(LinearProbingHashTable)Hash value of cba4321: " + lpht.hash("cba4321"));

        System.out.println("(ChainingHashTable)Hash value of abc43215: " + cht.hash("abc43215"));
        System.out.println("(ChainingHashTable)Hash value of cba43215: " + cht.hash("cba43215"));
        System.out.println("---------------------------------");

        // Verify that each of the implemented methods is working correctly
        System.out.println("Verify that each of the implemented methods is working correctly");

        // Insert key-value pairs
        System.out.println("---------------------------------");
        System.out.println("Insert method ");
        lpht.insert("key1", "value1");
        lpht.insert("key2", "value2");
        lpht.insert("key3", "value3");
        lpht.insert("key4", "value4");
        lpht.insert("key5", "value5");
        lpht.access();

        cht.insert("key1", "value1");
        cht.insert("key2", "value2");
        cht.insert("key3", "value3");
        cht.insert("key4", "value4");
        cht.insert("key5", "value5");
        cht.accessAll();
        System.out.println("---------------------------------");

        // Search for a key-value pair
        System.out.println("---------------------------------");
        System.out.println("Search method ");
        System.out.println("Search for key1 in LinearProbingHashTable: " + lpht.search("key1"));
        System.out.println("Search for key1 in ChainingHashTable: " + cht.search("key1"));
        System.out.println("---------------------------------");

        // Delete a key-value pair
        System.out.println("---------------------------------");
        System.out.println("Delete method ");
        lpht.delete("key1");
        lpht.access();
        cht.delete("key1");
        cht.accessAll();
        System.out.println("---------------------------------");

        // Access the key-value pairs
        System.out.println("---------------------------------");
        System.out.println("Access method ");
        lpht.access();
        cht.accessAll();
        System.out.println("---------------------------------");

        // Check the load factor
        System.out.println("---------------------------------");
        System.out.println("Load_factor method:");
        System.out.println("Load factor of LinearProbingHashTable: " + lpht.loadFactor());
        System.out.println("Load factor of ChainingHashTable: " + cht.loadFactor());
        System.out.println("---------------------------------");

        //Verify that the implementations of the Linear Probing way and Separate Chaining way for collision solutions are working effectively.
        System.out.println("Verify that the implementations of the Linear Probing way and Separate Chaining way for " +
                "collision solutions are working effectively");
        System.out.println("Insert key-value pairs to cause collisions");
        System.out.println("---------------------------------");
        lpht.insert("key6", "value6");
        lpht.insert("key7", "value7");
        lpht.insert("key8", "value8");
        lpht.insert("key9", "value9");
        lpht.insert("key10", "value10");
        lpht.access();

        cht.insert("key6", "value6");
        cht.insert("key7", "value7");
        cht.insert("key8", "value8");
        cht.insert("key9", "value9");
        cht.insert("key10", "value10");
        cht.accessAll();
        System.out.println("---------------------------------");

        //The inner structure of the generated hash tables should be clearly illustrated as the executed result of the program
        System.out.println("The inner structure of the generated hash tables should be clearly illustrated as the " +
                "executed result of the program");
        System.out.println("Access key-value pairs in the hash tables");
        System.out.println("---------------------------------");
        lpht.access();
        cht.accessAll();
        System.out.println("---------------------------------");

        // Clear the hash table
        lpht.clear();
        cht.clear();
    }
}