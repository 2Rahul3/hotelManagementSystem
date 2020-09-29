package hotelManagementSystem;

import javafx.beans.property.SimpleStringProperty;

public class Room {

    private SimpleStringProperty roomType;
    private SimpleStringProperty roomNo;
    private SimpleStringProperty maxNoOfPeople;
    private SimpleStringProperty floorNo;
    private SimpleStringProperty phoneNo;
    private SimpleStringProperty rate;
    private SimpleStringProperty status;

    public Room() {
        roomType = new SimpleStringProperty();
        roomNo = new SimpleStringProperty();
        maxNoOfPeople = new SimpleStringProperty();
        floorNo = new SimpleStringProperty();
        phoneNo = new SimpleStringProperty();
        rate = new SimpleStringProperty();
        status = new SimpleStringProperty();
    }

    public Room(String roomType, String roomNo, String maxNoOfPeople, String floorNo, String phoneNo, String rate,
                String status) {
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNo = new SimpleStringProperty(roomNo);
        this.maxNoOfPeople = new SimpleStringProperty(maxNoOfPeople);
        this.floorNo = new SimpleStringProperty(floorNo);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.rate = new SimpleStringProperty(rate);
        this.status = new SimpleStringProperty(status);
    }


    public String getRoomType() {
        return roomType.get();
    }

    public SimpleStringProperty roomTypeProperty() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType.set(roomType);
    }

    public String getRoomNo() {
        return roomNo.get();
    }

    public SimpleStringProperty roomNoProperty() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo.set(roomNo);
    }

    public String getMaxNoOfPeople() {
        return maxNoOfPeople.get();
    }

    public SimpleStringProperty maxNoOfPeopleProperty() {
        return maxNoOfPeople;
    }

    public void setMaxNoOfPeople(String maxNoOfPeople) {
        this.maxNoOfPeople.set(maxNoOfPeople);
    }

    public String getFloorNo() {
        return floorNo.get();
    }

    public SimpleStringProperty floorNoProperty() {
        return floorNo;
    }

    public void setFloorNo(String floorNo) {
        this.floorNo.set(floorNo);
    }

    public String getPhoneNo() {
        return phoneNo.get();
    }

    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
    }

    public String getRate() {
        return rate.get();
    }

    public SimpleStringProperty rateProperty() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate.set(rate);
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
