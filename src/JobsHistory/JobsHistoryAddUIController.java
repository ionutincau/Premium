package JobsHistory;

import Utils.UtilFunctions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class JobsHistoryAddUIController implements Initializable {

    private JobsHistoryController controller;
    private JobHistory jobHistory;
    @FXML
    private DatePicker jobHistoryEndDateDatePicker;

    @FXML
    private Button jobHistoryOkButton;

    @FXML
    private DatePicker jobHistoryStartDateDatePicker;

    @FXML
    private ChoiceBox<String> jobHistoryUserChoiceBox;
    @FXML
    private ChoiceBox<String> jobHistoryDepartmentChoiceBox;
    @FXML
    private ChoiceBox<String> jobHistoryJobChoiceBox;
    @FXML
    private ChoiceBox<String> jobHistoryTypeChoiceBox;
    public void initData(String name, JobHistory jobHistory, JobsHistoryController controller) {
        this.controller=controller;
        this.jobHistory=jobHistory;
        jobHistoryUserChoiceBox.setItems(FXCollections.observableArrayList(controller.getEmployeeNameList()));
        jobHistoryJobChoiceBox.setItems(FXCollections.observableArrayList(controller.getJobNameList()));
        jobHistoryDepartmentChoiceBox.setItems(FXCollections.observableArrayList(controller.getDepartmentNameList()));
        jobHistoryTypeChoiceBox.setItems(FXCollections.observableArrayList(controller.getTypeList()));
        jobHistoryOkButton.setText(name);
        if (name=="Adauga") {
            jobHistoryOkButton.setOnAction(e->Add());
        }
        else {

            Calendar end = jobHistory.getEnd_date();
            ZonedDateTime zdtEnd = ZonedDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
            LocalDate localDateEnd =zdtEnd.toLocalDate();
            jobHistoryEndDateDatePicker.setValue(localDateEnd);
            Calendar start = jobHistory.getStart_date();
            ZonedDateTime zdtStart = ZonedDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
            LocalDate localDateStart =zdtStart.toLocalDate();
            jobHistoryStartDateDatePicker.setValue(localDateStart);
            jobHistoryUserChoiceBox.setValue(controller.getNameJH(jobHistory.getId_employee()));
            jobHistoryDepartmentChoiceBox.setValue(controller.getDepartmentName(jobHistory.getId_department()));
            jobHistoryJobChoiceBox.setValue(controller.getJobName(jobHistory.getId_job()));
            jobHistoryTypeChoiceBox.setValue(jobHistory.getStatus());
            jobHistoryOkButton.setOnAction(e->Edit());
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    }
    public void Add() {
        try {
            if (jobHistoryTypeChoiceBox.getSelectionModel().getSelectedItem().equals("fired")){
                LocalDate localDateIn = jobHistoryStartDateDatePicker.getValue();
                Date dateIn = java.sql.Date.valueOf(localDateIn);
                Format formatter = new SimpleDateFormat("dd.MM.yyyy");
                String Start = formatter.format(dateIn);
                controller.add(Start,"30.12.2099",jobHistoryUserChoiceBox.getSelectionModel().getSelectedItem(),jobHistoryJobChoiceBox.getSelectionModel().getSelectedItem(),jobHistoryDepartmentChoiceBox.getSelectionModel().getSelectedItem(),jobHistoryTypeChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage)jobHistoryOkButton.getScene().getWindow();
                stage.close();
            }
            else {
                LocalDate localDateIn = jobHistoryStartDateDatePicker.getValue();
                Date dateIn = java.sql.Date.valueOf(localDateIn);
                Format formatter = new SimpleDateFormat("dd.MM.yyyy");
                String Start = formatter.format(dateIn);
                LocalDate localDateOut = jobHistoryEndDateDatePicker.getValue();
                Date dateOut = java.sql.Date.valueOf(localDateOut);
                String End = formatter.format(dateOut);
                controller.add(Start, End, jobHistoryUserChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryJobChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryDepartmentChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryTypeChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) jobHistoryOkButton.getScene().getWindow();
                stage.close();
            }
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }

    public  void Edit() {
        try {
            if (jobHistoryTypeChoiceBox.getSelectionModel().getSelectedItem().equals("fired")) {
                LocalDate localDateIn = jobHistoryStartDateDatePicker.getValue();
                Date dateIn = java.sql.Date.valueOf(localDateIn);
                Format formatter = new SimpleDateFormat("dd.MM.yyyy");
                String Start = formatter.format(dateIn);
                controller.edit(jobHistory.getIdJobHistory(), Start, "30.12.2099", jobHistoryUserChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryJobChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryDepartmentChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryTypeChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) jobHistoryOkButton.getScene().getWindow();
                stage.close();
            }
            else {
                LocalDate localDateIn = jobHistoryStartDateDatePicker.getValue();
                Date dateIn = java.sql.Date.valueOf(localDateIn);
                Format formatter = new SimpleDateFormat("dd.MM.yyyy");
                String Start = formatter.format(dateIn);
                LocalDate localDateOut = jobHistoryEndDateDatePicker.getValue();
                Date dateOut = java.sql.Date.valueOf(localDateOut);
                String End = formatter.format(dateOut);
                controller.edit(jobHistory.getIdJobHistory(), Start, End, jobHistoryUserChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryJobChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryDepartmentChoiceBox.getSelectionModel().getSelectedItem(), jobHistoryTypeChoiceBox.getSelectionModel().getSelectedItem());
                Stage stage = (Stage) jobHistoryOkButton.getScene().getWindow();
                stage.close();
            }
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }
}
