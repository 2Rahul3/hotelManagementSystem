package hotelManagementSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class homeScreenController {

    @FXML
    private Pane reservePane, availableRoomsPane, customerInfoPane, logoutPane, exitPane;


    @FXML
    private void reservePaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewReserveScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage reserveScreen = new Stage();
        Stage current = (Stage) reservePane.getScene().getWindow();
        Scene scene = new Scene(root);
        reserveScreen.setScene(scene);
        reserveScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        reserveScreen.show();

    }

    @FXML
    private void availableRoomsPaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AvailableRoomsScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage availableRoomsScreen = new Stage();
        Stage current = (Stage) reservePane.getScene().getWindow();
        Scene scene = new Scene(root);
        availableRoomsScreen.setScene(scene);
        availableRoomsScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        availableRoomsScreen.show();

    }

    @FXML
    private void reservationsPaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Reservations.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage customerInfoScreen = new Stage();
        Stage current = (Stage) reservePane.getScene().getWindow();
        Scene scene = new Scene(root);
        customerInfoScreen.setScene(scene);
        customerInfoScreen.initStyle(StageStyle.DECORATED);
        customerInfoScreen.setTitle("Reservation List");
        current.hide();
        customerInfoScreen.show();

    }

    @FXML
    private void logoutPaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage loginScreen = new Stage();
        Stage current = (Stage) reservePane.getScene().getWindow();
        Scene scene = new Scene(root);
        loginScreen.setScene(scene);
        loginScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        loginScreen.show();

    }

    @FXML
    private void exitPaneClicked () {

        System.exit(0);

    }



    @FXML
    public void mouseHoverReserve (MouseEvent event) {
        reservePane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseExitReserve (MouseEvent event) {
        reservePane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseHoverAvailableRooms (MouseEvent event) {
        availableRoomsPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseExitAvailableRooms (MouseEvent event) {
        availableRoomsPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseHoverCustomerInfo (MouseEvent event) {
        customerInfoPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseExitCustomerInfo (MouseEvent event) {
        customerInfoPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseHoverLogout (MouseEvent event) {
        logoutPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseExitLogout (MouseEvent event) {
        logoutPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseHoverExit (MouseEvent event) {
        exitPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    public void mouseExitExitPane (MouseEvent event) {
        exitPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }
}
