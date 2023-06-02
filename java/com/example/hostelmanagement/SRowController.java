package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SRowController {
    public Label id;
    public Label name;
    public Label phoneNumber;
    public Label degree;
    public Label CGPA;
    public Button details;

    public Label st_id;
    public TextField email;
    public  TextField CNICcode;
    public TextField CNICno;
    public TextField phoneCode;
    public TextField phoneNo;
    public TextField stfName;
    public TextField stlName;
    public TextField batch;
    public TextField dateofbirth;
    public Button save;
    public Button edit;
    public AnchorPane anchorPane;
    public Label dett;

    Stage inform = new Stage();

    @FXML
    public void Details(ActionEvent event) throws IOException, SQLException {

        try {
            String g = ((Button) event.getSource()).getId();
            String q = "select * from Student where st_id ='" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            if (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("StdLEFTPAGE.fxml"));
                Parent side = loader.load();

                SRowController gr = loader.getController();
                inform.setScene(new Scene(side));

                gr.st_id.setText(g);
                gr.phoneCode.setText(res.getString("st_PHNcode"));
                gr.phoneNo.setText(res.getString("st_PHNno"));
                gr.batch.setText(res.getString("batch"));
                gr.CNICcode.setText(res.getString("st_CNICcode"));
                gr.CNICno.setText(res.getString("st_CNICno"));
                gr.dateofbirth.setText(res.getString("st_dob"));
                gr.email.setText(res.getString("st_email"));
                gr.stfName.setText(res.getString("st_firstName"));
                gr.stlName.setText(res.getString("st_lastName"));

                inform.setTitle("Details");
                inform.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Edit(){

        //for changing the style of textfields and making it editable
        inform.setTitle("Edit Details");
        dett.setText("Edit Student Details");
        anchorPane.setStyle("-fx-border-color: green");
        email.setEditable(true);
        email.setStyle("-fx-border-color: #505472 ");
        CNICcode.setEditable(true);
        CNICcode.setStyle("-fx-border-color: #505472 ");
        CNICno.setEditable(true);
        CNICno.setStyle("-fx-border-color: #505472 ");
        phoneNo.setEditable(true);
        phoneNo.setStyle("-fx-border-color: #505472 ");
        phoneCode.setEditable(true);
        phoneCode.setStyle("-fx-border-color: #505472 ");
        batch.setEditable(true);
        batch.setStyle("-fx-border-color: #505472 ");
        dateofbirth.setEditable(true);
        dateofbirth.setStyle("-fx-border-color: #505472 ");
        stfName.setEditable(true);
        stfName.setStyle("-fx-border-color: #505472 ");
        stlName.setEditable(true);
        stlName.setStyle("-fx-border-color: #505472 ");
        save.setVisible(true);
    }

    @FXML
    public void EditHoverin(){

        edit.setStyle("-fx-background-color: #505472; -fx-text-fill: white");
    }

    @FXML
    public void SaveHoverOut(){

        save.setStyle("-fx-background-color: transparent; -fx-text-fill: green; -fx-border-color: green;");
    }


    @FXML
    public void SaveHoverin(){

        save.setStyle("-fx-background-color: green; -fx-text-fill: white");
    }

    @FXML
    public void EditHoverOut(){

        edit.setStyle("-fx-background-color: transparent; -fx-text-fill: #505472; -fx-border-color: #505472;");
    }

    @FXML
    protected  void Save(ActionEvent event){
        try{
            String g = ((Button) event.getSource()).getId();
            String edit_std_query = "update Student st_email = '" + email.getText() + "' , st_CNICcode = " + CNICcode.getText() +
                    " , st_CNICno = " + CNICno.getText() + " , st_firstName = '" + stfName.getText()+ "' , st_lastName = '" + stlName.getText()
                    +"' , st_PHNcode = " + phoneCode.getText() + " , st_PHNno = " + phoneNo.getText()+ " , batch ='" +
                    batch.getText() + "' , st_dob = '" + dateofbirth.getText() + "' where st_id = '" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(edit_std_query);
            if(res.next()){

                inform.close();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }


}
