public class Task4 {
    private String name;

    public Task4(String name) {
        this.name = name;
    }

    public void meow() {
        System.out.println("The cat named " + name + " says: Meow!");
    }

    public static void main(String[] args) {
        // Create two Task4 objects (representing cats)
        Task4 whiskers = new Task4("Whiskers");
        Task4 rex = new Task4("Rex");

        // Call meow methods in the specified order
        whiskers.meow(); // First Whiskers meow
        whiskers.meow(); // Second Whiskers meow
        rex.meow();      // Rex meow
        whiskers.meow(); // Third Whiskers meow
    }
}