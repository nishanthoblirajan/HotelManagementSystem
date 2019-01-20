package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sun.util.resources.ro.CurrencyNames_ro_RO;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginScreenController implements Initializable {



    @FXML
    JFXTextField username;

    @FXML
    JFXTextField password;

    @FXML
    StackPane stackPane;


    public void loginButton(MouseEvent mouseEvent) {

        /*If no user name is present*/

        /*Import controlsFX library for customised controls*/

        Image image = new Image("sample/delete.png");
        if(username.getText().toString().equals("")){
            Notifications notification = Notifications.create()
                    .title("Error")
                    .text("User name is not entered.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }else if(password.getText().toString().equals("")){
            Notifications notification = Notifications.create()
                    .title("Error")
                    .text("Password is not entered.")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }else {


            boolean isExist = false;
            String userPass = "";
            String userType = "";


            String sql = "select * from users where username='" + username.getText().toString().trim() + "'";

            Connection connection = DBConnection.getConnection();

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();


                while (resultSet.next()) {
//                if the user exist change the boolean to true and get the userpassword and the usertype
                    isExist = true;
                    userPass = resultSet.getString(3);
                    userType = resultSet.getString(9);

                }

                if (isExist) {
                    if (password.getText().toString().trim().equals(userPass)) {
                        if (userType.equals("admin")) {
//                        if user is admin ----> Show admin screen
                            Stage adminScreen = new Stage();
                            Parent root = null;

                            try {
                                root = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Stage current = (Stage) username.getScene().getWindow();
                            Scene scene = new Scene(root, 1366, 730);

                            adminScreen.setScene(scene);
                            adminScreen.initStyle(StageStyle.TRANSPARENT);

                            current.hide();
                            adminScreen.show();

                        } else {

//                        if user is normal ----> Show normal screen

                            Stage homeScreen = new Stage();
                            Parent root = null;

                            try {
                                root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));

                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Stage current = (Stage) username.getScene().getWindow();
                            Scene scene = new Scene(root, 1366, 730);

                            homeScreen.setScene(scene);
                            homeScreen.initStyle(StageStyle.TRANSPARENT);

                            current.hide();
                            homeScreen.show();

                        }
                    }else{
                        Notifications notification = Notifications.create()
                                .title("Error")
                                .text("Credentials not valid")
                                .hideAfter(Duration.seconds(3))
                                .position(Pos.BOTTOM_LEFT)
                                .graphic(new ImageView(image));
                        notification.darkStyle();
                        notification.show();


                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelButton(MouseEvent mouseEvent) {

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
