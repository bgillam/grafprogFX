
/************************************************************ 
*  Vector math.                                             *
*  A vector is a n x 1 array of Doubles: n rows, 1 column.  *
*  @author Bill Gillam                                      *
*  2/25/15                                                  *
*************************************************************/


import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigDecimal;


public class Vector {
    BigDecimal[][] vector;
    
    public static void main(String[] args){
    double[] va = {1, 3, 7, 9, 11, 13};
    System.out.println(Arrays.toString(va));
    Vector v = new Vector(va);
    System.out.println("v = "+v);
    System.out.println("v transposed = "+v.transpose());
    System.out.println("v times t = "+Matrix.product(v.toRowMatrix(), v.toColMatrix()));
}

    
        
    public Vector(int rows){
        vector = new BigDecimal[rows][1];
    }
    
    public Vector(BigDecimal[] v){
        int size = v.length;
        vector = new BigDecimal[size][1];
        for (int i = 0; i < size; i++){
            vector[i][0] = v[i];
        }
    }
    
    
    
    public Vector(double[] v){
        int size = v.length;
        vector = new BigDecimal[size][1];
        for (int i = 0; i < size; i++){
            //System.out.println("i: "+i+" size: "+size+" value: "+v[i]);
            vector[i][0] = new BigDecimal(v[i]);
        }
    }
    
     public Vector(Double[] v){
        //need to strip nulls here
        int size = v.length;
        vector = new BigDecimal[size][1];
        for (int i = 0; i < size; i++){
            //System.out.println("i: "+i+" size: "+size+" value: "+v[i]);
            if (v[i] != null) vector[i][0]= new BigDecimal(v[i]); else v[i] = null;
        }
    }
    
    public Matrix toRowMatrix(){
        Matrix c = new Matrix(1, length());
        for (int i = 1; i <= length(); i++){
            c.putValue(1,i,getValue(i));
        }
        return c;
    }
    
    public Matrix transpose(){
        return toRowMatrix();
    }
    
    public Matrix toColMatrix(){
        return new Matrix(vector);        
    }
    
    public BigDecimal[][] getArray(){ return vector; }
    
    public Vector stripEndNulls(){
        Vector stripped;
        int lastNonNullIndex = -1;
        for (int i = length(); i > 0; i--)
            if (getValue(i) != null) { lastNonNullIndex = i; break;}
        stripped = new Vector(lastNonNullIndex);
        for (int i = lastNonNullIndex; i>0; i--)
            stripped.putValue(i, getValue(i));
        return stripped;
                
    }
        
    public double[] toDouble(){
        double[] a = new double[length()];
         for (int i = 1; i <= length(); i++)
            a[i-1] = getValue(i).doubleValue();
        return a;  
    }
    
    public Double[] toDoubleObject(){
         Double[] a = new Double[length()];
         for (int i = 1; i <= length(); i++)
            a[i-1] = getValue(i).doubleValue();
        return a;  
    
    }
        
    public BigDecimal getValue(int row){
        
        return vector[row-1][0];
    }
    
    public void putValue(int row, BigDecimal val){
        vector[row-1][0] = val; 
    }
    
    public int length(){
        return vector.length;
    }
    
    public void print(){
        for (int i = 1; i <= length(); i++)
            System.out.println("["+getValue(i)+"]");
    }
    
    
    public String toString(){
        String s = "";
        for (int i = 1; i <= length(); i++)
            s = s + "["+getValue(i)+"]\n";
        return s;
    }
    
}
