public class CoffeeMakerDriver {
    public static void main(String[] args) {
        CoffeeMaker maker = new CoffeeMaker();

        maker.turnOn();
        maker.setCoffeeType("espresso");
        maker.setCoffeeAmount(50);
        maker.turnOff();
    }
}