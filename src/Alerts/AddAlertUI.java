package Alerts;

import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by Incau Ionut on 20-May-17.
 * Contact: ionut.incau@gmail.com
 */

public class AddAlertUI implements Initializable {
    @FXML private Button alertAddButton;
    @FXML private TextArea alertTextArea;
    @FXML private TextField dateField;

    private Alert alert;
    private AlertsController controller;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    public void initData(String name, Alert alert, AlertsController controller) {
        this.controller = controller;
        this.alert = alert;
        if (name == "Adauga") {
            alertAddButton.setOnAction(e->addAlert());
        }
        else {
            dateField.setText(alert.getDeadline().toString());
            alertTextArea.setText(alert.getText());
            alertAddButton.setOnAction(e->editAlert());
        }
    }

    public void addAlert() {
        int id_employee = 2;
        try {
            controller.add(controller.getAvaliableId(), id_employee, alertTextArea.getText(), dateField.getText(), new GregorianCalendar(), "send");
        }
        catch (ParseException e) {
            UtilFunctions.showInfo("Format invalid!\nData trebuie sa fie de forma: zz.ll.aaaa");
        }
    }

    public void editAlert() {

    }
}
