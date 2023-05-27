package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {

    public static Stage stage1 = new Stage();
    public Scene scene1;
    public Button next1;
    public Button next2;
    public Button cancel1;
    public Button cancel2;
    public Button done;



    @FXML
    protected void Register(ActionEvent event) throws IOException {
        FXMLLoader reg1 = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        stage1.setScene(new Scene(reg1.load()));
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.show();
    }

    @FXML

    protected void Next1(ActionEvent backevent) throws IOException {

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