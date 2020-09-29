package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class availableRoomsScreenController implements Initializable {

    @FXML
    private TableView<Room> roomDetailTable;

    @FXML
    private Button closeButton;

    @FXML
    private CheckBox busyCheckBox, availableCheckBox, outOfOrderCheckBox;

    @FXML
    private TextField searchTextField;

    private DataBase dataBase;

    private ObservableList<Room> rooms;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataBase = new DataBase();
        rooms = FXCollections.observableArrayList();
        dataBase.openConnection();
        dataBase.loadRooms();
        rooms = dataBase.getRooms();
        Collections.sort(rooms, new Comparator<Room>() {
            @Override
            public int compare(Room room1, Room room2) {
                return room1.getRoomNo().compareTo(room2.getRoomNo());
            }
        });
        roomDetailTable.setItems(rooms);
        roomDetailTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    public void closedButtonClicked() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomeScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage homeScreen = new Stage();
        Stage current = (Stage) closeButton.getScene().getWindow();
        Scene scene = new Scene(root);
        homeScreen.setScene(scene);
        homeScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        homeScreen.show();

    }

    @FXML
    public void roomNumberEntered() {

        String roomNoSearched = searchTextField.getText().trim();
        ObservableList<Room> roomsInPoolToSearch = FXCollections.observableArrayList();

        if (busyCheckBox.isSelected()) {

            roomsInPoolToSearch = busyCheckBoxClicked();

        } else if (availableCheckBox.isSelected()) {

            roomsInPoolToSearch = availableCheckBoxClicked();

        } else if (outOfOrderCheckBox.isSelected()) {

            roomsInPoolToSearch = outOfOrderCheckBoxClicked();

        } else {

            roomsInPoolToSearch = rooms;

        }

        if (searchTextField.getText().isBlank()) {

            roomDetailTable.setItems(roomsInPoolToSearch);

        } else {

            ObservableList<Room> searchedRoom = FXCollections.observableArrayList();

            char[] roomNoSearchedChars = roomNoSearched.toCharArray();
            int noOfChars = roomNoSearchedChars.length;

            for (Room room : roomsInPoolToSearch) {

                String roomFromList = room.getRoomNo();
                char[] searchedRoomNoChar = roomFromList.toCharArray();
                String nameToFind = "";

                for (int i = 0; i < noOfChars; i++) {

                    if (noOfChars <= searchedRoomNoChar.length) {

                        nameToFind = nameToFind + searchedRoomNoChar[i];

                    }
                }

                if (roomNoSearched.equals(nameToFind)) {

                    searchedRoom.add(room);
                    roomDetailTable.setItems(searchedRoom);

                } else {

                    roomDetailTable.setItems(searchedRoom);

                }
            }
        }
    }

    @FXML
    void clearButtonClicked(ActionEvent event) {

        ObservableList<Room> roomsInPoolToSearch = FXCollections.observableArrayList();

        if (busyCheckBox.isSelected()) {

            roomsInPoolToSearch = busyCheckBoxClicked();

        } else if (availableCheckBox.isSelected()) {

            roomsInPoolToSearch = availableCheckBoxClicked();

        } else if (outOfOrderCheckBox.isSelected()) {

            roomsInPoolToSearch = outOfOrderCheckBoxClicked();

        } else {

            roomsInPoolToSearch = rooms;

        }

        searchTextField.clear();
        roomDetailTable.setItems(roomsInPoolToSearch);

    }

    @FXML
    public ObservableList<Room> busyCheckBoxClicked() {


        if (busyCheckBox.isSelected()) {

            availableCheckBox.setSelected(false);
            outOfOrderCheckBox.setSelected(false);
            ObservableList<Room> searchedRoom = FXCollections.observableArrayList();

            for (Room room : rooms) {

                if (room.getStatus().equals("Busy")) {

                    searchedRoom.add(room);

                }
            }
            roomDetailTable.setItems(searchedRoom);
            return searchedRoom;
        } else {

            roomDetailTable.setItems(rooms);
            return rooms;

        }
    }

    @FXML
    public ObservableList<Room> availableCheckBoxClicked() {

        if (availableCheckBox.isSelected()) {

            busyCheckBox.setSelected(false);
            outOfOrderCheckBox.setSelected(false);
            ObservableList<Room> searchedRoom = FXCollections.observableArrayList();

            for (Room room : rooms) {

                if (room.getStatus().equals("Available")) {

                    searchedRoom.add(room);

                }
            }
            roomDetailTable.setItems(searchedRoom);
            return searchedRoom;
        } else {

            roomDetailTable.setItems(rooms);
            return rooms;
        }
    }

    @FXML
    public ObservableList<Room> outOfOrderCheckBoxClicked() {

        if (outOfOrderCheckBox.isSelected()) {

            busyCheckBox.setSelected(false);
            availableCheckBox.setSelected(false);
            ObservableList<Room> searchedRoom = FXCollections.observableArrayList();

            for (Room room : rooms) {

                if (room.getStatus().equals("Out of Order")) {

                    searchedRoom.add(room);

                }
            }
            roomDetailTable.setItems(searchedRoom);
            return searchedRoom;
        } else {

            roomDetailTable.setItems(rooms);
            return rooms;

        }
    }

    @FXML
    public void changeStatusButtonClicked() {

        int counter = 0;
        ObservableList<Room> selectedRooms = roomDetailTable.getSelectionModel().getSelectedItems();

        if (selectedRooms.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No room(s) selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select Room(s) to be updated.");
            alert.showAndWait();

        } else {

            for (Room room : selectedRooms) {

                if (room.getStatus().equals("Available")) {

                } else {

                    for (Room room1 : rooms) {

                        if (room1.getRoomNo().equals(room.getRoomNo())) {

                            room1.setStatus("Available");
                            dataBase.updateRoomStatus(room1, "Available");
                            counter++;

                        }
                    }
                }
            }
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Status Updated.");
        alert.setHeaderText(null);
        if (counter <= 1) {

            alert.setContentText(counter + " Room Updated.");
        } else {

            alert.setContentText(counter + " Rooms Updated.");
        }
        alert.showAndWait();
    }
}
