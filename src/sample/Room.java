package sample;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Room extends RecursiveTreeObject<Room> {

    StringProperty id;
    StringProperty roomType;
    StringProperty roomNumber;
    StringProperty numberOfPeople;
    StringProperty floorNumber;
    StringProperty roomPhone;
    StringProperty roomPrice;
    StringProperty roomStatus;

    public Room() {
        super();
    }

    public Room(String id, String roomType, String roomNumber, String numberOfPeople, String floorNumber, String roomPhone, String roomPrice, String roomStatus) {

        this.id = new SimpleStringProperty(id);
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.numberOfPeople = new SimpleStringProperty(numberOfPeople);
        this.floorNumber = new SimpleStringProperty(floorNumber);
        this.roomPhone = new SimpleStringProperty(roomPhone);
        this.roomPrice = new SimpleStringProperty(roomPrice);
        this.roomStatus = new SimpleStringProperty(roomStatus);
        }
}
