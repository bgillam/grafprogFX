/* **************************
 * RegressionMath
 * This object holds and provides access to various values for regression analysis, so that they can be calculated once and accesses from other classes.
 * Bill Gillam 4/23/18
 */

import javax.swing.JOptionPane;
import java.math.*;


public class RegressionMath {
    private Vector x;          // x values
    private Vector y;          //y values
    private Vector beta;       //coefficient vector
    private Vector yCalc;      //values calculated from model
    private Vector residuals;  //Predicted - actual y values
    private Vector errors;     //
    private double r;          //correlation coefficient
    
    public static void main(String[] args){
        System.out.println("two cubed"+getXPower(new BigDecimal(2), 3));
    
    }
       
    //constructor stores x and y vector stripped of nulls
    RegressionMath(Vector passedX, Vector passedY, int order){
        y = passedY.stripEndNulls(); //x values
        x = passedX.stripEndNulls(); //y values
        // order + 1
        int terms = order + 1;             //number of terms in resulting polynomial. linear = 2; quadratic = 3 ... etc
        //Class computes and holds regression values
        //number of (x,y) pairs
        int numDataPoints = x.length();  //number of ordered pairs
        //test that data is in correct format
        //error if x and y are not same length after nulls are removed;
        boolean pairError = false;
        if (numDataPoints != y.length()){
            JOptionPane.showMessageDialog(null, "X and Y vector lengths vary.", "Error!" , JOptionPane.ERROR_MESSAGE);
            pairError = true;
        }
        if (!pairError){
            //Do matrix calculations once up front to save computation
            Matrix xMat = makeXMat(numDataPoints, terms, x); //create x-value matrix for proper polynomial order
            Matrix xTx = Matrix.product(xMat.transpose(), xMat); //create square matrix for x values.
            beta = calcRegressionCoefficients(xMat, xTx, y); //coefficients of mdoel stored in beta
           
            yCalc = calcYCalc(numDataPoints, terms, beta, x); //y values calculated from model stored in yCalc
           
            residuals = calcResiduals(numDataPoints,yCalc,y); //differences between actual and predicted values
           
            errors = calcErrors(numDataPoints, terms, xTx, residuals);
            
            r = calcPearson(x,y);
            
        }
    }
    
    
    //computes variables for line/curve of fit. returns r. Saves errors and coefficients
    private static Vector calcRegressionCoefficients(Matrix xMat, Matrix xTx, Vector y){
        Matrix betaMatrix = Matrix.product(Matrix.product(xTx.inverse(),xMat.transpose()),y);
        return betaMatrix.columnVector(1);
    }
    
    private static Vector calcErrors(int nDataPoints, int nTerms, Matrix xTx, Vector residuals){
        double rMinusC; // = new BigDecimal();
        double srs = 0.0;
        Vector errors = new Vector(xTx.numCols());
        if (nDataPoints == nTerms) rMinusC = 1;
        else rMinusC = nDataPoints - nTerms;
        for (int i = 1; i <= nDataPoints; i++)
            srs = srs + Math.pow(residuals.getValue(i).doubleValue(), 2)   ;
        double see = Math.sqrt(srs/rMinusC);
        //System.out.println("numb rows xtx "+xTx.numRows());
        for (int i = 1; i <= xTx.numRows(); i++){
            //System.out.println("error number B"+i+" "+see*Math.sqrt(xTx.getValue(1,i).doubleValue()));
            errors.putValue(i, new BigDecimal(see*Math.sqrt(xTx.getValue(i, i).doubleValue())));
        } 
        //errors.print();
        //System.out.println("donw with calc errors");
        return errors;
    }
   
    private static double calcPearson(Vector x, Vector y){
        double numDataPoints = x.length();
        double sumY = 0;
        double sumY2 = 0;
        double srs = 0.0;
        double sumZxZy = 0.0;
        double zX;
        double zY;
        double mX = GrafStats.getMean(x.toDoubleObject());
        double mY = GrafStats.getMean(y.toDoubleObject());
        double sX = GrafStats.getStandardDeviationOfSample(x.toDoubleObject());
        double sY = GrafStats.getStandardDeviationOfSample(y.toDoubleObject());
        for (int i = 1; i <= numDataPoints; i++){
            zX = calcZ(x.getValue(i).doubleValue(),mX,sX);
            zY = calcZ(y.getValue(i).doubleValue(),mY,sY);
            sumZxZy = sumZxZy + zX*zY;
        }
        return sumZxZy/(numDataPoints- 1);
    }
    
    private static double calcZ(double x,double m,double s){return (x-m)/s;}
    
    private static Vector calcResiduals(int nDataPoints,Vector yCalc, Vector pY){
        Vector residuals = new Vector(nDataPoints);
        for (int i = 1; i <= nDataPoints; i++)
            residuals.putValue(i, new BigDecimal(yCalc.getValue(i).doubleValue() - pY.getValue(i).doubleValue()));
        return residuals;
    }
    
    //make matrix with 1's in first column and powers of x in successive columns
    private static Matrix makeXMat(int nDataPoints, int nTerms, Vector xV){
        Matrix matrix = new Matrix(nDataPoints, nTerms);
        for (int row = 1; row <= nDataPoints; row++){
            matrix.putValue(row,1, new BigDecimal(1.0));
            for (int col = 2; col <= nTerms; col++)
                matrix.putValue(row , col, 
                matrix.getValue(row, col - 1).multiply(
                xV.getValue(row)));
        }
        return matrix;
    }
    
    private static Vector calcYCalc(int nDataPoints, int nTerms, Vector pBeta, Vector pX){
        BigDecimal yPredicted;
        BigDecimal coefficient;
        BigDecimal xValue;
        BigDecimal xPower;
        BigDecimal termValue;
        
        Vector yC = new Vector(nDataPoints);
        for (int i = 1; i <= nDataPoints; i++){
             yPredicted = new BigDecimal(0.0);
             for (int j = 1; j <= nTerms; j++){
                 coefficient = pBeta.getValue(j);
                 xValue = pX.getValue(i);
                 xPower = getXPower(xValue,j-1);
                 termValue = coefficient.multiply(xPower);
                 yPredicted = yPredicted.add(termValue);   //yEqu.add(beta.getValue(j).multiply(x.getValue(i))));
                 //System.out.println("Coef: "+coefficient+" x: "+xValue+" degree: "+(j-1)+" term value: "+termValue+" xPower: "+xPower+" predicted: "+yPredicted);
                 
             }
            
             yC.putValue(i, yPredicted); 
        }
        
        return yC;  
        
    }
    

    
    private static BigDecimal getXPower(BigDecimal x, int power){//problem here
        BigDecimal powerValue = new BigDecimal(1.0);
        if (power == 0) return powerValue;
        for (int k = 1; k <= power; k++)
             powerValue = powerValue.multiply(x);
        return powerValue;
    }
    
    Vector getXVector(){
        return x;
    }
    
    Vector getYVector(){
        return y;
    }
    
    Vector getCoefficients(){
        return beta;
    }
    
    Vector getResiduals(){
        return residuals;
    }
    
    Vector getYCalc(){
        return yCalc;
    }
    
    double getRValue(){
        return r;
    }
    
    Vector getErrors(){
        return errors;
    }
}
