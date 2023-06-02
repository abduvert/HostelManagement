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

public class SRowController {
    public Label id;
    public Label name;
    public Label phoneNumber;
    public Label degree;
    public Label CGPA;
    public Button details;



    public Label st_id;
    public Text email;
    public Text CNIC;
    public Text religion;
    public Text batch;
    public Text dateofbirth;
    public Text address;

    Stage inform = new Stage();

    @FXML
    public void Details(ActionEvent event) throws IOException, SQLException {

        try {

            String g = ((Button) event.getSource()).getId();
            String q = "select * from Student where st_id ='" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println(g);

            if (res.next()) {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("StdLEFTPAGE.fxml"));


                Parent side = loader.load();

                SRowController gr = loader.getController();
                inform.setScene(new Scene(side));

                gr.address.setText(res.getString("s_houseNo") + ", " + res.getString("st_town") + ", " + res.getString("st_city"));
                gr.batch.setText(res.getString("batch"));
                gr.CNIC.setText(res.getString("st_CNICcode") + " - " + res.getString("st_CNICno"));
                gr.dateofbirth.setText(res.getString("st_dob"));
                gr.email.setText(res.getString("st_email"));
                gr.religion.setText(res.getString("religion"));


                inform.show();


            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
