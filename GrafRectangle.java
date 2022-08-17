/*GrafRectangle - graphs a rectangle
 * 
 * @author Bill Gillam
 * @version 1/27/17]
 */
import GrafUtils.GrafInputHelpers;

import java.awt.*;

public class GrafRectangle extends GrafObject implements IGrafable {
    private String functionString = "";
    private GrafProg myOwner;
    private GrafSettings gStuff;
    private double x = 0;
    private double y = 0;
    private double width = 0;
    private double height = 0;
    private Color fill = Color.WHITE;
    private boolean fillFlag = false;
    //private String yString = "";


    GrafRectangle() {
        gStuff = super.initGrafObject(GrafType.RECTANGLE);

    }


    GrafRectangle(double x1, double y1, double width, double height) {
        this();
        setX(x1);
        setY(y1);
        setWidth(width);
        setHeight(height);
    }

    private GrafRectangle(double x1, double y1, double width, double height, Color gColor) {
        this(x1, y1, width, height);
        setGrafColor(gColor);
    }

    private GrafRectangle(double x1, double y1, double width, double height, Color gColor, Color fColor) {
        this(x1, y1, width, height, gColor);
        setFillColor(fColor);
    }

    @Override
    public void drawGraf(Graphics2D gc) {
        /*if (fillFlag) {
            gc.setColor(fill);
            GrafPrimitives.fillRect(gStuff, x, y, width, height, gc);
        }*/
        gc.setColor(getFillColor());
        GrafPrimitives.fillRect(gStuff, x, y, width, height, gc);
        gc.setColor(super.getGrafColor());
        GrafPrimitives.grafRect(gStuff, x, y, width, height, gc);

       /* gc.setColor(Color.BLACK);
        gc.setPaint(Color.WHITE);*/

    }

    @Override
    public boolean isValidInput(GrafDialogController gdf) {
        boolean ok = true;
        if (GrafDialogController.getX1().equals("")) return false;
        if (GrafDialogController.getX2().equals("")) return false;
        if (GrafDialogController.getY1().equals("")) return false;
        if (GrafDialogController.getY2().equals("")) return false;
        if (!GrafInputHelpers.isDouble(GrafDialogController.getX1())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getX1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(GrafDialogController.getX2())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getX2TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(GrafDialogController.getY1())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getY1TextField(), "red" );
            ok = false;
        }
        if (!GrafInputHelpers.isDouble(GrafDialogController.getY2())) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getY2TextField(), "red" );
            ok = false;
        }
        return ok;
    }

    @Override
    public boolean deepEquals(GrafObject o) {
        GrafRectangle gr = (GrafRectangle) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gr.getGrafColor())) return false;
        if (!getFillColor().equals(gr.getFillColor())) return false;
        if (!(getX() == gr.getX())) return false;
        if (!(getY() == gr.getY())) return false;
        if (!(getWidth() == gr.getWidth())) return false;
        return getHeight() == gr.getHeight();
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc) {
        super.loadObjectFields(gdc);
        GrafDialogController.setX1("" + getX());
        GrafDialogController.setY1("" + getY());
        GrafDialogController.setX2("" + getWidth());
        GrafDialogController.setY2("" + getHeight());
        gdc.setfillColor(getFillColor());

    }

    @Override
    public void autoRange(){
        GrafProg.getGrafSettings().setYMax(getY()+GrafProg.getGrafSettings().getTenthWindowY());
        GrafProg.getGrafSettings().setYMin(getY()-getHeight()-GrafProg.getGrafSettings().getTenthWindowY());
    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc){
        return new GrafRectangle(Double.parseDouble(GrafDialogController.getX1()),
                Double.parseDouble(GrafDialogController.getY1()), Double.parseDouble(GrafDialogController.getX2()),
                Double.parseDouble(GrafDialogController.getY2()), gdc.getGrafColor(), gdc.getFillColor());
    }


    public void setX(double xval) {
        x = xval;
    }

    public double getX() {
        return x;
    }

    void setWidth(double val) {
        width = val;
    }

    double getWidth() {
        return width;
    }

    public void setY(double val) {
        y = val;
    }

    public double getY() {
        return y;
    }

    void setHeight(double val) {
        height = val;
    }

    double getHeight() {
        return height;
    }

    public Color getFillColor() {
        return fill;
    }

    void setFillColor(Color f) {
        fill = f;
    }

    public GrafSettings getGrafSettings() {
        return gStuff;
    }

    public void setFillFlag(boolean tf) {
        fillFlag = tf;
    }

    public boolean getFillFlag() {
        return fillFlag;
    }


    public String toString() {
        return "RECTANGLE: (" + getX() + ", " + getY() + "); (" + getWidth() + ", " + getHeight();//+ " "+getGrafColor()+" "+getFill()+")";
    }
}
   
