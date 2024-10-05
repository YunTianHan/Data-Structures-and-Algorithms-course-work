package CHC5223;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


// The MemberBST class that implements the IMemberDB interface
public class MemberBST implements IMemberDB {
    // A list to store the sequence of visited nodes
    private final List<String> visitedNodes;

    // Inner class Node representing a node in the binary search tree
    private class Node {
        // The data stored in the node, which is a Member object
        private Member data;
        // The left and right child nodes of the current node
        private Node left, right;

        // Constructor to initialize the node data with a Member object
        public Node(Member data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // The root node of the binary search tree
    private Node root;
    // The size of the binary search tree, indicating the number of nodes
    private int size;

    // Constructor to initialize the binary search tree
    public MemberBST() {
        // Print a message to the console indicating the creation of a binary search tree
        System.out.println("Binary Search Tree");
        // Initialize the root node as null, indicating an empty tree
        root = null;
        // Initialize the size of the tree as 0
        size = 0;
        // Initialize the list of visited nodes as an empty ArrayList
        visitedNodes = new ArrayList<>();
    }


    // Method to check if a member with the specified name exists in the database
    @Override
    public boolean containsName(String name) {
        // Assert that the name is not null and not an empty string
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        // Return true if the member with the given name is found, false otherwise
        return get(name) != null;
    }

    // Method to insert a member into the database
    @Override
    public Member put(Member member) {
        // Assert that the member and the member's name are not null and not an empty string
        assert member != null && member.getName() != null && !member.getName().isEmpty() : "Member or member name cannot be null or empty";

        // If the root is null (i.e., the tree is empty)
        if (root == null) {
            // Create a new node with the member as the data and assign it to the root
            root = new Node(member);
            // Add a log entry indicating that the root was empty
            visitedNodes.add("(Empty root node)");
            // Increment the size of the tree
            size++;
            // Log the operation
            log("put", member.getName());
            // Clear the list of visited nodes
            visitedNodes.clear();
            // Return null as there was no previous member with the same name
            return null;
        }

        // Initialize the current node to the root and the parent node to null
        Node current = root;
        Node parent = null;
        // Initialize a variable to hold the result of the comparison between the member's name and the current node's data's name
        int cmp = 0;

        // While the current node is not null
        while (current != null) {
            // Set the parent node to the current node
            parent = current;
            // Compare the member's name with the current node's data's name
            cmp = member.getName().compareTo(current.data.getName());

            // If the member's name is less than the current node's data's name
            if (cmp < 0) {
                // Add a log entry indicating that we're going to the left child
                visitedNodes.add(current.data.getName() + "(L)");
                // Move to the left child
                current = current.left;
            }
            // If the member's name is greater than the current node's data's name
            else if (cmp > 0) {
                // Add a log entry indicating that we're going to the right child
                visitedNodes.add(current.data.getName() + "(R)");
                // Move to the right child
                current = current.right;
            }
            // If the member's name is equal to the current node's data's name
            else {
                // Add a log entry indicating that we've found the node
                visitedNodes.add(current.data.getName());
                // Store the current node's data in a temporary variable
                Member oldData = current.data;
                // Replace the current node's data with the new member
                current.data = member;
                // Log the operation
                log("put", member.getName());
                // Clear the list of visited nodes
                visitedNodes.clear();
                // Return the old data
                return oldData;
            }
        }

        // Create a new node with the member as the data
        Node newNode = new Node(member);
        // If the member's name is less than the parent node's data's name
        if (cmp < 0) {
            // Add a log entry indicating that we're adding a left child
            visitedNodes.add("(Empty left child)");
            // Add the new node as the left child of the parent node
            parent.left = newNode;
        }
        // If the member's name is greater than the parent node's data's name
        else {
            // Add a log entry indicating that we're adding a right child
            visitedNodes.add("(Empty right child)");
            // Add the new node as the right child of the parent node
            parent.right = newNode;
        }

        // Increment the size of the tree
        size++;
        // Log the operation
        log("put", member.getName());
        // Clear the list of visited nodes
        visitedNodes.clear();
        // Return null as there was no previous member with the same name
        return null;
    }

    // Method to remove a member from the database
    @Override
    public Member remove(String name){
        // Assert that the name is not null and not an empty string
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        // Define the parent node, the node to be deleted, and two auxiliary nodes
        Node parent = null, del, p = null, q = null;
        // Define the result member
        Member result;
        // Start searching from the root node
        del = root;
        // While the node to be deleted is not null and the name of the data in the node to be deleted is not equal to the input name, continue searching
        while (del != null && !del.data.getName().equals(name)) {
            // Update the parent node to the current node to be deleted
            parent = del;
            // Add the name of the data in the node to be deleted to the visitedNodes list
            visitedNodes.add(del.data.getName());
            // If the input name is less than the name of the data in the node to be deleted, search in the left subtree
            if (name.compareTo(del.data.getName()) < 0)
                del = del.left;
                // Otherwise, search in the right subtree
            else
                del = del.right;
        }

        // If the node to be deleted is found
        if(del != null) {
            // Add the name of the data in the node to be deleted to the visitedNodes list
            visitedNodes.add(del.data.getName());

            // If the right child of the node to be deleted is null, p points to the left child of the node to be deleted
            if (del.right == null) {
                p = del.left;
                // Add the name of the data in the p node to the visitedNodes list
                if (p != null) {
                    visitedNodes.add(p.data.getName());
                }
            }
            // If the left child of the right child of the node to be deleted is null, p points to the right child of the node to be deleted, and the left child of the node to be deleted is assigned to the left child of p
            else if (del.right.left == null) {
                p = del.right;
                // Add the name of the data in the p node to the visitedNodes list
                visitedNodes.add(p.data.getName());
                p.left = del.left;
            } else {
                // Otherwise, p points to the right child of the node to be deleted, and then searches to the leftmost node
                p = del.right;
                while (p.left != null) {
                    // Add the name of the data in the p node to the visitedNodes list
                    visitedNodes.add(p.data.getName());
                    q = p;
                    p = p.left;
                }
                // Add the name of the data in the p node to the visitedNodes list
                visitedNodes.add(p.data.getName());
                // The left child of q points to the right child of p, the left child of p points to the left child of the node to be deleted, and the right child of p points to the right child of the node to be deleted
                q.left = p.right; p.left = del.left; p.right = del.right;
            }
            // If the node to be deleted is the root node, the root node points to p
            if(del == root) root = p;
                // If the name of the data in the node to be deleted is less than the name of the data in the parent node, the left child of the parent node points to p
            else if (del.data.getName().compareTo(parent.data.getName()) < 0)
                parent.left = p;
                // Otherwise, the right child of the parent node points to p
            else parent.right = p;
            // The result member is the data of the node to be deleted
            result = del.data;
            // Decrement the size of the binary search tree
            size--;
        }
        // If the node to be deleted is not found, the result member is null
        else result = null;

        // Log the operation
        log("remove", name);
        // Clear the visitedNodes list
        visitedNodes.clear();
        // Return the result member
        return result;
    }

    // Method to get a member with the specified name
    @Override
    public Member get(String name) {
        // Assert that the name is not null and not an empty string
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        // Call the getNode method to find the node with the specified name starting from the root
        Node node = getNode(name, root);
        // Log the operation
        log("get", name);
        // Clear the list of visited nodes
        visitedNodes.clear();
        // If the node is null (i.e., not found), return null; otherwise, return the data of the node
        return node == null ? null : node.data;
    }

    // Private method to get a node with the specified name
    private Node getNode(String name, Node node) {
        // Assert that the name is not null and not an empty string
        assert name != null && !name.isEmpty() : "Name cannot be null or empty";
        // If the node is null (i.e., we've reached a leaf node and haven't found the specified name), return null
        if (node == null) {
            visitedNodes.add("(Empty node)");
            return null;
        }
        // Compare the specified name with the name of the data in the node
        int cmp = name.compareTo(node.data.getName());
        // If the specified name is less than the name of the data in the node, search in the left subtree
        if (cmp < 0) {
            visitedNodes.add(node.data.getName()+"(L)");
            return getNode(name, node.left);
        }
        // If the specified name is greater than the name of the data in the node, search in the right subtree
        else if (cmp > 0) {
            visitedNodes.add(node.data.getName()+"(R)");
            return getNode(name, node.right);
        }
        // If the specified name is equal to the name of the data in the node, return the node
        else {
            visitedNodes.add(node.data.getName());
            return node;
        }
    }

    // Method to get the size of the database
    @Override
    public int size() {
        // Return the size of the binary search tree
        return size;
    }

    // Method to check if the database is empty
    @Override
    public boolean isEmpty() {
        // Return true if the size of the binary search tree is 0, false otherwise
        return size == 0;
    }

    // Method to display all members in the database
    @Override
    public void displayDB() {
        // Call the private displayDB method with the root node as the argument
        displayDB(root);
    }

    // Private recursive method to display all nodes in the binary search tree
    private void displayDB(Node node) {
        // If the node is not null
        if (node != null) {
            // Recursively call the method with the left child of the current node
            displayDB(node.left);
            // Print the name and affiliation of the member in the current node
            System.out.println(node.data.getName() + " " + node.data.getAffiliation());
            // Recursively call the method with the right child of the current node
            displayDB(node.right);
        }
    }

    // Method to clear the database
    @Override
    public void clearDB() {
        // Set the root node to null
        root = null;
        // Set the size of the binary search tree to 0
        size = 0;
    }

    // Define a private method for logging operations
    private void log(String operation, String name) {
        // Specify the log file name
        String logFile = "LogInformation.txt";
        // Use try-with-resources to ensure the BufferedWriter is closed at the end
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();
            // Create a DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            // Format the current date and time as a string
            String nowString = now.format(formatter);

            // Construct the log message with the current date and time, operation, member name, and visited node sequence
            String logMessage = "Time: "+ nowString + " | Operation: " + operation + " | Member Name: " + name + " | Visited Node Sequence : ";
            // Write the log message to the log file
            writer.write(logMessage);
            // Call the private method to write the node sequence to the log file
            writeNodeSequence(writer, visitedNodes);
            // Write a new line to the log file
            writer.newLine();
            // Print the log message to the console
            System.out.println(logMessage + String.join("->", visitedNodes));
        } catch (IOException e) {
            // Print the stack trace for any IOException
            e.printStackTrace();
        }
    }

