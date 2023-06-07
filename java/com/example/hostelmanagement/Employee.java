package com.example.hostelmanagement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ResourceBundle;

public class Employee implements Initializable {
    @FXML
    private TextField searchTextfield, salary_start, salary_end;
    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();
    @FXML
    public VBox empEntry;
    private CallableStatement callableStatement;

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
                 empRow.empName.setText(res.getString("emp_firstName") + " " + res.getString("emp_lastName"));
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

    @FXML
    protected void Search(){
        empEntry.getChildren().clear();
        String fullName = searchTextfield.getText();

        try {
            if(fullName.contains(" ")){
                callableStatement = HelloApplication.statement.getConnection().prepareCall("{call unconcatenate_name(?, ?, ?)}");
                callableStatement.setString(1, fullName);
                callableStatement.registerOutParameter(2, Types.VARCHAR);
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.execute();

                String stFirstName = callableStatement.getString(2);
                String stLastName = callableStatement.getString(3);

                stLastName = stLastName.trim().replaceAll("^\\s+", "");

                String query2 = "select * from Employees where emp_firstName = '" + stFirstName +
                        "' and emp_lastName = '" + stLastName + "'";
                ResultSet resultSet = HelloApplication.statement.executeQuery(query2);

                while (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpRow.fxml"));
                    Parent row = loader.load();

                    EmpRow empRow = loader.getController();

                    empRow.emp_id.setText(resultSet.getString("emp_id"));
                    empRow.empCNIC.setText(resultSet.getString("emp_CNICcode") + resultSet.getString("emp_CNICno"));
                    empRow.empName.setText(resultSet.getString("emp_firstName") + " " + resultSet.getString("emp_lastName"));
                    empRow.empGender.setText(resultSet.getString("emp_gender"));
                    empRow.empPhone.setText(resultSet.getString("emp_PHNcode") + resultSet.getString("emp_PHNno"));
                    empRow.empRole.setText(resultSet.getString("emp_role"));
                    empRow.empSalary.setText(resultSet.getString("emp_salary"));

                    empEntry.getChildren().add(row);
                }
            }
            else{
                String query = "select * from Employees where emp_firstName = '" + searchTextfield.getText() +
                        "'";
                ResultSet res = HelloApplication.statement.executeQuery(query);

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpRow.fxml"));
                    Parent row = loader.load();

                    EmpRow empRow = loader.getController();

                    empRow.emp_id.setText(res.getString("emp_id"));
                    empRow.empCNIC.setText(res.getString("emp_CNICcode") + res.getString("emp_CNICno"));
                    empRow.empName.setText(res.getString("emp_firstName") + " " + res.getString("emp_lastName"));
                    empRow.empGender.setText(res.getString("emp_gender"));
                    empRow.empPhone.setText(res.getString("emp_PHNcode") + res.getString("emp_PHNno"));
                    empRow.empRole.setText(res.getString("emp_role"));
                    empRow.empSalary.setText(res.getString("emp_salary"));

                    empEntry.getChildren().add(row);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void Filter(){
        ObservableList<String> items = FXCollections.observableArrayList();
        try {
            String q = "select * from Employees";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                items.add(res.getString("emp_role"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);
        comboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = comboBox.getEditor();
            final String selected = (String)comboBox.getSelectionModel().getSelectedItem();
            Platform.runLater(() -> {
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });
        empEntry.getChildren().clear();
        comboBox.setItems(filteredItems);

        try {
            String selectedRole = comboBox.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM Employees WHERE emp_role = '" + selectedRole + "'";
            ResultSet resultSet = HelloApplication.statement.executeQuery(query);

            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpRow.fxml"));
                Parent row = loader.load();

                EmpRow empRow = loader.getController();

                empRow.emp_id.setText(resultSet.getString("emp_id"));
                empRow.empCNIC.setText(resultSet.getString("emp_CNICcode") + resultSet.getString("emp_CNICno"));
                empRow.empName.setText(resultSet.getString("emp_firstName") + " " + resultSet.getString("emp_lastName"));
                empRow.empGender.setText(resultSet.getString("emp_gender"));
                empRow.empPhone.setText(resultSet.getString("emp_PHNcode") + resultSet.getString("emp_PHNno"));
                empRow.empRole.setText(resultSet.getString("emp_role"));
                empRow.empSalary.setText(resultSet.getString("emp_salary"));

                empEntry.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void Apply(){
        try{
            String query = "select * from Employees where emp_salary between " + salary_start.getText() +
                    " and " + salary_end.getText();
            ResultSet resultSet = HelloApplication.statement.executeQuery(query);

            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EmpRow.fxml"));
                Parent row = loader.load();

                EmpRow empRow = loader.getController();

                empRow.emp_id.setText(resultSet.getString("emp_id"));
                empRow.empCNIC.setText(resultSet.getString("emp_CNICcode") + resultSet.getString("emp_CNICno"));
                empRow.empName.setText(resultSet.getString("emp_firstName") + " " + resultSet.getString("emp_lastName"));
                empRow.empGender.setText(resultSet.getString("emp_gender"));
                empRow.empPhone.setText(resultSet.getString("emp_PHNcode") + resultSet.getString("emp_PHNno"));
                empRow.empRole.setText(resultSet.getString("emp_role"));
                empRow.empSalary.setText(resultSet.getString("emp_salary"));

                empEntry.getChildren().add(row);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



    }
}
