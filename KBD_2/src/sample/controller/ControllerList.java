package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.database.*;

import java.io.IOException;
import java.sql.SQLException;

//add exceptions

public class ControllerList {
    private DataBase dataBase;
    public static Technics technics;
    public static Subdivision subdivision;
    public static Transfer transfer;
    public static Staff staff;
    @FXML
    private VBox changeButtonsBox;

    @FXML
    private Label label;

    @FXML
    private TableView<Technics> tableTechnics;

    @FXML
    private TableColumn<Technics, Integer> inventoryNumber;

    @FXML
    private TableColumn<Technics, String> name;

    @FXML
    private TableColumn<Technics, String> date;

    @FXML
    private TableColumn<Technics, Double> price;

    @FXML
    private TableView<Subdivision> tableSubdivisions;

    @FXML
    private TableColumn<Subdivision, Integer> number;

    @FXML
    private TableColumn<Subdivision, String> fullName;

    @FXML
    private TableColumn<Subdivision, String> shortName;

    @FXML
    private TableView<?> tableStaff;

    @FXML
    private TableView<Transfer> tableTransfer;

    @FXML
    private TableColumn<Transfer, String> dateTransfer;

    @FXML
    private TableColumn<Transfer, String> fullNameTransfer;

    @FXML
    private TableColumn<Transfer, Integer> roomNumberTransfer;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button menuButton;

    private void createTechnicTable() {
        hideTables();
        tableTechnics.setVisible(true);
        System.out.println(dataBase.getSubdivisions().size());
        ObservableList<Technics> test = FXCollections.observableArrayList(dataBase.getTechnics());
        tableTechnics.setItems(test);
        inventoryNumber.setCellValueFactory(new PropertyValueFactory<Technics, Integer>("inventoryNumber"));
        name.setCellValueFactory(new PropertyValueFactory<Technics, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Technics, String>("date"));
        price.setCellValueFactory(new PropertyValueFactory<Technics, Double>("price"));
    }

    private void createSubdivisionTable() {
        hideTables();
        tableSubdivisions.setVisible(true);
        System.out.println(dataBase.getSubdivisions().size());
        ObservableList<Subdivision> test = FXCollections.observableArrayList(dataBase.getSubdivisions());
        tableSubdivisions.setItems(test);
        number.setCellValueFactory(new PropertyValueFactory<Subdivision, Integer>("number"));
        fullName.setCellValueFactory(new PropertyValueFactory<Subdivision, String>("fullName"));
        shortName.setCellValueFactory(new PropertyValueFactory<Subdivision, String>("shortName"));
    }

    private void createTransferTable() {
        hideTables();
        tableTransfer.setVisible(true);
        System.out.println(dataBase.getSubdivisions().size());
        ObservableList<Transfer> test = FXCollections.observableArrayList(dataBase.getTransfers());
        tableTransfer.setItems(test);
        dateTransfer.setCellValueFactory(new PropertyValueFactory<Transfer, String>("date"));
        fullNameTransfer.setCellValueFactory(new PropertyValueFactory<Transfer, String>("fullName"));
        roomNumberTransfer.setCellValueFactory(new PropertyValueFactory<Transfer, Integer>("roomNumber"));
    }

    private void hideTables() {
        tableSubdivisions.setVisible(false);
        tableStaff.setVisible(false);
        tableTechnics.setVisible(false);
        tableTransfer.setVisible(false);
    }

    private void setInterface() {
        switch (Controller.getPointerInterface()) {
            case 0: {
                createTechnicTable();
                addButton.setText("ДОБАВИТЬ ТЕХНИКУ");
                break;
            }
            case 1: {
                createSubdivisionTable();
                addButton.setText("ДОБАВИТЬ ПОДРАЗДЕЛЕНИЕ");
                break;
            }
            case 2: {
                addButton.setText("ДОБАВИТЬ ОТВЕТСТВЕННОЕ ЛИЦО");
                break;
            }
            case 3: {
                createTransferTable();
                addButton.setText("ДОБАВИТЬ ПЕРЕДАЧУ");
                break;
            }
            default:
                return;
        }
    }

