package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Employee implements Initializable {


    public VBox empEntry;

    @FXML
    public void add(ActionEvent event) throws IOException {
        FXMLLoader employ = new FXMLLoader(getClass().getResource("EmployeePage.fxml"));
        HelloApplication.stage.setScene(new Scene(employ.load()));
        HelloApplication.stage.setMaximized(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       loadAll();
    }

    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));

    }

    @FXML
    public void loadAll(){
        try {
            String q = "select * from Employees";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            System.out.println("before");
            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpRow.fxml"));
                Parent row = loader.load();

                 EmpRow empRow = loader.getController();


                 empRow.emp_id.setText(res.getString("emp_id"));
                 empRow.empCNIC.setText(res.getString("emp_CNICcode") + res.getString("emp_CNICno"));
                 empRow.empName.setText(res.getString("emp_firstName") + res.getString("emp_lastName"));
                 empRow.empGender.setText(res.getString("emp_gender"));
                 empRow.empPhone.setText(res.getString("emp_PHNcode") + res.getString("emp_PHNno"));
                 empRow.empRole.setText(res.getString("emp_role"));
                 empRow.empSalary.setText(res.getString("emp_salary"));




                empEntry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
