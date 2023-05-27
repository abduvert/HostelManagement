package com.example.hostelmanagement;

import javafx.beans.NamedArg;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class StudentController {
    public AnchorPane anchorPane;
    public Button student;
    public Button guardians;
    public Button visitors;
    public VBox entries;

    public Button register;
    public Button back;
    public BorderPane borderPane;


    @FXML
    public void add(ActionEvent event) throws IOException {

       FXMLLoader stud = new FXMLLoader(getClass().getResource("StdPage.fxml"));
       HelloApplication.stage.setScene(new Scene(stud.load()));
       HelloApplication.stage.setMaximized(true);

    }

    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));
    }

    @FXML
    public void StudentsHoverIn(){

        student.setStyle("-fx-background-color:   #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void StudentsHoverOut(){

        student.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }

    @FXML
    protected void Student() throws IOException {

        student.setStyle("-fx-background-color:   #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");
        FXMLLoader stud = new FXMLLoader(getClass().getResource("StdPage.fxml"));
        HelloApplication.stage.setScene(new Scene(stud.load()));
        HelloApplication.stage.setMaximized(true);


    }



    @FXML
    public void GuardiansHoverIn(){

      guardians.setStyle("-fx-background-color:   #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void GuardiansHoverOut(){

        guardians.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }

    @FXML
    protected void Guardians() throws IOException {
        guardians.setStyle("-fx-background-color:   #A7A5C6;");
        FXMLLoader guard = new FXMLLoader(getClass().getResource("Guardians.fxml"));
        FXMLLoader guard2 = new FXMLLoader(getClass().getResource("Guardians2.fxml"));

        borderPane.setCenter(guard.load());
        borderPane.setLeft(guard2.load());


    }


    @FXML
    public void VisitorsHoverIn(){

        visitors.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void VisitorsHoverOut(){

        visitors.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }


    @FXML
    protected void Visitors() throws IOException {
        visitors.setStyle("-fx-background-color:   #A7A5C6;");
        FXMLLoader vis = new FXMLLoader(getClass().getResource("Visitors.fxml"));
        FXMLLoader vis2 = new FXMLLoader(getClass().getResource("Visitors2.fxml"));
        borderPane.setCenter(vis.load());
        borderPane.setLeft(vis2.load());
    }
    @FXML
    public void RegHoverIn(){

        register.setStyle("-fx-background-color:  #A7A5C6; -fx-text-fill: black;");

    }

    @FXML
    public void RegHoverOut(){

        register.setStyle("-fx-background-color:  #505472; -fx-text-fill: white;");

    }


    @FXML
    protected void Register(ActionEvent event) throws IOException {
        RegisterController reg = new RegisterController();
        reg.Register(event);
    }

}

