package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class addNewUserController implements Initializable {

    @FXML
    private TextField userNameField, passwordField, fullNameField, addressField, phoneField, salaryField;

    @FXML
    private DatePicker startDate;

    @FXML
    private ChoiceBox<String> userType;

    @FXML
    private Button addButton, cancelButton;

    private ObservableList<String> userTypeChoiceBoxList = FXCollections.observableArrayList("Admin", "FrontDesk", "HouseKeeper");

    public ObservableList<User> userList;

    private Set<Integer> userIdSet = new HashSet<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userType.setItems(userTypeChoiceBoxList);
        userType.setValue("FrontDesk");
        startDate.setValue(LocalDate.now());

    }

    @FXML
    public void cancelButtonClicked() {

        Stage current = (Stage) cancelButton.getScene().getWindow();
        current.hide();

    }

    @FXML
    public User addButtonClicked() {

        boolean validateFields = validateEmptyFields();


        if(validateFields) {

            User user = createUser();

            if (findUser(user.getUsername())) {

                cancelButton.getScene().getWindow().hide();
                return user;

            }
        }
        return null;
    }

    private User createUser () {

        User user = new User();

        for (User user1 : userList) {

            int idInteger = Integer.parseInt(user1.getId());
            userIdSet.add(idInteger);

        }

        for (int i = 1; i < (userIdSet.size() + 2); i++) {

            boolean isIdExists = userIdSet.contains(i);

            if (!isIdExists) {

                String idString = Integer.toString(i);
                user.setId(idString);

            }
        }

        user.setUsername(userNameField.getText().trim());
        user.setPassword(passwordField.getText());
        user.setFullname(fullNameField.getText().toUpperCase().trim());
        user.setAddress(addressField.getText().trim());
        user.setPhone(phoneField.getText().trim());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        if(!(startDate.getValue() == null)) {

            user.setStartDate(startDate.getValue().format(df));

        }

        user.setSalary(salaryField.getText().trim());
        user.setUserType(userType.getValue());

        return user;

    }

    private boolean findUser(String userEntered) {

        for (User user : userList) {

            if (userEntered.equals(user.getUsername())) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Similar Username found.");
                alert.setHeaderText(null);
                alert.setContentText("Username " + userEntered + " is already exists.");
                userNameField.setStyle("-fx-border-color: red");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    private boolean validateEmptyFields () {

        if(userNameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || fullNameField.getText().trim().isEmpty() ||
            addressField.getText().trim().isEmpty() || phoneField.getText().trim().isEmpty() || salaryField.getText().trim().isEmpty()) {

            if(userNameField.getText().trim().isEmpty()) {
                userNameField.setStyle("-fx-border-color: red");
            } else {
                userNameField.setStyle("-fx-border-color: black");
            }
            if(passwordField.getText().trim().isEmpty()) {
                passwordField.setStyle("-fx-border-color: red");
            } else {
                passwordField.setStyle("-fx-border-color: black");
            }
            if(fullNameField.getText().trim().isEmpty()) {
                fullNameField.setStyle("-fx-border-color: red");
            } else {
                fullNameField.setStyle("-fx-border-color: black");
            }
            if(addressField.getText().trim().isEmpty()) {
                addressField.setStyle("-fx-border-color: red");
            } else {
                addressField.setStyle("-fx-border-color: black");
            }
            if(phoneField.getText().trim().isEmpty()) {
                phoneField.setStyle("-fx-border-color: red");
            } else {
                phoneField.setStyle("-fx-border-color: black");
            }
            if(salaryField.getText().trim().isEmpty()) {
                salaryField.setStyle("-fx-border-color: red");
            } else {
                salaryField.setStyle("-fx-border-color: black");
            }
            return false;
        } else {
            return true;
        }
    }
}
