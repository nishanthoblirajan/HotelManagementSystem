package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Users extends RecursiveTreeObject<Users> {

    StringProperty id;
    StringProperty username;
    StringProperty password;
    StringProperty fullName;
    StringProperty address;
    StringProperty phone;
    StringProperty startDate;
    StringProperty salary;
    StringProperty userType;

    public Users(String id, String username, String password, String fullName, String address, String phone, String startDate, String salary, String userType) {
        this.id = new SimpleStringProperty(id);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.fullName = new SimpleStringProperty(fullName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
        this.startDate = new SimpleStringProperty(startDate);
        this.salary = new SimpleStringProperty(salary);
        this.userType = new SimpleStringProperty(userType);
    }

    public Users() {
        super();

    }
}
