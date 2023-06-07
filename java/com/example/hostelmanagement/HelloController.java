package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class HelloController {
    public static Stage forStage;
    public Button login;
    public Button forgot;
    public CheckBox showpass;
    public TextField id_field;
    public PasswordField passwordField;
    public TextField security_field;
    public TextField Security_id_Field;
    public Button done;
    public Button cancel;
    public Button emp;
    public Button std;
    public Scene scene;

    public Stage newStage;

    public Circle circle;
    public Stage mainstage;

    @FXML
    protected void Login() {


        try{
            String q1 = "select * from Student where st_id ='" + id_field.getText() + "'and st_password = '" +  passwordField.getText() + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q1);

//            String q2 = "select * from Employees where emp_id ='" + id_field.getText() + "'and emp_password = '" +  passwordField.getText() + "'";
//            ResultSet res2 = HelloApplication.statement.executeQuery(q2);

            if(res.next()){  //Student has logged in
                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
                scene = new Scene(root);

                HelloApplication.stage.setScene(scene);

            }
//            else if (res2.next()) {     //Employee has logged in
//                //  TO CHECK LOGIN CONDITION FOR EMPLOYEE AND ADD ANOTHER SCENE INSTEAD OF HOMEPAGE.FXML TO DIFFER LOGIN PAGES FOR STUDENTS AND EMPLOYEES
//                Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
//                scene = new Scene(root);
//
//                HelloApplication.stage.setScene(scene);
//                return;
//            }
            else{
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Entered ID or Password is incorrect");
                a.show();
            }

            id_field.setText("");
            passwordField.setText("");

        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    protected void ForgotPassword(ActionEvent event) throws IOException {

            FXMLLoader loder = new FXMLLoader(getClass().getResource("ForgotPass.fxml"));
            forStage = loder.load();
            forStage.initStyle(StageStyle.UNDECORATED);
            forStage.show();

            try{
            String q1 = "select * from Student where st_SecurityQuestion = '" + security_field.getText() + "'and st_id = " + Security_id_Field.getText() + "'";
            ResultSet res1 = HelloApplication.statement.executeQuery(q1);

//            String q2 = "select * from Employees where emp_SecurityQuestion ='" + security_field.getText() + "'and emp_id = '" +  Security_id_Field.getText() + "'";
//            ResultSet res2 = HelloApplication.statement.executeQuery(q2);

                if(res1.next()){    //Student security question checked
                   // b_student = true;
                    FXMLLoader load = new FXMLLoader(HelloApplication.class.getResource("ForgotPass.fxml"));
                    Parent root = load.load();
                    scene = new Scene(root);

                    HelloApplication.stage.setScene(scene);
                }
//                else if(res2.next()){   //Employee security question checked
//                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ForgotPass.fxml")));
//                    scene = new Scene(root);
//
//                    HelloApplication.stage.setScene(scene);
//                }
                else{
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("ID and Security Answer not matched");
                    a.show();
                }

            }catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }


    @FXML
    protected void Confirm() throws IOException {
        FXMLLoader loder = new FXMLLoader(HelloApplication.class.getResource("NewPass.fxml"));
        newStage = loder.load();
        NewPasswordController npc = loder.getController();
        npc.ID = Security_id_Field.getText();
        forStage.close();

//        newStage.setScene(loder.load());
        newStage.initStyle(StageStyle.UNDECORATED);
        newStage.show();
    }

   @FXML
   protected void DoneHoverIn(){
        done.setStyle("-fx-background-color: #9A71AD; -fx-background-radius: 5");
   }

    @FXML
    protected void DoneHoverOut(){
        done.setStyle("-fx-background-color:   #32213A; -fx-background-radius: 5");
    }

    @FXML
    protected void CancelHoverIn(){
        cancel.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-background-radius: 5");
    }

    @FXML
    protected void CancelHoverOut(){
        cancel.setStyle("-fx-background-color: gray; -fx-background-radius: 5");
    }

    @FXML
    protected void Cancel() throws IOException {

            forStage.close();
    }

   @FXML
   protected void LoginHoverIn()
   {
           login.setStyle("-fx-background-color: #48BF84 ; -fx-background-radius: 10");

   }

   @FXML
   protected void LoginHoverOut()
   {
       login.setStyle("-fx-background-color:  #D4D6B9 ; -fx-background-radius: 10");

   }

    @FXML
    protected void ShowPassword(){
        if (showpass.isSelected()) {
            passwordField.setPromptText(passwordField.getText());
            passwordField.setText("");
        } else {
            passwordField.setText(passwordField.getPromptText());
            passwordField.setPromptText("");
        }

    }

    @FXML
    public void EmpHoverIn()
    {
         emp.setStyle("-fx-background-color:  #D4D6B9; -fx-text-fill: black");
    }

    @FXML
    public void EmpHoverout(){
        emp.setStyle("-fx-background-color: transparent; -fx-border-style: hidden hidden solid hidden; -fx-border-color:#D4D6B9; -fx-text-fill: white ");
    }

    @FXML
    public void Emp(){
        id_field.setPromptText("Employee ID");
    }

    @FXML
    public void STDHoverIn()
    {
        std.setStyle("-fx-background-color:  #D4D6B9; -fx-text-fill: black");
    }


    @FXML
    public void STDHoverout(){
        std.setStyle("-fx-background-color: transparent; -fx-border-style: hidden hidden solid hidden; -fx-border-color:#D4D6B9; -fx-text-fill: white ");
    }

    @FXML
    public void STD(){
        id_field.setPromptText("Student ID");
    }
}