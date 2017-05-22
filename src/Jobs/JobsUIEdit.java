package Jobs;

import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by MariusDK on 01.05.2017.
 */

public class JobsUIEdit implements Initializable {

    private Job job;
    private JobsController controller;
    @FXML private TextField jobNameTextField;
    @FXML private TextField minimumSalaryTextField;
    @FXML private TextField employeeNumberTextField;
    @FXML private Button jobOKButton;
    @FXML private Label employeeNumberLabel;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    public void initData(String name, Job job, JobsController controller) {
        this.controller=controller;
        this.job=job;
        jobOKButton.setText(name);
        if (name=="Adauga") {
            jobOKButton.setOnAction(e->Add());
        }
        else {
            jobNameTextField.setText(job.getName());
            minimumSalaryTextField.setText(job.getMin_salary());
            employeeNumberTextField.setText(job.getNumber() + "");
            jobOKButton.setOnAction(e->Edit());
        }
    }

    public void Add() {
        try {
            controller.addJob(jobNameTextField.getText(), minimumSalaryTextField.getText(), employeeNumberTextField.getText(),1);
            Stage stage = (Stage)jobOKButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }

    public void Edit() {
        try {
            controller.editJob(job.getId(),jobNameTextField.getText(),minimumSalaryTextField.getText(),employeeNumberLabel.getText(),1);
            Stage stage = (Stage) jobOKButton.getScene().getWindow();
            stage.close();
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }
}
