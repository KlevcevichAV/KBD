package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.database.Constant;
import sample.database.Technics;

import java.sql.SQLException;

public class ControllerSearchListTechnicSubdivision {

    @FXML
    private TextField subdivisionField;

    @FXML
    private TextField dayField;

    @FXML
    private TextField MonthField;

    @FXML
    private TextField yearField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Technics> table;

    @FXML
    private TableColumn<Technics, Integer> inventoryNumberColumn;

    @FXML
    private TableColumn<Technics, String> nameColumn;

    @FXML
    private TableColumn<Technics, String> modelColumn;

    @FXML
    private Button searchMenuButton;

    private void createTable() throws SQLException, ClassNotFoundException {
        table.setVisible(true);
        System.out.println(Controller.getDataBase().getSubdivisions().size());
        ObservableList<Technics> test = FXCollections.observableArrayList(Controller.getDataBase().searchTechnicsSubdivision(Integer.parseInt(subdivisionField.getText()), Constant.intToDate(Integer.parseInt(dayField.getText()), Integer.parseInt(MonthField.getText()), Integer.parseInt(yearField.getText()))));
        table.setItems(test);
        inventoryNumberColumn.setCellValueFactory(new PropertyValueFactory<Technics, Integer>("inventoryNumber"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Technics, String>("name"));
//        modelColumn.setCellValueFactory(new PropertyValueFactory<Technics, String>("date"));
    }

    @FXML
    void initialize() {
        searchButton.setOnAction(event -> {
            try {
                createTable();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        searchMenuButton.setOnAction(event -> {
            Stage stage = (Stage) searchMenuButton.getScene().getWindow();
            stage.close();
        });
    }
}
