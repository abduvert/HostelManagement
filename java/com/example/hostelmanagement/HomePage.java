package com.example.hostelmanagement;

import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.AccessibleAction;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;

public class HomePage {


    public Button student;
    public Button floor;
    public Button employee;
    public Button complaints;
    public Button bills;
    public Button logout;

    public static Stage splashStage;


    @FXML
    public void StudentHoverIn(){
        student.setStyle("-fx-background-color: #664375; -fx-background-radius:10;");
        student.setTextFill(Color.BLACK);
    }
    @FXML
    public void StudentClicked(ActionEvent event) throws IOException {

       StudentController studentController = new StudentController();
       studentController.add(event);

       // loadAllProducts(studentController.entries);

    }

    @FXML
    public void StudentHoverOut(){
        student.setStyle("-fx-background-color: transparent; -fx-background-radius:10;");
        student.setTextFill(Color.WHITE);
    }

    @FXML
    public void EmployeeHoverIn(){
        employee.setStyle("-fx-background-color: #664375; -fx-background-radius:10;");
        employee.setTextFill(Color.BLACK);
    }
    @FXML
    public void EmployeeHoverOut(){
        employee.setStyle("-fx-background-color: transparent; -fx-background-radius:10;");
        employee.setTextFill(Color.WHITE);
    }

    @FXML
    public void Employee(ActionEvent event) throws IOException {
        Employee emp = new Employee();
        emp.add(event);
    }

    @FXML
    public void FloorHoverIn(){
        floor.setStyle("-fx-background-color: #664375; -fx-background-radius:10;");
        floor.setTextFill(Color.BLACK);
    } @FXML
    public void FloorHoverOut(){
        floor.setStyle("-fx-background-color: transparent; -fx-background-radius:10;");
        floor.setTextFill(Color.WHITE);
    }

    @FXML
    public void ComplaintsHoverIn(){
        complaints.setStyle("-fx-background-color: #664375; -fx-background-radius:10;");
        complaints.setTextFill(Color.BLACK);
    } @FXML
    public void ComplaintsHoverOut(){
        complaints.setStyle("-fx-background-color: transparent; -fx-background-radius:10;");
        complaints.setTextFill(Color.WHITE);
    }
    @FXML
    public void Complaints(ActionEvent event) throws IOException {
        Complaints comp = new Complaints();
        comp.add(event);
    }

    @FXML
    public void BillsHoverIn(){
        bills.setStyle("-fx-background-color: #664375; -fx-background-radius:10;");
       bills.setTextFill(Color.BLACK);
    } @FXML
    public void BillsHoverOut(){
        bills.setStyle("-fx-background-color: transparent; -fx-background-radius:10;");
        bills.setTextFill(Color.WHITE);
    }

    @FXML
    protected void LogoutHoverIn()
    {
        logout.setStyle("-fx-background-color: #D95D39 ; -fx-background-radius: 15");

    }

    @FXML
    protected void LogoutHoverOut()
    {
        logout.setStyle("-fx-background-color:  transparent ;-fx-border-color: white; -fx-border-radius:15;  -fx-background-radius: 15");

    }


    @FXML
    protected void Logout() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Logout.fxml"));
        splashStage = loader.load();
        //splashStage.getIcons().add(new Image("C:\\Users\\Rida Abid\\IdeaProjects\\HOSTEL_MANAGEMENT\\src\\main\\resources\\com\\example\\hostelmanagement\\SU.png"));
        splashStage.initStyle(StageStyle.UNDECORATED);
        HelloApplication.stage.close();
        splashStage.show();

    }

}
