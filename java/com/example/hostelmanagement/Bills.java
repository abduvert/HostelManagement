package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.ResultSet;

public class Bills {


    public VBox entry = new VBox();

    public VBox main = new VBox();
    public BorderPane borderPane = new BorderPane();


    @FXML
    public void add() throws IOException {
        loadAll();
    }

    @FXML
    public void loadAll() throws IOException {
        try {
            String bill = "select * from BillShow";
            ResultSet resBill = HelloApplication.statement.executeQuery(bill);

        FXMLLoader loade = new FXMLLoader(getClass().getResource("Bills.fxml"));
        Parent rw = loade.load();
        Bills rr = loade.getController();

        while(resBill.next())
        {
            FXMLLoader load = new FXMLLoader(getClass().getResource("BillRow.fxml"));
            Parent row = load.load();

            BillsRow r = load.getController();

            r.id.setText(resBill.getString("allot_id"));
            r.name.setText(resBill.getString("st_firstName") + resBill.getString("st_lastName"));
            r.id.setId(resBill.getString("allot_id"));

            rr.entry.getChildren().add(row);
        }
        HelloApplication.stage.setScene(new Scene(rw));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
