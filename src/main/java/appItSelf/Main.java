package appItSelf;

import DBEntities.CredentialsEntity;
import dataBase.DBConnect;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 100, 300, 100));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(10,10,10,20));

        HBox hb2 = new HBox();
        hb2.setPadding(new Insets(110,40,0,0));

        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Implementing Nodes for GridPane
        Label lblUserName = new Label("Username");
        gridPane.add(lblUserName, 0, 0);
        final TextField txtUserName = new TextField();
        txtUserName.setPromptText("Type your username");
        GridPane.setConstraints(txtUserName, 1, 0);
        gridPane.getChildren().add(txtUserName);

        Label lblPassword = new Label("Password");
        gridPane.add(lblPassword, 0, 1);
        final PasswordField pf = new PasswordField();
        pf.setPromptText("Type your password");
        gridPane.add(pf, 1, 1);


        //Button
        Button btnLogin = new Button("Login");
        gridPane.add(btnLogin, 2, 1);
        final Label lblMessage = new Label();
        gridPane.add(lblMessage, 1, 2);


        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);

        //DropShadow effect
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Adding text and DropShadow effect to it
        Text text = new Text("JavaFX 2 Login");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);

        Text datbaseRecords = new Text("text2");
        datbaseRecords.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

        //Adding text to HBox
        hb.getChildren().add(text);
        hb2.getChildren().add(datbaseRecords);

        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        text.setId("text");
        datbaseRecords.setId("datbaseRecords");

        //Text
        final Text actiontarget = new Text();
        gridPane.add(actiontarget, 1, 6);
        actiontarget.setId("actiontarget");

        //Adding a Label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 4);
        gridPane.getChildren().add(label);

        //Setting an action for the Button Event
        btnLogin.setOnAction(actionEvent -> {
            //Check if UserName and Password are given
            if ((txtUserName.getText() != null && !txtUserName.getText().isEmpty()) && (pf.getText() != null && !pf.getText().isEmpty())) {
                //Check if Password meet requiments
                //String passwordPattern = "^(?=\\P{Ll}*\\p{Ll})(?=\\P{Lu}*\\p{Lu})(?=\\P{N}*\\p{N})(?=[\\p{L}\\p{N}]*[^\\p{L}\\p{N}])[\\s\\S]{8,}$";
                String passwordPattern = "^.*(?=.{2,})*$";
                /*
                1. ^ Assert position at the start of the line.
                2. (?=\P{Ll}*\p{Ll}) Ensure at least one lowercase letter (in any script) exists.
                3. (?=\P{Lu}*\p{Lu}) Ensure at least one uppercase letter (in any script) exists.
                4. (?=\P{N}*\p{N}) Ensure at least one number character (in any script) exists.
                5. (?=[\p{L}\p{N}]*[^\p{L}\p{N}]) Ensure at least one of any character (in any script) that isn't a letter or digit exists.
                6. [\s\S]{10,} Matches any character 10 or more times.
                7. $ Assert position at the end of the line.
                */
                String query;
                DBConnect.typeOfQuery typeOfQuery;
                if(!pf.getText().matches(passwordPattern)) {
                    label.setText("Your password did not meet our complexity!");
                }
                //Save UserName and Password to database
                else {
                    label.setText(txtUserName.getText() + " " + pf.getText());
                    //Check if UserName already exists
                    //language=sql
                    query = "select * from credentials";
                    List<CredentialsEntity> credentials = DBConnect.list(query);
                    System.out.print(credentials.toString() + "\n");

                    //Insert credencials to table credencials
                    //language=sql
                    typeOfQuery = DBConnect.typeOfQuery.UPDATE;
                    query = "insert into credentials (userName, password) " +
                            "values ('"+txtUserName.getText()+"', '"+pf.getText()+"')";
                    DBConnect dbConnect = new DBConnect(typeOfQuery, query);
                    actiontarget.setText("Credentials saved.");
                    System.out.print("Table updated");
                }
            } else {
                label.setText("You have not left a username or password");
            }
        });


        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setBottom(hb2);
        bp.setCenter(gridPane);

        //Adding BorderPane to the scene and loading CSS
        Scene scene = new Scene(bp);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("login.css")).toExternalForm());
        primaryStage.setScene(scene);
        /*primaryStage.titleProperty().bind(
                scene.widthProperty().asString().
                        concat(" : ").
                        concat(scene.heightProperty().asString()));*/
        //primaryStage.setResizable(false);
        primaryStage.show();
    }
}
