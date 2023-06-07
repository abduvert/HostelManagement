package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class FloorRes {

    public VBox vBox;


    @FXML
    public void loadA() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("FloorRes.fxml"));
        Parent rr = loader1.load();
        try {
            String q = "select * from Floor_Resources";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FloorResRow.fxml"));
                Parent row = loader.load();

                FloorResRow r = loader.getController();

                r.id.setText(res.getString("Fresource_id"));
                r.name.setText(res.getString("Fresource_name"));
                r.category.setText(res.getString("Fresource_category"));
                r.condition.setText(res.getString("Fresource_conition"));

                FloorRes ro = loader1.getController();
                ro.vBox.getChildren().add(row);
            }
            stage.setScene(new Scene(rr));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
