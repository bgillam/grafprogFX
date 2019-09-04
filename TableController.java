import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javax.swing.text.TableView;

public class TableController {


    @FXML    private AnchorPane tablePane;


    public void onMenuChoice(KeyEvent keyEvent) {
    }

    public AnchorPane getTablePane(){
        return tablePane;
    }
}
