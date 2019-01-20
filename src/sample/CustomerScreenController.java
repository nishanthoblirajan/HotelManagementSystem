package sample;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
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

public class CustomerScreenController implements Initializable {
    public JFXTreeTableView treeView;
    public JFXTextField searchText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadAllCustomers("SELECT * FROM customer");

    }


    public void loadAllCustomers(String sql){
        JFXTreeTableColumn<Customer, String> id = new JFXTreeTableColumn<>("ID");
        id.setPrefWidth(100);
        id.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().id;
            }
        });

        JFXTreeTableColumn<Customer, String> name = new JFXTreeTableColumn<>("Name");
        name.setPrefWidth(100);
        name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<Customer, String> email = new JFXTreeTableColumn<>("Email");
        email.setPrefWidth(100);
        email.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().email;
            }
        });

        JFXTreeTableColumn<Customer, String> address = new JFXTreeTableColumn<>("Address");
        address.setPrefWidth(100);
        address.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().address;
            }
        });
        JFXTreeTableColumn<Customer, String> phone = new JFXTreeTableColumn<>("Phone");
        phone.setPrefWidth(100);
        phone.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().phone;
            }
        });

        JFXTreeTableColumn<Customer, String> num_of_people = new JFXTreeTableColumn<>("Num Of People");
        num_of_people.setPrefWidth(100);
        num_of_people.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().numOfPeople;
            }
        });

        JFXTreeTableColumn<Customer, String> payment_type = new JFXTreeTableColumn<>("Payment Type");
        payment_type.setPrefWidth(100);
        payment_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().paymentType;
            }
        });

        JFXTreeTableColumn<Customer, String> duration = new JFXTreeTableColumn<>("Duration");
        duration.setPrefWidth(100);
        duration.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().duration;
            }
        });

        JFXTreeTableColumn<Customer, String> room_type = new JFXTreeTableColumn<>("Room Type");
        room_type.setPrefWidth(100);
        room_type.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().roomType;
            }
        });


        JFXTreeTableColumn<Customer, String> room_number = new JFXTreeTableColumn<>("Room Number");
        room_number.setPrefWidth(100);
        room_number.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().roomNumber;
            }
        });

        JFXTreeTableColumn<Customer, String> start_date = new JFXTreeTableColumn<>("Start Date");
        start_date.setPrefWidth(100);
        start_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().startDate;
            }
        });

        JFXTreeTableColumn<Customer, String> end_date = new JFXTreeTableColumn<>("End Date");
        end_date.setPrefWidth(100);
        end_date.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().endDate;
            }
        });

        JFXTreeTableColumn<Customer, String> price = new JFXTreeTableColumn<>("Price");
        price.setPrefWidth(100);
        price.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().price;
            }
        });

        JFXTreeTableColumn<Customer, String> services = new JFXTreeTableColumn<>("Services");
        services.setPrefWidth(100);
        services.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().services;
            }
        });

        JFXTreeTableColumn<Customer, String> total = new JFXTreeTableColumn<>("Total");
        total.setPrefWidth(100);
        total.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Customer, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Customer, String> param) {
                return param.getValue().getValue().total;
            }
        });



        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                customerObservableList.add(new Customer(
                        resultSet.getInt(1)+"",
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getString(14),
                        resultSet.getString(15)
                        ));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final TreeItem<Customer> customerTreeItem = new RecursiveTreeItem<Customer>(customerObservableList, RecursiveTreeObject::getChildren);

        treeView.getColumns().setAll(id,name,email,address,phone,num_of_people, payment_type,duration,room_type,room_number,start_date,end_date,price,services,total);
        treeView.setRoot(customerTreeItem);
        treeView.setShowRoot(false);

    }

    public void searchByRoomNumber(MouseEvent mouseEvent) {
        loadAllCustomers("SELECT * FROM customer WHERE roomNumber ='"+searchText.getText().toString().trim()+"'");

    }

    public void back(MouseEvent mouseEvent) {
        Stage home = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) searchText.getScene().getWindow();
        Scene scene = new Scene(root);

        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        home.show();
    }
}
