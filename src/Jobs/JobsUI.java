package Jobs;

import Login.LoginController;
import Utils.UtilFunctions;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by MariusDK on 01.05.2017.
 */

public class JobsUI implements Initializable,Observer{
    private JobsController controller;
    @FXML private ListView jobsListView;
    @FXML private Button addJobButton;
    @FXML private Button editJobButton;
    @FXML private Button deleteJobButton;

    public JobsUI() {
        this.controller = new JobsController();
        this.controller.addObserver(this);
        LoginController.getInstance().addObserver(this);
    }

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        jobsListView.setFixedCellSize(48);
        jobsListView.getItems().addAll(0, controller.getJobs());
        jobAdd();
        jobEdit();
        jobDelete();
    }

    private void loadWindow(String name, Job job) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_job.fxml"));
            AnchorPane root = loader.load();
            JobsUIEdit editController = loader.<JobsUIEdit>getController();
            editController.initData(name,job,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jobAdd()
    {
        addJobButton.setOnAction(event -> loadWindow("Adauga",null));
    }

    public void jobEdit() {
        editJobButton.setOnAction(event -> {
            jobsListView.getSelectionModel().getSelectedIndex();
            Job job=(Job)jobsListView.getSelectionModel().getSelectedItem();
            if (job!=null) {
                loadWindow("Editare",job);
            }
            else UtilFunctions.showInfo("Selectati un job");
        });
    }

    public  void jobDelete() {
        deleteJobButton.setOnAction(event -> {
            jobsListView.getSelectionModel().getSelectedIndex();
            Job job=(Job)jobsListView.getSelectionModel().getSelectedItem();
            if (job!=null)
            {
                controller.removeJob(job);
            }
            else UtilFunctions.showInfo("Selectati un job");
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        jobsListView.getItems().clear();
        jobsListView.getItems().addAll(0, controller.getJobs());
    }
}
