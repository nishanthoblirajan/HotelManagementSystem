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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerInfoScreenController implements Initializable {
    public LineChart lineChart;
    public StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        XYChart.Series y2015 = new XYChart.Series();
        y2015.setName("2015");

        y2015.getData().add(new XYChart.Data("Jan", 100));
        y2015.getData().add(new XYChart.Data("Feb", 150));
        y2015.getData().add(new XYChart.Data("Mar", 200));
        y2015.getData().add(new XYChart.Data("Apr", 100));
        y2015.getData().add(new XYChart.Data("May", 200));
        y2015.getData().add(new XYChart.Data("Jun", 250));
        y2015.getData().add(new XYChart.Data("Jul", 290));
        y2015.getData().add(new XYChart.Data("Aug", 320));
        y2015.getData().add(new XYChart.Data("Sep", 200));
        y2015.getData().add(new XYChart.Data("Oct", 300));
        y2015.getData().add(new XYChart.Data("Nov", 390));
        y2015.getData().add(new XYChart.Data("Dec", 470));

        XYChart.Series y2016 = new XYChart.Series();
        y2016.setName("2016");
        y2016.getData().add(new XYChart.Data("Jan", 120));
        y2016.getData().add(new XYChart.Data("Feb", 160));
        y2016.getData().add(new XYChart.Data("Mar", 200));
        y2016.getData().add(new XYChart.Data("Apr", 100));
        y2016.getData().add(new XYChart.Data("May", 320));
        y2016.getData().add(new XYChart.Data("Jun", 340));
        y2016.getData().add(new XYChart.Data("Jul", 310));
        y2016.getData().add(new XYChart.Data("Aug", 260));
        y2016.getData().add(new XYChart.Data("Sep", 300));
        y2016.getData().add(new XYChart.Data("Oct", 330));
        y2016.getData().add(new XYChart.Data("Nov", 390));
        y2016.getData().add(new XYChart.Data("Dec", 430));

        XYChart.Series y2017 = new XYChart.Series();
        y2017.setName("2017");
        y2017.getData().add(new XYChart.Data("Jan", 150));
        y2017.getData().add(new XYChart.Data("Feb", 200));
        y2017.getData().add(new XYChart.Data("Mar", 250));
        y2017.getData().add(new XYChart.Data("Apr", 350));
        y2017.getData().add(new XYChart.Data("May", 200));
        y2017.getData().add(new XYChart.Data("Jun", 280));
        y2017.getData().add(new XYChart.Data("Jul", 350));
        y2017.getData().add(new XYChart.Data("Aug", 400));
        y2017.getData().add(new XYChart.Data("Sep", 430));
        y2017.getData().add(new XYChart.Data("Oct", 460));
        y2017.getData().add(new XYChart.Data("Nov", 490));
        y2017.getData().add(new XYChart.Data("Dec", 470));

        lineChart.getData().addAll(y2015, y2016, y2017);
    }

    public void back(MouseEvent mouseEvent) {
        Stage home = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) lineChart.getScene().getWindow();
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
}
