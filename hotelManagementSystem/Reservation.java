package hotelManagementSystem;

import javafx.beans.property.SimpleStringProperty;

public class Reservation {

    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty phoneNo;
    private SimpleStringProperty address;
    private SimpleStringProperty email;
    private SimpleStringProperty duration;
    private SimpleStringProperty noOfPeople;
    private SimpleStringProperty paymentMethod;
    private SimpleStringProperty roomType;
    private SimpleStringProperty roomNo;
    private SimpleStringProperty arrivalDate;
    private SimpleStringProperty departureDate;
    private SimpleStringProperty pricePerNight;
    private SimpleStringProperty services;
    private SimpleStringProperty total;


    public Reservation() {
        id = new SimpleStringProperty();
        name = new SimpleStringProperty();
        phoneNo = new SimpleStringProperty();
        address = new SimpleStringProperty();
        email = new SimpleStringProperty();
        duration = new SimpleStringProperty();
        noOfPeople = new SimpleStringProperty();
        paymentMethod = new SimpleStringProperty();
        roomType = new SimpleStringProperty();
        roomNo = new SimpleStringProperty();
        arrivalDate = new SimpleStringProperty();
        departureDate = new SimpleStringProperty();
        pricePerNight = new SimpleStringProperty();
        services = new SimpleStringProperty();
        total = new SimpleStringProperty();
    }

    public Reservation(String id, String name, String phoneNo, String address, String email, String duration, String noOfPeople,
                       String paymentMethod, String roomType, String roomNo, String arrivalDate, String departureDate,
                       String pricePerNight, String services, String total) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.address = new SimpleStringProperty(address);
        this.email = new SimpleStringProperty(email);
        this.duration = new SimpleStringProperty(duration);
        this.noOfPeople = new SimpleStringProperty(noOfPeople);
        this.paymentMethod = new SimpleStringProperty(paymentMethod);
        this.roomType = new SimpleStringProperty(roomType);
        this.roomNo = new SimpleStringProperty(roomNo);
        this.arrivalDate = new SimpleStringProperty(arrivalDate);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.pricePerNight = new SimpleStringProperty(pricePerNight);
        this.services = new SimpleStringProperty(services);
        this.total = new SimpleStringProperty(total);
    }


    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getDuration() {
        return duration.get();
    }

    public SimpleStringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getNoOfPeople() {
        return noOfPeople.get();
    }

    public SimpleStringProperty noOfPeopleProperty() {
        return noOfPeople;
    }

    public void setNoOfPeople(String noOfPeople) {
        this.noOfPeople.set(noOfPeople);
    }

    public String getPaymentMethod() {
        return paymentMethod.get();
    }

    public SimpleStringProperty paymentMethodProperty() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod.set(paymentMethod);
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

    public String getArrivalDate() {
        return arrivalDate.get();
    }

    public SimpleStringProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public SimpleStringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getPricePerNight() {
        return pricePerNight.get();
    }

    public SimpleStringProperty pricePerNightProperty() {
        return pricePerNight;
    }

    public void setPricePerNight(String pricePerNight) {
        this.pricePerNight.set(pricePerNight);
    }

    public String getServices() {
        return services.get();
    }

    public SimpleStringProperty servicesProperty() {
        return services;
    }

    public void setServices(String services) {
        this.services.set(services);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty totalProperty() {
        return total;
    }

    public void setTotal(String total) {
        this.total.set(total);
    }
}
