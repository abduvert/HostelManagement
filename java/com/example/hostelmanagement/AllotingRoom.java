package com.example.hostelmanagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AllotingRoom {



    public void add(Stage stage1) throws IOException {
        FXMLLoader allot = new FXMLLoader(getClass().getResource("AllotingRoom.fxml"));
        stage1.setScene(new Scene(allot.load()));
    }

    public void LoadRooms()
    {
        
    }
}
