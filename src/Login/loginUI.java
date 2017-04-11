package Login;

import Employees.Employee;
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
        loginController = new LoginController();
    }

    public void login() {
        try {
            loginController.login(usernameField.getText(), passwordField.getText());
        }
        catch (Exception e) {
            showInfo(e.getMessage());
            if (e.getMessage().equals("Wrong password!")) {
                passwordField.setText("");
            }
            else {
                usernameField.setText("");
                passwordField.setText("");
            }
        }
        Employee user = loginController.getUser();
        if (user != null) {
            closeTab(loginTab);
            addTabs(user.getRole());
        }
    }

    public void addTabs(String usertype) {
        try {
            Tab pontaj = FXMLLoader.load(this.getClass().getResource("../Clocking/clocking_" + usertype + ".fxml"));
            tabPane.getTabs().add(pontaj);

            Tab cereri = FXMLLoader.load(this.getClass().getResource("../Requests/requests_" + usertype + ".fxml"));
            tabPane.getTabs().add(cereri);

            Tab notificari = FXMLLoader.load(this.getClass().getResource("../Alerts/alerts_" + usertype + ".fxml"));
            tabPane.getTabs().add(notificari);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void showInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(info);
        alert.show();
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
