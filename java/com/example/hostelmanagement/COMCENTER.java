package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.hostelmanagement.HelloApplication.statement;

public class COMCENTER implements Initializable {

    public TextField stID;
    public TextField roomID;
    public TextField empID;
    public TextArea complainn;
    public Button submit;
    public Button cancel;

    public int newCMPid;
    @FXML
    public void CancelIN() {
        cancel.setStyle("-fx-background-color: red; -fx-text-fill: white");
    }


    @FXML
    public void CancelOUT() {
        cancel.setStyle("-fx-background-color: #9DA2AB ;-fx-text-fill: black;");
    }

    @FXML
    public void Cancel() {

    }


    public void Submit() throws SQLException, IOException {
        // Retrieve the maximum st_id value
        String maxStIdQuery = "SELECT MAX(cmp_id) FROM Complaints";
        ResultSet resultSet = statement.executeQuery(maxStIdQuery);
        resultSet.next();
        int maxEId = resultSet.getInt(1);

        System.out.println("old" + maxEId);

        newCMPid = maxEId + 1;

        resultSet.close();


        String InsertComp = "insert into Complaints values(" + newCMPid + ", '" + stID.getText() + "', " + roomID.getText() + ", '" + empID.getText() +
                "' , 'Open' , '" + complainn.getText() + "')";



        if(stID.getText().isEmpty() || complainn.getText().isEmpty() || empID.getText().isEmpty() || roomID.getText().isEmpty() )
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("A text field is empty. Complain has not been sent");
            a.show();
        }
        else {
           submit.setStyle("-fx-background-color: Green");
           submit.setText("Submitted");
            statement.executeQuery(InsertComp);
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
