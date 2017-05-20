package Requests;

import DocumentTypes.Income;
import DocumentTypes.Vacation;
import DocumentTypes.WorkedPeriod;
import Documents.Document;
import Documents.DocumentsProvider;
import Login.LoginController;
import Utils.UtilFunctions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 4/3/2017.
 */

public class RequestsUIUser implements Initializable,Observer{

    private RequestsController controller;
    @FXML private ListView cereriListView;
    @FXML private Button creeazaCerereButton;
    @FXML private ChoiceBox cereriChoiceBox;

    public RequestsUIUser() {
        this.controller = new RequestsController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        cereriListView.setFixedCellSize(48);
        cereriListView.getItems().addAll(0, controller.getRequestsListForUser());
        cereriChoiceBox.setItems(FXCollections.observableArrayList(controller.getRequestType()));
        creeazaCerereButton.setOnAction(e -> {
            String type = (String) cereriChoiceBox.getSelectionModel().getSelectedItem();
            if (type != null) {
                controller.saveRequestUserAndDoc(type);
            }
            else {
                UtilFunctions.showInfo("Selectati un tip de cerere");
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        cereriListView.getItems().clear();
        cereriListView.getItems().addAll(0,controller.getRequestsListForUser());
    }

    public void seeFile () {
        Request request = (Request) cereriListView.getSelectionModel().getSelectedItem();
        if (request != null) {
            if (request.getStatus().equals("approved")) {
                Document document = DocumentsProvider.getDocumentById(request.getId_document());
                int doctype = document.getId_doctype();
                String filename = null;
                if (doctype == 1) {
                    Vacation vacation = (Vacation) document.getDoc();
                    vacation.generatePDF();
                    filename = vacation.getFilenameName();
                }
                else if (doctype == 2) {
                    WorkedPeriod workedPeriod = (WorkedPeriod) document.getDoc();
                    workedPeriod.generatePDF();
                    filename = workedPeriod.getFilenameName();
                }
                else if (doctype == 3) {
                    Income income = (Income) document.getDoc();
                    income.generatePDF();
                    filename = income.getFilenameName();
                }
                if (Desktop.isDesktopSupported()) {
                    try {
                        File myFile = new File(filename);
                        Desktop.getDesktop().open(myFile);
                    }
                    catch (IOException ex) {
                        UtilFunctions.showInfo("No application registered for PDFs");
                    }
                }
            }
            else {
                UtilFunctions.showInfo("Cererea nu a fost inca aprobata");
            }
        }
        else {
            UtilFunctions.showInfo("Selectati o cerere");
        }
    }
}
