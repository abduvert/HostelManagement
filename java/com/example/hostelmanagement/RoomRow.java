package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class RoomRow {

    public Button details;
    public Label roomId;
    public Label roomType;
    public Label floorid;
    public Label status;

    @FXML
    public void Details(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("RoomTypeRes.fxml"));
        Parent rr = loader1.load();
        RTres room = loader1.getController();


        try{
            String g = ((Button) event.getSource()).getId();
            System.out.println(g);
            String q = "select * from ResourceShow sh\n" +
                    "where sh.Rtype_id in (select r.Rtype_id from Rooms r where r.r_id = " + g + ")";

            ResultSet res = HelloApplication.statement.executeQuery(q);


            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RRrow.fxml"));
                Parent row = loader.load();
                RRrow r = loader.getController();

                r.id.setText(res.getString("Rresource_id"));
                r.name.setText(res.getString("Rresource_name"));
                r.quantity.setText(res.getString("Rquantity"));
                room.type.setText(g);
                room.resV.getChildren().add(row);


            }
                stage.setScene(new Scene(rr));
                stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
