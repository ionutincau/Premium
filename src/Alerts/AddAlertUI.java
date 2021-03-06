package Alerts;

import Utils.UtilFunctions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    @FXML private ChoiceBox alertEmployeeLabelChoiceBox;
    @FXML private CheckBox checkBox;

    private Alert alert;
    private AlertsController controller;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    public void initData(String name, Alert alert, AlertsController controller) {
        this.controller = controller;
        this.alert = alert;
        alertEmployeeLabelChoiceBox.setItems(FXCollections.observableArrayList(controller.getEmployeeName()));
        alertAddButton.setText(name);
        if (name == "Adauga") {
            alertAddButton.setOnAction(e->addAlert());
        }
        else {
            dateField.setText(UtilFunctions.get_date_format(alert.getDeadline()));
            alertTextArea.setText(alert.getText());
            alertAddButton.setOnAction(e->editAlert());
        }
    }

    public void addAlert() {
        String name = null;
        if (alertEmployeeLabelChoiceBox.getSelectionModel().getSelectedItem() != null) {
            name = alertEmployeeLabelChoiceBox.getSelectionModel().getSelectedItem().toString();
        }
        boolean checked = checkBox.isSelected();
        try {
            if (name == null && checked == false) throw new Exception("Selectati un angajat sau trimiteti la toti angajatii");
            if (alertTextArea.getText().isEmpty()) throw new Exception("Completati mesajul");
            controller.add(name, checked, alertTextArea.getText(), dateField.getText(), new GregorianCalendar(), "send");
            Stage stage = (Stage) alertAddButton.getScene().getWindow();
            stage.close();
        }
        catch (ParseException e) {
            UtilFunctions.showInfo("Format invalid!\nData trebuie sa fie de forma: zz.ll.aaaa");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }

    public void editAlert() {
        String name = null;
        if (alertEmployeeLabelChoiceBox.getSelectionModel().getSelectedItem() != null) {
            name = alertEmployeeLabelChoiceBox.getSelectionModel().getSelectedItem().toString();
        }
        boolean checked = checkBox.isSelected();
        try {
            if (name == null && checked == false) throw new Exception("Selectati un angajat sau trimiteti la toti angajatii");
            if (alertTextArea.getText().isEmpty()) throw new Exception("Completati mesajul");
            controller.removeAlert(alert);
            controller.add(name, checked, alertTextArea.getText(), dateField.getText(), new GregorianCalendar(), "send");
            Stage stage = (Stage) alertAddButton.getScene().getWindow();
            stage.close();
        }
        catch (ParseException e) {
            UtilFunctions.showInfo("Format invalid!\nData trebuie sa fie de forma: zz.ll.aaaa");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }
}
