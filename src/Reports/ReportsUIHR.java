package Reports;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class ReportsUIHR implements Initializable, Observer {
    private ReportsController controller;
    @FXML private ListView reportsListView;

    public ReportsUIHR() {
        this.controller = new ReportsController();
        this.controller.addObserver(this);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reportsListView.setFixedCellSize(48);
        //reportsListView.getItems().addAll(0, controller.getAllReports());
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        reportsListView.getItems().clear();
        //reportsListView.getItems().addAll(0, controller.getAllReports());
    }

    public void generateTime() {
        controller.generateTime();
    }
}

