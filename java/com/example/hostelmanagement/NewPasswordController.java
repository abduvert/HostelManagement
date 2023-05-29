package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NewPasswordController {
    @FXML
    public TextField new_pass_field;
    @FXML
    public TextField confirm_passs_field;
    @FXML
    public Button update;
    @FXML
    public Button cancel2;
    @FXML
    public static Stage newStage;
    public Scene scene;
    public Stage forStage;
    @FXML
    protected void UpdateHoverIn(){
        update.setStyle("-fx-background-color: #9A71AD; -fx-background-radius: 5");
    }

    @FXML
    protected void UpdateHoverOut(){

        update.setStyle("-fx-background-color:  #32213A; -fx-background-radius: 5");

    }
    String ID = "";
    @FXML
    protected void Update(){
        if(new_pass_field.getText().equals(confirm_passs_field.getText())){
            try {
                System.out.println(new_pass_field.getText());
                System.out.println(ID);
                String query = "update Student set st_password = '" + new_pass_field.getText() + "' where st_id ='" + ID + "'";
                int res = HelloApplication.statement.executeUpdate(query);
                System.out.println(res);

                if(res > 0){
                    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("HomePage.fxml"));
                    Parent root = loader.load();
                    scene = new Scene(root);
                    HelloApplication.stage.setScene(scene);
                }
//                   }
//                   else{
//                       Alert a = new Alert(Alert.AlertType.WARNING);
//                       a.setContentText("Passwords do not match");
//                       a.show();
//                   }
////                   else{
//                       String query = "update Employees set emp_password = '" + new_pass_field.getText() + "' where emp_id =" + Security_id_Field.getText() + "'";
//                       int res = HelloApplication.statement.executeUpdate(query);
//                   }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Your password has been updated");
            newStage.close();
            a.show();

        }
        else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("Password not matched");
            a.show();
        }
        /*
        newStage.close();
        Stage updated = new Stage();
        BorderPane upd = new BorderPane();
        Label ud = new Label("Your Password has been updated!");
        ud.setStyle("-fx-text-fill: #2D1E34");
        upd.setCenter(ud);
        updated.setScene(new Scene(upd,400,200));
        updated.show();
        */

    }

    @FXML
    protected void Cancel2HoverIn(){
        cancel2.setStyle("-fx-background-color: white; -fx-text-fill: black;-fx-background-radius: 5");
    }

    @FXML
    protected void Cancel2HoverOut(){
        cancel2.setStyle("-fx-background-color: gray; -fx-background-radius: 5");
    }

    @FXML
    protected void Cancel2() throws IOException {

        newStage.close();
        forStage.show();
    }
}
