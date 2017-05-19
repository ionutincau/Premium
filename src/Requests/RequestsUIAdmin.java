package Requests;

import Login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */
public class RequestsUIAdmin implements Initializable,Observer {
    private RequestsController controller;

    @FXML private ListView cereriListView;
    @FXML private Label cereriAcceptateLabel;
    @FXML private Label cereriRespinseLabel;
    @FXML private Label cereriInAsteptareLabel;
    @FXML private Label cereriTotalLabel;

    public RequestsUIAdmin()
    {
        this.controller=new RequestsController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        cereriListView.setFixedCellSize(48);
        cereriListView.getItems().addAll(0,controller.getRequestsListForAdmin());

        cereriAcceptateLabel.setText("Acceptate: "+controller.getNrAcc());
        cereriRespinseLabel.setText("Respinse: "+controller.getNrRes());
        cereriTotalLabel.setText("Total: "+controller.getMaxAcc());
        cereriInAsteptareLabel.setText("In asteptare: "+controller.getNrPending());
    }
    @Override
    public void update(Observable o, Object arg) {
        cereriListView.getItems().clear();
        cereriListView.getItems().addAll(0,controller.getRequestsListForAdmin());
        cereriAcceptateLabel.setText("Acceptate: "+controller.getNrAcc());
        cereriRespinseLabel.setText("Respinse: "+controller.getNrRes());
        cereriTotalLabel.setText("Total: "+controller.getMaxAcc());
        cereriInAsteptareLabel.setText("In asteptare: "+controller.getNrPending());
    }
}
