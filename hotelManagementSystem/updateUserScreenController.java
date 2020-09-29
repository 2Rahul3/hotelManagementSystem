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
import java.util.ResourceBundle;

public class updateUserScreenController implements Initializable {

    @FXML
    private TextField passwordField, userNameField, fullNameField, addressField, phoneField, salaryField;

    @FXML
    private DatePicker startDate;

    @FXML
    private ChoiceBox<String> userType;

    @FXML
    private Button updateButton, cancelButton;

    private ObservableList<String> userTypeChoiceBoxList = FXCollections.observableArrayList("Admin", "FrontDesk", "HouseKeeper");

    public ObservableList<User> userList;

    public User userToUpdate;

    public User userToSendBack = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userType.setItems(userTypeChoiceBoxList);
        userType.setValue("FrontDesk");
        userList = FXCollections.observableArrayList();
//        userNameField.setDisable(true);

    }

    public User getUserToSendBack() {
        return userToSendBack;
    }

    public void setValues (User userToUpdate) {

        userNameField.setText(userToUpdate.getUsername());
        passwordField.setText(userToUpdate.getPassword());
        fullNameField.setText(userToUpdate.getFullname());
        addressField.setText(userToUpdate.getAddress());
        phoneField.setText(userToUpdate.getPhone());

        DateTimeFormatter df = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String startDateString = userToUpdate.getStartDate();

        LocalDate localDate = LocalDate.parse(startDateString, df);

        startDate.setValue(localDate);
        salaryField.setText(userToUpdate.getSalary());
        userType.setValue(userToUpdate.getUserType());

    }

    @FXML
    public void updateButtonClicked() {

        boolean validateFields = validateEmptyFields();

        if(validateFields) {

            User user = createUser();

                userToSendBack = user;
                cancelButton.getScene().getWindow().hide();

            }
        }

    @FXML
    public void cancelButtonClicked() {

        Stage current = (Stage) cancelButton.getScene().getWindow();
        userToSendBack = null;
        current.hide();

    }

    private User createUser () {

        User user = new User();

        user.setId(userToUpdate.getId());
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
