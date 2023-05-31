package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;

import static com.example.hostelmanagement.HelloApplication.statement;

public class RegisterController {

    public static Stage stage1 = new Stage();
    public Scene scene1;
    @FXML
    public Button next1;
    @FXML
    public Button next2;
    @FXML
    public Button cancel1;
    @FXML
    public Button cancel2;
    @FXML
    public Button done;
    @FXML
    public TextField fname, lname, dob, semester, cgpa, degree, batch, PhnCode, PhnNo, CNICcode, CNICno, email, religion, Hno, town;

    public TextField security;
    public PasswordField password;

   //public ComboBox<String>;
    @FXML
    protected void Register(ActionEvent event) throws IOException {
        FXMLLoader reg1 = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        stage1.setScene(new Scene(reg1.load()));
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.show();
    }

    @FXML

    protected void Next1(ActionEvent backevent) throws IOException {

        try{
            // Step 1: Retrieve the maximum st_id value
            String maxStIdQuery = "SELECT MAX(st_id) FROM Student";
            ResultSet resultSet = statement.executeQuery(maxStIdQuery);
            resultSet.next();
            String maxStId = resultSet.getString(1);

            // Step 2: Generate the new st_id
            int numericPart = Integer.parseInt(maxStId.substring(1)); // Extract numeric part
            String newStId = "S" + String.format("%03d", numericPart + 1); // Increment and format

            // Step 3: Insert the new record
            String InsertQuery = "insert into Student(st_id, batch, degree, st_firstName, st_lastName, st_PHNcode, st_PHNno, st_email, st_dob, st_CNICcode, st_CNICno, religion, st_city, st_town, s_houseNo, cgpa, st_password, st_SecurityQuestion) values " +
                    "('" + newStId + "' , '" + batch.getText() + "' , '" + degree.getText() + "' , '" + fname.getText() + "' , '" + lname.getText() + "' , " + PhnCode.getText() + ", " + PhnNo.getText() + ", '" + email.getText() + "' , '" + dob.getText() + "' , " + CNICcode.getText() + ", " + CNICno.getText() + ", '" + religion.getText() + "' , '" + "CITY TO BE INSERTED" + "' , '" + town.getText() + "' , '" + Hno.getText() + "' , " + cgpa.getText() + ", '" + password.getText() +"' , '" + security.getText() + "'";

            statement.executeUpdate(InsertQuery);
        } catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register2.fxml"));
        stage1.setScene(new Scene(reg2.load()));

    }


    @FXML

    protected void Next2(ActionEvent backevent) throws IOException {

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register3.fxml"));
        stage1.setScene(new Scene(reg2.load()));
    }

    @FXML

    protected void Cancel1(ActionEvent backevent) throws IOException {

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        stage1.setScene(new Scene(reg2.load()));
    }


    @FXML

    protected void Cancel2(ActionEvent backevent) throws IOException {

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register2.fxml"));
        stage1.setScene(new Scene(reg2.load()));
    }


    @FXML
    protected void Done(){
        stage1.close();
        Stage updated = new Stage();
        BorderPane upd = new BorderPane();
        Label ud = new Label("The Student has been Registered");
        upd.setStyle("-fx-background-color: #25283D;");
        ud.setStyle("-fx-text-fill: white");
        upd.setCenter(ud);

        updated.setScene(new Scene(upd,400,200));
        updated.show();
    }
}