package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Transfer;

public class ControllerAddEditWindowTransfer {

    private boolean result = false;
    private static Transfer transfer;

    public static Transfer getTransfer() {
        return transfer;
    }

    @FXML
    private TextField dayField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField fullName;

    @FXML
    private TextField roomNumber;

    @FXML
    private TextField inventoryNumber;

    @FXML
    private Button decisionButton;

    @FXML
    private Button cancelButton;

    private void setTextInField() {
        Transfer temp = ControllerList.transfer;
        dayField.setText(temp.getDay() + "");
        monthField.setText(temp.getMonth() + "");
        yearField.setText(temp.getYear() + "");
        fullName.setText(temp.getFullName());
        roomNumber.setText(temp.getRoomNumber() + "");
        inventoryNumber.setText(temp.getInventoryNumber() + "");
    }

    @FXML
    void initialize() {
        if (ControllerList.transfer != null) {
            decisionButton.setText("Редактировать");
            setTextInField();
        }else {
            decisionButton.setText("Добавить");
        }

        cancelButton.setOnAction(event -> {
            result = false;
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();

        });

        decisionButton.setOnAction(event -> {
            result = true;
            transfer = new Transfer(Integer.parseInt(dayField.getText()), Integer.parseInt(monthField.getText()), Integer.parseInt(yearField.getText()), fullName.getText(), Integer.parseInt(roomNumber.getText()), Integer.parseInt(inventoryNumber.getText()));
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

}
