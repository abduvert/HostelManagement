package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Complaints implements Initializable {

    public VBox comEntry;
    public Button stat;


        Stage st = new Stage();
    @FXML
    public void add(ActionEvent event) throws IOException {
        FXMLLoader complain = new FXMLLoader(getClass().getResource("Complaints.fxml"));
        HelloApplication.stage.setScene(new Scene(complain.load()));
        HelloApplication.stage.setMaximized(true);
    }

    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));
    }


    @FXML
    public void close(){
        st.close();
    }


    @FXML
    public void StatusHoverIn(){

        stat.setStyle("-fx-background-color:  #A7A5C6; -fx-text-fill: black;");

    }
    @FXML
    public void StatusHoverOut(){

        stat.setStyle("-fx-background-color:  #505472; -fx-text-fill: white;");

    }



    @FXML
    public void Status() throws IOException {

        FXMLLoader cpm = new FXMLLoader(getClass().getResource("StatusUpdate.fxml"));
        st.setScene(new Scene(cpm.load()));
        st.initStyle(StageStyle.UNDECORATED);
        st.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAll();
    }

    @FXML
    public void loadAll(){
        try {
            String q = "select * from Complaints_show";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println("before");
            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ComRow.fxml"));
                Parent row = loader.load();

                ComRow comRow = loader.getController();

                comRow.comID.setText(res.getString("cmp_id"));
                comRow.empName.setText(res.getString("emp_firstName") + res.getString("emp_lastName"));
                comRow.r_id.setText(res.getString("r_id"));
                comRow.complain.setText(res.getString("complain"));
                comRow.status.setText(res.getString("status"));



                comEntry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
