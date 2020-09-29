package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class newReserveScreenController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameTextField, phoneNoTextField, emailTextField;

    @FXML
    private TextArea addressTextArea;

    @FXML
    private ComboBox<String> noofPeopleComboBox, roomTypeComboBox, serviceComboBox, roomNoComboBox, paymentTypeComboBox;

    @FXML
    private DatePicker arrivalDatePicker, departureDatePicker;

    @FXML
    private Label priceLabel, durationLabel, totalLabel;

    private ObservableList<String> numberOfPeople = FXCollections.observableArrayList("1", "2", "3", "4");

    private ObservableList<String> services = FXCollections.observableArrayList("None", "Pet", "Extra Bed", "Pool Side");

    private ObservableList<String> paymentMethods = FXCollections.observableArrayList("Cash", "Visa", "Mastercard", "Amex");

    private ObservableList<String> roomTypes;

    private ObservableList<Room> rooms;

    private ObservableList<Reservation> reservations;

    private Set<Integer> reservationIdList = new HashSet<>();

    private DataBase dataBase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataBase = new DataBase();
        dataBase.openConnection();

        noofPeopleComboBox.setItems(numberOfPeople);
        noofPeopleComboBox.setValue("2");
        serviceComboBox.setItems(services);
        serviceComboBox.setValue("None");
        paymentTypeComboBox.setItems(paymentMethods);
        paymentTypeComboBox.setValue("Cash");

        dataBase.loadRooms();
        rooms = dataBase.getRooms();

        dataBase.loadReservations();
        reservations = dataBase.getReservations();

        roomTypes = FXCollections.observableArrayList();
        loadRoomTypes();

    }

    private void loadRoomTypes() {

        availableRoomTypes();
        Collections.sort(roomTypes, new Comparator<String>() {
            @Override
            public int compare(String roomType1, String roomType2) {
                return roomType1.compareTo(roomType2);
            }
        });
        roomTypeComboBox.setItems(roomTypes);
        roomTypeComboBox.setValue("Select Room Type");

    }

    private void availableRoomTypes() {

        roomTypes.add("Select Room Type");
        Set<String> uniqueValues = new HashSet<>();

        for (Room room : rooms) {

            String outOfOrder = "Out of Order";

            if (!room.getStatus().equals(outOfOrder)) {

                uniqueValues.add(room.getRoomType());

            }
        }
        roomTypes.addAll(uniqueValues);
    }

    @FXML
    public void cancelButtonClicked() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomeScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage homeScreen = new Stage();
        Stage current = (Stage) cancelButton.getScene().getWindow();
        Scene scene = new Scene(root);
        homeScreen.setScene(scene);
        homeScreen.initStyle(StageStyle.TRANSPARENT);
        dataBase.closeConnection();
        current.hide();
        homeScreen.show();
    }

    @FXML
    public void addRoomNumbersToComboBox() {

        String roomType = roomTypeComboBox.getValue();
        String outOfOrder = "Out of Order";
        ObservableList<String> roomNo = FXCollections.observableArrayList();

        for (Room room : rooms) {

            if ((room.getRoomType().equals(roomType)) && (!room.getStatus().equals(outOfOrder))) {

                roomNo.add(room.getRoomNo());
                priceLabel.setText(room.getRate());

            }
        }
        Collections.sort(roomNo, new Comparator<String>() {
            @Override
            public int compare(String number1, String number2) {
                return number1.compareTo(number2);
            }
        });
        roomNo.add("Assign Room");
        roomNoComboBox.setItems(roomNo);
        roomNoComboBox.setValue("Assign Room");
    }

    @FXML
    public void bookButtonClicked() {

        Reservation reservation = createReservation();

        dataBase.addNewReservation(reservation);
        dataBase.loadReservations();
        reservations = dataBase.getReservations();

        resetButtonClicked();

    }

    private Reservation createReservation() {

        Reservation reservation = new Reservation();

        for (Reservation reservation1 : reservations) {

            int idInteger = Integer.parseInt(reservation1.getId());
            reservationIdList.add(idInteger);

        }

        for (int i = 1; i < (reservationIdList.size() + 2); i++) {

            boolean isIdExists = reservationIdList.contains(i);

            if (!isIdExists) {

                String idString = Integer.toString(i);
                reservation.setId(idString);

            }
        }

        reservation.setName(nameTextField.getText().trim());
        reservation.setPhoneNo(phoneNoTextField.getText().trim());
        reservation.setAddress(addressTextArea.getText().trim());
        reservation.setEmail(emailTextField.getText().trim());
        reservation.setDuration(durationLabel.getText());
        reservation.setNoOfPeople(noofPeopleComboBox.getValue());
        reservation.setPaymentMethod(paymentTypeComboBox.getValue());
        reservation.setRoomType(roomTypeComboBox.getValue());
        reservation.setRoomNo(roomNoComboBox.getValue());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        reservation.setArrivalDate(arrivalDatePicker.getValue().format(df));
        reservation.setDepartureDate(departureDatePicker.getValue().format(df));

        reservation.setDuration(durationLabel.getText());
        reservation.setPricePerNight(priceLabel.getText());
        reservation.setServices(serviceComboBox.getValue());
        reservation.setTotal(totalLabel.getText());

        return reservation;

    }

    @FXML
    public void setDurationValue() {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        LocalDate arrivalDate = arrivalDatePicker.getValue();
        LocalDate departureDate = departureDatePicker.getValue();
        long durationLong = ChronoUnit.DAYS.between(arrivalDate, departureDate);
        String durationString = Long.toString(durationLong);
        durationLabel.setText(durationString);

        double pricePerNight = Double.parseDouble(priceLabel.getText());
        int duration = Integer.parseInt(durationLabel.getText());

        double totalCost = pricePerNight * duration;
        String totalCostString = String.format("%.2f", totalCost);
        totalLabel.setText(totalCostString);

    }

    @FXML
    public void resetButtonClicked() {

        nameTextField.clear();
        emailTextField.clear();
        addressTextArea.clear();
        phoneNoTextField.clear();
        noofPeopleComboBox.setValue("2");
        roomTypes.clear();
        reservationIdList.clear();
        loadRoomTypes();
        serviceComboBox.setValue("None");
        roomNoComboBox.setValue("Assign Room");
        arrivalDatePicker.setValue(LocalDate.now());
        departureDatePicker.setValue(LocalDate.now().plusDays(1));
        durationLabel.setText("0");
        priceLabel.setText("--$--");
        totalLabel.setText("--$--");
        paymentTypeComboBox.setValue("Cash");

    }
}
