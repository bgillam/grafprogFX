package GrafProg.GrafObjects;
/* *
 * Write a description of interface GrafProg.GrafObjects.IGrafable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import GrafProg.GrafObjects.Dialog.GrafDialogController;

import java.awt.*;

public interface IGrafable
{
    
    void drawGraf(Graphics2D gc);
    void loadObjectFields(GrafDialogController gdc);
    void autoRange();
    
}
