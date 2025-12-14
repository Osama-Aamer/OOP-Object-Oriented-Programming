package org.example;

/**explaining what the class does:
 * Represents a Pen that can draw in different colors.
 * The pen has a cap that must be removed before drawing.
 * Color can only be changed when the cap is on.
 */
public class Pen {

    /**
     * Enum representing the available pen colors.
     */
    public enum Color {
        RED("red"), GREEN("green"), BLUE("blue");

        private final String color;

        Color(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return color;
        }
    }

    private Color currentColor;
    private boolean capOn;

    /**
     * Creates a new Pen with the default color (RED).
     */
    public Pen() {
        this.currentColor = Color.RED;
        this.capOn = true;
    }

    /**
     * Creates a new Pen with the specified color.
     * @param color the initial color of the pen
     */
    public Pen(Color color) {
        this.currentColor = color;
        this.capOn = true;
    }

    /**
     * Removes the cap from the pen, allowing it to draw.
     */
    public void capOff() {
        this.capOn = false;
    }

    /**
     * Puts the cap on the pen, preventing it from drawing.
     */
    public void capOn() {
        this.capOn = true;
    }

    /**
     * Draws with the pen.
     * @return "Drawing [color]" if cap is off, or empty string if cap is on
     */
    public String draw() {
        if (capOn) {
            return "";
        }
        return "Drawing " + currentColor.toString();
    }

    /**
     * Changes the pen color. Only works when the cap is on.
     *  color = the new color to set
     */
    public void changeColor(Color color) {
        if (capOn) {
            this.currentColor = color;
        }
    }
}
