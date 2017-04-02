package UI;

import Login.LoginController;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class UIController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab loginTab;


    private LoginController loginController;

    public UIController()
    {
        loginController = new LoginController();

    }

    public void Login()
    {
        loginController.Login(usernameField.getText(), passwordField.getText());

        if (loginController.isAuthenticated())
        {
            tabPane.getTabs().get(1).setDisable(false);
            tabPane.getTabs().get(2).setDisable(false);
            tabPane.getTabs().get(3).setDisable(false);
            closeTab( loginTab);

        }
    }



    public void initialize() {
        // TODO separate tabs in different fxml files, and open them only after login
        /*
        Tab myNewTab = FXMLLoader.load(this.getClass().getResource(fxmlFile));
        myTabPane.getTabs().add(myNewTab);
        //openTabs.put(fxmlFile, myNewTab);

         */
        // disable tabs before login
        tabPane.getTabs().get(1).setDisable(true);
        tabPane.getTabs().get(2).setDisable(true);
        tabPane.getTabs().get(3).setDisable(true);

    }

    private void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        } else {
            tab.getTabPane().getTabs().remove(tab);
        }
    }
}
