package JobsHistory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * Created by Aurelian on 5/19/2017.
 */
public class JobsHistoryAddUIController implements Initializable,Observer {

    @FXML
    private DatePicker jobHistoryEndDateDatePicker;

    @FXML
    private Button jobHistoryOkButton;

    @FXML
    private DatePicker jobHistoryStartDateDatePicker;

    @FXML
    private ChoiceBox<String> jobHistoryUserChoiceBox;

    @FXML
    private ChoiceBox<String> jobHistoryTypeChoiceBox;

    public JobsHistoryAddUIController()
    {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    @Override
    public void update(Observable o, Object arg)
    {

    }
}
