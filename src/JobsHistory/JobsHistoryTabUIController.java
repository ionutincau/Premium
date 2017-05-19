package JobsHistory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class JobsHistoryTabUIController implements Initializable, Observer {

    @FXML
    private Button jobsHistoryEditButton;

    @FXML
    private Button jobsHistoryAddButton;

    @FXML
    private Button jobsHistoryDeleteButton;

    @FXML
    private ListView<String> jobsHistoryListView;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @Override
    public void update(Observable o, Object arg)
    {

    }


}
