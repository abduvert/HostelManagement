package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Types {
    public Label id = new Label();
    public Label type = new Label();
    public Label capacity = new Label();
    public TextField price1 = new TextField();



    public Button update = new Button();





    @FXML
    public void loadA() throws IOException {
    Stage stage = new Stage();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Types.fxml"));
            Parent rr = loader1.load();
        try {
            String q = "select * from Room_Types";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TypeRow.fxml"));
                Parent row = loader.load();

                Types r = loader.getController();
               r.id.setText(res.getString("Rtype_id"));
               r.capacity.setText(res.getString("Rtype_capacity"));
               r.type.setText(res.getString("Rtype_name"));
               r.price1.setText(res.getString("Rtype_price"));

               TypeRow type = loader1.getController();
               type.vbox.getChildren().add(row);
                System.out.println(res.getString("Rtype_price"));
            }
                stage.setScene(new Scene(rr));
                stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
