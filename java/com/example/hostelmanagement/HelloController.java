package com.example.hostelmanagement;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController {

    public static Stage forStage;
    public static Stage newStage;

    public Button login;
    public Button forgot;
    public CheckBox showpass;
    public PasswordField password;
    public Stage mainstage;

    public Button done;
    public Button cancel;

    public Button update;
    public Button cancel2;
    public Scene scene;

    public Circle circle;






    @FXML
   protected void Login() throws IOException {
       Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomePage.fxml")));
       scene = new Scene(root);


       HelloApplication.stage.setScene(scene);



   }




   @FXML
   protected void ForgotPassword(ActionEvent event) throws IOException {

      FXMLLoader loder = new FXMLLoader(getClass().getResource("ForgotPass.fxml"));
      forStage = loder.load();
      forStage.initStyle(StageStyle.UNDECORATED);
      forStage.show();


   }


   @FXML
   protected void UpdateHoverIn(){
        update.setStyle("-fx-background-color: #9A71AD; -fx-background-radius: 5");
   }

   @FXML
   protected void UpdateHoverOut(){

       update.setStyle("-fx-background-color:  #32213A; -fx-background-radius: 5");

   }

   @FXML
   protected void Update(){
        newStage.close();
        Stage updated = new Stage();
        BorderPane upd = new BorderPane();
        Label ud = new Label("Your Password has been updated!");
        ud.setStyle("-fx-text-fill: #2D1E34");
        upd.setCenter(ud);
        updated.setScene(new Scene(upd,400,200));
        updated.show();
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
   @FXML
   protected void DoneHoverIn(){
        done.setStyle("-fx-background-color: #9A71AD; -fx-background-radius: 5");
   }


    @FXML
    protected void DoneHoverOut(){
        done.setStyle("-fx-background-color:   #32213A; -fx-background-radius: 5");
    }

    @FXML
    protected void Done() throws IOException {
        FXMLLoader loder = new FXMLLoader(getClass().getResource("NewPass.fxml"));
        newStage = loder.load();
        newStage.initStyle(StageStyle.UNDECORATED);
        forStage.close();
        newStage.show();
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
            password.setPromptText(password.getText());
            password.setText("");
        } else {
            password.setText(password.getPromptText());
            password.setPromptText("");
        }

    }



}