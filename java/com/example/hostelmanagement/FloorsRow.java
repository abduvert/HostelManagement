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

public class FloorsRow {

    public Label id;
    public Label name;
    public Label rooms;
    public Button resource;


    @FXML
    public void Details(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("FRrow.fxml"));   //misplaced the name in fxml so its the correct one
        Parent rr = loader1.load();
        FRres fRres = loader1.getController();


        try{
            String g = ((Button) event.getSource()).getId();
            System.out.println(g);
            String q = "select * from ResourceFloor sh\n" +
                    "where sh.floor_id in (select r.floor_id from Floors r where r.floor_id =" + g + ")";

            ResultSet res = HelloApplication.statement.executeQuery(q);


            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FRres.fxml"));
                Parent row = loader.load();
                FRrow r = loader.getController();

                r.name.setText(res.getString("Fresource_name"));
                r.quantity.setText(res.getString("quantity"));
                r.resId.setText(res.getString("Fresource_id"));
                fRres.id.setText(g);

                fRres.resV.getChildren().add(row);

            }
            stage.setScene(new Scene(rr));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
