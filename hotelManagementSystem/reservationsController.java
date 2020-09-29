package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.*;

public class reservationsController implements Initializable {

    @FXML
    private TableView<Reservation> reservationsTable;

    @FXML
    private Button backButton;

    private DataBase dataBase;

    ObservableList<Reservation> reservations;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataBase = new DataBase();
        reservations = FXCollections.observableArrayList();
        dataBase.openConnection();
        dataBase.loadReservations();
        reservations = dataBase.getReservations();
        Collections.sort(reservations, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation reservation1, Reservation reservation2) {
                return reservation1.getId().compareTo(reservation2.getId());
            }
        });

        reservationsTable.setItems(reservations);
    }

    @FXML
    public void backButtonClicked() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomeScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage homeScreen = new Stage();
        Stage current = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(root);
        homeScreen.setScene(scene);
        homeScreen.initStyle(StageStyle.TRANSPARENT);
        dataBase.closeConnection();
        current.hide();
        homeScreen.show();
    }

    @FXML
    public void deleteButtonClicked() {

        Reservation selectedReservation = reservationsTable.getSelectionModel().getSelectedItem();

        if (selectedReservation == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No reservation(s) selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select Reservation(s) to be deleted.");
            alert.showAndWait();

        } else {

            dataBase.removeReservation(selectedReservation);
            reservations.remove(selectedReservation);

        }
    }
}
