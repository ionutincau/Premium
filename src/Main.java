import Login.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by Incau Ionut on 28-Feb-17.
 * Contact: ionut.incau@gmail.com
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Premium");

        LoginController login = new LoginController(primaryStage);
        login.setScene();
        primaryStage.show();
    }
}
