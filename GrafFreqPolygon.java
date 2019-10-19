import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Arrays;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.*;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import java.awt.BorderLayout;

import javax.swing.UIManager;

import javax.swing.border.BevelBorder;

public class GrafFreqPolygon extends GrafHistogram implements IGrafable{

         //Instance Variables
        private int columnNumber;
        private GrafProg myOwner;
        private GrafSettings gStuff;
        private GrafTable table;
        private boolean relative = false;
        private boolean labelAxisByBoundries = true;
        private boolean byNumClasses = true;
        private boolean displayCounts = true;
        private double  begin;
        private double  end;
        private int numClasses = 10;
        private double classWidth = (begin - end)/10;
        private Color fillColor = Color.WHITE;
        private boolean fillFlag = false;
        private double[] classLimits;
        //private double[] counts;



        //Constructor
        public GrafFreqPolygon(){
            gStuff = super.initGrafObject(GrafType.FREQPOLYGON);
            setColumnNumber(1);
            table = GrafProg.getData();
            GrafProg.setMessage1("Plotting Column "+columnNumber);
        }



        //constructor
        public GrafFreqPolygon(int column){
            this();
            setColumnNumber(column);
            GrafProg.setMessage1("Histogram for Column "+columnNumber);
        }

        public GrafFreqPolygon(int column, double b, double e, int numCl, Color c, Color fillColor, boolean boundries, boolean counts, boolean rel){
            this(column);
            setFillColor(fillColor);
            setLabelAxisByBoundries(boundries);
            setDisplayCounts(counts);
            relative = rel;
            begin = b;
            end = e;
            numClasses = numCl;
            setGrafColor(c);
            byNumClasses = true;
            classLimits = GrafStats.getClassesByNumber(numClasses, begin, end);    //problem here!
            classWidth = classLimits[1] - classLimits[0];
        }

        public GrafFreqPolygon( int column, double b, double e, double classW, Color c, Color fillColor, boolean boundries, boolean counts, boolean rel){
            this(column);
            setFillColor(fillColor);
            setLabelAxisByBoundries(boundries);
            setDisplayCounts(counts);
            relative = rel;
            begin = b;
            end = e;
            classWidth = classW;
            setGrafColor(c);
            byNumClasses = false;
            classLimits = GrafStats.getClassesByClassSize(classW, begin, end);
            numClasses = classLimits.length;
        }






        //drawGraf overriding method in parent GrafObject
        @Override
        public void drawGraf(Graphics2D gc){
            gc.setColor(super.getGrafColor());
            Double[] temp = GrafStats.getRidOfNulls(myOwner.getData().getColumnValues(columnNumber));
            Arrays.sort(temp);
            int totalCount = 0;
            int numValues = temp.length;
            String formatString = "%."+gStuff.getDecPlaces()+"f";
            double[] counts = getCounts(numValues,temp);
            if (myOwner.getGrafSettings().getReverseXY()) grafOnY(numValues, counts, gc);
            else grafOnX(numValues, counts, gc);
            myOwner.setMessage2("");
            myOwner.incrementBoxPlotsPlotted();
            gc.setColor(Color.BLACK);

        }

