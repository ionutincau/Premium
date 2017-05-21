package Departments;

import Utils.UtilFunctions;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by MariusDK on 01.05.2017.
 */

public class DepartmentsUIEdit implements Initializable{

    private Department department;
    private DepartmentsController controller;

    @FXML private TextField departmentNameTextField;
    @FXML private ChoiceBox departmentManagerChoiceBox;
    @FXML private Button departmentOKButton;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {}

    public void initData(String name, Department department, DepartmentsController controller) {
        this.controller = controller;
        this.department = department;
        departmentManagerChoiceBox.setItems(FXCollections.observableArrayList(controller.getEmployeeName()));
        departmentOKButton.setText(name);
        if (name == "Adauga") {
            departmentOKButton.setOnAction(e->Add());
        }
        else {
            departmentNameTextField.setText(department.getName());
            departmentManagerChoiceBox.setValue(controller.getManagerName(department.getId_manager()));
            departmentOKButton.setOnAction(e->Edit());
        }
    }

    public void Add() {
        try {
            if(departmentNameTextField.getText().isEmpty()){UtilFunctions.showInfo("Introduceti numele departamentului!");}
            else if(!departmentNameTextField.getText().matches("[a-zA-Z_]+")){
                UtilFunctions.showInfo("Numele departamentului nu poate fi numar!");
            }
            else{
                controller.addDepartment(departmentNameTextField.getText(), departmentManagerChoiceBox.getSelectionModel().getSelectedItem().toString());
                Stage stage = (Stage) departmentOKButton.getScene().getWindow();
                stage.close();
            }
        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch(NullPointerException e)
        {
            UtilFunctions.showInfo("Alegeti Manager");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }

    public  void Edit() {
        try {
            if(departmentNameTextField.getText().isEmpty()){UtilFunctions.showInfo("Introduceti numele departamentului!");}
            else if(!departmentNameTextField.getText().matches("[a-zA-Z_]+")){
                UtilFunctions.showInfo("Numele departamentului nu poate fi numar!");
            }
            else{
                controller.editDepartment(department.getId(),departmentNameTextField.getText(),departmentManagerChoiceBox.getSelectionModel().getSelectedItem().toString());
                Stage stage = (Stage) departmentOKButton.getScene().getWindow();
                stage.close();
            }

        }
        catch (NumberFormatException e) {
            UtilFunctions.showInfo("Format invalid!\n");
        }
        catch(NullPointerException e)
        {
            UtilFunctions.showInfo("Alegeti Manager");
        }
        catch (Exception e) {
            UtilFunctions.showInfo(e.getMessage());
        }
    }
}
