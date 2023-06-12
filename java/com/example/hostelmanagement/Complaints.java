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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.hostelmanagement.HelloApplication.statement;

public class Complaints implements Initializable {
    public VBox comEntry;
    public Button stat;
    @FXML
    ComboBox<String > comboBox = new ComboBox<>();
    Stage st = new Stage();
    ObservableList<String> comItems = FXCollections.observableArrayList();
    ObservableList<String> items = FXCollections.observableArrayList();
    @FXML
    public ComboBox<String> comID = new ComboBox<>();
    public static String selectedID;
    public static String selectedStatus;
    public TextField from_tf, to_tf,commonField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loadAll();

        //to fill comboBox of filter by complaint status
        items = FXCollections.observableArrayList("Open", "Closed");
        comboBox.setItems(items);

        //to fill values of comboBox in status update
        try{
            String query = "select * from Complaints c\n" +
                    "where c.status = 'Open'";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                String item = resultSet.getString("cmp_id");
                comItems.add(item);
            }
            comID.setItems(comItems);
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void add(ActionEvent event) throws IOException {
        FXMLLoader complain = new FXMLLoader(getClass().getResource("Complaints.fxml"));
        HelloApplication.stage.setScene(new Scene(complain.load()));
        HelloApplication.stage.setMaximized(true);
    }

    @FXML
    protected void Back() throws IOException {
        FXMLLoader back = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        HelloApplication.stage.setScene(new Scene(back.load()));

    }

    @FXML
    public void Update() throws SQLException {
        selectedID = comID.getValue();
        String update = "Update Complaints " +
                "set status = 'Closed'\n" +
                "where cmp_id = " + selectedID;

        comID.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedID = newValue;
        });
        System.out.println("Selected complain is: " + selectedID);

        statement.executeUpdate(update);
        st.close();
        Stage updated = new Stage();
        BorderPane upd = new BorderPane();
        Label ud = new Label("The Complaint status has been Updated!");
        upd.setStyle("-fx-background-color: #25283D;");
        ud.setStyle("-fx-text-fill: white");
        upd.setCenter(ud);

        updated.setScene(new Scene(upd,400,200));
        updated.show();
    }



    @FXML
    public void StatusHoverIn(){

        stat.setStyle("-fx-background-color:  #A7A5C6; -fx-text-fill: black;");

    }
    @FXML
    public void StatusHoverOut(){

        stat.setStyle("-fx-background-color:  #505472; -fx-text-fill: white;");

    }


    @FXML
    public void Status() throws IOException {
        FXMLLoader cpm = new FXMLLoader(getClass().getResource("StatusUpdate.fxml"));
        Parent cp = cpm.load();

        st.setScene(new Scene(cp));
        st.initStyle(StageStyle.UNDECORATED);
        st.show();
    }


    @FXML
    public void close(){
        st.close();
    }

    @FXML
    public void loadAll(){
        try {
            String q = "select * from Complaints_show";
            ResultSet res = HelloApplication.statement.executeQuery(q);

            while (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ComRow.fxml"));
                Parent row = loader.load();

                ComRow comRow = loader.getController();

                comRow.comID.setText(res.getString("cmp_id"));
                comRow.empName.setText(res.getString("emp_firstName") + " " + res.getString("emp_lastName"));
                comRow.r_id.setText(res.getString("r_id"));
                comRow.complain.setText(res.getString("complain"));
                comRow.status.setText(res.getString("status"));

                comEntry.getChildren().add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void Filter(){
        selectedStatus = comboBox.getValue();
        comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedStatus = newValue;
        });
        comboBox.setValue(selectedStatus);

        FilteredList<String> filteredItems = new FilteredList<String>(items, p -> true);
        comboBox.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            final TextField editor = comboBox.getEditor();
            final String selected = (String)comboBox.getSelectionModel().getSelectedItem();

            System.out.println("selected: " + selected);
            Platform.runLater(() -> {
                if (selected == null || !selected.equals(editor.getText())) {
                    filteredItems.setPredicate(item -> {
                        if (item.toUpperCase().startsWith(newValue.toUpperCase())) {
                            return true;
                        } else {
                            return false;
                        }
                    });
                }
            });
        });
        comboBox.setItems(filteredItems);
        comEntry.getChildren().clear();


        try {
            selectedStatus = comboBox.getSelectionModel().getSelectedItem();

            String query = "SELECT * FROM Complaints_show WHERE status = '" + selectedStatus + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ComRow.fxml"));
                Parent row = loader.load();

                ComRow comRow = loader.getController();

                comRow.comID.setText(resultSet.getString("cmp_id"));
                comRow.empName.setText(resultSet.getString("emp_firstName") + " " + resultSet.getString("emp_lastName"));
                comRow.r_id.setText(resultSet.getString("r_id"));
                comRow.complain.setText(resultSet.getString("complain"));
                comRow.status.setText(resultSet.getString("status"));

                comEntry.getChildren().add(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    protected void filter_by_rooms(){
        comEntry.getChildren().clear();
        try{
            String query = "select * from Complaints_show where r_id between " + from_tf.getText() + " " +
                    "and " + to_tf.getText();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ComRow.fxml"));
                Parent row = loader.load();

                ComRow comRow = loader.getController();

                comRow.comID.setText(resultSet.getString("cmp_id"));
                comRow.empName.setText(resultSet.getString("emp_firstName") + " " + resultSet.getString("emp_lastName"));
                comRow.r_id.setText(resultSet.getString("r_id"));
                comRow.complain.setText(resultSet.getString("complain"));
                comRow.status.setText(resultSet.getString("status"));

                comEntry.getChildren().add(row);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public void Common() throws SQLException, IOException {
        String common = "select * from Complaints_show where complain like '%" + commonField.getText() +  "%'";
        ResultSet res = statement.executeQuery(common);

        comEntry.getChildren().clear();
        while (res.next()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComRow.fxml"));
            Parent row = loader.load();

            ComRow comRow = loader.getController();

            comRow.comID.setText(res.getString("cmp_id"));
            comRow.empName.setText(res.getString("emp_firstName") + " " + res.getString("emp_lastName"));
            comRow.r_id.setText(res.getString("r_id"));
            comRow.complain.setText(res.getString("complain"));
            comRow.status.setText(res.getString("status"));

            comEntry.getChildren().add(row);
        }

    }
}
