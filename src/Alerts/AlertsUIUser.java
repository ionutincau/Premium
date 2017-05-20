package Alerts;

import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */


public class AlertsUIUser implements Initializable, Observer {
    private AlertsController controller;
    @FXML private ListView notificariListView;

    public AlertsUIUser() {
        this.controller = new AlertsController();
        this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        notificariListView.setFixedCellSize(48);
        notificariListView.getItems().addAll(0, controller.getAlerts());
    }

    @Override
    public void update(Observable o, Object arg) {
        notificariListView.getItems().clear();
        notificariListView.getItems().addAll(0, controller.getAlerts());
    }

    public void setRead() {
        Alert alert = (Alert) notificariListView.getSelectionModel().getSelectedItem();
        if (alert != null) {
            controller.setRead(alert);
        }
        else UtilFunctions.showInfo("Selectati o alerta");
    }
}
