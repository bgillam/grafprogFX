import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GrafMainController {
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
        GrafStage.dialogController.hidePoints();

    }





}
