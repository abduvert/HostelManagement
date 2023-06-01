package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class GuardSide {
    public Button details;
    public Label detID;
    public Text detRelation;
    public Text detCNIC;
    public Text detADDRESS;


    //issue with details button
    @FXML
    public void Details(ActionEvent event)  {

        try {
            String g = ((Button) event.getSource()).getId();
            String q = "select g_CNICcode,g_CNICno,g_relation,g_houseNo,g_town,g_city from Guardian where st_id ='" + g + "'" ;
            ResultSet res = HelloApplication.statement.executeQuery(q);

            if (res.next()) {



                detID.setText(null);
                detID.setText(g);
                detRelation.setText(null);
                detRelation.setText(res.getString("g_relation"));
                System.out.println(res.getString("g_relation"));
                detADDRESS.setText(null);
                detADDRESS.setText(res.getString("g_houseNo") + res.getString("g_town") + res.getString("g_city"));
                detCNIC.setText(null);

                detCNIC.setText(res.getString("g_CNICcode") + res.getString("g_CNICno"));

            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
