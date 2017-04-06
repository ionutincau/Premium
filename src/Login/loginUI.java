package Login;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class loginUI {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab loginTab;

    private LoginController loginController;

    public loginUI()
    {
        loginController = new LoginController();

    }

    public void Login() {
        loginController.Login(usernameField.getText(), passwordField.getText());

        if (loginController.isAuthenticated()) {

            closeTab(loginTab);

            String usertype = loginController.getUserType().toLowerCase();

            switch (usertype)
            {
                case "admin":
                {
                    usertype = "admin";
                    break;
                }
                case "hr":
                {
                    usertype = "hr";
                    break;
                }
                default:
                {
                    usertype = "user";
                }
            }

            try {
                Tab pontaj = FXMLLoader.load(this.getClass().getResource("../Clocking/clocking_" + usertype + ".fxml"));
                tabPane.getTabs().add(pontaj);

                Tab cereri = FXMLLoader.load(this.getClass().getResource("../Requests/requests_" + usertype + ".fxml"));
                tabPane.getTabs().add(cereri);

                Tab notificari = FXMLLoader.load(this.getClass().getResource("../Alerts/alerts_" + usertype + ".fxml"));
                tabPane.getTabs().add(notificari);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



    public void initialize() {
        //TODO adaugare tipuri cerere in dropdown

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
