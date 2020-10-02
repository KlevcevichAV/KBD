package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class ControllerSearchWindow {

    @FXML
    private Button listTechnicSubdivision;

    @FXML
    private Button listRoom;

    @FXML
    private Button listTechnicPerson;

    @FXML
    private Button menu;

    @FXML
    void initialize() {
        menu.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/sample.fxml"));
                Stage stage = (Stage) menu.getScene().getWindow();
                stage.setTitle("Test");
                stage.setScene(new Scene(root, 600, 350));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        listTechnicSubdivision.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/searchListTechnicSubdivision.fxml"));
                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(root, 600, 500);
                dialogStage.setScene(scene);
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
