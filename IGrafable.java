
/**
 * Write a description of interface IGrafable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.awt.*;
import java.util.ArrayList;
public interface IGrafable
{
    
    public void drawGraf(Graphics2D gc);
    public void loadObjectFields(GrafDialogController gdc);
    public void autoRange();
    
}
