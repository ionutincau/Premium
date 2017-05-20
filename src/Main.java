import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Parent root = FXMLLoader.load(getClass().getResource("Login/app.fxml"));
        primaryStage.setTitle("Premium");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
