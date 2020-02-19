import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class TableColumnGeneratorController {

    @FXML private RadioButton rdbtnDoubles;
    @FXML private RadioButton rdbtnIntegers;
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
    @FXML private Label anLabel1;
    @FXML private TextField t2;
    @FXML private Label anLabel2;

    private GrafTable myDaddy = GrafProg.getData();




    enum GenType  {RANDOM, FUNCTION, RECURSIVE, HEADER }
    private GenType genType;


    public void hideAll(){
        ya1.setVisible(false);
        t1.setVisible(false);
        anLabel1.setVisible(false);
        t2.setVisible(false);
        anLabel2.setVisible(false);

        col1ChoiceLabel.setVisible(false);
        col2ChoiceLabel.setVisible(false);
        col1Chooser.setVisible(false);
        col2Chooser.setVisible(false);

        numStart.setVisible(false);
        numStartText.setVisible(false);
        numEnd.setVisible(false);
        numEndText.setVisible(false);

        rowStart.setVisible(false);
        rowStartText.setVisible(false);
        rowEnd.setVisible(false);
        rowEndText.setVisible(false);

        rdbtnDoubles.setVisible(false);
        rdbtnIntegers.setVisible(false);

        cancelButton.setVisible(false);
        okButton.setVisible(false);


    }

    public void showRandomDialog(){
        hideAll();
        GrafProg.getGenStage().setTitle("Generate Random Values");
        col1ChoiceLabel.setText("Output Column:");
        col1ChoiceLabel.setVisible(true);
        col1Chooser.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));
        col1Chooser.getSelectionModel().select(0);
        col1Chooser.setVisible(true);
        numStart.setVisible(true);
        numStartText.setVisible(true);
        numStartText.setEditable(true);
        numEnd.setVisible(true);
        numEndText.setVisible(true);
        numEndText.setEditable(true);
        rowStart.setVisible(true);
        rowStartText.setVisible(true);
        rowStartText.setEditable(true);
        rowStartText.setText("1");
        rowEnd.setVisible(true);
        rowEndText.setVisible(true);
        rowEndText.setEditable(true);
        rowEndText.setText(GrafProg.getData().getNumRows()+"");
        rdbtnDoubles.setVisible(true);
        rdbtnIntegers.setVisible(true);
        okButton.setVisible(true);
        cancelButton.setVisible(true);
        GrafProg.getTableStage().setAlwaysOnTop(false);
        GrafProg.getGenStage().setAlwaysOnTop(true);
        //resetChooser();
        genType = GenType.RANDOM;

    }

    private void generateRandomIntegers(){
        double lower = Double.parseDouble(numStartText.getText());
        double upper = Double.parseDouble(numEndText.getText());
        int begin = Integer.parseInt(rowStartText.getText());
        int end = Integer.parseInt(rowEndText.getText());
        int numRows = myDaddy.getNumRows();
        if (upper > numRows) upper = numRows;
        double r;
        //int n;
        for (int i = begin; i <= end ; i++){
            r = (Math.random() * (upper - lower)) + lower;
            r =  (double) Math.round(r);
            myDaddy.setCellValueDouble(i, col1Chooser.getSelectionModel().getSelectedIndex()+1, r);
        }
    }

    private void generateRandomDoubles(){
        double lower = Double.parseDouble(numStartText.getText());
        double upper = Double.parseDouble(numEndText.getText());
        int begin = Integer.parseInt(rowStartText.getText());
        if (begin < 1) begin = 1;
        int end = Integer.parseInt(rowEndText.getText());
        int numRows = myDaddy.getNumRows();
        if (upper > numRows) upper = numRows;
        double r;
        for (int i = begin; i <= end ; i++){
            r = (Math.random() * (upper - lower)) + lower;
            myDaddy.setCellValueDouble(i, col1Chooser.getSelectionModel().getSelectedIndex()+1, r);
        }
    }

    private boolean generateRandom(){
        //myDaddy.setHeaderString(selectedColumn, beginTextField.getText());
        //headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
        boolean ok = true;
        if (!GrafInputHelpers.isInt(rowStartText.getText())) {
            GrafInputHelpers.setTextFieldColor(rowStartText, "red");  ok = false;  }
        if (!GrafInputHelpers.isInt(rowEndText.getText())) {
            GrafInputHelpers.setTextFieldColor(rowEndText, "red");  ok = false;      }
        if (!GrafInputHelpers.isInt(numStartText.getText())) {
            GrafInputHelpers.setTextFieldColor(numStartText, "red");   ok = false;  }
        if (!GrafInputHelpers.isDouble(numEndText.getText())) {
            GrafInputHelpers.setTextFieldColor(numEndText, "red"); ok = false;  }
        if (!ok) return false;
        GrafInputHelpers.setTextFieldColor(rowStartText, "black");
        GrafInputHelpers.setTextFieldColor(rowEndText, "black");
        GrafInputHelpers.setTextFieldColor(numStartText, "black");
        GrafInputHelpers.setTextFieldColor(numEndText, "black");
        if (rdbtnIntegers.isSelected()) generateRandomIntegers();
        else if (rdbtnDoubles.isSelected()) generateRandomDoubles();
        //resetChooser();
        return true;
        //myDaddy.repaint();
    }

    public void showRecursiveDialog() {
        hideAll();
        GrafProg.getGenStage().setTitle("Generate Recursive Values");
        ya1.setText("A1:");
        ya1.setVisible(true);
        t1.setVisible(true);
        t1.setEditable(true);
        anLabel1.setText("An:");
        anLabel1.setVisible(true);
        t2.setVisible(true);
        t2.setEditable(true);
        anLabel2.setVisible(true);

        col1ChoiceLabel.setVisible(true);
        col1Chooser.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));
        col1Chooser.getSelectionModel().select(0);
        col1Chooser.setVisible(true);

        rowStart.setVisible(true);
        rowStartText.setVisible(true);
        rowStartText.setText("1");
        rowEnd.setVisible(true);
        rowEndText.setVisible(true);
        rowEndText.setText(GrafProg.getData().getNumRows()+"");

        okButton.setVisible(true);
        cancelButton.setVisible(true);
        GrafProg.getTableStage().setAlwaysOnTop(false);
        GrafProg.getGenStage().setAlwaysOnTop(true);
        //resetChooser();
        genType = GenType.RECURSIVE;
    }



    private boolean generateRecursiveValues(){
        //System.out.println("in");
        double startA = 1;
        String functionString = "";
        int pos = -1;
        int fLength = -1;
        int beginRow = 1;
        int endRow = myDaddy.getNumRows();
        boolean ok = true;
        if (!GrafInputHelpers.isAnInteger(t1.getText())) 	{GrafInputHelpers.setTextFieldColor(t1, "red");  ok = false;  }
        startA = Integer.parseInt(t1.getText());
        functionString =  t2.getText();
        fLength = functionString.length();
        String first, last;
        for (int i = 0; i < fLength; i++){
            pos = functionString.indexOf("@");
            if (pos == -1) break;
            first = functionString.substring(0, pos);
            if (pos+1 == fLength) last = ""; else  last = functionString.substring(pos+1, fLength);
            functionString = first+"X"+last;
        }
        if (FunctionString.checkFunctionString(functionString)!= 0) {GrafInputHelpers.setTextFieldColor(t2, "red");  ok = false;  }
        else if (!FunctionString.isValidAtXIgnoreDomainError(functionString, 1)) {GrafInputHelpers.setTextFieldColor(t2, "red");  ok = false;  }
        if (!GrafInputHelpers.isInt(rowStartText.getText())) {GrafInputHelpers.setTextFieldColor(rowStartText, "red");  ok = false;  }
        else beginRow = Integer.parseInt(rowStartText.getText());
        if (!GrafInputHelpers.isInt(rowEndText.getText())) {GrafInputHelpers.setTextFieldColor(rowEndText, "red");  ok = false;  }
        else endRow = Integer.parseInt(rowEndText.getText());
        /*beginRow = 1;
        endRow = myDaddy.getNumRows();*/
        if (!ok) return false;
        System.out.println("past tests");
        GrafInputHelpers.setTextFieldColor(t1, "black");
        GrafInputHelpers.setTextFieldColor(t2, "black");
        GrafInputHelpers.setTextFieldColor(rowStartText, "black");
        GrafInputHelpers.setTextFieldColor(rowEndText, "black");
        double anMinus1 = startA;
        double newVal = startA;
        for (int row = beginRow; row <= endRow; row++)
            try {
                newVal = FunctionString.fValue(functionString, anMinus1);
                myDaddy.setCellValueDouble(row, col1Chooser.getSelectionModel().getSelectedIndex()+1, newVal);
                anMinus1=newVal;
            } catch (DomainViolationException e) { }  //we know we should not get an exception because tested previously
            catch (FunctionFormatException e) {}
        //myDaddy.repaint();
        return true;
    }

    public void showFunctionDialog() {
        hideAll();
        GrafProg.getGenStage().setTitle("Generate Function Values");
        ya1.setText("Y1:");
        ya1.setVisible(true);
        t1.setVisible(true);
        t1.setEditable(true);


        col1ChoiceLabel.setVisible(true);
        col1Chooser.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArrayCdr(GrafProg.getData())));
        col1Chooser.getSelectionModel().select(0);
        col1Chooser.setVisible(true);

        col2ChoiceLabel.setVisible(true);
        col2Chooser.setItems(FXCollections.observableArrayList(TableHeaderActions.getHeaderArray(GrafProg.getData())));
        col2Chooser.getSelectionModel().select(0);
        col2Chooser.setVisible(true);

        rowStart.setVisible(true);
        rowStartText.setVisible(true);
        rowStartText.setText("1");
        rowEnd.setVisible(true);
        rowEndText.setVisible(true);
        rowEndText.setText(GrafProg.getData().getNumRows()+"");

        okButton.setVisible(true);
        cancelButton.setVisible(true);
        GrafProg.getTableStage().setAlwaysOnTop(false);
        GrafProg.getGenStage().setAlwaysOnTop(true);
        //resetChooser();
        genType = GenType.FUNCTION;
    }

    private boolean generateFunctionValues(){
        int beginRow = 1;
        int endRow =  myDaddy.getNumRows();
        boolean ok = true;
        //myDaddy.setHeaderString(selectedColumn, beginTextField.getText());
        //headerComboBox.setModel(new javax.swing.DefaultComboBoxModel(myDaddy.getHeaderArray()));
        if (!GrafInputHelpers.isAnIntegerWithMessage(rowStartText.getText())) {GrafInputHelpers.setTextFieldColor(rowStartText, "red");  ok = false;  }
        else
            beginRow = Integer.parseInt(rowStartText.getText());
        if (!GrafInputHelpers.isAnIntegerWithMessage(rowEndText.getText())) {GrafInputHelpers.setTextFieldColor(rowEndText, "red");  ok = false;  }
        else
            endRow = Integer.parseInt(rowEndText.getText());
        if (endRow > myDaddy.getNumRows()) endRow = myDaddy.getNumRows();
        if (FunctionString.checkFunctionString(t1.getText())!= 0) {GrafInputHelpers.setTextFieldColor(t2, "red");  ok = false;  }
        if (!FunctionString.isValidAtXIgnoreDomainError(t1.getText(), 1)) {GrafInputHelpers.setTextFieldColor(rowStartText, "red");  ok = false;  }
        if (!ok) return false;
        //System.out.println("made it here");
        GrafInputHelpers.setTextFieldColor(rowStartText, "black");
        GrafInputHelpers.setTextFieldColor(rowEndText, "black");
        GrafInputHelpers.setTextFieldColor(t1, "black");
        int inputColumn = col2Chooser.getSelectionModel().getSelectedIndex();
        int outputColumn = col1Chooser.getSelectionModel().getSelectedIndex()+1;
        //System.out.println(inputColumn);
        for (int row = beginRow; row <= endRow; row++)
            try {
                myDaddy.setCellValueDouble(row, outputColumn, FunctionString.fValue(t1.getText(), myDaddy.getCellValue(row, inputColumn)));
            } catch (NullPointerException e)  { } //empty cells
            catch (DomainViolationException e) { }   //we know we will not get an exception because tested in previous if statement
            catch (FunctionFormatException e) {}
        return true;
        //myDaddy.repaint();
    }

    /*private void allowEdits(){
        rowStartText.setEditable(true);
        rowEndText.setEditable(true);
        numStartText.setEditable(true);
        numEndText.setEditable(true);
        //.setText("Enter Limits and Destination");
    }*/

   /* private void resetChooser(){
        col1Chooser.getSelectionModel().select(0);
        rowStartText.setEditable(false);
        rowEndText.setEditable(false);
        numStartText.setEditable(false);
        numEndText.setEditable(false);
        col1ChoiceLabel.setText("Choose Column to Place Values");
    }*/




    public void onCancel(ActionEvent actionEvent) {
        GrafProg.getGenStage().hide();
    }

    public void onOK(ActionEvent actionEvent) {
        boolean ok = true;
        switch (genType){
            case RANDOM: ok =  generateRandom(); break;
            case FUNCTION:  ok = generateFunctionValues(); break;
            case RECURSIVE: ok = generateRecursiveValues(); break;
            case HEADER:
        }

        if (ok) GrafProg.getGenStage().hide();
    }
}
