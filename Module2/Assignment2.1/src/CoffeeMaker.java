public class CoffeeMaker {
    private boolean isOn;
    private String coffeeType; // "normal" or "espresso"
    private int coffeeAmount; // 10 to 80 ml

    public CoffeeMaker() {
        this.isOn = false;
        this.coffeeType = "normal";
        this.coffeeAmount = 50;
    }

    public void turnOn() {
        if (!isOn) {
            isOn = true;
            System.out.println("Coffee maker is on");
        }
    }

    public void turnOff() {
        if (isOn) {
            isOn = false;
            System.out.println("Coffee maker is off");
        }
    }

    public void setCoffeeType(String type) {
        if (!isOn) return;
        if ("normal".equalsIgnoreCase(type) || "espresso".equalsIgnoreCase(type)) {
            this.coffeeType = type.toLowerCase();
            System.out.println("Coffee type is " + this.coffeeType);
        }
    }

    public void setCoffeeAmount(int ml) {
        if (!isOn) return;
        if (ml >= 10 && ml <= 80) {
            this.coffeeAmount = ml;
            System.out.println("Coffee amount is " + ml + " ml");
        }
    }

    public boolean isOn() {
        return isOn;
    }
}