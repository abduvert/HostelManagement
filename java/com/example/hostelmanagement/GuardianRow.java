package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    public Label dett;
    public AnchorPane pane;
    public Label detID = new Label();
    public TextField fname;
    public TextField lname;

    public TextField detRelation;
    public TextField detCNICcode;
    public TextField detCNICno;
    public TextField detPhonecode;
    public TextField detPhoneNo;

    Stage inform = new Stage();

    public Label st_id;
    public Label guardianName;
    public Label g_Phone;
    public Label relation;
    public Label g_email;

    public Button edit;
    public Button save;




    //issue with details button
    @FXML
    public void Details(ActionEvent event) throws IOException, SQLException {

        try {

            String g = ((Button) event.getSource()).getId();
            String q = "select * from Guardian where st_id ='" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println(g);

            if (res.next()) {
                System.out.println(res.getString("g_relation"));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Guardians2.fxml"));
                Parent side = loader.load();

                GuardianRow gr = loader.getController();
                inform.setScene(new Scene(side));
                gr.detID.setText(g);
                gr.fname.setText(res.getString("g_firstName"));
                gr.lname.setText(res.getString("g_lastName"));
                gr.detRelation.setText(res.getString("g_relation"));
                gr.detPhonecode.setText(res.getString("g_PHNcode"));
                gr.detPhoneNo.setText(res.getString("g_PHNno"));
                gr.detCNICcode.setText(res.getString("g_CNICcode"));
                gr.detCNICno.setText(res.getString("g_CNICno"));

                inform.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Edit(){
        inform.setTitle("Edit details");
        save.setVisible(true);
        pane.setStyle("-fx-border-color: green;");
        fname.setStyle("-fx-border-color: #505472");
        lname.setStyle("-fx-border-color: #505472");
        detRelation.setStyle("-fx-border-color: #505472");
        detCNICno.setStyle("-fx-border-color: #505472");
        detCNICcode.setStyle("-fx-border-color: #505472");
        detPhoneNo.setStyle("-fx-border-color: #505472");
        detPhonecode.setStyle("-fx-border-color: #505472");
        dett.setText("Edit Guardian Details");

    }

    @FXML
    public void EditHoverin(){

        edit.setStyle("-fx-background-color: #505472; -fx-text-fill: white");
    }

    @FXML
    public void SaveHoverOut(){

        save.setStyle("-fx-background-color: transparent; -fx-text-fill: green; -fx-border-color: green;");
    }


    @FXML
    public void SaveHoverin(){

        save.setStyle("-fx-background-color: green; -fx-text-fill: white");
    }

    @FXML
    public void EditHoverOut(){

        edit.setStyle("-fx-background-color: transparent; -fx-text-fill: #505472; -fx-border-color: #505472;");
    }

    @FXML
    protected void Save(ActionEvent event){

                inform.close();

    }



}

