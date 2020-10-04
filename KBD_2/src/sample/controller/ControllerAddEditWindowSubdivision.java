package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.database.Subdivision;

public class ControllerAddEditWindowSubdivision {

    public static boolean result = false;

    private static Subdivision subdivision;

    public static Subdivision getSubdivision() {
        return subdivision;
    }

    @FXML
    private TextField numberField;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField shortNameField;

    @FXML
    private Button decisionButton;

    @FXML
    private Button cancelButton;

    private void setTextInField() {
        Subdivision temp = ControllerList.subdivision;
        numberField.setText(temp.getNumber() + "");
        fullNameField.setText(temp.getFullName());
        shortNameField.setText(temp.getShortName());
    }

    @FXML
    void initialize() {
        if (ControllerList.subdivision != null) {
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
            subdivision = new Subdivision(Integer.parseInt(numberField.getText()), fullNameField.getText(), shortNameField.getText());
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
    }

}