        private void grafOnX(int numValues, double[] counts, Graphics2D gc){
            double height;
            double lastCount = 0;
            double lastX = 0;
            double[] x = new double[classLimits.length];
            double[] y = new double[classLimits.length];;
            for (int j = 0; j < classLimits.length-1; j++) {
                if (relative) height = counts[j]/numValues; else height = counts[j];
               //implement fill here
                gc.setColor(getGrafColor());

                GrafPrimitives.grafLine(gStuff,classLimits[j]-0.5*getClassWidth(), lastCount, classLimits[j]+0.5*getClassWidth(), height, gc);
                x[j] = classLimits[j]-0.5*getClassWidth();
                y[j] = lastCount;


                lastCount = height;
                lastX = classLimits[j]+0.5*getClassWidth();
                if (labelAxisByBoundries) {
                    String formatString = "%."+gStuff.getDecPlaces()+"f";
                    GrafPrimitives.grafLine(gStuff,classLimits[j], gStuff.getGrafHeight()/50, classLimits[j], -gStuff.getGrafHeight()/50, gc);
                    GrafPrimitives.grafString(gStuff,classLimits[j], -gStuff.getGrafHeight()/25, ""+String.format(formatString, classLimits[j], gc), gc);
                }
                if (displayCounts)  GrafPrimitives.grafString(gStuff,classLimits[j],height+gStuff.getGrafHeight()/50, ""+counts[j], gc);
                //System.out.println(counts[j]);
            }
            GrafPrimitives.grafLine(gStuff,lastX, lastCount, lastX+getClassWidth(), 0, gc);

            //This should fill, but does not
            Path2D path = new Path2D.Double();
            path.moveTo(x[0], y[0]);
            for(int i = 1; i < x.length; ++i) {
                GrafPrimitives.grafString(gStuff, x[i], y[i] , "X", gc);
                path.lineTo(x[i], y[i]);
            }
            path.lineTo(lastX, lastCount);
            GrafPrimitives.grafString(gStuff, lastX, lastCount , "X", gc);
            path.lineTo(lastX+getClassWidth(), 0);
            //path.lineTo(x[0],y[0]);
            path.closePath();
            gc.setColor(getFillColor());
            gc.fill(path);
            gc.setColor(getGrafColor());
            //End fill

        }

        private void grafOnY(int numValues, double[] counts, Graphics2D gc){
            double height;
            double lastCount = 0;
            double lastX = 0;
            for (int j = 0; j < classLimits.length-1; j++) {
                if (relative) height = counts[j]/numValues; else height = counts[j];
                //implement fill here
                gc.setColor(getGrafColor());

                GrafPrimitives.grafRect(gStuff,lastCount, classLimits[j]-0.5*getClassWidth(), height, classLimits[j]+0.5*getClassWidth(), gc);
                lastCount = height;
                lastX = classLimits[j]+0.5*getClassWidth();


                if (labelAxisByBoundries) {
                    String formatString = "%."+gStuff.getDecPlaces()+"f";
                    GrafPrimitives.grafLine(gStuff,gStuff.getGrafHeight()/50, classLimits[j],   -gStuff.getGrafHeight()/50, classLimits[j], gc);
                    GrafPrimitives.grafString(gStuff,-gStuff.getGrafHeight()/25, classLimits[j], ""+String.format(formatString, classLimits[j], gc), gc);
                }
                if (displayCounts)  GrafPrimitives.grafString(gStuff,height+gStuff.getGrafHeight()/50,classLimits[j]+classWidth/2, ""+counts[j], gc);
                //System.out.println(counts[j]);
            }
            GrafPrimitives.grafLine(gStuff,lastCount, lastX, 0, lastX+getClassWidth(), gc);
        }


        private double[] getCounts(int numValues, Double[] temp){
            int upperBoundIndex = 1;
            int binCount = 0;
            int i = 0;
            double[] counts = new double[classLimits.length];
            while (i < numValues){
                if (temp[i] < classLimits[upperBoundIndex]){
                    binCount++;
                    i++;
                }
                else{
                    counts[upperBoundIndex-1] = binCount;
                    binCount = 0;
                    upperBoundIndex++;
                }

            }
            counts[upperBoundIndex-1] = binCount;
            return counts;
        }

