public class Triangle extends Shape {
    private double base, height;

    public Triangle(double base, double height, String color) {
        super(color);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    public String toString() {
        return String.format("Triangle{base=%.1f, height=%.1f, color='%s'}", base, height, color);
    }
}