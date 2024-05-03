import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
        size++;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value; // Update value if key already exists
        return node;
    }

    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) node = node.left;
            else if (cmp > 0) node = node.right;
            else return node.value;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
        size--;
    }

    private Node delete(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.right == null) return node.left;
            if (node.left == null) return node.right;
            Node minNode = findMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = deleteMin(node.right);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public Iterable<Node> iterator() {
        List<Node> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return nodes;
    }

    private void inOrderTraversal(Node node, List<Node> nodes) {
        if (node != null) {
            inOrderTraversal(node.left, nodes);
            nodes.add(node);
            inOrderTraversal(node.right, nodes);
        }
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(2, "Two");
        tree.put(4, "Four");
        tree.put(6, "Six");
        tree.put(8, "Eight");

        for (BST<Integer, String>.Node node : tree.iterator()) {
            System.out.println("Key: " + node.key + ", Value: " + node.value);
        }

        System.out.println("Size of tree: " + tree.size());
    }
}
