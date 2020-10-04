package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Staff;
import sample.database.Subdivision;

public class ControllerAddEditWindowStaff {

    public static boolean result = false;

    private static Staff staff;

    public static Staff getStaff() {
        return staff;
    }

    @FXML
    private TextField fullName;

    @FXML
    private TextField position;

    @FXML
    private TextField subdivisionField;

    @FXML
    private Button decisionButton;

    @FXML
    private Button cancelButton;

    private void setTextInField() {
        Staff temp = ControllerList.staff;
        fullName.setText(temp.getFullName());
        position.setText(temp.getPosition());
        subdivisionField.setText(temp.getSubdivisionName());
    }

    @FXML
    void initialize() {
        if (ControllerList.staff != null) {
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
            staff = new Staff(fullName.getText(), position.getText(), subdivisionField.getText());
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }


}
