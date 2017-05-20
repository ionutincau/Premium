package Alerts;

import Login.LoginController;
import Utils.UtilFunctions;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
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
        this.notificariListView.setCellFactory(new Callback<ListView<Alert>, ListCell<Alert>>() {
            public ListCell<Alert> call(ListView<Alert> proposalListView) {
                return new AlertsUIUser.ColorRectCell();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        notificariListView.getItems().clear();
        notificariListView.getItems().addAll(0, controller.getAlerts());
        this.notificariListView.setCellFactory(new Callback<ListView<Alert>, ListCell<Alert>>() {
            public ListCell<Alert> call(ListView<Alert> proposalListView) {
                return new AlertsUIUser.ColorRectCell();
            }
        });
    }

    public void setRead() {
        Alert alert = (Alert) notificariListView.getSelectionModel().getSelectedItem();
        if (alert != null) {
            controller.setRead(alert);
        }
        else UtilFunctions.showInfo("Selectati o alerta");
    }

    static class ColorRectCell extends ListCell<Alert> {
        private final Rectangle rect;
        public PseudoClass inactive = PseudoClass.getPseudoClass("inactive");
        ArrayList ids;

        ColorRectCell() {
            this.setContentDisplay(ContentDisplay.CENTER);
            this.rect = new Rectangle(580, 45);
            this.ids = AlertsProvider.unreadAlerts(LoginController.getInstance().getLoggedUser().getId());
        }

        public void updateItem(Alert item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                if (ids.contains(item.getId_alert())) {
                    this.rect.setFill(Color.web("#e74c3c"));
                    this.setGraphic(this.rect);
                }
            }
            else {
                this.setGraphic((Node)null);
            }
            setText(empty ? null : item.toString());
            pseudoClassStateChanged(inactive, item != null && item.toString().endsWith(" - not active"));
        }
    }
}
