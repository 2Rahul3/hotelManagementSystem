package hotelManagementSystem;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class loginScreenController implements Initializable {

    @FXML
    private Button cancelButton, loginButton, newUserButton;

    @FXML
    private TextField userNameField, passwordField;

    @FXML
    private Label userNameWarning, passwordWarning;

    private ObservableList<User> userList;

    private DataBase dataBase;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dataBase = new DataBase();

    }

    @FXML
    public void loginButtonClicked() {


        userNameWarning.setText("");
        userNameField.setStyle("-fx-border-color: none");
        passwordWarning.setText("");
        passwordField.setStyle("-fx-border-color: none");

        String userNameEntered;
        User user;

        if (userNameField.getText().trim().isBlank()) {

            userNameWarning.setText("Please enter the Username");

        } else {

            userNameEntered = userNameField.getText().trim();
            user = findUser(userNameEntered);

            if (user == null) {

                userNameWarning.setText("Username is Incorrect.");
                userNameField.setStyle("-fx-border-color: red");

            } else if (passwordField.getText().trim().isBlank()) {

                passwordWarning.setText("Please enter the Password");

            } else {

                String password = user.getPassword();

                if (password.equals(passwordField.getText().trim())) {

                    if (user.getUserType().equals("Admin")) {
                        //Load Admin screen

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("AdminScreen.fxml"));

                        try {
                            loader.load();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Parent root = loader.getRoot();
                        Stage adminScreen = new Stage();
                        Stage current = (Stage) cancelButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        adminScreen.setScene(scene);
                        adminScreen.initStyle(StageStyle.TRANSPARENT);
                        current.hide();
                        adminScreen.show();

                    } else {
                        //Load Home screen

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
                        current.hide();
                        homeScreen.show();

                        System.out.println("Front Desk");

                    }

                } else {

                    passwordWarning.setText("Password is Incorrect.");
                    passwordField.setStyle("-fx-border-color: red");

                }
            }
        }
    }

    private User findUser(String userEntered) {

        dataBase.openConnection();
        dataBase.loadUsers();
        userList = dataBase.getUsers();
        dataBase.closeConnection();

        for (User user : userList) {

            if (userEntered.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }


    @FXML
    public void cancelButtonClicked() {

        cancelButton.getScene().getWindow().hide();

    }
}
