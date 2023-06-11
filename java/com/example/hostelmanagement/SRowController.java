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
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SRowController {
    @FXML
    public Label id, name, phoneNumber, degree, CGPA, st_id, dett;
    @FXML
    public Button details, save, edit;
    @FXML
    public TextField email, CNICcode, CNICno, phoneCode, phoneNo, stfName, stlName, batch, dateofbirth;
    public AnchorPane anchorPane;
    public String g;
   static Stage inform = new Stage();

    @FXML
    public void Details(ActionEvent event) throws IOException, SQLException {

        try {
            g = ((Button) event.getSource()).getId();
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
        save.setVisible(true);
        inform.setTitle("Edit Details");
        dett.setText("Edit Student Details");
        anchorPane.setStyle("-fx-border-color: green");
        email.setStyle("-fx-border-color: #505472 ");
        CNICcode.setStyle("-fx-border-color: #505472 ");
        CNICno.setStyle("-fx-border-color: #505472 ");
        phoneNo.setStyle("-fx-border-color: #505472 ");
        phoneCode.setStyle("-fx-border-color: #505472 ");
        batch.setStyle("-fx-border-color: #505472 ");
        dateofbirth.setStyle("-fx-border-color: #505472 ");
        stfName.setStyle("-fx-border-color: #505472 ");
        stlName.setStyle("-fx-border-color: #505472 ");
        CNICcode.setEditable(true);
        CNICno.setEditable(true);
        phoneNo.setEditable(true);
        phoneCode.setEditable(true);
        dateofbirth.setEditable(true);
        batch.setEditable(true);
        stfName.setEditable(true);
        stlName.setEditable(true);
        email.setEditable(true);
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
    protected void Save(ActionEvent event){
        try{
            String edit_std_query = "update Student set st_email = '" + email.getText() + "' , st_CNICcode = " + CNICcode.getText() +
                    " , st_CNICno = " + CNICno.getText() + " , st_firstName = '" + stfName.getText()+ "' , st_lastName = '" + stlName.getText()
                    +"' , st_PHNcode = " + phoneCode.getText() + " , st_PHNno = " + phoneNo.getText()+ " , batch ='" +
                    batch.getText() + "' , st_dob = '" + dateofbirth.getText() + "' where st_id = '" + st_id.getText() + "'";
            HelloApplication.statement.executeUpdate(edit_std_query);

            inform.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void Delete(){
        try{
            String delete_query = "delete from Student where st_id = '" + st_id.getText() + "'";
            HelloApplication.statement.executeUpdate(delete_query);

            inform.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
