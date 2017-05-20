package Login;

import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class logoutUI {
    @FXML private Tab logoutTab;
    private LoginController loginController;

    public logoutUI() {
        loginController = LoginController.getInstance();
    }

    public void logout() {
        loginController.setLoggedUser(null);
        TabPane tabPane = logoutTab.getTabPane();
        Collection tabs = tabPane.getTabs();
        tabPane.getTabs().removeAll(tabs);

        try {
            Tab loginTab = FXMLLoader.load(this.getClass().getResource("login.fxml"));
            tabPane.getTabs().add(loginTab);
        }
        catch (IOException ex) {
            UtilFunctions.showInfo("Application can't manage usertype\nContact system administrator");
        }
    }
}
