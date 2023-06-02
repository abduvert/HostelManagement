package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GuardianRow {
    @FXML

    public Button details;
    public Label detID = new Label();
    public Text detRelation;
    public Text detCNIC;
    public Text detADDRESS;

    Stage inform = new Stage();

    public Label st_id;
    public Label guardianName;
    public Label g_Phone;
    public Label relation;
    public Label g_email;


    //issue with details button
    @FXML
    public void Details(ActionEvent event) throws IOException, SQLException {

        try {

            String g = ((Button) event.getSource()).getId();
            String q = "select g_CNICcode,g_CNICno,g_relation,g_houseNo,g_town,g_city from Guardian where st_id ='" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println(g);

            if (res.next()) {

                System.out.println(res.getString("g_relation"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Guardians2.fxml"));


                Parent side = loader.load();

                GuardianRow gr = loader.getController();
                inform.setScene(new Scene(side));
                gr.detID.setText(g);

                gr.detRelation.setText(res.getString("g_relation"));

                gr.detADDRESS.setText(res.getString("g_houseNo") + ", " + res.getString("g_town") + ", " +res.getString("g_city"));


                gr.detCNIC.setText(res.getString("g_CNICcode") + " - " + res.getString("g_CNICno"));


                inform.show();


            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    public void Close(){
        inform.close();
    }
}

