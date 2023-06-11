package com.example.hostelmanagement;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Logout implements Initializable {
    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Thread taskThread = new Thread(() -> {
            try {
                // delay for 2 seconds  //
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            // Switch to the next FXML file
            Platform.runLater(() -> {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));


                    HelloApplication.stage = loader.load();
                    //HelloApplication.stage.getIcons().add(new Image("C:\\Users\\Rida Abid\\IdeaProjects\\HOSTEL_MANAGEMENT\\src\\main\\resources\\com\\example\\hostelmanagement\\SU.png"));
                    HelloApplication.stage.setTitle("SU HOSTEL");


                    if(HomePage.splashStage ==null)
                    {
                    StudentLog.splashStage.close();
                        HelloApplication.stage.show();
                        return;
                    }
                    HomePage.splashStage.close();
                    HelloApplication.stage.show();


                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });

        taskThread.start();
    }

}