        /*@Override
        public boolean isValidInput(GrafDialogController gdf){
            if (!GrafInputHelpers.isDouble(gdf.getX1())) {
                GrafInputHelpers.setTextFieldColor(gdf.getX1TextField(), "red");
                return false;}
            if (!GrafInputHelpers.isDouble(gdf.getX2())) {
                GrafInputHelpers.setTextFieldColor(gdf.getX2TextField(), "red");
                return false;}
            if (gdf.getClassSizeButton().isSelected() && !GrafInputHelpers.isDouble(gdf.getClassWidthText())) {
                GrafInputHelpers.setTextFieldColor(gdf.getClassWidthTextField(), "red");
                return false;}
        *//*if (gdf.getNumClassButton().isSelected() && !GrafInputHelpers.isAnInteger(gdf.getNumClasses())) {
            GrafInputHelpers.setTextFieldColor(gdf.getClassWidthTextField(), "red");
            return false;}*//*
            return true;
        }



        @Override
        public boolean deepEquals(GrafObject o){
            GrafHistogram gh = (GrafHistogram) o;
            if (getType() != o.getType()) return false;
            if (!getGrafColor().equals(gh.getGrafColor())) return false;
            if (!getFillColor().equals(gh.getFillColor())) return false;
            if (!(getColumnNumber() == gh.getColumnNumber())) return false;
            if (!(getBegin()==gh.getBegin())) return false;
            if (!(getEnd()==gh.getBegin())) return false;
            if (!(getClassWidth()==gh.getClassWidth())) return false;
            if (!isRelative()==gh.isRelative()) return false;
            if (!isLabelAxisByBoundries()==gh.isLabelAxisByBoundries()) return false;
            if (!isDisplayCounts()==gh.isDisplayCounts()) return false;
            if (!isByNumClasses()==gh.isByNumClasses()) return false;
            //if (!isFillFlag()==gh.isFillFlag()) return false;

            return true;
        }

        @Override
        public void loadObjectFields(GrafDialogController gdc){
            super.loadObjectFields(gdc);
            gdc.setColumn1ChooserColumn(getColumnNumber()-1);
            gdc.setfillColor(getFillColor());
            gdc.setX1(""+getBegin());
            gdc.setX2(""+getEnd());
            gdc.setClassWidthText(getClassWidth()+"");
            gdc.setFNS(isRelative());
            gdc.getCountCheckBox().setSelected(getShowCounts());
            gdc.getBoundariesCheckBox().setSelected(labelAxisByBoundries);
            gdc.getNumClassButton().setSelected(getByNumClassChecked());
            gdc.getClassSizeButton().setSelected(!getByNumClassChecked());
        }
*/


        //Setters and Getters
        public void setColumnNumber(int c){ columnNumber = c;}
        public int getColumnNumber(){ return columnNumber;}
        /*public void setFillFlag(boolean tf){
              fillFlag = tf;
        }
        public boolean getFillFlag(){
            return fillFlag;
        }*/
        public Color getFillColor(){
            return fillColor;
        }
        public void setFillColor(Color c){
            this.fillColor = c;
        }
        public double getBegin(){
            return begin;
        }
        public double getEnd(){
            return end;
        }
        public boolean getByNumClassChecked(){
            return byNumClasses;
        }
        public int getNumClasses(){
            return numClasses;
        }
        public double getClassWidth(){
            return classWidth;
        }
        public boolean isRelativeHisto(){
            return relative;
        }
        public void setRelativeHisto(boolean tf){
            relative = tf;
        }
        public void setShowCounts(boolean tf){
            displayCounts = tf;
        }
        public boolean getShowCounts(){
            return displayCounts;
        }
        public boolean labelAxis(){
            return labelAxisByBoundries;
        }
        public void setLabelAxis(boolean tf){
            labelAxisByBoundries = tf;
        }
        public boolean isRelative() {        return relative;    }
        public void setRelative(boolean relative) {        this.relative = relative;    }
        public boolean isLabelAxisByBoundries() {   return labelAxisByBoundries;    }
        public void setLabelAxisByBoundries(boolean labelAxisByBoundries) {        this.labelAxisByBoundries = labelAxisByBoundries;    }
        public boolean isByNumClasses() {        return byNumClasses;    }
        public void setByNumClasses(boolean byNumClasses) {        this.byNumClasses = byNumClasses;    }
        public boolean isDisplayCounts() {        return displayCounts;    }
        public void setDisplayCounts(boolean displayCounts) {        this.displayCounts = displayCounts;    }

        //public boolean isFillFlag() {        return fillFlag;    }

        public String toString(){
            return "freqpolygon: Col "+getColumnNumber();//+", "+ getGrafColor();
        }

        public GrafSettings getGStuff() {
            return gStuff;
        }
    }






