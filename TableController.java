import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.TableView;
import java.util.Random;

public class TableController {


    @FXML    private AnchorPane tablePane;


    public void onMenuChoice(KeyEvent keyEvent) {
    }

    public AnchorPane getTablePane(){
        return tablePane;
    }

    public void onRandom(ActionEvent actionEvent) {
        RandomDialog rd = new RandomDialog(GrafProg.getData());
        rd.showDialog();
        //GrafProg.getTableStage().setFocused(true);
    }
}
