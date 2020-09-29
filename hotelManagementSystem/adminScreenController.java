package hotelManagementSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class adminScreenController {



    @FXML
    private Pane homePane, employeesPane, customersPane, manageRomsPane, logoutPane, exitPane;




    @FXML
    private void homePaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomeScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage homeScreen = new Stage();
        Stage current = (Stage) homePane.getScene().getWindow();
        Scene scene = new Scene(root);
        homeScreen.setScene(scene);
        homeScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        homeScreen.show();

    }

    @FXML
    private void employeesPaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EmployeesScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage employeesScreen = new Stage();
        Stage current = (Stage) homePane.getScene().getWindow();
        Scene scene = new Scene(root);
        employeesScreen.setScene(scene);
        employeesScreen.initStyle(StageStyle.DECORATED);
        employeesScreen.setTitle("Employee List");
        current.hide();
        employeesScreen.show();

    }

    @FXML
    private void customersPaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CustomersScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage customersScreen = new Stage();
        Stage current = (Stage) homePane.getScene().getWindow();
        Scene scene = new Scene(root);
        customersScreen.setScene(scene);
        customersScreen.initStyle(StageStyle.DECORATED);
        customersScreen.setTitle("Customer Data");
        current.hide();
        customersScreen.show();

    }

    @FXML
    private void manageRoomsPaneClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ManageRoomsScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage manageRoomsScreen = new Stage();
        Stage current = (Stage) homePane.getScene().getWindow();
        Scene scene = new Scene(root);
        manageRoomsScreen.setScene(scene);
        manageRoomsScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        manageRoomsScreen.show();

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
        Stage current = (Stage) homePane.getScene().getWindow();
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
    void mouseExitHome (MouseEvent event) {
        homePane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseExitEmployees (MouseEvent event) {
        employeesPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseExitCustomers(MouseEvent event) {
        customersPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseExitLogout(MouseEvent event) {
        logoutPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseExitExitPane (MouseEvent event) {
        exitPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseExitManageRooms (MouseEvent event) {
        manageRomsPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseHoverHome(MouseEvent event) {
        homePane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseHoverEmployees(MouseEvent event) {
        employeesPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseHoverCustomers(MouseEvent event) {
        customersPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseHoverLogout(MouseEvent event) {
        logoutPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseHoverExitPane(MouseEvent event) {
        exitPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }

    @FXML
    void mouseHoverManageRooms(MouseEvent event) {
        manageRomsPane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 10px;");
    }



}
