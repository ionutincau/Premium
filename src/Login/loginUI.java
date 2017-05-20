package Login;

import Alerts.AlertsProvider;
import Employees.Employee;
import Utils.UtilFunctions;
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
    @FXML private Tab loginTab;
    private TabPane tabPane;

    private LoginController loginController;

    public loginUI() {
        loginController = LoginController.getInstance();
    }

    public void login() {
        try {
            loginController.login(usernameField.getText(), passwordField.getText());
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
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
            if (usertype.equals("admin")) {
                Tab angajati = FXMLLoader.load(this.getClass().getResource("../Employees/employees.fxml"));
                Tab departamente = FXMLLoader.load(this.getClass().getResource("../Departments/departments_tab.fxml"));
                Tab job = FXMLLoader.load(this.getClass().getResource("../Jobs/jobs_tab.fxml"));
                Tab arhiva = FXMLLoader.load(this.getClass().getResource("../JobsHistory/jobs_history_tab.fxml"));

                tabPane.getTabs().add(arhiva);
                tabPane.getTabs().add(angajati);
                tabPane.getTabs().add(departamente);
                tabPane.getTabs().add(job);
            }
            if (usertype.equals("hr")) {
                Tab angajati = FXMLLoader.load(this.getClass().getResource("../Employees/employees.fxml"));
                Tab departamente = FXMLLoader.load(this.getClass().getResource("../Departments/departments_tabHR.fxml"));
                Tab job = FXMLLoader.load(this.getClass().getResource("../Jobs/jobs_tabHR.fxml"));

                tabPane.getTabs().add(angajati);
                tabPane.getTabs().add(departamente);
                tabPane.getTabs().add(job);
            }

            Tab pontaj = FXMLLoader.load(this.getClass().getResource("../Clocking/clocking_" + usertype + ".fxml"));
            tabPane.getTabs().add(pontaj);

            Tab cereri = FXMLLoader.load(this.getClass().getResource("../Requests/requests_" + usertype + ".fxml"));
            tabPane.getTabs().add(cereri);

            Tab notificari = FXMLLoader.load(this.getClass().getResource("../Alerts/alerts_" + usertype + ".fxml"));
            tabPane.getTabs().add(notificari);

            Tab requests = FXMLLoader.load(this.getClass().getResource("../Requests/requests_user.fxml"));
            tabPane.getTabs().add(requests);

            Tab logout = FXMLLoader.load(this.getClass().getResource("logout.fxml"));
            tabPane.getTabs().add(logout);

            if (!AlertsProvider.unreadAlerts(loginController.getLoggedUser().getId()).isEmpty()) {
                UtilFunctions.showInfo("Aveti alerte necitite\nVerificati tabul Alerte!");
            }
        }
        catch (IOException ex) {
            UtilFunctions.showInfo("Application can't manage usertype " + usertype + "\nContact system administrator");
        }
    }

    private void closeTab(Tab tab) {
        EventHandler<Event> handler = tab.getOnClosed();
        if (null != handler) {
            handler.handle(null);
        }
        else {
            tabPane = tab.getTabPane();
            tabPane.getTabs().remove(tab);
        }
    }
}
