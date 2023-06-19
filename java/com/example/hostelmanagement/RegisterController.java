package com.example.hostelmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.hostelmanagement.HelloApplication.statement;

public class RegisterController {

    public static Stage stage1 = new Stage();
    public Scene scene1;
    @FXML
    public Button next1, next2, cancel1, cancel2, done;
    @FXML
    public TextField fname, lname, dob, cgpa, degree, batch, Phncode, PhnNo, CNICcode, CNICno, email, religion, Hno, town, security;
    @FXML
    public PasswordField password;
    @FXML
   public ComboBox<String> comboBox = new ComboBox<>();
    public String newStId, selected_city, G_city;
    public static String selected_room;
    @FXML
    public TextField G_fname, G_lname, G_relation, G_PHNcode, G_PHNno, G_CNICcode, G_CNICno, G_email, G_religion, G_Hno, G_town;
    @FXML
    public ComboBox<String> comboBox2 = new ComboBox<>();
    @FXML
    public TextField v_fname, v_lname, v_relation, v_PHNcode, v_PHNno, v_CNICcode, v_CNICno;
    @FXML
    public TextField v2_fname, v2_lname, v2_relation, v2_PHNcode, v2_PHNno, v2_CNICcode, v2_CNICno;
    @FXML
    ComboBox<String> room_comboBox = new ComboBox<>();
    ObservableList<String> room_items = FXCollections.observableArrayList();
    @FXML
    Label room_type, floor_id;
    @FXML
    protected void Register(ActionEvent event) throws IOException {
        FXMLLoader reg1 = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        stage1.setScene(new Scene(reg1.load()));
        stage1.initStyle(StageStyle.UNDECORATED);
        stage1.show();
    }

    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList("Arizona", "Auburn", "Boston",
                "Chicago", "California", "Florence", "Florida", "Greenville", "Kansas", "New York", "Ohio", "Portland", "Seattle");
        comboBox.setItems(items);

        ObservableList<String> items2 = FXCollections.observableArrayList("Arizona", "Auburn", "Boston",
                "Chicago", "California", "Florence", "Florida", "Greenville", "Kansas", "New York", "Ohio", "Portland", "Seattle");
        comboBox2.setItems(items2);

