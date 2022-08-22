package GrafProg;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

class EditContextMenu {

    private static int result = 0;

    static int editContextMenu(Node node, IPasteable target) {
        // Create ContextMenu
        ContextMenu contextMenu = new ContextMenu();

        MenuItem cutItem = new MenuItem("Cut");
        cutItem.setOnAction(event -> {
            //System.out.println(target.cut());
            target.cut();
            result = 1;
        });

        MenuItem copyItem = new MenuItem("Copy");
        copyItem.setOnAction(event -> {
            target.copy();
            result = 2;
        });

        MenuItem pasteItem = new MenuItem("Paste");
        pasteItem.setOnAction(event -> {
            target.paste();
            result = 3;
        });

        // Add MenuItem to ContextMenu
        contextMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        // When user right-click on Circle
        node.setOnContextMenuRequested(event -> contextMenu.show(node, event.getScreenX(), event.getScreenY()));

        return result;

    }

    //public int getResult(){ return result; }

}
