package hotelManagementSystem;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class splashScreenController implements Initializable {

    @FXML
    private ImageView image;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FadeTransition fadeTransition = new FadeTransition((Duration.millis(5000)), image);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0);
        fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Stage loginScreen = new Stage();
                Parent root = null;

                try {

                    root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

                } catch (Exception e) {

                }

                Stage current = (Stage) image.getScene().getWindow();
                Scene scene = new Scene(root);
                loginScreen.setScene(scene);
                loginScreen.initStyle(StageStyle.TRANSPARENT);
                current.hide();
                loginScreen.show();
            }
        });
        fadeTransition.play();
    }
}