        try{
            String query = "select r_id from Rooms where occ_status = 'Vacant' or occ_status = 'Partially Occupied'";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String item = resultSet.getString("r_id");
                room_items.add(item);
            }
            room_comboBox.setItems(room_items);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void Show(){
        selected_city = comboBox.getValue();
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
            System.out.println("2." + selected_city);
            selected_city = newValue;
        });
    }

    @FXML
    protected void Next1(ActionEvent backevent) throws IOException {
        try{
            // Step 1: Retrieve the maximum st_id value
            String maxStIdQuery = "SELECT MAX(st_id) FROM Student";
            ResultSet resultSet = statement.executeQuery(maxStIdQuery);
            resultSet.next();
            String maxStId = resultSet.getString(1);

            // Step 2: Generate the new st_id
            int numericPart = Integer.parseInt(maxStId.substring(1)); // Extract numeric part

            newStId = "S" + String.format("%03d", numericPart + 1); // Increment and format


            // Step 3: Insert the new record
            String InsertQuery = "insert into Student(st_id, batch, degree, st_firstName, st_lastName, st_PHNcode, " +
                    "st_PHNno, st_email, st_dob, st_CNICcode," + " st_CNICno, religion, st_city, st_town, s_houseNo, " +
                    "cgpa, st_password, st_SecurityQuestion) values " + "('" + newStId + "' , '" + batch.getText() +
                    "' , '" + degree.getText() + "' , '" + fname.getText() + "' , '" + lname.getText() + "' , " +
                    Phncode.getText() + ", " + PhnNo.getText() + ", '" + email.getText() + "' , '" + dob.getText() +
                    "' , " + CNICcode.getText() + ", " + CNICno.getText() + ", '" + religion.getText() + "' , '" +
                    selected_city + "' , '" + town.getText() + "' , '" + Hno.getText() + "' , " + cgpa.getText() +
                    ", '" + password.getText() +"' , '" + security.getText() + "')";

            if(email.getText().contains("@") &&  email.getText().chars().mapToObj(Character::isDigit).anyMatch(Boolean::valueOf))
            {
            HelloApplication.statement.executeQuery(InsertQuery);

            }
            else {
                Alert a = new Alert(Alert.AlertType.WARNING);
                a.setContentText("Wrong email format");
                a.show();
            }
        } catch (Exception e){
            System.out.println( e.getMessage());
            e.printStackTrace();
        }

        if(batch.getText().isEmpty() || degree.getText().isEmpty() || fname.getText().isEmpty() ||
           lname.getText().isEmpty() || Phncode.getText().isEmpty() || PhnNo.getText().isEmpty() ||
           email.getText().isEmpty() || dob.getText().isEmpty() || CNICcode.getText().isEmpty() ||
           CNICno.getText().isEmpty() || religion.getText().isEmpty() || town.getText().isEmpty() ||
           Hno.getText().isEmpty() || cgpa.getText().isEmpty() || password.getText().isEmpty() ||
           security.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("A text field is empty. Student has not been registered");
            a.show();
        }
        else{
            FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register2.fxml"));
            stage1.setScene(new Scene(reg2.load()));
        }
    }

    @FXML
    protected void Show2(){
        G_city = comboBox2.getValue();
        comboBox2.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("Selected value: " + newValue);
            G_city = newValue;
        });
    }

    @FXML
    protected void Next2(ActionEvent backevent) throws IOException {
        try{
            // Retrieve the maximum st_id value
            String maxStIdQuery = "SELECT MAX(st_id) FROM Student";
            ResultSet resultSet = statement.executeQuery(maxStIdQuery);
            resultSet.next();
            String maxStId = resultSet.getString(1);
            System.out.println("in 2nd st_id: " + maxStId);


            String InsertQuery2 = "insert into Guardian(st_id, g_firstName, g_lastname, g_CNICcode, g_CNICno," +
                  " g_relation,g_PHNcode, g_PHNno, g_city, g_town, g_houseNo, g_email) values " + "('" +
                  maxStId + "' , '" + G_fname.getText() + "' , '" + G_lname.getText() + "' ," +
                  G_CNICcode.getText() + "," + G_CNICno.getText() + ", '" + G_relation.getText() + "' , " +
                  G_PHNcode.getText() + ", " + G_PHNno.getText() + ", '" + G_city + "' , '" + G_town.getText()
                  + "' , '" + G_Hno.getText() + "' , '" + G_email.getText() + "')";

            HelloApplication.statement.executeQuery(InsertQuery2);


        } catch (Exception e){
            System.out.println( e.getMessage());
            e.printStackTrace();
    }
        if(G_fname.getText().isEmpty() || G_lname.getText().isEmpty() || G_CNICcode.getText().isEmpty() ||
           G_CNICno.getText().isEmpty() || G_relation.getText().isEmpty() || G_PHNcode.getText().isEmpty() ||
           G_PHNno.getText().isEmpty() || G_town.getText().isEmpty() || G_Hno.getText().isEmpty() ||
           G_email.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setContentText("A text field is empty. Guardian has not been registered");
            a.show();
        }
        else{
            FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register3.fxml"));
            stage1.setScene(new Scene(reg2.load()));
        }
    }

    @FXML
    protected void Cancel1(ActionEvent backevent) throws IOException {

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
        stage1.setScene(new Scene(reg2.load()));
    }


    @FXML

    protected void Cancel2(ActionEvent backevent) throws IOException {

        FXMLLoader reg2 = new FXMLLoader(getClass().getResource("Register2.fxml"));
        stage1.setScene(new Scene(reg2.load()));
    }

    @FXML
    protected void Next3() throws IOException {
        try{
            String maxStIdQuery = "SELECT MAX(st_id) FROM Student";
            ResultSet resultSet = statement.executeQuery(maxStIdQuery);
            resultSet.next();
            String maxStId = resultSet.getString(1);

            String maxV_IdQuery = "SELECT MAX(v_id) FROM Visitors";
            ResultSet resultSet2 = statement.executeQuery(maxV_IdQuery);
            resultSet2.next();
            String v_id = resultSet2.getString(1);
            int numericPart = Integer.parseInt(maxStId.substring(1)); // Extract numeric part

            v_id = "V" + String.format("%03d", numericPart + 1);
            String v_id2 = "V" + String.format("%03d", numericPart + 2);

            System.out.println("v_id: "+v_id);
            System.out.println("v_id2: " + v_id2);
            String InsertQuery3 = "insert into Visitors values ('"+ maxStId + "' , '" + v_id + "' , '" + v_fname.getText() + "' , '" + v_lname.getText() + "' , "
                    + v_CNICcode.getText() + " , " + v_CNICno.getText() + " , '" + v_relation.getText() + "' , " + v_PHNcode.getText() + " , "
                    + v_PHNno.getText() +")";

            String q2 = "insert into Visitors values ('" + maxStId + "' , '" + v_id2 + "' , '" + v2_fname.getText() + "' , '" + v2_lname.getText() +
            "' , " + v2_CNICcode.getText() + " , " + v2_CNICno.getText() + " , '" + v2_relation.getText() + "' , " + v2_PHNcode.getText() +
                    " , " + v2_PHNno.getText() + ")";

            HelloApplication.statement.executeUpdate(InsertQuery3);
            HelloApplication.statement.executeUpdate(q2);
        } catch (Exception e){
            System.out.println( e.getMessage());
            e.printStackTrace();
        }

        rooms_Show();

        //shifting to another class for a new page
        AllotingRoom room = new AllotingRoom();
        room.add(stage1);
    }

    @FXML
    protected void rooms_Show() throws IOException {
        selected_room = room_comboBox.getValue();
        room_comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            selected_room = newValue;
        });
        System.out.println("Selected room is: " + selected_room);
        room_comboBox.setValue(selected_room);
        RoomSel(selected_room);
    }


    @FXML
    public void RoomSel(String selected_room) throws IOException {
        FXMLLoader allot = new FXMLLoader(getClass().getResource("AllotingRoom.fxml"));
        Parent all = allot.load();

        RegisterController r = allot.getController();
        try{
            System.out.println("Selected room inside: " + selected_room);
            String query = "select Rtype_name, floor_id from RoomShow where r_id = " + selected_room;
            ResultSet resultSet = statement.executeQuery(query);

            if(resultSet.next()){
                System.out.println("room type is: " + resultSet.getString("Rtype_name"));
                r.room_type.setText(resultSet.getString("Rtype_name"));
                r.floor_id.setText(resultSet.getString("floor_id"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        stage1.setScene(new Scene(all));
    }

    @FXML
    protected void Done(ActionEvent event) throws IOException {

        try{
            String maxAllotIdQuery = "SELECT MAX(allot_id) FROM Allotment";
            ResultSet resultSet = statement.executeQuery(maxAllotIdQuery);
            resultSet.next();
            String maxAllot_Id = resultSet.getString(1);
            System.out.println("\nAllot id: " + maxAllot_Id);
            int numericPart = Integer.parseInt(maxAllot_Id.substring(2));
            maxAllot_Id = "AL" + String.format("%03d", numericPart + 1);
            System.out.println("MAX ALLOT ID: " + maxAllot_Id);


            String maxStIdQuery = "SELECT MAX(st_id) FROM Student";
            ResultSet resultSet2 = statement.executeQuery(maxStIdQuery);
            resultSet2.next();
            String maxStId = resultSet2.getString(1);
            System.out.println("MAX ST_ID: " + maxStId);


                //SELECTED ROOM IS NULL HERE
            System.out.println("Selected room for insertion into allotment: " + selected_room);


            Calendar calendar = Calendar.getInstance();
            Date currentDate = calendar.getTime();

            int month = calendar.get(Calendar.MONTH) + 1;  // Adding 1 since months are zero-based
            int year = calendar.get(Calendar.YEAR);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            CallableStatement callableStatement = statement.getConnection().prepareCall("{call UpdateRoomOccupancyStatus(?)}");
            callableStatement.setString(1, selected_room);
            callableStatement.execute();

            String InsertQuery = "INSERT INTO Allotment (allot_id, st_id, r_id, allot_date, allot_month, allot_year)"
                    + "VALUES ('" + maxAllot_Id +  "' , '" + maxStId + "' , " + selected_room + ", " + day +
                    ", " + month + ", " + year + ")";

            HelloApplication.statement.executeQuery(InsertQuery);
        } catch (Exception e){
            System.out.println( e.getMessage());
            e.printStackTrace();
        }

        stage1.close();
        Stage updated = new Stage();
        BorderPane upd = new BorderPane();
        Label ud = new Label("The Student has been Registered");
        upd.setStyle("-fx-background-color: #25283D;");
        ud.setStyle("-fx-text-fill: white");
        upd.setCenter(ud);

        updated.setScene(new Scene(upd,400,200));
        updated.show();
    }
}