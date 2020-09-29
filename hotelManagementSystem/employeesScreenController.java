package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

public class employeesScreenController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView imageView;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableView<User> userListTableView;

    private ObservableList<User> users;

    private DataBase dataBase;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.fitWidthProperty().bind(stackPane.widthProperty());

        dataBase = new DataBase();
        dataBase.openConnection();
        dataBase.loadUsers();
        users = FXCollections.observableArrayList();
        users = dataBase.getUsers();
        rearrangeUsersById(users);
    }

    @FXML
    public void addButtonClicked() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddNewUserScreen.fxml"));

        try {

            loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }

        addNewUserController controller = loader.getController();
        controller.userList = dataBase.getUsers();

        Parent root = loader.getRoot();
        Stage newUser = new Stage();
        Scene scene = new Scene(root);
        newUser.setScene(scene);
        newUser.setTitle("New User");
        newUser.initModality(Modality.APPLICATION_MODAL);
        newUser.initStyle(StageStyle.TRANSPARENT);
        newUser.showAndWait();

        User user = controller.addButtonClicked();

        if (user != null) {

            dataBase.addNewUser(user);
            users.add(user);

            users.clear();
            dataBase.loadUsers();
            users = dataBase.getUsers();
            rearrangeUsersById(users);

        }
    }

    @FXML
    public void closeButtonClicked() {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage adminScreen = new Stage();
        Stage current = (Stage) stackPane.getScene().getWindow();
        Scene scene = new Scene(root);
        adminScreen.setScene(scene);
        adminScreen.initStyle(StageStyle.TRANSPARENT);
        dataBase.closeConnection();
        current.hide();
        adminScreen.show();

    }

    @FXML
    public void removeButtonClicked() {

        User userToRemove = userListTableView.getSelectionModel().getSelectedItem();

        if (userToRemove == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No User selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select User to be remove.");
            alert.showAndWait();
            return;

        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle("Remove User");
        alert.setHeaderText("Confirm the User to be removed.");
        alert.setContentText("Delete Room : " + userToRemove.getFullname() + " ? ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dataBase.removeUser(userToRemove);
            users.remove(userToRemove);

            users.clear();
            dataBase.loadUsers();
            users = dataBase.getUsers();
            rearrangeUsersById(users);
        }
    }

    private void rearrangeUsersById(ObservableList<User> users) {

        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return user1.getId().compareTo(user2.getId());
            }
        });

        userListTableView.setItems(users);
    }

    @FXML
    public void updateButtonClicked() {

        User userToUpdate = userListTableView.getSelectionModel().getSelectedItem();

        if (userToUpdate == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No User selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select User to be updated.");
            alert.showAndWait();
            return;

        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateUserScreen.fxml"));

        try {

            loader.load();

        } catch (Exception e) {
            e.printStackTrace();
        }

        updateUserScreenController controller = loader.getController();
        controller.userToUpdate = userToUpdate;
        controller.setValues(userToUpdate);

        Parent root = loader.getRoot();
        Stage newUser = new Stage();
        Scene scene = new Scene(root);
        newUser.setScene(scene);
        newUser.setTitle("Update User info.");
        newUser.initModality(Modality.APPLICATION_MODAL);
        newUser.initStyle(StageStyle.TRANSPARENT);
        newUser.showAndWait();


        User user = controller.getUserToSendBack();

        if (user != null) {

            dataBase.removeUser(userToUpdate);
            users.remove(userToUpdate);

            dataBase.addNewUser(user);
            users.add(user);

            users.clear();
            dataBase.loadUsers();
            users = dataBase.getUsers();
            rearrangeUsersById(users);

        }
    }

    @FXML
    public void searchTextTyped() {

        String idSearched = searchTextField.getText().trim();

        if (searchTextField.getText().isBlank()) {

            userListTableView.setItems(users);

        } else {

            ObservableList<User> searchedId = FXCollections.observableArrayList();

            char[] idSearchedChars = idSearched.toCharArray();
            int noOfChars = idSearchedChars.length;

            for (User user : users) {

                String idFromList = user.getId();
                char[] searchedIdChar = idFromList.toCharArray();
                String idToFind = "";

                for (int i = 0; i < noOfChars; i++) {

                    if (noOfChars <= searchedIdChar.length) {

                        idToFind = idToFind + searchedIdChar[i];

                    }
                }

                if (idSearched.equals(idToFind)) {

                    searchedId.add(user);
                    userListTableView.setItems(searchedId);

                } else {

                    userListTableView.setItems(searchedId);

                }
            }
        }
    }

    @FXML
    public void clearButtonClicked () {

        searchTextField.clear();
        userListTableView.setItems(users);

    }
}


