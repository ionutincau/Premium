import DocumentTypes.Income;
import DocumentTypes.TimeSheet;
import DocumentTypes.Vacation;
import DocumentTypes.WorkedPeriod;
import Utils.UtilFunctions;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by Incau Ionut on 28-Feb-17.
 * Contact: ionut.incau@gmail.com
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch();
        /*Income i = new Income(UtilFunctions.dialog("Prezenta serveste la: "));
        i.generatePDF();
        Vacation v = new Vacation(UtilFunctions.dialog("Data inceput: "), UtilFunctions.dialog("Data sfarsit: "));
        v.generatePDF();
        WorkedPeriod w = new WorkedPeriod();
        w.generatePDF();*/
        ArrayList ar = new ArrayList();
        ar.add("a");
        ar.add("b");
        ar.add("c");
        TimeSheet t = new TimeSheet("x", "y", ar);
        t.generatePDF();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login/login.fxml"));
        primaryStage.setTitle("Premium");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
