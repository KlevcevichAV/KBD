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
import sample.database.Transfer;

import java.sql.SQLException;

public class ControllerSearchListRoom {

    @FXML
    private TextField subdivisionField;

    @FXML
    private Button searchButton;

    @FXML
    private TableView<Transfer> table;

    @FXML
    private TableColumn<Transfer, Integer> numberRoomColumn;

    @FXML
    private Button searchMenuButton;

    private void createTable() throws SQLException, ClassNotFoundException {
        table.setVisible(true);
        ObservableList<Transfer> test = FXCollections.observableArrayList(Controller.getDataBase().searchNumberRoom(Integer.parseInt(subdivisionField.getText())));
        table.setItems(test);
        numberRoomColumn.setCellValueFactory(new PropertyValueFactory<Transfer, Integer>("roomNumber"));
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
