public class Rectangle extends Shape {
    private double width, height;

    public Rectangle(double width, double height, String color) {
        super(color);
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public String toString() {
        return String.format("Rectangle{width=%.1f, height=%.1f, color='%s'}", width, height, color);
    }
}