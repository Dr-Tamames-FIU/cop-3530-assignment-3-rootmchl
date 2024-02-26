package BST;

import Queue.MyQueue;

public class BinarySearchTree implements BSTInterface {

    BSTNode root = null;

    public static void main(String[] args)
    {
        BinarySearchTree tree = new BinarySearchTree();

        // Optional code for extra points
        boolean Optional = false;

        // Testing for inserting words into the tree
        // Prints inorder traversal of tree
        tree.put("dog");
        tree.put("cat");
        tree.put("alligator");
        tree.put("giraffe");
        tree.put("elephant");
        tree.put("hippo");
        tree.put("zebra");
        tree.printTreeStructure();

        System.out.println("Contains dog: " + tree.contains("dog"));
        System.out.println("Contains alligator: " + tree.contains("alligator"));
        System.out.println("Contains horse: " + tree.contains("horse"));

        System.out.println();
        tree.delete("dog");
        tree.printTreeStructure();

        System.out.println();
        tree.delete("elephant");
        tree.printTreeStructure();

        System.out.println();
        tree.delete("cat");
        tree.printTreeStructure();

        tree.put("penguin");
        tree.put("aardvark");
        tree.put("bear");
        tree.printTreeStructure();

        // Optional
        if (Optional) {

            MyQueue inOrder = tree.inOrder();
            MyQueue preOrder = tree.preOrder();
            MyQueue postOrder = tree.postOrder();

            System.out.println("In order: " + inOrder);
            System.out.println("Preorder: " + preOrder);
            System.out.println("Postorder: " + postOrder);
        }

        // Testing if clearing the tree works
        System.out.println();
        tree.makeEmpty();
        System.out.println(tree.isEmpty());
    }

    public boolean isEmpty(){
        return root == null;
    } // returns true if the BST is empty, false otherwise

    public void makeEmpty(){
        root = null;
    } // Empties the BST

    protected String getSmallest(BSTNode node) {
     if (node.left == null) {
        return node.item;
     } else {
        return getSmallest(node.left);
     }
    }

    public void inorderTraversal(BSTNode node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.item + " ");
            inorderTraversal(node.right);
        }
    }

    public boolean contains(String s){
        return recursiveSearch(root, s);
    } // Returns true if the BST contains the String, false otherwise

    public void put(String s){
        root = recursiveInsert(root, s);
    } // Adds a String to the BST. If the String is already in the BST, does nothing.

    public void delete(String s){
        root = recursiveRemove(root, s);
    } // Removes a String from the BST. If the String isn't in the BST, does nothing.

    public MyQueue inOrder(){
        MyQueue queue = new MyQueue();
        inOrderRecursive(root, queue);
        return queue;
    } // OPTIONAL, Returns a queue with the elements in order

    public MyQueue preOrder(){
        MyQueue queue = new MyQueue();
        preOrderRecursive(root, queue);
        return queue;
    } // OPTIONAL, Returns a queue with the elements pre order

    public MyQueue postOrder(){
        MyQueue queue = new MyQueue();
        postOrderRecursive(root, queue);
        return queue;
    } // OPTIONAL, Returns a queue with the elements post order

    // TODO: Fill this in and call it from contains()
    protected boolean recursiveSearch(BSTNode node, String s) {
        if (node == null) {
            return false;
          } else if (node.item.equals(s)) {
            return true;
          } else if (s.compareTo(node.item) < 0) {
            return recursiveSearch(node.left, s);
          } else {
            return recursiveSearch(node.right, s);
          }
    }

    // TODO: Fill this in and call it from put()
    protected BSTNode recursiveInsert(BSTNode node, String s) {
        if (node == null) {
            return new BSTNode(s);
        }

        if (s.compareTo(node.item) < 0) {
            node.left = recursiveInsert(node.left, s);
        } else if (s.compareTo(node.item) > 0) {
            node.right = recursiveInsert(node.right, s);
        }

        return node;
    }

    // TODO: Fill this in and call it from delete()
    protected BSTNode recursiveRemove(BSTNode node, String s) {
        if (node == null) {
            return null;
          } else if (s.compareTo(node.item) < 0) {
            node.left = recursiveRemove(node.left, s);
          } else if (s.compareTo(node.item) > 0) {
            node.right = recursiveRemove(node.right, s);
          } else {
            node = deleteNode(node);
          }
          return node;
    }

    // TODO: Fill this in and call it from recursiveRemove()
    protected BSTNode deleteNode(BSTNode node) {
        if (node.left == null && node.right == null) {
            return null;
          } else if (node.left == null) {
            return node.right;
          } else if (node.right == null) {
            return node.left;
          } else {
            String smallest = getSmallest(node.right);
            node.item = smallest;
            node.right = recursiveRemove(node.right, smallest);
            return node;
          }
    }

    // Extra Credit

    // TODO: Fill this in and call it from inOrder()
    protected void inOrderRecursive(BSTNode node, MyQueue queue) {
        if (node != null) {
            inOrderRecursive(node.left, queue);
            queue.enqueue(node.item);
            inOrderRecursive(node.right, queue);
          }
    }

    // TODO: Fill this in and call it from preOrder()
    protected void preOrderRecursive(BSTNode node, MyQueue queue) {
        if (node != null) {
            queue.enqueue(node.item);
            preOrderRecursive(node.left, queue);
            preOrderRecursive(node.right, queue);
          }
    }

    // TODO: Fill this in and call it from postOrder()
    protected void postOrderRecursive(BSTNode node, MyQueue queue) {
        if (node != null) {
            postOrderRecursive(node.left, queue);
            postOrderRecursive(node.right, queue);
            queue.enqueue(node.item);
          }
    } 

    // Prints out the tree structure, using indenting to show the different levels
    // of the tree
    public void printTreeStructure() {
        printTree(0, root);
        System.out.println(); // Ensure the final newline is printed after the tree
    }

    // Recursive helper for printTreeStructure()
    protected void printTree(int depth, BSTNode node) {
        for (int i = 0; i < depth; i++)
        System.out.print("  "); // Indents two spaces for each unit of depth
    if (node == null) {
        System.out.println("null");
    } else {
        System.out.println(node.item);
        printTree(depth + 1, node.left);
        printTree(depth + 1, node.right);
    }
    }

    protected void indent(int depth) {
        for (int i = 0; i < depth; i++)
            System.out.print("  "); // Indents two spaces for each unit of depth
    }


}