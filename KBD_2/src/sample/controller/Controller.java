package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

public class Controller {
    private static int pointerInterface;

    public static int getPointerInterface() {
        return pointerInterface;
    }

    @FXML
    private Button technics;

    @FXML
    private Button subdivisions;

    @FXML
    private Button staff;

    @FXML
    private Button transfer;

    @FXML
    private Button search;

    @FXML
    void initialize() {
        subdivisions.setOnAction(event -> {
            try {
                pointerInterface = 1;
                Parent root = FXMLLoader.load(Main.class.getResource("view/list.fxml"));
                Stage stage = (Stage) subdivisions.getScene().getWindow();
                stage.setTitle("Subdivisions");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        transfer.setOnAction(event -> {
            try {
                pointerInterface = 3;
                Parent root = FXMLLoader.load(Main.class.getResource("view/list.fxml"));
                Stage stage = (Stage) subdivisions.getScene().getWindow();
                stage.setTitle("Transfer");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        technics.setOnAction(event -> {
            try {
                pointerInterface = 0;
                Parent root = FXMLLoader.load(Main.class.getResource("view/list.fxml"));
                Stage stage = (Stage) subdivisions.getScene().getWindow();
                stage.setTitle("Technics");
                stage.setScene(new Scene(root, 650, 350));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
