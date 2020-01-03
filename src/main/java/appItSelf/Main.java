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

import java.awt.image.AbstractMultiResolutionImage;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Main extends Application {
    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws SQLException{
        window = primaryStage;

        window.setTitle("Hello");

        //Adding BorderPane
        BorderPane bp1 = new BorderPane();
        bp1.setPadding(new Insets(10, 60, 60, 60));

        //Adding HBox
        HBox hbL = new HBox();
        hbL.setPadding(new Insets(10,10,10,20));

        HBox hb2L = new HBox();
        hb2L.setPadding(new Insets(10,10,0,0));


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
        gridPane.add(btnLogin, 2, 0);
        final Label lblMessage = new Label();
        gridPane.add(lblMessage, 1, 2);

        Button btnSwitchScene = new Button("SwitchScene");
        gridPane.add(btnSwitchScene, 2, 1);


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


        //Adding text to HBox
        hbL.getChildren().add(text);

        //Add ID's to Nodes
        bp1.setId("bp1");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        btnSwitchScene.setId("btnSwitchScene");
        text.setId("text");

        //Text
        final Text actiontarget = new Text();
        gridPane.add(actiontarget, 1, 6);
        actiontarget.setId("actiontarget");

        //Adding a Label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        GridPane.setColumnSpan(label, 4);
        gridPane.getChildren().add(label);

        //Setting an action for the btnLogin
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
                DBConnect.typeOfQuery update = DBConnect.typeOfQuery.UPDATE;
                DBConnect.typeOfQuery execute = DBConnect.typeOfQuery.EXECUTE;


                List<CredentialsEntity> credentials = list();
                String userName = txtUserName.getText().trim().toLowerCase();
                for(CredentialsEntity ce:credentials) {
                    if(ce.getUserName().trim().toLowerCase().contains(userName)) {
                        label.setText("That user name is already in use");
                        System.out.println("That user name is already in use");
                        return;
                    }
                }

                if(!pf.getText().matches(passwordPattern)) {
                    label.setText("Your password did not meet our complexity!");
                }
                //Check if UserName already exists
                //Save UserName and Password to database
                else{
                    label.setText(txtUserName.getText() + " " + pf.getText());
                    //Insert credencials to table credencials
                    //language=sql
                    query = "insert into credentials (userName, password) " +
                            "values ('"+txtUserName.getText()+"', '"+pf.getText()+"')";
                    DBConnect dbConnect = new DBConnect(update, query);
                    actiontarget.setText("Credentials saved.");
                    System.out.print("Table `credentials` updated");
                }
            } else {
                label.setText("You have not left a username or password");
            }
        });

        //Seeting an action for the btnSwtichScene
        btnSwitchScene.setOnAction(actionEvent -> {
            //Adjusting Scene2

            //Adding BorderPane2
            BorderPane bp2 = new BorderPane();
            bp2.setPadding(new Insets(10,60,60,60));

            //Adding HBox2
            HBox hbR = new HBox();
            hbL.setPadding(new Insets(10,10,10,20));

            HBox hb2R = new HBox();
            hb2L.setPadding(new Insets(10,10,0,0));

            //Adding GridPane2
            GridPane gridPane2 = new GridPane();
            gridPane2.setPadding(new Insets(20,20,20,20));
            gridPane2.setHgap(5);
            gridPane2.setVgap(5);

            //Implementing Nodes for GridPane2
            Label lblPasswordRepeat = new Label("PasswordRepeat");
            gridPane2.add(lblPasswordRepeat, 0, 1);
            final PasswordField pfr = new PasswordField();
            pfr.setPromptText("Repeat your password");
            gridPane2.add(pfr, 1, 1);

            //Add ID's to Nodes
            bp2.setId("bp2");

            //Adding Nodes for GridPane2
            gridPane2.add(lblUserName, 0, 0);
            gridPane2.getChildren().add(txtUserName);
            gridPane2.add(lblPassword,0,1);
            gridPane2.add(pf,2,2);


            gridPane2.add(btnLogin,2,0);
            gridPane2.add(lblMessage,1,2);
            gridPane2.add(btnSwitchScene,2,1);

            window.setScene(scene2);
            //Adding text to HBox2
            hb2R.getChildren().add(text);
            bp2.setTop(hbR);
            bp2.setBottom(hb2R);
            bp2.setCenter(gridPane);
        });


        //Add HBox and GridPane layout to BorderPane Layout
        bp1.setTop(hbL);
        bp1.setBottom(hb2L);
        bp1.setCenter(gridPane);



        //Adding BorderPane to the scene and loading CSS
        scene1 = new Scene(bp1);
        scene1.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("login.css")).toExternalForm());

        scene2 = new Scene(bp2);
        scene2.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("register.css")).toExternalForm());

        window.setScene(scene1);
        /*window.titleProperty().bind(
                scene.widthProperty().asString().
                        concat(" : ").
                        concat(scene.heightProperty().asString()));*/
        //window.setResizable(false);
        window.show();
    }
    public static List<CredentialsEntity> list(){
        List<CredentialsEntity> credencials = new ArrayList<>();
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "root");
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * from credentials");
        ){
            while (resultSet.next()){
                CredentialsEntity credentialsEntity = new CredentialsEntity();
                credentialsEntity.setId(resultSet.getInt("id"));
                credentialsEntity.setUserName(resultSet.getString("userName"));
                credentialsEntity.setPassword(resultSet.getString("password"));
                credencials.add(credentialsEntity);
            }
        }catch (Exception ex){
            System.out.print("Error" + ex);
        }
        return credencials;
    }
}
