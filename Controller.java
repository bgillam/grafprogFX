package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Controller {

    @FXML
    public void onSaveButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onDiscardButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onExitButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }

    @FXML
    public void onEditButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onDeleteButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onClearButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }

    @FXML
    public void onFontButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onFillButtonClicked(ActionEvent e){
        System.out.println(e.getSource()+ "was Clicked");
    }
    @FXML
    public void onColorButtonClicked(ActionEvent e) {
        System.out.println(e.getSource() + "was Clicked");
    }
}
