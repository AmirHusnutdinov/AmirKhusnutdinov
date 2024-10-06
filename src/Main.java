public class Main {
    public static void main(String[] args) {
        CustomList<String> list = new CustomArrayList<>();

        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        System.out.println("Size: " + list.size()); // Size: 3
        System.out.println("Element at index 1: " + list.get(1)); // Element at index 1: Banana

        String removed = list.remove(1);
        System.out.println("Removed element: " + removed); // Removed element: Banana
        System.out.println("Size after removal: " + list.size()); // Size after removal: 2
        System.out.println("Element at index 1 after removal: " + list.get(1)); // Element at index 1 after removal: Cherry
    }
}
