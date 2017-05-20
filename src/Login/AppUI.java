package Login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class AppUI implements Initializable {
    @FXML private TabPane tabPane;

    public AppUI() {

    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        try {
            Tab login = FXMLLoader.load(this.getClass().getResource("login.fxml"));
            tabPane.getTabs().add(login);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
