package sample;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.awt.*;
import java.awt.print.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReservationScreenController implements Initializable {
    public JFXTextField name;
    public JFXTextField phone;
    public JFXTextField address;
    public JFXTextField email;
    public JFXTextField duration;
    public JFXTextField num_people;
    public JFXTextField paymentType;
    public JFXTextField roomType;
    public JFXTextField roomNum;
    public JFXTextField price;
    public JFXTextField services;
    public JFXTextField total;
    public JFXDatePicker startDate;
    public JFXDatePicker endDate;
    public StackPane stackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void back(MouseEvent mouseEvent) {

        Stage home = new Stage();
        Parent root = null;

        try {
            root = FXMLLoader.load(getClass().getResource("HomeScreen.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage current = (Stage) roomNum.getScene().getWindow();
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

    public void rest(MouseEvent mouseEvent) {
        name.setText("");
        email.setText("");
        address.setText("");
        phone.setText("");
        num_people.setText("");
        paymentType.setText("");
        duration.setText("");
        roomType.setText("");
        roomNum.setText("");
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now());
        price.setText("");
        services.setText("");
        total.setText("");

    }

    public void book(MouseEvent mouseEvent) {

        int res = 0;

        String sql = "INSERT INTO customer (name,email,address,phone,numOfPeople,paymentType,duration,roomType,roomNumber,startDate,endDate,price,services,total) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setString(1, name.getText().toString());
            preparedStatement.setString(2, email.getText().toString());
            preparedStatement.setString(3, address.getText().toString());
            preparedStatement.setString(4, phone.getText().toString());
            preparedStatement.setString(5, num_people.getText().toString());
            preparedStatement.setString(6, paymentType.getText().toString());
            preparedStatement.setString(7, duration.getText().toString());
            preparedStatement.setString(8, roomType.getText().toString());
            preparedStatement.setString(9, roomNum.getText().toString());
            preparedStatement.setString(10, startDate.getValue().toString());
            preparedStatement.setString(11, endDate.getValue().toString());
            preparedStatement.setString(12, price.getText().toString());
            preparedStatement.setString(13, services.getText().toString());
            preparedStatement.setString(14, total.getText().toString());


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
            updateStatus();
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

    private void updateStatus() {
        String text = roomNum.getText().toString().trim();
        String sql = "UPDATE room SET roomStatus=? WHERE roomNumber=?";
        Connection connection = DBConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "busy");
            preparedStatement.setString(2, text);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void print(MouseEvent mouseEvent) {


        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(new BillPrintable(),getPageFormat(printerJob));
        try {
            printerJob.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }


    public class BillPrintable implements Printable {




        public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
                throws PrinterException
        {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());


                FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));

                int idLength=metrics.stringWidth("000");
                int amtLength=metrics.stringWidth("000000");
                int qtyLength=metrics.stringWidth("00000");
                int priceLength=metrics.stringWidth("000000");
                int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;



                int productPosition = 0;
                int discountPosition= prodLength+5;
                int pricePosition = discountPosition +idLength+10;
                int qtyPosition=pricePosition + priceLength + 4;
                int amtPosition=qtyPosition + qtyLength;



                try{
                    /*Draw Header*/
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    int headerRectHeighta=40;

                    String  user_name=name.getText();
                    String phone=email.getText();
                    String add=address.getText();
                    String payentType=duration.getText();
                    String num=num_people.getText();


                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawString("-------------------------------------",12,y);y+=yShift;
                    g2d.drawString("      Hotel Bill Receipt        ",12,y);y+=yShift;
                    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("                                     ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                    g2d.drawString("  Name                    " +user_name+"  ",10,y);y+=yShift;
                    g2d.drawString("  Address                 " +add+"  ",10,y);y+=yShift;
                    g2d.drawString("  Payment                 " +payentType+"  ",10,y);y+=yShift;
                    g2d.drawString("  Number Of People        " +num+"  ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Total amount:      sum=" +total.getText().toString()+"           ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("          Hotel Phone Number         ",10,y);y+=yShift;
                    g2d.drawString("           +91 9865141001            ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("      THANK YOU FOR YOUR VISIT       ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;



                }
                catch(Exception r){
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }

    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight =8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

}
