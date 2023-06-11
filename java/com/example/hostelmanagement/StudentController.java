package com.example.hostelmanagement;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.sql.*;
import java.sql.Types;

public class StudentController {
    public AnchorPane anchorPane;
    @FXML
    public Button student, visitors, guardians, register, back;
    public VBox entries = new VBox();
    public VBox entries2 = new VBox();
    public VBox entries3 = new VBox();
    public BorderPane borderPane;
    @FXML
    public ComboBox<String> comboBox = new ComboBox<>();
    ObservableList<String> st_items = FXCollections.observableArrayList();
    @FXML
    public ComboBox<String> comboBox2 = new ComboBox<>();
    ObservableList<String> g_items = FXCollections.observableArrayList();
    @FXML
    public ComboBox<String> comboBox3 = new ComboBox<>();
    ObservableList<String> v_items = FXCollections.observableArrayList();

    @FXML
    public TextField st_search_textfield, guardian_search_textfield, vis_search_textfield;
    private CallableStatement callableStatement;

    @FXML
    public void add(ActionEvent event) throws IOException {

       FXMLLoader stud = new FXMLLoader(getClass().getResource("StdPage.fxml"));
       HelloApplication.stage.setScene(new Scene(stud.load()));
       HelloApplication.stage.setMaximized(true);
    }