    private void setChange(boolean checkAdd) throws SQLException {
        switch (Controller.getPointerInterface()) {
            case 0: {
                Technics temp = ControllerAddEditWindowTechnics.getTechnics();
                if (checkAdd) {
                    dataBase.addTechnics(temp);
                } else {
                    dataBase.editTechnics(technics, temp);
                }
                ObservableList<Technics> tempList = FXCollections.observableArrayList(dataBase.getTechnics());
                tableTechnics.setItems(tempList);
                technics = null;
                break;
            }
            case 1: {
                Subdivision temp = ControllerAddEditWindowSubdivision.getSubdivision();
                if (checkAdd) {
                    dataBase.addSubdivisions(temp);
                } else {
                    dataBase.editSubdivisions(subdivision, temp);
                }
                ObservableList<Subdivision> tempList = FXCollections.observableArrayList(dataBase.getSubdivisions());
                tableSubdivisions.setItems(tempList);
                subdivision = null;
                break;
            }
            case 3: {
                Transfer temp = ControllerAddEditWindowTransfer.getTransfer();
                if (checkAdd) {
                    dataBase.addTransfers(temp);
                } else {
                    dataBase.editTransfers(transfer, temp);
                }
                ObservableList<Transfer> tempList = FXCollections.observableArrayList(dataBase.getTransfers());
                tableTransfer.setItems(tempList);
                transfer = null;
                break;
            }
            default:
                return;
        }
    }

    private void createDialogWindow(boolean checkAdd) throws IOException, SQLException {
        Parent root;
        switch (Controller.getPointerInterface()) {
            case 0: {
                if (!checkAdd) technics = tableTechnics.getSelectionModel().getSelectedItem();
                if (technics == null && !checkAdd) return;
                root = FXMLLoader.load(Main.class.getResource("view/addEditWindowTechnics.fxml"));
                break;
            }
            case 1: {
                if (!checkAdd) subdivision = tableSubdivisions.getSelectionModel().getSelectedItem();
                if (subdivision == null && !checkAdd) return;
                root = FXMLLoader.load(Main.class.getResource("view/addEditWindowSubdivision.fxml"));
                break;
            }
            case 3: {
                if (!checkAdd) transfer = tableTransfer.getSelectionModel().getSelectedItem();
                if (transfer == null && !checkAdd) return;
                root = FXMLLoader.load(Main.class.getResource("view/addEditWindowTransfer.fxml"));
                break;
            }
            default:
                return;
        }
        Stage dialogStage = new Stage();
        if (checkAdd) {
            dialogStage.setTitle("Add");
        } else {
            dialogStage.setTitle("Edit");
        }
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(root, 600, 500);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
        setChange(checkAdd);
    }

    private void delete() throws SQLException {
        switch (Controller.getPointerInterface()) {
            case 0: {
                if (tableTechnics.getSelectionModel().getSelectedItem() == null) return;
                dataBase.deleteTechnics(tableTechnics.getSelectionModel().getSelectedItem());
                ObservableList<Technics> temp = FXCollections.observableArrayList(dataBase.getTechnics());
                tableTechnics.setItems(temp);
                break;
            }
            case 1: {
                if (tableSubdivisions.getSelectionModel().getSelectedItem() == null) return;
                dataBase.deleteSubdivisions(tableSubdivisions.getSelectionModel().getSelectedItem());
                ObservableList<Subdivision> temp = FXCollections.observableArrayList(dataBase.getSubdivisions());
                tableSubdivisions.setItems(temp);
            }
            case 3: {
                if (tableTransfer.getSelectionModel().getSelectedItem() == null) return;
                dataBase.deleteTransfers(tableTransfer.getSelectionModel().getSelectedItem());
                ObservableList<Transfer> temp = FXCollections.observableArrayList(dataBase.getTransfers());
                tableTransfer.setItems(temp);
            }
        }
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        hideTables();
        dataBase = new DataBase();
        setInterface();
        menuButton.setOnAction(event -> {
            try {
                Parent root = FXMLLoader.load(Main.class.getResource("view/sample.fxml"));
                Stage stage = (Stage) addButton.getScene().getWindow();
                stage.setTitle("Hello World");
                stage.setScene(new Scene(root, 600, 350));

            } catch (IOException e) {
                e.printStackTrace();
            }
//        Controller controller = new Controller(root);
        });
        addButton.setOnAction(event -> {
            try {
                createDialogWindow(true);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
        editButton.setOnAction(event -> {
            try {
                createDialogWindow(false);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        });
        deleteButton.setOnAction(event -> {
            try {
                delete();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });
//        if(pointerInterface == -1) return;
    }
}
