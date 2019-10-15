import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TableColumnGeneratorController {

    @FXML private Label numStart;
    @FXML private TextField numStartText;
    @FXML private Label numEnd;
    @FXML private TextField numEndText;
    @FXML private Label rowEnd;
    @FXML private TextField rowEndText;
    @FXML private Button cancelButton;
    @FXML private Button okButton;
    @FXML private Label col1ChoiceLabel;
    @FXML private ChoiceBox col1Chooser;
    @FXML private ChoiceBox col2Chooser;
    @FXML private Label col2ChoiceLabel;
    @FXML private Label rowStart;
    @FXML private TextField rowStartText;
    @FXML private Label ya1;
    @FXML private TextField t1;
    @FXML private Label an;
    @FXML private TextField t2;
    @FXML private Label anLabel;


    public void hideAll(){
        ya1.setVisible(false);
        t1.setVisible(false);
        an.setVisible(false);
        t2.setVisible(false);
        anLabel.setVisible(false);

        col1ChoiceLabel.setVisible(false);
        col2ChoiceLabel.setVisible(false);
        col1Chooser.setVisible(false);
        col2Chooser.setVisible(false);
        rowStart.setVisible(false);
        rowStartText.setVisible(false);
        rowEnd.setVisible(false);
        rowEndText.setVisible(false);
        cancelButton.setVisible(false);
        okButton.setVisible(false);

        numStart.setVisible(false);
        numStartText.setVisible(false);
        numEnd.setVisible(false);
        numEndText.setVisible(false);
    }

    public void showRandomDialog(){
        col1ChoiceLabel.setVisible(true);
        numStart.setVisible(true);
        numStartText.setVisible(true);
        numEnd.setVisible(true);
        numEndText.setVisible(true);
        rowStart.setVisible(true);
        rowStartText.setVisible(true);
        rowEnd.setVisible(true);
        rowEndText.setVisible(true);
        
    }


}
