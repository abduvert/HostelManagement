package com.example.hostelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    public TextField religion;
    public TextField batch;
    public TextField dateofbirth;
    public TextField address;
    public Button save;
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
                gr.address.setText(res.getString("s_houseNo") + ", " + res.getString("st_town") + ", " + res.getString("st_city"));
                gr.batch.setText(res.getString("batch"));
                gr.CNICno.setText(res.getString("st_CNICcode") + " - " + res.getString("st_CNICno"));
                gr.dateofbirth.setText(res.getString("st_dob"));
                gr.email.setText(res.getString("st_email"));
                gr.religion.setText(res.getString("religion"));

                inform.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Edit(){
        email.setEditable(true);
        religion.setEditable(true);
        batch.setEditable(true);
        dateofbirth.setEditable(true);
        address.setEditable(true);
        CNICno.setEditable(true);
        save.setVisible(true);
    }

    @FXML
    protected  void Save(ActionEvent event){
        try{
            String g = ((Button) event.getSource()).getId();
            String edit_std_query = "update Student st_email = '" + email.getText() + "', st_CNICcode =" + CNICcode.getText() +
                    " , st_CNICno =" + CNICno.getText() + " , religion ='" + religion.getText() + "' , batch ='" +
                    batch.getText() + "' , st_dob = '" + dateofbirth.getText() + "' where st_id = '" + g + "'";
            ResultSet res = HelloApplication.statement.executeQuery(edit_std_query);
            if(res.next()){

            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


}
