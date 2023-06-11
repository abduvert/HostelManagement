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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AllotmentPage implements Initializable {
    @FXML
    public Button room;


    public Button room1;
    public Button room2;

    @FXML
    public VBox entry, entry2;
    @FXML
    public BorderPane borderPane = new BorderPane();
    @FXML
    public Button back, allot;
    @FXML
    public TextField search_textField;
    public DatePicker startDate = new DatePicker();
    LocalDate start;
    public DatePicker endDate = new DatePicker();
    LocalDate end;
    String stDay,stMonth,stYear;
    String endDay,endMonth,endYear;

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
    public void add(ActionEvent event) throws IOException {

        FXMLLoader allot = new FXMLLoader(getClass().getResource("AllotmentPage.fxml"));
        HelloApplication.stage.setScene(new Scene(allot.load()));
        HelloApplication.stage.setTitle("Allotment");
    }

    @FXML
    public void AllotHoverIn(){

        allot.setStyle("-fx-background-color:  #A7A5C6;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: #383B53");

    }

    @FXML
    public void AllotHoverOut(){

        allot.setStyle("-fx-background-color:   #A7A5C6;-fx-border-color: transparent");

    }

    @FXML
    public void Allotment() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotmentPage.fxml"));
        HelloApplication.stage.setScene(new Scene(loader.load()));

    }



    @FXML
    public void RoomHoverIn(){

        room.setStyle("-fx-background-color:   #505472; -fx-text-fill: white;");

    }

    @FXML
    public void RoomHoverOut(){

        room.setStyle("-fx-background-color:  white; -fx-text-fill:  #505472; -fx-border-color:  #505472;");

    }
    @FXML
    public void Room1HoverIn(){

        room1.setStyle("-fx-background-color:   #505472; -fx-text-fill: white;");

    }

    @FXML
    public void Room1HoverOut(){

        room1.setStyle("-fx-background-color:  white; -fx-text-fill:  #505472; -fx-border-color:  #505472;");

    }
    @FXML
    public void Room2HoverIn(){

        room2.setStyle("-fx-background-color:   #505472; -fx-text-fill: white;");

    }

    @FXML
    public void Room2HoverOut(){

        room2.setStyle("-fx-background-color:  white; -fx-text-fill:  #505472; -fx-border-color:  #505472;");

    }




    @FXML
    public void loadAll(){
        try {
            String q = "select * from Allotment";
            ResultSet res = HelloApplication.statement.executeQuery(q);


            while (res.next()) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotROW.fxml"));
                Parent row = loader.load();

                AllotRow al = loader.getController();

                al.r_id.setText(res.getString("r_id"));
                al.st_id.setText(res.getString("st_id"));
                al.allotid.setText(res.getString("allot_id"));
                al.year.setText(res.getString("allot_year"));
                al.date.setText(res.getString("allot_date"));
                al.month.setText(res.getString("allot_month"));

                entry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void Apply(){
        start = startDate.getValue();


        stDay =  Integer.toString(start.getDayOfMonth());
        stMonth = Integer.toString(start.getMonthValue());
        stYear = Integer.toString(start.getYear());


        System.out.println("date 1: " + stDay + " " + stMonth + " " + stYear);

        entry.getChildren().clear();
        try {
            String query = "\n" +
                    "select * from Allotment\n" +
                    "where allot_date =" + stDay + " and allot_month = " + stMonth +" and allot_year= " + stYear;

            ResultSet res = HelloApplication.statement.executeQuery(query);
            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotROW.fxml"));
                Parent row = loader.load();

                AllotRow al = loader.getController();

                al.r_id.setText(res.getString("r_id"));
                al.st_id.setText(res.getString("st_id"));
                al.allotid.setText(res.getString("allot_id"));
                al.year.setText(res.getString("allot_year"));
                al.date.setText(res.getString("allot_date"));
                al.month.setText(res.getString("allot_month"));

                entry.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void Month(){
        start = startDate.getValue();

        stMonth = Integer.toString(start.getMonthValue());


        entry.getChildren().clear();
        try {
            String query = "\n" +
                    "select * from Allotment\n" +
                    "where allot_month = " + stMonth;

            ResultSet res = HelloApplication.statement.executeQuery(query);
            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotROW.fxml"));
                Parent row = loader.load();

                AllotRow al = loader.getController();

                al.r_id.setText(res.getString("r_id"));
                al.st_id.setText(res.getString("st_id"));
                al.allotid.setText(res.getString("allot_id"));
                al.year.setText(res.getString("allot_year"));
                al.date.setText(res.getString("allot_date"));
                al.month.setText(res.getString("allot_month"));

                entry.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    @FXML
    public void Year(){
        start = startDate.getValue();

        stYear = Integer.toString(start.getYear());


        entry.getChildren().clear();
        try {
            String query = "\n" +
                    "select * from Allotment\n" +
                    "where allot_year = " + stYear;

            ResultSet res = HelloApplication.statement.executeQuery(query);
            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotROW.fxml"));
                Parent row = loader.load();

                AllotRow al = loader.getController();

                al.r_id.setText(res.getString("r_id"));
                al.st_id.setText(res.getString("st_id"));
                al.allotid.setText(res.getString("allot_id"));
                al.year.setText(res.getString("allot_year"));
                al.date.setText(res.getString("allot_date"));
                al.month.setText(res.getString("allot_month"));

                entry.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
    @FXML
    protected void Search(){
        entry.getChildren().clear();

        try{
            String query = "select * from Allotment where r_id = " + search_textField.getText();
            ResultSet resultSet = HelloApplication.statement.executeQuery(query);

            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AllotROW.fxml"));
                Parent row = loader.load();

                AllotRow al = loader.getController();

                al.r_id.setText(resultSet.getString("r_id"));
                al.st_id.setText(resultSet.getString("st_id"));
                al.allotid.setText(resultSet.getString("allot_id"));
                al.year.setText(resultSet.getString("allot_year"));
                al.date.setText(resultSet.getString("allot_date"));
                al.month.setText(resultSet.getString("allot_month"));

                entry.getChildren().add(row);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}

