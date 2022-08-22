package GrafProg.GrafObjects.Function;

import GrafProg.GrafObjects.Dialog.GrafDialogController;
import GrafProg.GrafObjects.GrafObject;
import GrafProg.GrafObjects.GrafType;
import GrafProg.GrafObjects.IGrafable;
import GrafProg.GrafProg;
import GrafProg.GrafUI.GrafSettings;
import GrafProg.GrafUtils.GrafInputHelpers;
import GrafProg.GrafPrimitives;
import GrafProg.GrafUtils.Scales;

import java.awt.*;

//Class Header
public class GrafFunction extends GrafObject implements IGrafable {
    //Instance Variables
    private String functionString;
    private static final int numSegments = 200;
    private GrafSettings gStuff;

    //Constructors
    public GrafFunction() {
        gStuff = super.initGrafObject(GrafType.FUNCTION);
    }

    private GrafFunction(String fString) {
        this();
        this.functionString = fString;
        GrafProg.setMessage1(functionString);
    }

    private GrafFunction(String fString, Color c) {
        this(fString);
        setGrafColor(c);
    }


    //drawGraf overriding method in parent GrafProg.GrafObjects.GrafObject
    @Override
    public void drawGraf(Graphics2D gc) {
        double xMin = gStuff.getXMin();
        double xMax = gStuff.getXMax();
        double dx = (xMax - xMin) / numSegments;
        double x = xMin;
        gc.setColor(super.getGrafColor());
        while (x < xMax) {
            GrafPrimitives.grafLine(gStuff, x, FunctionString.fValue(functionString, x), x + dx, FunctionString.fValue(functionString, x + dx), gc);
          x = x + dx;
        }
        GrafPrimitives.grafLine(gStuff, x - dx, FunctionString.fValue(functionString, x), xMax, FunctionString.fValue(functionString, x + dx), gc);

        //GrafFX.drawLineFX(10,20,25, 50);

        gc.setColor(Color.BLACK);
    }

    @Override
    public boolean isValidInput(GrafDialogController gdf) {
        if (gdf.getFunctionString().equals("") && gdf.functionStringIsVisible()) return false;

        int errorCode = FunctionStringErrorChecks.checkFunctionString(gdf.getFunctionString());
        if (errorCode != 0) {
            GrafInputHelpers.setTextFieldColor(GrafDialogController.getFunctionStringTextField(), "red");
            return false;
        }
        return true;
    }

    @Override
    public boolean deepEquals(GrafObject o) {
        GrafFunction gf = (GrafFunction) o;
        if (getType() != o.getType()) return false;
        if (!getGrafColor().equals(gf.getGrafColor())) return false;
        return functionString.equals(gf.getFunction());
    }

    @Override
    public void loadObjectFields(GrafDialogController gdc) {
        super.loadObjectFields(gdc);
        gdc.setFunctionString(getFunction());

    }

    @Override
    public void autoRange() {
        Scales.autoRange(GrafProg.getGrafSettings(), getFunction());

    }

    @Override
    public GrafObject createGrafObjectFromController(GrafDialogController gdc) {
        return new GrafFunction(gdc.getFunctionString(), gdc.getGrafColor());
    }


    private String shortColorString(Color c) {
        return "R" + c.getRed() + ":G" + c.getGreen() + ":B" + c.getBlue();
    }

    //Setters and Getters
    public void setFunction(String s) {
        functionString = s;
    }

    public String getFunction() {
        return functionString;
    }

    public String toString() {
        return getFunction() + "; " + shortColorString(getGrafColor());
    }

    public static String yFunctionList() {
        StringBuilder functionList = new StringBuilder();
        for (GrafObject graf : GrafProg.getGrafList()) {
            if (graf.getType() == GrafType.FUNCTION) {
                GrafFunction gf = (GrafFunction) graf;
                functionList.append("y=").append(gf.getFunction()).append("; ");
            }
        }
        return functionList.toString();
    }
}
