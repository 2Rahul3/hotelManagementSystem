package hotelManagementSystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Set;

public class customersController implements Initializable {

    @FXML
    private StackPane stackPane;

    @FXML
    private ImageView imageView;

    @FXML
    private Button closeButton;

    @FXML
    private LineChart<String, Integer> lineChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        imageView.fitHeightProperty().bind(stackPane.heightProperty());
        imageView.fitWidthProperty().bind(stackPane.widthProperty());

        addDataToChart();

    }

    @FXML
    public void closeButtonClicked () {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminScreen.fxml"));

        try {
            loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage adminScreen = new Stage();
        Stage current = (Stage) closeButton.getScene().getWindow();
        Scene scene = new Scene(root);
        adminScreen.setScene(scene);
        adminScreen.initStyle(StageStyle.TRANSPARENT);
        current.hide();
        adminScreen.show();
    }

    private void addDataToChart () {

        Set<String> months = new LinkedHashSet<>();
        Random randomNumber = new Random();

        months.add("Jan");
        months.add("Feb");
        months.add("Mar");
        months.add("Apr");
        months.add("May");
        months.add("Jun");
        months.add("Jul");
        months.add("Aug");
        months.add("Sep");
        months.add("Oct");
        months.add("Nov");
        months.add("Dec");


        XYChart.Series year2014 = new XYChart.Series();
        year2014.setName("Year-2014");
        XYChart.Series year2015 = new XYChart.Series();
        year2015.setName("Year-2015");
        XYChart.Series year2016 = new XYChart.Series();
        year2016.setName("Year-2016");
        XYChart.Series year2017 = new XYChart.Series();
        year2017.setName("Year-2017");
        XYChart.Series year2018 = new XYChart.Series();
        year2018.setName("Year-2018");


        for (String month : months) {

            int number;

            number = 100 + randomNumber.nextInt(400);
            year2014.getData().add(new XYChart.Data(month, number));

            number = 100 + randomNumber.nextInt(400);
            year2015.getData().add(new XYChart.Data(month, number));

            number = 100 + randomNumber.nextInt(400);
            year2016.getData().add(new XYChart.Data(month, number));

            number = 100 + randomNumber.nextInt(400);
            year2017.getData().add(new XYChart.Data(month, number));

            number = 100 + randomNumber.nextInt(400);
            year2018.getData().add(new XYChart.Data(month, number));

        }

        lineChart.getData().addAll(year2014, year2015, year2016, year2017, year2018);

    }
}
