package com.example.hostelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentLog implements Initializable {

    public BorderPane pane = new BorderPane();
    public Button bill,complain,logout;
    public Label stdName;
    static Stage splashStage;



    @FXML
    public void Bill() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("STUDENTLOGIN.fxml"));
        HelloApplication.stage.setScene(new Scene(loader.load()));

    }

    @FXML
    public void BillHoverIn(){
        bill.setStyle("-fx-background-color:  #9DA2AB;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: 9DA2AB");
    }

    @FXML
    public void BillHoverOut(){
        bill.setStyle("-fx-background-color:  #0D324D;-fx-border-color: transparent");
    }

    @FXML
    public void ComHoverIn(){
        complain.setStyle("-fx-background-color:  #9DA2AB;-fx-border-style: hidden hidden solid hidden ; -fx-border-color: 9DA2AB");
    }

    @FXML
    public void ComHoverOut(){
        complain.setStyle("-fx-background-color:  #0D324D;-fx-border-color: transparent");
    }


    @FXML
    public void Complain() throws IOException {
        STDCOMPLAIN comp = new STDCOMPLAIN();
        comp.add(pane);
    }

    @FXML
    protected void LogoutHoverIn()
    {
        logout.setStyle("-fx-background-color: #D95D39 ; -fx-background-radius: 15");

    }

    @FXML
    protected void LogoutHoverOut()
    {
        logout.setStyle("-fx-background-color: #2D1E34;-fx-border-color: white; -fx-border-radius:15;  -fx-background-radius: 15");

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
    @FXML
    public void loadAll() throws SQLException, IOException {

        String q  = "select * from BillShow b\n" +
                "where b.st_firstName = (select s.st_firstName from Student s where s.st_firstName = '"+ HelloController.storeName + "' )";

        ResultSet res = HelloApplication.statement.executeQuery(q);

            if(res.next())
            {
                if(res.getString("paid_date") == null) {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("STDLOGCENTER.fxml"));
                    Parent row = loader.load();

                    STDLOGCENTER std = loader.getController();


                    std.allot.setText(res.getString("allot_id"));
                    std.comments.setText(res.getString("comments"));
                    std.duedate.setText(res.getDate("due_date").toString());
                    std.dues.setText(res.getString("dues"));
                    std.method.setText(res.getString("payment_method"));
                    std.fine.setText(res.getString("fine"));
                    stdName.setText(HelloController.storeName);

                    pane.setCenter(row);


                }
                else{
                    FXMLLoader load = new FXMLLoader(getClass().getResource("PAIDROW.fxml"));
                    Parent rr = load.load();
                    stdName.setText(HelloController.storeName);

                    PAIDROW p = load.getController();
                    p.allotment.setText(res.getString("allot_id"));
                    p.paiddate.setText(res.getString("paid_date"));
                    p.duess.setText(res.getString("dues"));

                    pane.setCenter(rr);


                }
            }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
