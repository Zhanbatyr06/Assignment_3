public class MyTestingClass implements Comparable<MyTestingClass> {
    private String name;
    private int id;

    public MyTestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(MyTestingClass other) {
        return id - other.id; // ID comparison
    }

    @Override
    public int hashCode() {
        return name.hashCode() ^ id;
    }

    @Override
    public String toString() {
        return "MyTestingClass{" + "name='" + name + '\'' + ", id=" + id + '}';
    }

    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Integer> table = new MyHashTable<>();

        // Adding items to a hash table
        MyTestingClass obj1 = new MyTestingClass("Object1", 1);
        MyTestingClass obj2 = new MyTestingClass("Object2", 2);
        MyTestingClass obj3 = new MyTestingClass("Object3", 3);
        table.put(obj1, 100);
        table.put(obj2, 200);
        table.put(obj3, 300);

        // Getting value by key
        System.out.println("Value for obj1: " + table.get(obj1));

        // Deleting an item by key
        System.out.println("Removed value for obj2: " + table.remove(obj2));

        // Checking the presence of an element
        System.out.println("Contains obj3: " + table.contains(300));

        // Getting the key by value
        System.out.println("Key for value 300: " + table.getKey(300));
    }
}
