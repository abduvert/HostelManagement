package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Rooms implements Initializable {
    public VBox entry;

    public Button back;
    public Button rooms;
    public Button floors;
    public Button types;
    public Button resources;


    public BorderPane borderPane = new BorderPane();


    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));
    }

    @FXML
    public void add() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rooms.fxml"));
        HelloApplication.stage.setScene(new Scene(loader.load()));
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

        FXMLLoader vc = new FXMLLoader(getClass().getResource("Floors.fxml"));
        borderPane.setCenter(vc.load());

    }


    @FXML
    public void typesHoverIn(){
        types.setStyle("-fx-background-color: #505472 ;-fx-text-fill: white");
    }

    @FXML
    public void typesHoverOut(){
        types.setStyle("-fx-background-color: white ;-fx-text-fill: #505472; -fx-border-color: #505472");
    }

    @FXML
    public void ResHoverIn(){
        resources.setStyle("-fx-background-color: #505472 ;-fx-text-fill: white");
    }

    @FXML
    public void ResHoverOut(){
        resources.setStyle("-fx-background-color: white ;-fx-text-fill: #505472; -fx-border-color: #505472");
    }
    @FXML
    public void Roomtypes() throws IOException {

        Types type = new Types();
        type.loadA();
    }

    @FXML
    public void RoomResources() throws IOException {

        Resources row = new Resources();
        row.loadA();

    }



    @FXML
    public void loadRooms(){
        try {
            String q = "select * from RoomShow";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomsRow.fxml"));
                Parent row = loader.load();


                RoomRow r = loader.getController();
                r.roomId.setText(res.getString("r_id"));
                r.roomType.setText(res.getString("Rtype_name"));
                r.floorid.setText(res.getString("floor_id"));
                r.status.setText(res.getString("occ_status"));
                r.details.setId(res.getString("Rtype_id"));


                entry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadRooms();

    }
}
