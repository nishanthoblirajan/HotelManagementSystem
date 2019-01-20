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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeesScreenController implements Initializable {
    public JFXTextField username;
    public JFXTextField password;
    public JFXTextField fullName;
    public JFXTextField address;
    public JFXTextField phone;
    public JFXTextField salary;
    public JFXTextField userType;
    public JFXDatePicker startDate;
    public JFXTextField searchID;
    public JFXTreeTableView treeView;
    public StackPane stackPane;


    public void loadAllEmployees(String sql) {
        JFXTreeTableColumn<Users, String> id = new JFXTreeTableColumn<>("ID");
        id.setPrefWidth(100);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().id;
            }
        });

        JFXTreeTableColumn<Users, String> user_name = new JFXTreeTableColumn<>("User Name");
        user_name.setPrefWidth(100);
        user_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().username;
            }
        });

        JFXTreeTableColumn<Users, String> password = new JFXTreeTableColumn<>("Password");
        password.setPrefWidth(100);
        password.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().password;
            }
        });

        JFXTreeTableColumn<Users, String> full_name = new JFXTreeTableColumn<>("Full Name");
        full_name.setPrefWidth(100);
        full_name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().fullName;
            }
        });

        JFXTreeTableColumn<Users, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(100);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().address;
            }
        });

        JFXTreeTableColumn<Users, String> phone = new JFXTreeTableColumn<>("Phone");
        phone.setPrefWidth(100);
        phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().phone;
            }
        });

        JFXTreeTableColumn<Users, String> start_date = new JFXTreeTableColumn<>("Start Date");
        start_date.setPrefWidth(100);
        start_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().startDate;
            }
        });

        JFXTreeTableColumn<Users, String> salary = new JFXTreeTableColumn<>("Salary");
        salary.setPrefWidth(100);
        salary.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().salary;
            }
        });

        JFXTreeTableColumn<Users, String> user_type = new JFXTreeTableColumn<>("User Type");
        user_type.setPrefWidth(100);
        user_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Users, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Users, String> param) {
                return param.getValue().getValue().userType;
            }
        });


        ObservableList<Users> usersObservableList = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersObservableList.add(new Users(
                        resultSet.getInt(1) + "",
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<Users> usersTreeItem = new RecursiveTreeItem<Users>(usersObservableList, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(id, user_name, password, full_name, address, phone, start_date, salary, user_type);
        treeView.setRoot(usersTreeItem);
        treeView.setShowRoot(false);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllEmployees("SELECT * FROM users");


    }

    public void insert(MouseEvent mouseEvent) {

        int res = 0;

        String sql = "INSERT INTO users (username,password,fullName,address,phone,startDate,salary,userType) VALUES (?,?,?,?,?,?,?,?) ";

        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, username.getText().toString());
            preparedStatement.setString(2, password.getText().toString());
            preparedStatement.setString(3, fullName.getText().toString());
            preparedStatement.setString(4, address.getText().toString());
            preparedStatement.setString(5, phone.getText().toString());
            preparedStatement.setString(6, startDate.getValue().toString());
            preparedStatement.setString(7, salary.getText().toString());
            preparedStatement.setString(8, userType.getText().toString());

            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (res > 0) {
            Notifications notification = Notifications.create()
                    .title("Update successful")
                    .text("Data Added Successfully")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT);
            notification.darkStyle();
            notification.show();
        } else {
            Notifications notification = Notifications.create()
                    .title("Error")
                    .text("Update Error")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT);
            notification.darkStyle();
            notification.show();
        }

    }


    public void update(MouseEvent mouseEvent) {
        int res = 0;
        String sql = "UPDATE users SET username=?,password=?,fullName=?,address=?,phone=?,startDate=?,salary=?,userType=? WHERE id= ?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, username.getText().toString());
            preparedStatement.setString(2, password.getText().toString());
            preparedStatement.setString(3, fullName.getText().toString());
            preparedStatement.setString(4, address.getText().toString());
            preparedStatement.setString(5, phone.getText().toString());
            preparedStatement.setString(6, startDate.getValue().toString());
            preparedStatement.setString(7, salary.getText().toString());
            preparedStatement.setString(8, userType.getText().toString());
            preparedStatement.setString(9, searchID.getText().toString());

            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (res > 0) {
            Notifications notification = Notifications.create()
                    .title("Update successful")
                    .text("Data Updated Successfully")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT);
            notification.darkStyle();
            notification.show();
        } else {
            Notifications notification = Notifications.create()
                    .title("Error")
                    .text("Update Error")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT);
            notification.darkStyle();
            notification.show();
        }
    }

    public void delete(MouseEvent mouseEvent) {

        int res = 0;
        String sql = "DELETE FROM users WHERE id= ?";
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, searchID.getText().toString());

            res = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (res > 0) {
            Notifications notification = Notifications.create()
                    .title("Update successful")
                    .text("Data Deleted Successfully")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT);
            notification.darkStyle();
            notification.show();
        } else {
            Notifications notification = Notifications.create()
                    .title("Error")
                    .text("Delete Error")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT);
            notification.darkStyle();
            notification.show();
        }

    }


    public void clear(MouseEvent mouseEvent) {
        username.setText("");
        password.setText("");
        fullName.setText("");
        phone.setText("");
        address.setText("");
        salary.setText("");
        startDate.setValue(LocalDate.now());
        userType.setText("");
    }

    public void back(MouseEvent mouseEvent) {
        Stage home = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) username.getScene().getWindow();
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


        JFXDialog jfxDialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.CENTER);


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

        dialogLayout.setActions(okButton, cancelButton);
        jfxDialog.show();

    }

    public void search(MouseEvent mouseEvent) {
        String sql = "SELECT * FROM users WHERE id =?";

        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, searchID.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                username.setText(resultSet.getString(2));
                password.setText(resultSet.getString(3));
                fullName.setText(resultSet.getString(5));
                address.setText(resultSet.getString(4));
                salary.setText(resultSet.getString(7));
                userType.setText(resultSet.getString(8));
                //txt_date.setText(resultSet.getString(2));
            }
        } catch (SQLException ex) {
        }
        loadAllEmployees("SELECT * FROM users WHERE id='" + searchID.getText().trim() + "'");

    }
}
