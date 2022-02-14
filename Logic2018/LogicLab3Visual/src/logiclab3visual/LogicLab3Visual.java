package logiclab3visual;

import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author Rob
 */
public class LogicLab3Visual extends Application {
    // global/member variables
    double startPointX, startPointY, cornerX1, cornerY1, cornerX2, cornerY2, cornerX3, cornerY3;
    Random rng; // random variable
    int iterations; // variable for user input number of iterations
    
    @Override
    public void start(Stage primaryStage) {
        
        Scanner s = new Scanner(System.in); // instanciated user input object
        System.out.println("Please input the number of iterations the Sierpinski triangle should run:");
        iterations = s.nextInt(); // takes user input into the variable
        
        primaryStage.setTitle("Sierpinski Triangle"); // title of the window
        Group root = new Group();
        Canvas canvas = new Canvas(400, 400); // canvas object and its width an height
        GraphicsContext gc = canvas.getGraphicsContext2D(); //part of the canvas that is drawn on
        rng = new Random();
        
        // start point and corner point inital coordinates
        startPointX = 200;
        startPointY = 200;
        cornerX1 = 100;
        cornerY1 = 250;
        cornerX2 = 200;
        cornerY2 = 100;
        cornerX3 = 300;
        cornerY3 = 250;
        
        drawTriangle(gc);  //draw all dots for the triangle
        root.getChildren().add(canvas); // set up the window to be shown
        primaryStage.setScene(new Scene(root)); // combine everything for the window
        primaryStage.show(); // show window
    }
    
    private void drawTriangle(GraphicsContext gc){ // draws triangle onto a screen
        gc.setStroke(Color.BLACK); // point fill colour is black
        
        for (int i = 0; i < iterations; i++){ // runs for as many times as the user entered number
             int randomNumber = rng.nextInt(3) + 1; // takes a random number between one and three
             double vectorX; // vector point variables which details the points movement
             double vectorY;
             switch (randomNumber){ // switch will change to each x and y coordinate randomly
                 case 1:  
                        vectorX = cornerX1 - startPointX; // vector variables are updated with new values
                        vectorY = cornerY1 - startPointY;
                        startPointX = startPointX + (vectorX / 2); // the startpoint is then updated accordingly
                        startPointY = startPointY + (vectorY / 2);
                 break;
                 
                 case 2:
                        vectorX = cornerX2 - startPointX;
                        vectorY = cornerY2 - startPointY;
                        startPointX = startPointX + (vectorX / 2);
                        startPointY = startPointY + (vectorY / 2);
                 break;
                 
                 case 3:
                        vectorX = cornerX3 - startPointX;
                        vectorY = cornerY3 - startPointY;
                        startPointX = startPointX + (vectorX / 2);
                        startPointY = startPointY + (vectorY / 2);
                 break;     
            }
            gc.fillOval(startPointX, startPointY, 1, 1); // each updated point for each case is filled in with a black point
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args); 
    }
    
}
