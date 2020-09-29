package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageRoomsScreenController implements Initializable {

    @FXML
    private TextField phoneField, rateField;

    @FXML
    private ComboBox<String> roomTypeCombo, roomNoCombo, noOfPeopleCombo, floorCombo, roomStatusCombo;

    @FXML
    private Button addButton, doneButton;

    @FXML
    private TableView<Room> roomDetailTable;

    private ObservableList<String> roomTypes = FXCollections.observableArrayList("1 King, Non-Smk", "1 King, Smk", "2 Queen, Non-Smk",
            "2 Queen, Smk");

    private ObservableList<String> roomNumbers = FXCollections.observableArrayList("101", "102", "103", "104", "105", "106", "107", "108", "109", "110",
            "201", "202", "203", "204", "205", "206", "207", "208", "209", "210");

    private ObservableList<String> numberOfPeople = FXCollections.observableArrayList("1", "2", "3", "4");

    private ObservableList<String> floors = FXCollections.observableArrayList("1", "2");

    private ObservableList<String> roomStatus = FXCollections.observableArrayList("Available", "Busy", "Out of Order");

    private DataBase dataBase;

    private ObservableList<Room> rooms;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        roomTypeCombo.setItems(roomTypes);
        roomTypeCombo.setValue("2 Queen, Non-Smk");
        roomNoCombo.setItems(roomNumbers);
        roomNoCombo.setValue("101");
        noOfPeopleCombo.setItems(numberOfPeople);
        noOfPeopleCombo.setValue("2");
        floorCombo.setItems(floors);
        floorCombo.setValue("1");
        roomStatusCombo.setItems(roomStatus);
        roomStatusCombo.setValue("Available");

        dataBase = new DataBase();
        rooms = FXCollections.observableArrayList();
        dataBase.openConnection();
        dataBase.loadRooms();
        rooms = dataBase.getRooms();
        rearrangeRoomsByNo(rooms);
    }

    @FXML
    private void doneButtonClicked() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage adminScreen = new Stage();
        Stage current = (Stage) doneButton.getScene().getWindow();
        Scene scene = new Scene(root);
        adminScreen.setScene(scene);
        adminScreen.initStyle(StageStyle.TRANSPARENT);
        dataBase.closeConnection();
        current.hide();
        adminScreen.show();

    }

    @FXML
    private void addButtonClicked() {


        roomNoCombo.setStyle("-fx-border-color: none");
        phoneField.setStyle("-fx-border-color: none");
        rateField.setStyle("-fx-border-color: none");
        Room room = createRoom();

        if (findRoom(room)) {

            if(validatePhoneNo(room)) {

                if(validateRate(room)) {

                    dataBase.addRoom(room);
                    rooms.add(room);
                    rearrangeRoomsByNo(rooms);

                }
            }
        }
    }

    private Room createRoom() {

        return new Room(roomTypeCombo.getValue(), roomNoCombo.getValue(), noOfPeopleCombo.getValue(),
                floorCombo.getValue(), phoneField.getText().trim(), rateField.getText().trim(), roomStatusCombo.getValue());
    }

    private void rearrangeRoomsByNo (ObservableList<Room> rooms) {

        Collections.sort(rooms, new Comparator<Room>() {
            @Override
            public int compare(Room room1, Room room2) {
                return room1.getRoomNo().compareTo(room2.getRoomNo());
            }
        });
        roomDetailTable.setItems(rooms);

    }

    private boolean findRoom(Room room) {

        for (Room room1 : rooms) {

            if (room.getRoomNo().equals(room1.getRoomNo())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Similar Room number Found.");
                alert.setHeaderText(null);
                alert.setContentText("Room " + room.getRoomNo() + " is already exists.");
                roomNoCombo.setStyle("-fx-border-color: red");
                alert.showAndWait();
                return false;
            }
        }
        roomNoCombo.setStyle("-fx-border-color: none");
        return true;
    }

    private boolean validatePhoneNo (Room room) {

        try {
            long phoneNo = Long.parseLong(room.getPhoneNo());
            phoneField.setStyle("-fx-border-color: none");
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong input");
            alert.setContentText("Please add valid Phone number.");
            alert.setHeaderText(null);
            phoneField.setStyle("-fx-border-color: red");
            alert.showAndWait();
            return false;
        }
    }

    private boolean validateRate (Room room) {

        try {
            double phoneNo = Double.parseDouble(room.getRate());
            rateField.setStyle("-fx-border-color: none");
            return true;
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong input");
            alert.setContentText("Please enter valid Room Rate. Number only.");
            alert.setHeaderText(null);
            rateField.setStyle("-fx-border-color: red");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    public void removeButtonClicked() {

        Room roomToDelete = roomDetailTable.getSelectionModel().getSelectedItem();

        if (roomToDelete == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Room selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select Room to be deleted.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Remove Room");
        alert.setHeaderText("Confirm the Room to be removed.");
        alert.setContentText("Delete Room : " + roomToDelete.getRoomNo() + " ? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dataBase.removeRoom(roomToDelete);
            rooms.remove(roomToDelete);
            rearrangeRoomsByNo(rooms);
        }
    }

    @FXML
    public void selectRoom() {

        Room roomToDelete = roomDetailTable.getSelectionModel().getSelectedItem();

        if (roomToDelete != null) {

            roomNoCombo.setValue(roomToDelete.getRoomNo());
            roomTypeCombo.setValue(roomToDelete.getRoomType());
            noOfPeopleCombo.setValue(roomToDelete.getMaxNoOfPeople());
            floorCombo.setValue(roomToDelete.getFloorNo());
            phoneField.setText(roomToDelete.getPhoneNo());
            rateField.setText(roomToDelete.getRate());
            roomStatusCombo.setValue(roomToDelete.getStatus());
        }
    }

    @FXML
    public void updateRoom() {

        Room selectedRoom = roomDetailTable.getSelectionModel().getSelectedItem();

        if (selectedRoom == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Room selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select Room to be updated.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Update Room");
        alert.setHeaderText("Confirm the Room to be updated.");
        alert.setContentText("Are you sure you want to update Room details for selected room ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dataBase.removeRoom(selectedRoom);
            rooms.remove(selectedRoom);

            Room room = createRoom();
            dataBase.addRoom(room);
            rooms.add(room);
            rearrangeRoomsByNo(rooms);

            for (Room roomToSelect : rooms) {

                if(roomToSelect.getRoomNo().equals(room.getRoomNo())) {
                    roomDetailTable.getSelectionModel().select(roomToSelect);
                }
            }
        }
    }
}
