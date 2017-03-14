package Login;

import Clocking.ClockingUI;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class LoginController {
    private Stage primaryStage;

    public LoginController(Stage stage) {
        this.primaryStage = stage;
        //TODO: first set loginUI, and, after user is logged in setScene()
    }

    public void setScene() {
        // TODO: choose between user, hr, or admin stage

        ClockingUI clockingUI = new ClockingUI();
        Scene scene = clockingUI.getScene();
        this.primaryStage.setScene(scene);
    }
}
