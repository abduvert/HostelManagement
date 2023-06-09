package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class BillsRow {
    public Button id;
    public Label name;


    @FXML
    public void Detail(ActionEvent event) throws IOException, SQLException {

        String g = ((Button) event.getSource()).getId();
        String b = "select * from Bill where allot_id = '" + g + "'";
        ResultSet resBill = HelloApplication.statement.executeQuery(b);



            FXMLLoader loade = new FXMLLoader(getClass().getResource("Bills.fxml"));
            Parent rw = loade.load();
            Bills rr = loade.getController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("BillCenter.fxml"));
        Parent row = loader.load();

        if(resBill.next()) {



            BillCenter bill = loader.getController();


            System.out.println(resBill.getString("dues"));
            Date date = resBill.getDate("due_date");
            bill.duedate.setText(date.toString());
            bill.dues.setText(resBill.getString("dues"));
            bill.fine.setText(resBill.getString("fine"));
            bill.comments.setText(resBill.getString("comments"));
            bill.method.setText(resBill.getString("payment_method"));
            rr.borderPane.setCenter(row);

            if (resBill.getString("paid_date") == null) {
                bill.paidDate.setText("UNPAID");
            } else {
                bill.paidDate.setText(resBill.getString("paid_date"));
            }
        }



    }
}
