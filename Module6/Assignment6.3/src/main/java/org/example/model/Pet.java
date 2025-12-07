package org.example.model;

public class Pet {
    private double x;
    private double y;
    private static final double SPEED = 3.0; // pixels per update

    public Pet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Moves the pet towards the target position.
     * Uses trigonometry to calculate the direction.
     *
     * returns true if the pet has reached the target, otherwise returns false.
     */
    public boolean moveTowards(double targetX, double targetY) {
        // Calculate the distance to the target
        double dx = targetX - x;
        double dy = targetY - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        // If the pet is close enough to the target, it stops moving
        if (distance < SPEED) {
            x = targetX;
            y = targetY;
            return true;
        }

        // Calculate the angle towards the target using trigonometry
        double angle = Math.atan2(dy, dx);

        // Move the pet towards the target at a limited speed
        x += Math.cos(angle) * SPEED;
        y += Math.sin(angle) * SPEED;

        return false; // still moving
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static double getSpeed() {
        return SPEED;
    }
}