    @FXML
    public void initialize(){
        loadAllProducts();
        Guardload();
        VisLoad();

        //to fill values in student filter comboox
        try {
            String q = "select DISTINCT degree from Student";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                st_items.add(res.getString("degree"));
            }
            comboBox.setItems(st_items);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //to fill comboBox2 in guardians
        try {
            String q2 = "select DISTINCT g_city from Guardian";
            ResultSet res2 = HelloApplication.statement.executeQuery(q2);

            while (res2.next()) {
                g_items.add(res2.getString("g_city"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //to fill comboBox3 in visitors
        try {
            String q = "select distinct v_relation from Visitors";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                v_items.add(res.getString("v_relation"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));
        HelloApplication.stage.setMaximized(true);
    }

    @FXML
    public void StudentsHoverIn(){
        student.setStyle("-fx-background-color:   #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");
    }

    @FXML
    public void StudentsHoverOut(){
        student.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");
    }

    @FXML
    protected void Student() throws IOException {
        //student.setStyle("-fx-background-color:   #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");
        FXMLLoader stud = new FXMLLoader(getClass().getResource("StdPage.fxml"));
        HelloApplication.stage.setScene(new Scene(stud.load()));
        HelloApplication.stage.setMaximized(true);
    }
    @FXML
    public void GuardiansHoverIn(){
        guardians.setStyle("-fx-background-color:   #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");
    }

    @FXML
    public void GuardiansHoverOut(){
        guardians.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");
    }

    @FXML
    protected void Guardians() throws IOException {
        //guardians.setStyle("-fx-background-color:   #A7A5C6;");
        FXMLLoader guard = new FXMLLoader(getClass().getResource("Guardians.fxml"));
        FXMLLoader guard2 = new FXMLLoader(getClass().getResource("Guardians2.fxml"));

        borderPane.setCenter(guard.load());
        borderPane.setLeft(null);
    }

    @FXML
    public void VisitorsHoverIn(){

        visitors.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void VisitorsHoverOut(){

        visitors.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }

    @FXML
    protected void Visitors() throws IOException {
//        visitors.setStyle("-fx-background-color:   #A7A5C6;");
        FXMLLoader vis = new FXMLLoader(getClass().getResource("Visitors.fxml"));
        FXMLLoader vis2 = new FXMLLoader(getClass().getResource("Visitors2.fxml"));
        borderPane.setCenter(vis.load());
        borderPane.setLeft(null);

    }

    @FXML
    public void RegHoverIn(){

        register.setStyle("-fx-background-color:  #A7A5C6; -fx-text-fill: black;");

    }

    @FXML
    public void RegHoverOut(){

        register.setStyle("-fx-background-color:  #505472; -fx-text-fill: white;");

    }

    @FXML
    protected void Register(ActionEvent event) throws IOException {
        RegisterController reg = new RegisterController();
        reg.Register(event);
    }

    public void loadAllProducts() {
        try {
            String q = "select * from Student";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Std_Row.fxml"));
                Parent row = loader.load();

                SRowController prc = loader.getController();
                prc.id.setText(res.getString("st_id"));
                prc.name.setText(res.getString("st_firstName") + " " + res.getString("st_lastName"));
                prc.phoneNumber.setText(res.getString("st_PHNcode") + res.getString("st_PHNno"));
                prc.degree.setText(res.getString("degree"));
                prc.CGPA.setText(res.getString("cgpa"));

                prc.details.setId(res.getString("st_id"));

                entries.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Guardload() {
        try {
            String q = "select * from Guardian";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GrdROW.fxml"));
                Parent row = loader.load();

                GuardianRow gRow = loader.getController();
                gRow.st_id.setText(res.getString("st_id"));
                gRow.guardianName.setText(res.getString("g_firstName") + " " + res.getString("g_lastName"));
                gRow.g_Phone.setText(res.getString("g_PHNcode") + res.getString("g_PHNno"));
                gRow.relation.setText(res.getString("g_relation"));
                gRow.g_email.setText(res.getString("g_email"));
                gRow.details.setId(res.getString("st_id"));

                entries2.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void VisLoad() {
        try {
            String q = "select * from Visitors";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VisRow.fxml"));
                Parent row = loader.load();

                VisRow visRow = loader.getController();
                visRow.st_id.setText(res.getString("st_id"));
                visRow.vis_Name.setText(res.getString("v_firstName") + " " + res.getString("v_lastName"));
                visRow.Vphone.setText(res.getString("v_PHNcode") + res.getString("v_PHNno"));
                visRow.Vrelation.setText(res.getString("v_relation"));
                visRow.vis_id.setText(res.getString("v_id"));
                visRow.details.setId(res.getString("st_id"));

                entries3.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    protected void st_Search(){
        String fullName = st_search_textfield.getText();

        try {
            if(fullName.contains(" ")){
                callableStatement = HelloApplication.statement.getConnection().prepareCall("{call unconcatenate_name(?, ?, ?)}");
                callableStatement.setString(1, fullName);
                callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
                callableStatement.execute();

                // Retrieve the unconcatenated names
                String stFirstName = callableStatement.getString(2);
                String stLastName = callableStatement.getString(3);

                // Remove leading spaces using trim()
                stLastName = stLastName.trim().replaceAll("^\\s+", "");

                String query2 = "select * from Student where st_firstName = '" + stFirstName +
                        "' and st_lastName = '" + stLastName + "'";
                ResultSet res2 = HelloApplication.statement.executeQuery(query2);
                entries.getChildren().clear();

                while (res2.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Std_Row.fxml"));
                    Parent row = loader.load();

                    SRowController prc = loader.getController();
                    prc.id.setText(res2.getString("st_id"));
                    prc.name.setText(res2.getString("st_firstName") + " " + res2.getString("st_lastName"));
                    prc.phoneNumber.setText(res2.getString("st_PHNcode") + res2.getString("st_PHNno"));
                    prc.degree.setText(res2.getString("degree"));
                    prc.CGPA.setText(res2.getString("cgpa"));

                    prc.details.setId(res2.getString("st_id"));

                    entries.getChildren().add(row);
                }
            }
            else{
                String query = "select * from Student where st_firstName = '" + st_search_textfield.getText() +
                            "'";
                ResultSet res = HelloApplication.statement.executeQuery(query);
                entries.getChildren().clear();

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Std_Row.fxml"));
                    Parent row = loader.load();

                    SRowController prc = loader.getController();
                    prc.id.setText(res.getString("st_id"));
                    prc.name.setText(res.getString("st_firstName") + " " + res.getString("st_lastName"));
                    prc.phoneNumber.setText(res.getString("st_PHNcode") + res.getString("st_PHNno"));
                    prc.degree.setText(res.getString("degree"));
                    prc.CGPA.setText(res.getString("cgpa"));

                    prc.details.setId(res.getString("st_id"));

                    entries.getChildren().add(row);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void st_Filter(){

        FilteredList<String> filteredItems = new FilteredList<String>(st_items, p -> true);
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
        entries.getChildren().clear();
        comboBox.setItems(filteredItems);

        try {
            String selectedDegree = comboBox.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM Student WHERE degree = '" + selectedDegree + "'";

            ResultSet resultSet = HelloApplication.statement.executeQuery(query);
            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Std_Row.fxml"));
                Parent row = loader.load();

                SRowController prc = loader.getController();
                prc.id.setText(resultSet.getString("st_id"));
                prc.name.setText(resultSet.getString("st_firstName") + " " + resultSet.getString("st_lastName"));
                prc.phoneNumber.setText(resultSet.getString("st_PHNcode") + resultSet.getString("st_PHNno"));
                prc.degree.setText(resultSet.getString("degree"));
                prc.CGPA.setText(resultSet.getString("cgpa"));

                prc.details.setId(resultSet.getString("st_id"));

                entries.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void guardian_Search(){
        String fullName = guardian_search_textfield.getText();

        try {
            if(fullName.contains(" ")){
                callableStatement = HelloApplication.statement.getConnection().prepareCall("{call unconcatenate_name(?, ?, ?)}");
                callableStatement.setString(1, fullName);
                callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                callableStatement.registerOutParameter(3, Types.VARCHAR);
                callableStatement.execute();

                // Retrieve the unconcatenated names
                String g_FirstName = callableStatement.getString(2);
                String g_LastName = callableStatement.getString(3);

                // Remove leading spaces using trim()
                g_LastName = g_LastName.trim().replaceAll("^\\s+", "");

                String query = "select * from Guardian where g_firstName = '" + g_FirstName +
                        "' and g_lastName = '" + g_LastName + "'";
                ResultSet res = HelloApplication.statement.executeQuery(query);
                entries2.getChildren().clear();

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GrdROW.fxml"));
                    Parent row = loader.load();

                    GuardianRow gRow = loader.getController();
                    gRow.st_id.setText(res.getString("st_id"));
                    gRow.guardianName.setText(res.getString("g_firstName") + " " + res.getString("g_lastName"));
                    gRow.g_Phone.setText(res.getString("g_PHNcode") + res.getString("g_PHNno"));
                    gRow.relation.setText(res.getString("g_relation"));
                    gRow.g_email.setText(res.getString("g_email"));
                    gRow.details.setId(res.getString("st_id"));

                    entries2.getChildren().add(row);
                }
            }
            else{
                String query = "select * from Guardian where g_firstName = '" + guardian_search_textfield.getText() +
                        "'";
                ResultSet resultSet = HelloApplication.statement.executeQuery(query);
                entries2.getChildren().clear();

                while (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("GrdROW.fxml"));
                    Parent row = loader.load();

                    GuardianRow gRow = loader.getController();
                    gRow.st_id.setText(resultSet.getString("st_id"));
                    gRow.guardianName.setText(resultSet.getString("g_firstName") + " " + " " + resultSet.getString("g_lastName"));
                    gRow.g_Phone.setText(resultSet.getString("g_PHNcode") + resultSet.getString("g_PHNno"));
                    gRow.relation.setText(resultSet.getString("g_relation"));
                    gRow.g_email.setText(resultSet.getString("g_email"));
                    gRow.details.setId(resultSet.getString("st_id"));

                    entries2.getChildren().add(row);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    protected void g_Filter(){
        FilteredList<String> filteredItems = new FilteredList<String>(g_items, p -> true);
        comboBox2.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = comboBox2.getEditor();
            final String selected = (String)comboBox2.getSelectionModel().getSelectedItem();
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
        entries2.getChildren().clear();
        comboBox2.setItems(filteredItems);

        try {
            String selectedCity = comboBox2.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM Guardian WHERE g_city = '" + selectedCity + "'";

            ResultSet resultSet = HelloApplication.statement.executeQuery(query);
            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("GrdROW.fxml"));
                Parent row = loader.load();

                GuardianRow gRow = loader.getController();
                gRow.st_id.setText(resultSet.getString("st_id"));
                gRow.guardianName.setText(resultSet.getString("g_firstName") + " " + resultSet.getString("g_lastName"));
                gRow.g_Phone.setText(resultSet.getString("g_PHNcode") + resultSet.getString("g_PHNno"));
                gRow.relation.setText(resultSet.getString("g_relation"));
                gRow.g_email.setText(resultSet.getString("g_email"));
                gRow.details.setId(resultSet.getString("st_id"));

                entries2.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    protected void Vis_Search(){
        String fullName = vis_search_textfield.getText();

        try {
            if(fullName.contains(" ")){
                callableStatement = HelloApplication.statement.getConnection().prepareCall("{call unconcatenate_name(?, ?, ?)}");
                callableStatement.setString(1, fullName);
                callableStatement.registerOutParameter(2, java.sql.Types.VARCHAR);
                callableStatement.registerOutParameter(3, java.sql.Types.VARCHAR);
                callableStatement.execute();

                // Retrieve the unconcatenated names
                String v_FirstName = callableStatement.getString(2);
                String v_LastName = callableStatement.getString(3);

                // Remove leading spaces using trim()
                v_LastName = v_LastName.trim().replaceAll("^\\s+", "");

                String query = "select * from Visitors where v_firstName = '" + v_FirstName +
                        "' and v_lastName = '" + v_LastName + "'";
                ResultSet res = HelloApplication.statement.executeQuery(query);
                entries3.getChildren().clear();

                while (res.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VisRow.fxml"));
                    Parent row = loader.load();

                    VisRow visRow = loader.getController();
                    visRow.st_id.setText(res.getString("st_id"));
                    visRow.vis_Name.setText(res.getString("v_firstName") + " " + res.getString("v_lastName"));
                    visRow.Vphone.setText(res.getString("v_PHNcode") + res.getString("v_PHNno"));
                    visRow.Vrelation.setText(res.getString("v_relation"));
                    visRow.vis_id.setText(res.getString("v_id"));
                    visRow.details.setId(res.getString("st_id"));

                    entries3.getChildren().add(row);
                }
            }
            else{
                String query = "select * from Visitors where v_firstName = '" + vis_search_textfield.getText() +
                        "'";
                ResultSet resultSet = HelloApplication.statement.executeQuery(query);
                entries3.getChildren().clear();

                while (resultSet.next()) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VisRow.fxml"));
                    Parent row = loader.load();

                    VisRow visRow = loader.getController();
                    visRow.st_id.setText(resultSet.getString("st_id"));
                    visRow.vis_Name.setText(resultSet.getString("v_firstName") + " " + resultSet.getString("v_lastName"));
                    visRow.Vphone.setText(resultSet.getString("v_PHNcode") + resultSet.getString("v_PHNno"));
                    visRow.Vrelation.setText(resultSet.getString("v_relation"));
                    visRow.vis_id.setText(resultSet.getString("v_id"));
                    visRow.details.setId(resultSet.getString("st_id"));

                    entries3.getChildren().add(row);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @FXML
    protected void vis_Filer(){
        FilteredList<String> filteredItems = new FilteredList<String>(v_items, p -> true);
        comboBox3.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = comboBox3.getEditor();
            final String selected = (String)comboBox3.getSelectionModel().getSelectedItem();
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
        entries3.getChildren().clear();
        comboBox3.setItems(filteredItems);

        try {
            String v_relation = comboBox3.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM Visitors WHERE v_relation = '" + v_relation + "'";

            ResultSet resultSet = HelloApplication.statement.executeQuery(query);
            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("VisRow.fxml"));
                Parent row = loader.load();

                VisRow visRow = loader.getController();
                visRow.st_id.setText(resultSet.getString("st_id"));
                visRow.vis_Name.setText(resultSet.getString("v_firstName") + " " + resultSet.getString("v_lastName"));
                visRow.Vphone.setText(resultSet.getString("v_PHNcode") + resultSet.getString("v_PHNno"));
                visRow.Vrelation.setText(resultSet.getString("v_relation"));
                visRow.vis_id.setText(resultSet.getString("v_id"));
                visRow.details.setId(resultSet.getString("st_id"));

                entries3.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}