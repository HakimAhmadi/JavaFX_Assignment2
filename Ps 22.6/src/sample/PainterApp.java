package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PainterApp extends Application {
    @Override
    public void start(Stage Stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PainterApp.fxml"));
        Scene scene = new Scene(root);
        Stage.setTitle("Painter App");
        Stage.setScene(scene);
        Stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
