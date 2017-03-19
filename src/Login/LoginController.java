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
        //TODO: first set loginUI, and, after user is logged in setLoginScene()

    }

    public void setLoginScene() {
        // TODO: choose between user, hr, or admin stage

        LoginUI loginUI = new LoginUI();
        Scene loginScene = loginUI.getScene();
        this.primaryStage.setScene(loginScene);

        /*
        ClockingUI clockingUI = new ClockingUI();
        Scene scene = clockingUI.getScene();
        this.primaryStage.setLoginScene(scene);
        */
    }

    public void setUserScene()
    {
        ClockingUI clockingUI = new ClockingUI();
        Scene scene = clockingUI.getScene();
        this.primaryStage.setScene(scene);
    }

    public void setHRScene()
    {

    }

    public void setAdminScene()
    {

    }

    public void login(String username, String password)
    {
        //TODO replace code below after database is up
        if (username.equals("admin") && password.equals("123456"))
        {
            this.setUserScene();

        }
    }
}
