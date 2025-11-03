public class TestSportsCar {
    public static void main(String[] args) {
        SportsCar ferrari = new SportsCar(60);
        System.out.println(ferrari);
        ferrari.accelerate(50);
        System.out.println(ferrari);
    }
}