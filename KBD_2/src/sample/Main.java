package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
//        Controller controller = new Controller(root);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();
        System.out.println("Good!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
