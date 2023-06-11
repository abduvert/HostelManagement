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

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class Bills1 implements Initializable {

    public VBox entry = new VBox();
    public Label stdEnr;
    public Label dueDateON;
    public Label total;
    Date date,date2;
    public Button fineBut;
    public Button unpaidd;


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


                date = res.getDate("due_date");
                date2 = res.getDate("paid_date");
                row.id.setText(res.getString("allot_id"));
               row.comments.setText(res.getString("comments"));
                row.duedate.setText(date.toString());




                row.paidDate.setText(date2.toString());
                row.dues.setText(res.getString("dues"));
                row.fine.setText(res.getString("fine"));
                row.method.setText(res.getString("payment_method"));


                entry.getChildren().add(rr);
            }

            res.close();

            String q1 = "select count(allot_id) as counts from Bill";
            ResultSet qS = HelloApplication.statement.executeQuery(q1);

            if(qS.next())
            {
                stdEnr.setText(qS.getString("counts"));
            }

            qS.close();


            if(qS.isClosed()) {
                String q2 = "SELECT CAST(b.due_date AS DATE) AS due FROM Bill b WHERE b.allot_id = 'AL001'";
                ResultSet qW = HelloApplication.statement.executeQuery(q2);

                if (qW.next()) {
                    dueDateON.setText(qW.getString("due"));
                }


                qW.close();
            }

                String q3 = "select sum(dues) as total from Bill";
                ResultSet qV = HelloApplication.statement.executeQuery(q3);

                if (qV.next()) {
                    total.setText(qV.getString("total"));
                }

            qV.close();




        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAll();
    }


    @FXML
    public void unpaid() throws SQLException, IOException {

        String q ="select * from Bill b\n" +
                "where b.paid_date is null";

        ResultSet res = HelloApplication.statement.executeQuery(q);

        entry.getChildren().clear();
        while (res.next())
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BillRow1.fxml"));
            Parent rr = loader.load();


            BillsRow1 row = loader.getController();


            date = res.getDate("due_date");

            row.id.setText(res.getString("allot_id"));
            row.comments.setText(res.getString("comments"));
            row.duedate.setText(date.toString());

            row.paidDate.setText("UNPAID");
            row.dues.setText(res.getString("dues"));
            row.fine.setText(res.getString("fine"));
            row.method.setText(res.getString("payment_method"));


            entry.getChildren().add(rr);

        }


    }
    @FXML
    public void fineBut() throws SQLException, IOException {

        String q = "select * from BillShow\n" +
                "where fine <> 0.00";

        ResultSet res = HelloApplication.statement.executeQuery(q);


        entry.getChildren().clear();

        while (res.next()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BillRow1.fxml"));
            Parent rr = loader.load();


            BillsRow1 row = loader.getController();


            date = res.getDate("due_date");
            date2 = res.getDate("paid_date");
            row.id.setText(res.getString("allot_id"));
            row.comments.setText(res.getString("comments"));
            row.duedate.setText(date.toString());


            row.paidDate.setText(date2.toString());
            row.dues.setText(res.getString("dues"));
            row.fine.setText(res.getString("fine"));
            row.method.setText(res.getString("payment_method"));


            entry.getChildren().add(rr);
        }


    }

    @FXML
    public void typesHoverIn(){
        fineBut.setStyle("-fx-background-color: #505472 ;-fx-text-fill: white");
    }

    @FXML
    public void typesHoverOut(){
        fineBut.setStyle("-fx-background-color: white ;-fx-text-fill: #505472; -fx-border-color: #505472");
    }

    @FXML
    public void HoverIn(){
        unpaidd.setStyle("-fx-background-color: #505472 ;-fx-text-fill: white");
    }

    @FXML
    public void HoverOut(){
        unpaidd.setStyle("-fx-background-color: white ;-fx-text-fill: #505472; -fx-border-color: #505472");
    }



}
