package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisRow {

    public Label st_id;
    public Label vis_id;
    public Label vis_Name;
    public Label Vrelation;
    public Label Vphone;

    public Button details;

    Stage inform = new Stage();
    public Label v_id;
    public Text v_cnic;
    public Text v_relation;
    public Text v_student;



    @FXML
    public void Details(ActionEvent event) throws IOException, SQLException {

        try {

            String g = ((Button) event.getSource()).getId();
            String q = "select * from VisShow where st_id ='" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println(g);

            if (res.next()) {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("Visitors2.fxml"));


                Parent side = loader.load();

                VisRow gr = loader.getController();
                inform.setScene(new Scene(side));
                gr.v_id.setText(res.getString("v_id"));

                gr.v_relation.setText(res.getString("v_relation"));

                gr.v_cnic.setText(res.getString("v_CNICcode") + " - " + res.getString("v_CNICno"));

                gr.v_student.setText(res.getString("st_firstName") + " " + res.getString("st_lastName"));

                inform.show();


            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
