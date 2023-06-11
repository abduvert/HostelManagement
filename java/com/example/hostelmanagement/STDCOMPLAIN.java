
package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class STDCOMPLAIN implements Initializable {


    public VBox vbox = new VBox();
    public Button compp;
    public BorderPane pane2;

    public void add(BorderPane pane) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("STDCOMPLAIN.fxml"));
        pane.setCenter(load.load());
    }


    @FXML
    public void typesHoverIn(){
        compp.setStyle("-fx-background-color: #505472 ;-fx-text-fill: white");
    }

    @FXML
    public void typesHoverOut(){
        compp.setStyle("-fx-background-color: white ;-fx-text-fill: #505472; -fx-border-color: #505472");
    }


    @FXML
    public void ComplainHere() throws IOException {
        FXMLLoader load =  new FXMLLoader(getClass().getResource("COMCENTER.fxml"));

        pane2.setCenter(load.load());

    }

    @FXML
    public void loadAll() throws SQLException, IOException {
        String q = "select * from Complaints where st_id = '" +  HelloController.storeID + "'";
        ResultSet res = HelloApplication.statement.executeQuery(q);

        while (res.next())
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("STDCOMROW.fxml"));
            Parent rr = loader.load();
            STDCOMROW r = loader.getController();

            r.cmpid.setText(res.getString("cmp_id"));
            r.complainn.setText(res.getString("complain"));
            r.statuss.setText(res.getString("status"));
            r.empid.setText(res.getString("emp_id"));

            vbox.getChildren().add(rr);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
