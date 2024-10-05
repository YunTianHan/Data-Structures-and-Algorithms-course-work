public class Main {
    public static void main(String[] args) throws Exception {
        // Create an instance of LinearProbingHashTable and ChainingHashTable
        LinearProbingHashTable lpht = new LinearProbingHashTable(5);
        ChainingHashTable cht = new ChainingHashTable(5,9);// Test the limit of the hash table

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
        lpht.insert("Apple");
        lpht.insert("Banana");
        lpht.insert("Cherry");
        lpht.insert("Date");
        lpht.insert("Elderberry");
        lpht.access();

        cht.insert("Red");
        cht.insert("Orange");
        cht.insert("Yellow");
        cht.insert("Green");
        cht.insert("Blue");
        cht.accessAll();
        System.out.println("---------------------------------");

        // Search for a key-value pair
        System.out.println("---------------------------------");
        System.out.println("Search method ");
        System.out.println("Search for Apple in lpht: " + lpht.search("Apple"));
        System.out.println("Search for Red in cht: " + cht.search("Red"));
        System.out.println("---------------------------------");

        // Delete a key-value pair
        System.out.println("---------------------------------");
        System.out.println("Delete method ");
        lpht.delete("Apple");
        lpht.access();
        cht.delete("Red");
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

        //Verify that the implementations of the Linear Probing way and Separate Chaining way for collision solutions
        // are working effectively.
        System.out.println("Verify that the implementations of the Linear Probing way and Separate Chaining way for " +
                "collision solutions are working effectively");
        System.out.println("Insert key-value pairs to cause collisions");
        System.out.println("---------------------------------");
        lpht.insert("China");
        lpht.insert("Japan");
        lpht.insert("Korea");
        lpht.insert("Vietnam");
        lpht.insert("Thailand");
        lpht.access();

        cht.insert("Food");
        cht.insert("God");
        cht.insert("Dessert");
        cht.insert("Fruit");
        cht.insert("Vegetable");
        cht.accessAll();
        System.out.println("---------------------------------");

        //The inner structure of the generated hash tables should be clearly illustrated as the executed result of the
        // program
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