package Clocking;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by MariusDK on 13.03.2017.
 */

public class ClockingUI extends Application {

    Button button;
    StackPane layout;
    Scene scene;

    public ClockingUI() {}

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ClockingUI");
        button = new Button("Clockin");
        layout = new StackPane();
        layout.getChildren().add(button);
        scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void run() {
        launch();
    }
}
