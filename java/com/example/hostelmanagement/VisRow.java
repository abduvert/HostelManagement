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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VisRow {

    @FXML
    public Label st_id, vis_id, vis_Name, Vrelation, Vphone, v_id;
    @FXML
    public Button details;
    Stage inform = new Stage();

    @FXML
    public Text v_cnic, v_relation, v_student;



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

    public Button edit;
    public Button save;


    //update this page
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


                gr.detID.setText(res.getString("v_id"));

                gr.detRelation.setText(res.getString("v_relation"));

                gr.detCNICcode.setText(res.getString("v_CNICcode"));
                gr.detCNICno.setText(res.getString("v_CNICno"));

                gr.fname.setText(res.getString("st_firstName"));
                gr.lname.setText(res.getString("st_lastName"));

                gr.detPhonecode.setText(res.getString("v_PHNcode"));
                gr.detPhoneNo.setText(res.getString("v_PHNno"));


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
    protected  void Save(ActionEvent event){

        inform.close();

    }

}
