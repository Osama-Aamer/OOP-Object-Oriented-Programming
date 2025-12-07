package org.example.controller;

import javafx.animation.AnimationTimer;
import org.example.model.Pet;
import org.example.view.PetView;

public class PetController {
    private Pet pet;
    private PetView view;
    private AnimationTimer animationTimer;

    private double targetX;
    private double targetY;
    private boolean isMouseOnCanvas;
    private boolean hasReachedTarget;

    public PetController(PetView view) {
        this.view = view;
        // will start pet in the center of the canvas
        this.pet = new Pet(view.getCanvasWidth() / 2, view.getCanvasHeight() / 2);
        this.isMouseOnCanvas = false;
        this.hasReachedTarget = false;

        setupAnimationTimer();
    }

    /**
     * Sets up the animation timer that basically continuously updates the pet's position.
     */
    private void setupAnimationTimer() {
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePetPosition();
            }
        };
        animationTimer.start();
    }

    /**
     * Updates the pet's position if the mouse is on the canvas
     * and if the pet hasn't reached the target yet.
     */
    private void updatePetPosition() {
        if (isMouseOnCanvas && !hasReachedTarget) {
            hasReachedTarget = pet.moveTowards(targetX, targetY);
            view.updateCanvas(pet.getX(), pet.getY());
        }
    }

    /**
     * Called when the mouse moves on the canvas.
     * Updates the target position and resets the hasReachedTarget flag.
     *
     */
    public void handleMouseMoved(double x, double y) {
        this.targetX = x;
        this.targetY = y;
        this.isMouseOnCanvas = true;
        this.hasReachedTarget = false;
    }


    public void handleMouseEntered() {
        this.isMouseOnCanvas = true;
    }

    /**
     * when the mouse exits the canvas.
     * Stops the pet from moving.
     */
    public void handleMouseExited() {
        this.isMouseOnCanvas = false;
    }

    public double getPetX() {
        return pet.getX();
    }

    public double getPetY() {
        return pet.getY();
    }
}

