package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminScreenController implements Initializable {

    public Pane pane_1;
    public Pane pane_2;
    public Pane pane_3;
    public Pane pane_4;
    public Pane pane_5;
    public StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void mouse_hover_1(MouseEvent mouseEvent) {

        pane_1.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px");
    }

    public void mouse_exit_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: white; -fx-background-radius: 6px");

    }

    public void mouse_hover_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px");


    }

    public void mouse_exit_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: white; -fx-background-radius: 6px");

    }


    public void mouse_hover_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px");

    }

    public void mouse_exit_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: white; -fx-background-radius: 6px");

    }

    public void mouse_hover_4(MouseEvent mouseEvent) {
        pane_4.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px");
    }

    public void mouse_exit_4(MouseEvent mouseEvent) {
        pane_4.setStyle("-fx-background-color: white; -fx-background-radius: 6px");

    }

    public void mouse_hover_5(MouseEvent mouseEvent) {
        pane_5.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px");
    }

    public void mouse_exit_5(MouseEvent mouseEvent) {
        pane_5.setStyle("-fx-background-color: white; -fx-background-radius: 6px");

    }

    public void homeScreen(MouseEvent mouseEvent) {

        Stage home = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) pane_1.getScene().getWindow();
        Scene scene = new Scene(root);

        home.setScene(scene);
        home.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        home.show();


    }

    public void employeesScreen(MouseEvent mouseEvent) {


        Stage employees = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("EmployeesScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) pane_1.getScene().getWindow();
        Scene scene = new Scene(root);

        employees.setScene(scene);
        employees.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        employees.show();

    }

    public void customersScreen(MouseEvent mouseEvent) {

        Stage customer = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("CustomerInfoScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) pane_1.getScene().getWindow();
        Scene scene = new Scene(root);

        customer.setScene(scene);
        customer.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        customer.show();
    }

    public void logout(MouseEvent mouseEvent) {

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        dialogLayout.setHeading(new Text("Alert"));
        dialogLayout.setBody(new Text("Do you want to Logout?"));


        JFXButton okButton = new JFXButton("OK");
        JFXButton cancelButton = new JFXButton("Cancel");



        JFXDialog jfxDialog = new JFXDialog(stackPane,dialogLayout,JFXDialog.DialogTransition.CENTER);


        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Stage home = new Stage();
                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Stage current = (Stage) pane_1.getScene().getWindow();
                Scene scene = new Scene(root);

                home.setScene(scene);
                home.initStyle(StageStyle.TRANSPARENT);
                current.hide();
                home.show();


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

    public void exit(MouseEvent mouseEvent) {

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
