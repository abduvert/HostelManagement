package com.example.hostelmanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import javafx.stage.StageStyle;

import java.sql.*;


public class HelloApplication extends Application {




    @Override
    public void start(Stage stage) throws IOException, SQLException {

        HelloApplication.stage = stage;
        DriverManager.registerDriver(new SQLServerDriver());
        Connection con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-OSAF15A;Database=SU_Hostel;encrypt=true;trustServerCertificate=true;IntegratedSecurity=true;");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loading1.fxml"));
        splashStage = loader.load();
        splashStage.getIcons().add(new Image("C:\\Users\\hp\\IdeaProjects\\HostelManagement\\src\\main\\resources\\com\\example\\hostelmanagement\\SU.png"));
        splashStage.initStyle(StageStyle.UNDECORATED);
        splashStage.show();
    }

    public static Stage stage;
    public static Stage splashStage;
    public static void main(String[] args) {
        launch();
    }
}