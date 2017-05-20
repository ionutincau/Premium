package JobsHistory;

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
 * Created by Aurelian on 5/19/2017.
 */
public class JobsHistoryTabUIController implements Initializable,Observer {

    private JobsHistoryController controller;
    @FXML private Button jobsHistoryEditButton;
    @FXML private Button jobsHistoryAddButton;
    @FXML private Button jobsHistoryDeleteButton;
    @FXML private ListView<JobHistory> jobsHistoryListView;

    private JobsHistoryTabUIController()
    {
        this.controller=new JobsHistoryController();
        this.controller.addObserver(this);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println("OK");
       jobsHistoryListView.setFixedCellSize(48);
       jobsHistoryListView.getItems().addAll(0, controller.getJobHistoryList());
       JobHistoryAdd();
       JobsHistoryDelete();
       JobsHistoryEdit();
    }
    private void loadWindow(String name, JobHistory jobHistory) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("jobs_history_add.fxml"));
            AnchorPane root = loader.load();
            JobsHistoryAddUIController editController = loader.<JobsHistoryAddUIController>getController();
            editController.initData(name,jobHistory,controller);
            Stage stage = new Stage();
            stage.setTitle(name);
            stage.setScene(new Scene(root, 300, 250));
            stage.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void JobHistoryAdd()
    {
        jobsHistoryAddButton.setOnAction(event ->loadWindow("Adauga", null));
    }
    public void JobsHistoryEdit()
    {
        jobsHistoryEditButton.setOnAction(event -> {
            jobsHistoryListView.getSelectionModel().getSelectedIndex();
            JobHistory jobHistory = (JobHistory) jobsHistoryListView.getSelectionModel().getSelectedItem();
            if (jobHistory!=null) {
                loadWindow("Editare", jobHistory);
            }
            else UtilFunctions.showInfo("Selectati un camp");
        });
    }
    public void JobsHistoryDelete() {
        jobsHistoryDeleteButton.setOnAction(e->{
            jobsHistoryListView.getSelectionModel().getSelectedIndex();
            JobHistory jobHistory = (JobHistory) jobsHistoryListView.getSelectionModel().getSelectedItem();
            if (jobHistory!=null)
            {
                controller.remove(jobHistory);
            }
            else UtilFunctions.showInfo("Selectati un camp");
        });
    }
    @Override
    public void update(Observable o, Object arg) {
        jobsHistoryListView.getItems().clear();
        jobsHistoryListView.getItems().addAll(0, controller.getJobHistoryList());
    }


}
