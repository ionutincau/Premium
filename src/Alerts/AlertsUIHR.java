package Alerts;

import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class AlertsUIHR implements Initializable, Observer {
    private AlertsController controller;
    @FXML private ListView notificariListView;

    public AlertsUIHR() {
        this.controller = new AlertsController();
        this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notificariListView.setFixedCellSize(48);
        notificariListView.getItems().addAll(0, controller.getAllAlerts());
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        notificariListView.getItems().clear();
        notificariListView.getItems().addAll(0, controller.getAllAlerts());
    }

    private void loadWindow(String name, Alert alert) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("alert_add.fxml"));
            AnchorPane root = loader.load();
            AddAlertUI editController = loader.<AddAlertUI>getController();
            editController.initData(name, alert, controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAlert() {
        loadWindow("Adauga", null);
    }


    public void editAlert() {
        notificariListView.getSelectionModel().getSelectedIndex();
        Alert alert = (Alert) notificariListView.getSelectionModel().getSelectedItem();
        if (alert != null) {
            loadWindow("Editare", alert);
        }
        else UtilFunctions.showInfo("Selectati o alerta");
    }


    public void deleteAlert() {
        Alert alert = (Alert) notificariListView.getSelectionModel().getSelectedItem();
        if (alert != null) {
            controller.removeAlert(alert);
        }
        else UtilFunctions.showInfo("Selectati o alerta");
    }
}

