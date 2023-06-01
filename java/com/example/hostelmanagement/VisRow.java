package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.sql.ResultSet;

public class VisRow {

    public Label st_id;
    public Label vis_id;
    public Label vis_Name;
    public Label Vrelation;
    public Label Vphone;
    public Label VCNIC;


    public Text v_id;
    public Text v_cnic;
    public Text v_relation;
    public Text v_student;


//
//    @FXML
//    public void Details(ActionEvent event)  {
//
//        try {
//            String g = ((Button) event.getSource()).getId();
//            String q = "select v_id,v_relation,v where st_id ='" + g + "'" ;
//            ResultSet res = HelloApplication.statement.executeQuery(q);
//
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Visitors.fxml"));
//            StudentController std = new StudentController();
//            GuardianRow side = loader.getController();
//            if (res.next()) {
//
//
//                side.detID.setText(null);
//                side.detID.setText(g);
//                side.detRelation.setText(null);
//                side.detRelation.setText(res.getString("g_relation"));
//                System.out.println(res.getString("g_relation"));
//                side.detADDRESS.setText(null);
//                side.detADDRESS.setText(res.getString("g_houseNo") + res.getString("g_town") + res.getString("g_city"));
//                side.detCNIC.setText(null);
//
//                side.detCNIC.setText(res.getString("g_CNICcode") + res.getString("g_CNICno"));
//
//
//            }
//            std.borderPane.setLeft(loader.load());
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
