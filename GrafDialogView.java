import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.util.ArrayList;


public class GrafDialogView {
    public static void setTextandTitle() {
        GrafType objectType = getWorkingObject().getType();
        String objectTypeString = getWorkingObject().getType().toString();
        getObjectComboBox().setItems(FXCollections.observableArrayList(createObjectList(objectType)));
        getObjectComboBox().setValue(objectTypeString);
        GrafProg.getDialogStage().setTitle(objectTypeString);
        getChooseObject().setText("Choose " + objectTypeString);
    }


    private static ArrayList<GrafObject> createObjectList(GrafType gtype) {
        ArrayList<GrafObject> grafObjects = new ArrayList<>();
        for (GrafObject g : getTempGrafList()) {

            if (g.getType() == gtype) grafObjects.add(g);
        }
        return grafObjects;
    }


    private static ComboBox getObjectComboBox() {
        return GrafProg.getDialogController().getObjectComboBox();
    }

    private static Label getChooseObject() {
        return GrafProg.getDialogController().getChooseObject();
    }

    private static ArrayList<GrafObject> getTempGrafList(){
        return GrafProg .getDialogController().getTempGrafList();
    }

    private static GrafObject getWorkingObject(){
        return GrafProg.getDialogController().getWorkingObject();
    }
}
