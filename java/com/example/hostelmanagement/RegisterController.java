package com.example.hostelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.concurrent.atomic.AtomicReference;

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
    public TextField fname, lname, dob, semester, cgpa, degree, batch, Phncode, PhnNo, CNICcode, CNICno, email, religion, Hno, town, security;
    @FXML
    public PasswordField password;
    @FXML
   public ComboBox<String> comboBox = new ComboBox<>();
    public String newStId;
    public String selected_city;
    @FXML
    public TextField G_fname, G_lname, G_relation, G_PHNcode, G_PHNno, G_CNICcode, G_CNICno, G_email, G_religion, G_Hno, G_town;
    @FXML
    public ComboBox<String> comboBox2 = new ComboBox<>();
    public String G_city;
    @FXML
    public TextField v_fname, v_lname, v_relation, v_PHNcode, v_PHNno,  v_CNICcode, v_CNICno;
    @FXML
    protected void Register(ActionEvent event) throws IOException {
        FXMLLoader reg1 = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        stage1.setScene(new Scene(reg1.load()));
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.show();
    }

    @FXML
    public void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList("Arizona", "Auburn", "Boston",
                "Chicago", "California", "Florence", "Florida", "Greenville", "Kansas", "New York", "Ohio", "Portland", "Seattle");
        comboBox.setItems(items);

        ObservableList<String> items2 = FXCollections.observableArrayList("Arizona", "Auburn", "Boston",
                "Chicago", "California", "Florence", "Florida", "Greenville", "Kansas", "New York", "Ohio", "Portland", "Seattle");
        comboBox2.setItems(items2);
    }

    @FXML
    protected void Show(){
        selected_city = comboBox.getValue();
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
            System.out.println("2." + selected_city);
            selected_city = newValue;
        });
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

            newStId = "S" + String.format("%03d", numericPart + 1); // Increment and format

            // Step 3: Insert the new record
            String InsertQuery = "insert into Student(st_id, batch, degree, st_firstName, st_lastName, st_PHNcode, st_PHNno, st_email, st_dob, st_CNICcode, st_CNICno, religion, st_city, st_town, s_houseNo, cgpa, st_password, st_SecurityQuestion) values " +
                    "('" + newStId + "' , '" + batch.getText() + "' , '" + degree.getText() + "' , '" + fname.getText() + "' , '" + lname.getText() + "' , " + Phncode.getText() + ", " + PhnNo.getText() + ", '" + email.getText() + "' , '" + dob.getText() + "' , "
                    + CNICcode.getText() + ", " + CNICno.getText() + ", '" + religion.getText() + "' , '" + selected_city + "' , '" + town.getText() + "' , '" + Hno.getText() + "' , " + cgpa.getText() + ", '" + password.getText() +"' , '" + security.getText() + "')";

            HelloApplication.statement.executeQuery(InsertQuery);
        } catch (Exception e){
            System.out.println( e.getMessage());
            e.printStackTrace();
        }

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register2.fxml"));
        stage1.setScene(new Scene(reg2.load()));

    }
    @FXML
    protected void Show2(){
        selected_city = comboBox.getValue();
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
            G_city = newValue;
        });
    }
    @FXML
    protected void Next2(ActionEvent backevent) throws IOException {

        try{
            // Retrieve the maximum st_id value
            String maxStIdQuery = "SELECT MAX(st_id) FROM Student";
            ResultSet resultSet = statement.executeQuery(maxStIdQuery);
            resultSet.next();
            String maxStId = resultSet.getString(1);
            System.out.println("in 2nd st_id: " + maxStId);


        String InsertQuery2 = "insert into Guardian(st_id, g_firstName, g_lastname, g_CNICcode, g_CNICno, g_relation,g_PHNcode, g_PHNno, g_city, g_town, g_houseNo, g_email) values " +
                "('" + maxStId + "' , '" + G_fname.getText() + "' , '" + G_lname.getText() + "' ," + G_CNICcode.getText() + "," + G_CNICno.getText() + ", '" + G_relation.getText() + "' , " + G_PHNcode.getText() + ", " + G_PHNno.getText() + ", '" + G_city + "' , '"
                + G_town.getText() + "' , '" + G_Hno.getText() + "' , '" + G_email.getText() + "')";

        HelloApplication.statement.executeQuery(InsertQuery2);
    } catch (Exception e){
            System.out.println( e.getMessage());
            e.printStackTrace();
    }

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
    protected void Next3() throws IOException {
        FXMLLoader allot = new FXMLLoader(getClass().getResource("AllotingRoom.fxml"));
        stage1.setScene(new Scene(allot.load()));
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