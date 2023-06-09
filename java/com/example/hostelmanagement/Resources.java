package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Resources {
    public VBox vbox;

    @FXML
    public void loadA() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("RoomResources.fxml"));
        Parent rr = loader1.load();
        try {
            String q = "select * from Room_Resources";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ResRow.fxml"));
                Parent row = loader.load();

                ResRow r = loader.getController();

                r.id.setText(res.getString("Rresource_id"));
                r.name.setText(res.getString("Rresource_name"));
                r.category.setText(res.getString("Rresource_category"));
                r.condition.setText(res.getString("Rresource_condition"));

                Resources ro = loader1.getController();
                ro.vbox.getChildren().add(row);
            }
            stage.setScene(new Scene(rr));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
