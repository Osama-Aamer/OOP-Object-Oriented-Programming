public abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double calculateArea();

    @Override
    public String toString() {
        return "Shape{color='" + color + "'}";
    }
}