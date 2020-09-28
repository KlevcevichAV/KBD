package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Main;
import sample.database.*;

import java.io.IOException;
import java.sql.SQLException;

public class ControllerList {

    private DataBase dataBase;
    public static Equipment equipment;
    public static Subdivision subdivision;
    public static LocationOfEquipment locationOfEquipment;
    public static ResponsiblePerson responsiblePerson;
    @FXML
    private VBox changeButtonsBox;

    @FXML
    private Label label;

    @FXML
    private TableView<?> tableTechnics;

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
    private TableView<?> tableTransfer;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button menuButton;

    private void createSubdivisionTable(){
        tableSubdivisions.setVisible(true);
        System.out.println(dataBase.getSubdivisions().size());
        ObservableList<Subdivision> test = FXCollections.observableArrayList(dataBase.getSubdivisions());
        tableSubdivisions.setItems(test);
        number.setCellValueFactory(new PropertyValueFactory<Subdivision, Integer>("number"));
        fullName.setCellValueFactory(new PropertyValueFactory<Subdivision, String>("fullName"));
        shortName.setCellValueFactory(new PropertyValueFactory<Subdivision, String>("shortName"));
    }

    private void hideTables(){
        tableSubdivisions.setVisible(false);
        tableStaff.setVisible(false);
        tableTechnics.setVisible(false);
        tableTransfer.setVisible(false);
    }

    private int setInterface(){
        int pointer;
        switch (Controller.getPointerInterface()){
            case 0:{
                pointer = 0;
                addButton.setText("ДОБАВИТЬ ТЕХНИКУ");
                break;
            }
            case 1:{
                pointer = 1;
                createSubdivisionTable();
                addButton.setText("ДОБАВИТЬ ПОДРАЗДЕЛЕНИЕ");
                break;
            }
            case 2:{
                pointer = 2;
                addButton.setText("ДОБАВИТЬ ОТВЕТСТВЕННОЕ ЛИЦО");
                break;
            }
            case 3:{
                pointer = 3;
                addButton.setText("ДОБАВИТЬ ПЕРЕДАЧУ");
                break;
            }
            default: return -1;
        }
        return pointer;
    }

    private void createDialogWindow() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/addEditWindowSubdivision.fxml"));
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
//        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(root, 350,350);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        hideTables();
        dataBase = new DataBase();
        int pointerInterface = setInterface();
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
                createDialogWindow();
                if(ControllerAddEditWindowSubdivision.result){
                    dataBase.addSubdivisions(ControllerAddEditWindowSubdivision.getSubdivision());
                    ObservableList<Subdivision> temp = FXCollections.observableArrayList(dataBase.getSubdivisions());
                    tableSubdivisions.setItems(temp);
                }
                equipment = null;
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        });
//        if(pointerInterface == -1) return;
    }
}
