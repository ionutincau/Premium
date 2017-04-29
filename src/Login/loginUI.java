package Login;

import Documents.DocumentsController;
import Employees.Employee;
import Utils.UIAlerts;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Created by Incau Ionut on 14-Mar-17.
 * Contact: ionut.incau@gmail.com
 */

public class loginUI {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TabPane tabPane;
    @FXML private Tab loginTab;

    private LoginController loginController;

    public loginUI() {
        loginController = LoginController.getInstance();
    }

    public void login() {
        try {
            loginController.login(usernameField.getText(), passwordField.getText());
        }
        catch (Exception e) {
            UIAlerts.showInfo(e.getMessage());
            if (e.getMessage().equals("Parola incorecta!")) {
                passwordField.setText("");
            }
            else {
                usernameField.setText("");
                passwordField.setText("");
            }
        }
        Employee user = loginController.getLoggedUser();
        if (user != null) {
            closeTab(loginTab);
            addTabs(user.getRole());
        }
    }

    public void addTabs(String usertype) {
        try {
            if (usertype.equals("hr") || usertype.equals("admin")) {
                Tab angajati = FXMLLoader.load(this.getClass().getResource("../Employees/employees.fxml"));
                tabPane.getTabs().add(angajati);
            }

            Tab pontaj = FXMLLoader.load(this.getClass().getResource("../Clocking/clocking_" + usertype + ".fxml"));
            tabPane.getTabs().add(pontaj);

            Tab cereri = FXMLLoader.load(this.getClass().getResource("../Requests/requests_" + usertype + ".fxml"));
            tabPane.getTabs().add(cereri);

            Tab notificari = FXMLLoader.load(this.getClass().getResource("../Alerts/alerts_" + usertype + ".fxml"));
            tabPane.getTabs().add(notificari);
        }
        catch (IOException ex) {
            UIAlerts.showInfo("Application can't manage usertype " + usertype + "\nContact system administrator");
        }

        DocumentsController d = new DocumentsController(); // todo: remove this
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
