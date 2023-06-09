package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class Bills1 implements Initializable {

    public VBox entry = new VBox();


    @FXML
    public void add() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Bill1.fxml"));
        Parent row = loader.load();
        HelloApplication.stage.setScene(new Scene(row));
    }


    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));

    }
    @FXML
    public void loadAll(){
        try {
            String q = "select * from BillShow";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println("before");
            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("BillRow1.fxml"));
                Parent rr = loader.load();


                BillsRow1 row = loader.getController();


                Date date = res.getDate("due_date");
                Date date2 = res.getDate("paid_date");
                row.id.setText(res.getString("allot_id"));
                row.comments.setText(res.getString("comments"));
                row.duedate.setText(date.toString());
                row.paidDate.setText(date2.toString());
                row.dues.setText(res.getString("dues"));
                row.fine.setText(res.getString("fine"));
                row.method.setText(res.getString("payment_method"));



                entry.getChildren().add(rr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAll();
    }
}