    // Define a private method for writing the node sequence to the log file
    private void writeNodeSequence(BufferedWriter writer,List<String> visitedNodes) throws IOException {
        // For each visited node
        for (String visitedNode : visitedNodes) {
            // Write the visited node to the log file, prefixed with "->"
            writer.write("->" + visitedNode);
        }
    }
}
//    // Private method to get the node with the smallest name in the binary search tree
//    private Node min(Node node) {
//        // If the left child of the node is null, return the node; otherwise, recursively call the method with the left child of the node
//        if (node.left == null) return node;
//        else return min(node.left);
//    }
//
//    // Private method to delete the node with the smallest name in the binary search tree
//    private Node deleteMin(Node node) {
//        // If the left child of the node is null, return the right child of the node
//        if (node.left == null) return node.right;
//        // Otherwise, set the left child of the node to the result of the recursive call of the method with the left child of the node, then return the node
//        node.left = deleteMin(node.left);
//        return node;
//    }

    //    /**
//     * Inserts a Member object into the database, with the key of the supplied
//     * member's name.
//     * Note: If the name already exists as a key, then then the original entry
//     * is overwritten.
//     * This method must return the previous associated value
//     * if one exists, otherwise null
//     *
//     * @pre member not null and member name not empty string
//     */
//    @Override
//    public Member put(Member member) {
//        assert member != null && member.getName() != null && !member.getName().isEmpty() : "Member or member name cannot be null or empty";
//        root = put(root, member);
//        size++;
//        log("put", member.getName());
//        visitedNodes.clear();
//        return member;
//    }
//
//    // 递归方法，向二叉搜索树中添加一个节点
//    private Node put(Node node, Member member) {
//        // 断言：成员不��空，成员名称不为空且不为空字符串
//        assert member != null && member.getName() != null && !member.getName().isEmpty() : "Member or member name cannot be null or empty";
//        // 如果节点为空，创建一个新的节点并返回
//        if (node == null) {
//            node = new Node(member);;
//        }
//        // 比较成员名称和节点数据的名称
//        int cmp = member.getName().compareTo(node.data.getName());
//        // 将节点数据的名称添加到访问过的节点列表
//        visitedNodes.add(node.data.getName());
//        // 如果成员名称小于节点数据的名称，将成员添加到左子节点
//        if (cmp < 0) {
//            node.left = put(node.left, member);
//        }
//        // 如果成员名称大于节点数据的���称，将成员添加到右子节点
//        else if (cmp > 0) {
//            node.right = put(node.right, member);
//        }
//        // 如果成员名称等于节点数据的名称，更新节点数据
//        else {
//            node.data = member;
//        }
//        // 返回节点
//        return node;
//    }

//    // 向数据库中添加一个成员
//    @Override
//    public Member put (Member member) {
//        assert member != null && member.getName() != null && !member.getName().isEmpty() : "Member or member name cannot be null or empty";
//        Member result = null;
//        if (addNode(member) == 0) {
//            result = member;
//        }
//        log("put", member.getName());
//        visitedNodes.clear();
//        return result;
//    } // BinarySearchTree.insert
//
//    public int addNode(Member member) { // does not use recursive way
//        assert member != null && member.getName() != null && !member.getName().isEmpty() : "Member or member name cannot be null or empty";
//        Node current = root;
//        Node parent = null;
//        int cmp = 0;
//        while (current != null && current.data != member) {
//            parent = current;
//            cmp = member.getName().compareTo(current.data.getName());
//            visitedNodes.add(current.data.getName());
//            if (cmp < 0) {
//                current = current.left;
//            } else {
//                current = current.right;
//            }
//        }
//        if (current == null) {
//            Node newNode = new Node(member);
//            if (current == root) {
//                root = newNode;
//            } else if (cmp < 0) {
//                parent.left = newNode;
//            } else {
//                parent.right = newNode;
//            }
//            size++;
//            return -1;
//        }
//        else {
//            current.data = member;
//            return 0;
//        }
//    }
