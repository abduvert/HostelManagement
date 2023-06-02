package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Rooms {
    public VBox entry;
    public Button back;
    public Button rooms;
    public Button floors;
    public BorderPane borderPane = new BorderPane();


    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));
    }

    @FXML
    public void RoomHoverIn(){

        rooms.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void RoomHoverOut(){

        rooms.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }

    @FXML
    public void Room() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rooms.fxml"));
        HelloApplication.stage.setScene(new Scene(loader.load()));

    }

    @FXML
    public void FloorHoverIn(){

        floors.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void FloorHoverOut() {

        floors.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");
    }


    @FXML
    public void Floor(ActionEvent event) throws IOException {
        FXMLLoader vc = new FXMLLoader(getClass().getResource(".fxml"));
        borderPane.setCenter(vc.load());
    }




}
