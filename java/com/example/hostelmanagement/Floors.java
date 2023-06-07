package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Floors implements Initializable {
    public VBox entry;
    public Button resources;


    @FXML
    public void ResHoverIn(){
        resources.setStyle("-fx-background-color: #505472 ;-fx-text-fill: white");
    }

    @FXML
    public void ResHoverOut(){
        resources.setStyle("-fx-background-color: white ;-fx-text-fill: #505472; -fx-border-color: #505472");
    }

    @FXML
    public void loadFloors(){
        try {
            String q = "select * from Floors";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FloorsRow.fxml"));
                Parent row = loader.load();


                FloorsRow r = loader.getController();

                r.id.setText(res.getString("floor_id"));
                r.name.setText(res.getString("floor_name"));
                r.rooms.setText(res.getString("total_rooms"));
                r.resource.setId(res.getString("floor_id"));


                entry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @FXML
    public void Resources() throws IOException {
            FloorRes res =  new FloorRes();
            res.loadA();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFloors();
    }
}
