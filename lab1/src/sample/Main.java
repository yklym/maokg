package sample;

import javafx.scene.paint.Color;

import javafx.fxml.FXMLLoader;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
//        primaryStage.setTitle("Hello World!");

        Group root = new Group();
        Scene scene = new Scene(root, 300, 250);

        scene.setFill(Color.rgb(0, 128, 128));

        Polygon part1 = new Polygon();
        part1.getPoints().addAll(
                60.0, 125.0,
                140.0, 50.0,
                280.0, 100.0,
                200.0, 145.0);
        part1.setFill(Color.rgb(4, 250, 4));

        Line delimiter = new Line(60.0f, 125.0f, 200.0, 145.0);
        delimiter.setStrokeWidth(2.0);

        Polygon part2 = new Polygon();
        part2.getPoints().addAll(
                100.0, 220.0,
                60.0, 125.0,
                200.0, 145.0,
                250.0, 200.0);
        part2.setFill(Color.rgb(4, 250, 4));

        Rectangle eye1 = new Rectangle(120, 100, 8, 8);
        eye1.setFill(Color.rgb(0,128,0));

        Rectangle eye2 = new Rectangle(110, 150, 8, 8);
        eye2.setFill(Color.rgb(0,128,0));

        Polygon tail = new Polygon();
        tail.getPoints().addAll(
                215.0, 145.0,
                270.0, 120.0,
                255.0, 183.0);
        tail.setFill(Color.rgb(234,239,59));


        Line antenna1 = new Line(30.0f, 50.0f, 75.0, 115.0);
        antenna1.setStrokeWidth(5.0);

        Line antenna2 = new Line(30.0f, 190.0f, 75.0, 145.0);
        antenna2.setStrokeWidth(5.0);


        root.getChildren().add(part1);
        root.getChildren().add(part2);
        root.getChildren().add(delimiter);
        root.getChildren().add(eye1);
        root.getChildren().add(eye2);
        root.getChildren().add(tail);
        root.getChildren().add(antenna1);
        root.getChildren().add(antenna2);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
