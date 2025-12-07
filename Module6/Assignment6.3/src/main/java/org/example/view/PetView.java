package org.example.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.controller.PetController;


public class PetView extends Application {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 400;
    private static final int PET_SIZE = 50;

    private Canvas canvas;
    private GraphicsContext gc;
    private PetController controller;
    private Image petImage;

    /**
    two versions: 1 where we load an image for the pet
     and 1 where we draw a cute pet if no image is found
    */
    @Override
    public void init() {
        // Load the pet image
        try {
            // Try to load a pet image from resources
            petImage = new Image(getClass().getResourceAsStream("/pet.png"));
        } catch (Exception e) {
            // If no image found, we'll draw a cute pet instead
            petImage = null;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create the controller after the view is set up
        controller = new PetController(this);

        // Sets up mouse event handlers
        canvas.setOnMouseMoved(event -> {
            controller.handleMouseMoved(event.getX(), event.getY());
        });

        canvas.setOnMouseEntered(event -> {
            controller.handleMouseEntered();
        });

        canvas.setOnMouseExited(event -> {
            controller.handleMouseExited();
        });

        // Set up the scene
        StackPane root = new StackPane(canvas);
        root.setStyle("-fx-background-color: #90EE90;"); // Light green background
        Scene scene = new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT);

        primaryStage.setTitle("Virtual Pet - Walk your pet!");
        primaryStage.setScene(scene);
        primaryStage.show();

        // the initial pet position
        updateCanvas(controller.getPetX(), controller.getPetY());
    }

    /**
     * Updates the canvas by clearing it and redrawing the pet at its new position.
     *
     */
    public void updateCanvas(double petX, double petY) {
        // Clear the canvas
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // a nice grassy background
        gc.setFill(Color.LIGHTGREEN);
        gc.fillRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        // some grass details
        gc.setStroke(Color.GREEN);
        for (int i = 0; i < CANVAS_WIDTH; i += 20) {
            gc.strokeLine(i, CANVAS_HEIGHT, i + 5, CANVAS_HEIGHT - 10);
            gc.strokeLine(i + 10, CANVAS_HEIGHT, i + 15, CANVAS_HEIGHT - 8);
        }

        // Draw the pet
        if (petImage != null && !petImage.isError()) {
            // the pet image is centered at the pet's position
            gc.drawImage(petImage, petX - PET_SIZE / 2.0, petY - PET_SIZE / 2.0, PET_SIZE, PET_SIZE);
        } else {
            // Draw a cute cartoon pet (a cat-like creature)
            drawCutePet(petX, petY);
        }
    }

    /**
     * Draws a cute cartoon pet at the specified position.
     * This is used when no pet image is available.
     */
    private void drawCutePet(double x, double y) {
        double size = PET_SIZE;
        double halfSize = size / 2;

        // Body (oval)
        gc.setFill(Color.ORANGE);
        gc.fillOval(x - halfSize, y - halfSize * 0.6, size, size * 0.8);

        // Head (circle)
        gc.setFill(Color.ORANGE);
        gc.fillOval(x - halfSize * 0.8, y - halfSize * 1.2, size * 0.8, size * 0.8);

        // Ears
        gc.setFill(Color.DARKORANGE);
        gc.fillOval(x - halfSize * 0.9, y - halfSize * 1.4, size * 0.3, size * 0.4);
        gc.fillOval(x + halfSize * 0.3, y - halfSize * 1.4, size * 0.3, size * 0.4);

        // Eyes
        gc.setFill(Color.WHITE);
        gc.fillOval(x - halfSize * 0.5, y - halfSize * 0.9, size * 0.25, size * 0.25);
        gc.fillOval(x + halfSize * 0.1, y - halfSize * 0.9, size * 0.25, size * 0.25);

        // Pupils
        gc.setFill(Color.BLACK);
        gc.fillOval(x - halfSize * 0.4, y - halfSize * 0.8, size * 0.12, size * 0.12);
        gc.fillOval(x + halfSize * 0.2, y - halfSize * 0.8, size * 0.12, size * 0.12);

        // Nose
        gc.setFill(Color.PINK);
        gc.fillOval(x - halfSize * 0.1, y - halfSize * 0.5, size * 0.15, size * 0.12);

        // Mouth (smile)
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1.5);
        gc.strokeArc(x - halfSize * 0.2, y - halfSize * 0.5, size * 0.3, size * 0.2, 180, 180, javafx.scene.shape.ArcType.OPEN);

        // Tail
        gc.setStroke(Color.ORANGE);
        gc.setLineWidth(4);
        gc.strokeArc(x + halfSize * 0.5, y - halfSize * 0.2, size * 0.4, size * 0.5, 0, 180, javafx.scene.shape.ArcType.OPEN);

        // Legs
        gc.setFill(Color.ORANGE);
        gc.fillOval(x - halfSize * 0.6, y + halfSize * 0.1, size * 0.2, size * 0.25);
        gc.fillOval(x - halfSize * 0.1, y + halfSize * 0.1, size * 0.2, size * 0.25);
        gc.fillOval(x + halfSize * 0.2, y + halfSize * 0.1, size * 0.2, size * 0.25);
        gc.fillOval(x + halfSize * 0.5, y + halfSize * 0.1, size * 0.2, size * 0.25);

        // Paws
        gc.setFill(Color.DARKORANGE);
        gc.fillOval(x - halfSize * 0.6, y + halfSize * 0.25, size * 0.2, size * 0.1);
        gc.fillOval(x - halfSize * 0.1, y + halfSize * 0.25, size * 0.2, size * 0.1);
        gc.fillOval(x + halfSize * 0.2, y + halfSize * 0.25, size * 0.2, size * 0.1);
        gc.fillOval(x + halfSize * 0.5, y + halfSize * 0.25, size * 0.2, size * 0.1);
    }

    public int getCanvasWidth() {
        return CANVAS_WIDTH;
    }

    public int getCanvasHeight() {
        return CANVAS_HEIGHT;
    }
}

