package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Technics;

public class ControllerAddEditWindowTechnics {

    private static Technics technics;
    public static boolean result;

    public static Technics getTechnics() {
        Technics result = technics;
        technics = null;
        return result;
    }

    @FXML
    private TextField inventoryNumberField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField dayField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField priceField;

    @FXML
    private Button decisionButton;

    @FXML
    private Button cancelButton;

    private void setTextInField() {
        Technics temp = ControllerList.technics;
        inventoryNumberField.setText("" + temp.getInventoryNumber());
        nameField.setText(temp.getName());
        dayField.setText(temp.getDay() + "");
        monthField.setText(temp.getMonth() + "");
        yearField.setText(temp.getYear() + "");
        priceField.setText(temp.getPrice() + "");
    }

    @FXML
    void initialize() {
        if (ControllerList.technics != null) {
            setTextInField();
        }

        cancelButton.setOnAction(event -> {
            result = false;
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();

        });

        decisionButton.setOnAction(event -> {
            result = true;
            technics = new Technics(Integer.parseInt(inventoryNumberField.getText()), nameField.getText(), Integer.parseInt(dayField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()), Double.parseDouble(priceField.getText()));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}
