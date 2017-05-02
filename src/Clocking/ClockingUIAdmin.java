package Clocking;

import Login.LoginController;
import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class ClockingUIAdmin implements Initializable, Observer{

    private ClockingController controller;

    @FXML private ListView pontajListView;
    @FXML private Button adaugaPontajButton;
    @FXML private Button editeazaPontajButton;
    @FXML private Button stergePontajButton;
    @FXML private TextField deLaPontajField;
    @FXML private TextField panaLaPontajField;
    @FXML private Button filtreazaButton;

    public ClockingUIAdmin() {
        this.controller = new ClockingController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        pontajListView.setFixedCellSize(48);
        pontajListView.getItems().addAll(0, controller.getClocking());
        get_buttons();
    }

    private void get_buttons() {
        adaugaPontajButton.setOnAction(e-> adaugare());
        editeazaPontajButton.setOnAction(e-> editare());
        stergePontajButton.setOnAction(e-> stergere());
        filtreazaButton.setOnAction(e -> filtrare());
    }

    public void adaugare() {
        loadWindow("Adauga", null);
    }

    public void editare() {
        pontajListView.getSelectionModel().getSelectedIndex();
        Clocking clocking = (Clocking) pontajListView.getSelectionModel().getSelectedItem();
        if (clocking != null) loadWindow("Editeaza", clocking);
        else UtilFunctions.showInfo("Selectati un pontaj");
    }

    public void stergere() {
        pontajListView.getSelectionModel().getSelectedIndex();
        Clocking clocking = (Clocking) pontajListView.getSelectionModel().getSelectedItem();
        if (clocking != null) controller.delete(clocking);
        else UtilFunctions.showInfo("Selectati un pontaj");
    }

    public void filtrare() {
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.search(deLaPontajField.getText(),panaLaPontajField.getText()));
    }

    @Override
    public void update(Observable o, Object arg) {
        pontajListView.getItems().clear();
        pontajListView.getItems().addAll(0, controller.getClocking());
    }

    private void loadWindow(String name, Clocking clocking) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("clocking_edit.fxml"));
            AnchorPane root = loader.load();
            ClockingUIEdit editController = loader.<ClockingUIEdit>getController();
            editController.initData(name, clocking, controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
