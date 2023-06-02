package com.example.hostelmanagement;

import javafx.beans.NamedArg;
import javafx.beans.property.DoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;

public class StudentController {
    public AnchorPane anchorPane;
    public Button student;


    public Button visitors;
    public Button guardians;

    public VBox entries = new VBox();
    public VBox entries2 = new VBox();
    public VBox entries3 = new VBox();

    public Button register;
    public Button back;
    public BorderPane borderPane;




    @FXML
    public void add(ActionEvent event) throws IOException {

       FXMLLoader stud = new FXMLLoader(getClass().getResource("StdPage.fxml"));
       HelloApplication.stage.setScene(new Scene(stud.load()));
       HelloApplication.stage.setMaximized(true);
//       loadAllProducts();

    }

    @FXML
    public void initialize(){
        loadAllProducts();
        Guardload();
        VisLoad();
    }


    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));
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


    //int count=0;



    public void loadAllProducts() {
        try {
            String q = "select * from Student";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Std_Row.fxml"));
                Parent row = loader.load();

                SRowController prc = loader.getController();
                prc.id.setText(res.getString("st_id"));
                prc.name.setText(res.getString("st_firstName") + res.getString("st_lastName"));
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
                gRow.guardianName.setText(res.getString("g_firstName") + res.getString("g_lastName"));
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
                visRow.vis_Name.setText(res.getString("v_firstName") + res.getString("v_lastName"));
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



}

