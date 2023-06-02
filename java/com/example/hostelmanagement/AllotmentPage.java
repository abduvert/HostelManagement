package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AllotmentPage implements Initializable {


    public Button room;
    public VBox entry;
    public VBox entry2;
    public BorderPane borderPane = new BorderPane();
    public Button back;
    public Button allot;
    public Button vacat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAll();
    }


    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));

    }

    @FXML
    public void add(ActionEvent event) throws IOException {

        FXMLLoader allot = new FXMLLoader(getClass().getResource("AllotmentPage.fxml"));
        HelloApplication.stage.setScene(new Scene(allot.load()));
        HelloApplication.stage.setTitle("Allotment");
    }

    @FXML
    public void AllotHoverIn(){

        allot.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void AllotHoverOut(){

        allot.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }

    @FXML
    public void Allotment() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotmentPage.fxml"));
        HelloApplication.stage.setScene(new Scene(loader.load()));

    }

    @FXML
    public void VacatHoverIn(){

        vacat.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void VacatHoverOut() {

        vacat.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");
    }


    @FXML
    public void Vacat(ActionEvent event) throws IOException {
        FXMLLoader vc = new FXMLLoader(getClass().getResource("Vacating.fxml"));
        borderPane.setCenter(vc.load());
    }


    @FXML
    public void RoomHoverIn(){

        room.setStyle("-fx-background-color:  #A7A5C6; -fx-text-fill: black;");

    }

    @FXML
    public void RoomHoverOut(){

        room.setStyle("-fx-background-color:  #505472; -fx-text-fill: white;");

    }
    @FXML
    public void loadAll(){
        try {
            String q = "select * from Allotment";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println("before");
            while (res.next()) {

                System.out.println("HERE");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotROW.fxml"));
                Parent row = loader.load();

                AllotRow al = loader.getController();

               al.r_id.setText(res.getString("r_id"));
               al.st_id.setText(res.getString("st_id"));
               al.allotid.setText(res.getString("allot_id"));
               al.year.setText(res.getString("allot_year"));
               al.date.setText(res.getString("allot_date"));
               al.month.setText(res.getString("allot_month"));



               entry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

