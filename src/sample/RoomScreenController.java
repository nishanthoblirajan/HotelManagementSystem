package sample;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RoomScreenController  implements Initializable {
    public JFXTreeTableView treeView;
    public JFXTextField search_text;
    public JFXCheckBox busy;
    public JFXCheckBox available;
    public StackPane stackPane;

    String status = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadAllRooms("SELECT * FROM room");

    }



    public void loadAllRooms(String sql) {

        JFXTreeTableColumn<Room, String> room_id = new JFXTreeTableColumn<>("Room Id");
        room_id.setPrefWidth(100);
        room_id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().id;
            }
        });


        JFXTreeTableColumn<Room, String> room_type = new JFXTreeTableColumn<>("Room Type");
        room_type.setPrefWidth(100);
        room_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().roomType;
            }
        });


        JFXTreeTableColumn<Room, String> room_number = new JFXTreeTableColumn<>("Room Number");
        room_number.setPrefWidth(100);
        room_number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().roomNumber;
            }
        });


        JFXTreeTableColumn<Room, String> number_of_people = new JFXTreeTableColumn<>("Number Of People");
        number_of_people.setPrefWidth(100);
        number_of_people.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().numberOfPeople;
            }
        });


        JFXTreeTableColumn<Room, String> floor_number = new JFXTreeTableColumn<>("Floor Number");
        floor_number.setPrefWidth(100);
        floor_number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().floorNumber;
            }
        });


        JFXTreeTableColumn<Room, String> room_phone = new JFXTreeTableColumn<>("Room Phone");
        room_phone.setPrefWidth(100);
        room_phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().roomPhone;
            }
        });


        JFXTreeTableColumn<Room, String> room_price = new JFXTreeTableColumn<>("Room Price");
        room_price.setPrefWidth(100);
        room_price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().roomPrice;
            }
        });


        JFXTreeTableColumn<Room, String> room_status = new JFXTreeTableColumn<>("Room Status");
        room_status.setPrefWidth(100);
        room_status.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Room, String> param) {
                return param.getValue().getValue().roomStatus;
            }
        });


        ObservableList<Room> roomObservableList = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                roomObservableList.add(new Room(
                        resultSet.getInt(1)+"",
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        final TreeItem<Room> roomTreeItem = new RecursiveTreeItem<Room>(roomObservableList, RecursiveTreeObject::getChildren);
        treeView.getColumns().setAll(room_id,room_type,room_number, number_of_people,floor_number,room_phone,room_price,room_status);
        treeView.setRoot(roomTreeItem);
        treeView.setShowRoot(false);
    }


    public void searchByRoomNumber(MouseEvent mouseEvent) {

        loadAllRooms("SELECT * FROM room WHERE roomNumber ='"+search_text.getText().toString().trim()+"'");
    }

    public void makeItAvailable(MouseEvent mouseEvent) {

        String text = search_text.getText().toString().trim();
        int res = 0;

        String sql = "UPDATE room SET roomStatus=? WHERE roomNumber=?";
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"available");
            preparedStatement.setString(2,text);

            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(res>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data Update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Record updated successfully!");
            alert.showAndWait();
            loadAllRooms("SELECT * FROM room WHERE 1");
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data Update");
            alert.setHeaderText("Information Dialog");
            alert.setContentText("Error");
            alert.showAndWait();
        }

    }

    public void searchByStatus(MouseEvent mouseEvent) {

        loadAllRooms(status);
        
    }

    public void searchBusy(ActionEvent actionEvent) {

        available.setSelected(false);
        status = "SELECT * FROM room WHERE roomStatus = 'busy'";
    }

    public void searchAvailable(ActionEvent actionEvent) {
        busy.setSelected(false);
        status = "SELECT * FROM room WHERE roomStatus = 'available'";

    }

    public void goBack(MouseEvent mouseEvent) {
        Stage home = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) search_text.getScene().getWindow();
        Scene scene = new Scene(root);

        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        home.show();
    }

    public void close(MouseEvent mouseEvent) {
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Close"));
        dialogLayout.setBody(new Text("Do you want to exit?"));


        JFXButton okButton = new JFXButton("OK");
        JFXButton cancelButton = new JFXButton("Cancel");



        JFXDialog jfxDialog = new JFXDialog(stackPane,dialogLayout,JFXDialog.DialogTransition.CENTER);


        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                jfxDialog.close();
            }
        });

        dialogLayout.setActions(okButton,cancelButton);
        jfxDialog.show();

    }
}
