package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Equipment;

public class ControllerAddEditWindowEquipment {

    private static Equipment equipment;
    public static boolean result;

    public static Equipment getEquipment() {
        Equipment result = equipment;
        equipment = null;
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

    private void setTextInField(){
        Equipment temp = ControllerList.equipment;
        inventoryNumberField.setText("" + temp.getInventoryNumber());
        nameField.setText(temp.getName());
        dayField.setText(temp.getDay() + "");
        monthField.setText(temp.getMonth() + "");
        yearField.setText(temp.getYear() + "");
        priceField.setText(temp.getPrice() + "");
    }

    @FXML
    void initialize(){
        if(ControllerList.equipment != null){
            setTextInField();
        }

        cancelButton.setOnAction(event -> {
            cancelButton.setOnAction(e->{
                result = false;
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            });
        });

        decisionButton.setOnAction(event -> {
            result = true;
            equipment = new Equipment(Integer.parseInt(inventoryNumberField.getText()), nameField.getText(), Integer.parseInt(dayField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()), Double.parseDouble(priceField.getText()));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }
}