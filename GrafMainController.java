import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GrafMainController {
    @FXML
    public Label message1;
    @FXML
    public Label message2;
    @FXML
    public Label message3;
    @FXML
    public Pane grafPane;


    @FXML
    public void onButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");

    }

    @FXML
    public void onMenuChoice(KeyEvent keyEvent) {
    }

    public void fxInput(ActionEvent actionEvent) {
        //GrafStage.dialogStage.initModality(Modality.APPLICATION_MODAL);

        GrafStage.dialogStage.show();
        GrafStage.dialogController.showFxEntry();

    }


    public void showCalc(ActionEvent actionEvent) {
        GrafCalc calc= new GrafCalc();
        calc.setVisible(true);
    }

    public void setMessage1(String message){ message1.setText(message); }
    public void setMessage2(String message){ message2.setText(message); }
    public void setMessage3(String message){ message3.setText(message); }
}
