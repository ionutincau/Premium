package Alerts;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class AlertsUIAdmin implements Initializable, Observer {
    private AlertsController controller;

    public AlertsUIAdmin() {
        this.controller = new AlertsController();
        this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void update(java.util.Observable o, Object arg) {

    }
}
