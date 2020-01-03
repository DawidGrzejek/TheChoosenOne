package tests;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java.awt.*;

import static javafx.application.Application.launch;

public class StageController extends Application {
    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        Label label1 = new Label("Welcome to the first scene!");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1 - children are laid out in vertical column
        StackPane layout1 = new StackPane();
        label1.getChildrenUnmodifiable().addAll(label1, button1);
        scene1 = new Scene(layout1, 200, 200);

        //Button 2
        Button button2 = new Button("This scene sucks, go back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout1, 600, 300);

        window.setScene(scene1);
        window.setTitle("Title here");
        window.show();
    }
}
