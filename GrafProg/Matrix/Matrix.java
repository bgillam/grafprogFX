package GrafProg.Matrix;
/* public methods:
 * GrafProg.Matrix.Matrix(int row, int col)
 * GrafProg.Matrix.Matrix(BigDecimal[][] m)
 * GrafProg.Matrix.Matrix(BigDecimal[][] m)
 * product(GrafProg.Matrix.Matrix A, GrafProg.Matrix.Matrix B)
 * product(GrafProg.Matrix.Matrix A, GrafProg.Matrix.Vector B)
 * product(GrafProg.Matrix.Vector A, GrafProg.Matrix.Matrix B)
 * copy()
 * getValue(int row, int col);
 * putValue(int row, int col, BigDecimal val)
 * swap(int row1, int col1, int row2, int col2)
 * numRows()
 * numCols()
 * rowVector(int row)
 * columnVector(int col)
 * getArray()
 * BigDecimalArray()
 * transpose()
 * determinant()
 * inverse()
 * static GrafProg.Matrix.Matrix identity(int dim)
 * print()
 * toString()
 */

import javax.swing.JOptionPane;
import java.math.*;

public class Matrix {
    private BigDecimal[][] matrix;
    
    //test
    public static void main(String[] args){
       double[][] ma = {{1, 5, 6, 2.2},      
                       {3.3, 9, 10, 1},
                       {7, 9, 3.2, 5.1},
                       {5, 8, 6.3, 2}};
       //double[][] ma = 
       //     {{1,1},
       //      {1,2},
       //      {1,3},
       //      {1,4},
       //      {1,5},
      //       {1,6}};
       Matrix m = new Matrix(ma);
       //GrafProg.Matrix.Matrix mT = m.transpose();
       //GrafProg.Matrix.Matrix mTxm = GrafProg.Matrix.Matrix.product(mT,m);
       //System.out.println("GrafProg.Matrix.Matrix :");
       System.out.println(m);
       Vector v = m.columnVector(1);
       System.out.println(v);
       //System.out.println("GrafProg.Matrix.Matrix mT:");
       //System.out.println(mT);
       //System.out.println("GrafProg.Matrix.Matrix mTxm: ");
       //System.out.println(mTxm);
       //GrafProg.Matrix.Matrix inv = mTxm.inverse();
       //System.out.println("inv");
       //System.out.println(inv);
       
    }
    
    public Matrix(int rows, int cols){
        matrix = new BigDecimal[rows][cols];
    }
    
    Matrix(BigDecimal[][] m){
        matrix = m;
    }
    
    public Matrix(Double[][] m){
    //need to strip nulls here
    matrix = new BigDecimal[m.length] [m[0].length];
    for (int i = 0; i < m.length; i++)
        for (int j = 0; j < m[0].length; j++)
            if (m[i][j] != null) matrix[i][j] = new BigDecimal(m[i][j]); else m[i][j] = null;
    
    }
    
    private Matrix(double[][] m){
    matrix = new BigDecimal[m.length] [m[0].length];
    for (int i = 0; i < m.length; i++)
        for (int j = 0; j < m[0].length; j++){
            BigDecimal bd = new BigDecimal(m[i][j]);
            matrix[i][j] = (bd);
        }
    }
    
   //public static GrafProg.Matrix.Matrix fromArray(BigDecimal[][] a){
   //    GrafProg.Matrix.Matrix m = new GrafProg.Matrix.Matrix(a.length, a[0].length);
   //    for (int i = 0; i<a.length; i++)
   //         for (int j = 0; j<a[0].length; j++)
   //             m.putValue(i+1, j+1, a[i][j]);
   //    return m;
   //}
      
    public static Matrix product(Matrix A, Matrix B){
        int colsA = A.numCols();
        int rowsB = B.numRows();
        if (colsA != rowsB) JOptionPane.showMessageDialog(null, "GrafProg.Matrix.Matrix A and B are not compatible for multiplication ", "Error!" , JOptionPane.ERROR_MESSAGE);
        BigDecimal sum = new BigDecimal(0.0);
        int rowsA = A.numRows();
        int colsB = B.numCols();
        Matrix result = new Matrix(rowsA, colsB);
        for (int r = 1; r <= rowsA; r++){
            for (int c = 1;  c <= colsB; c++){
                for (int k = 1; k<= rowsB; k++){
                    sum = sum.add(A.getValue(r,k).multiply(B.getValue(k,c)));
                }
                result.putValue(r,c,sum);
                sum = new BigDecimal(0.0);
            }  
        }
        return result;
    }
    
    public static Matrix product(Matrix A, Vector B){
        return product(A, B.toColMatrix());
    }
    
    public static Matrix product(Vector A, Matrix B){
        return product(A.toColMatrix(), B);
    }
    
    private boolean checkValidRow(int row){
    if (row < 1 || row > numRows()) {
        JOptionPane.showMessageDialog(null, "Row Value Out of Range", "Error!" , JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
    }
    private boolean checkValidColumn(int col){
    if (col < 1 || col > numCols()) {
        JOptionPane.showMessageDialog(null, "Column Value Out of Range", "Error!" , JOptionPane.ERROR_MESSAGE);
        return false;
    }
    return true;
    }
        
    public BigDecimal getValue(int row, int col){
        if (checkValidRow(row)&& checkValidColumn(col))
            return matrix[row - 1][col - 1]; 
        else return null;
    }
    
    public void putValue(int row, int col, BigDecimal val){
        if (checkValidRow(row)&& checkValidColumn(col))
            matrix[row-1][col-1] = val; 
    }
    
    public int numRows(){
        return matrix.length;
    }
    
    public int numCols(){
        return matrix[0].length;
    }
    
    public Vector rowVector(int row){
        if (checkValidRow(row)){
            Vector v = new Vector(numCols());
            for (int col = 1; col <= numCols(); col++)
                v.putValue(col, getValue(row, col));
            return v;
        } else return null;
    }
    
    public Vector columnVector(int col){
        if (checkValidColumn(col)){
           Vector v = new Vector(numRows());
           for (int row = 1; row <= numRows(); row++)
                v.putValue(row, getValue(row, col));
           return v;
        } else return null;
    }
    
    
    public Matrix transpose(){
        int numRows = numRows();
        int numCols = numCols();
        Matrix xT = new Matrix(numCols, numRows);
        for (int r = 1; r<=numRows; r++)
            for (int c = 1; c<=numCols; c++)
                xT.putValue(c, r, getValue(r , c));
        return xT;
    }
    
    private BigDecimal[][] BigDecimalArray(){
          BigDecimal[][] newArray = new BigDecimal[numRows()][numCols()]; 
          for (int i = 1; i <= numRows(); i++)
              for (int j = 1; j<=numCols(); j++)
                   newArray[i-1][j-1] = getValue(i,j);
          return newArray;
    }
    
    public BigDecimal[][] getArray(){ return matrix; }
    
   
    
   public BigDecimal determinant(){
        if (numRows() != numCols()) {
            JOptionPane.showMessageDialog(null, "You can only take the determinate of a square matrix. ", "Error!" , JOptionPane.ERROR_MESSAGE);
        }    
        if(numRows() == 1)
        {
            return getValue(1,1);
        }
        if (numRows() == 2)
        {
            return getValue(1,1).multiply(getValue(2,2)).subtract(getValue(2,1).multiply(getValue(1,2)));
        }
        return getArrayDeterminant(BigDecimalArray());
   }
  
  private static BigDecimal getArrayDeterminant(BigDecimal[][] mat){
    BigDecimal sum = new BigDecimal(0.0);
    BigDecimal sign = new BigDecimal(1);
    if(mat.length == 1){  //bottom case of recursion. size 1 matrix determinant is itself.
       return(mat[0][0]);
    }
    for(int i=0; i < mat.length; i++){ 
      BigDecimal[][]sub = new BigDecimal[mat.length-1][mat.length-1]; 
      for(int j=1; j < mat.length; j++){
        for(int k=0; k < mat.length; k++){
          if(k<i){
            sub[j-1][k]= mat[j][k];
          }
          else if(k>i){
            sub[j-1][k-1]=mat[j][k];
          }
        }
      }
      if(i%2 == 0){ //sign changes based on i
        sign = new BigDecimal(1.0);
      }
      else{
        sign = new BigDecimal(-1.0);
      }
      //sum = sum.add
      sum =sum.add(mat[0][i].multiply(sign).multiply((getArrayDeterminant(sub))));
    }
    return(sum); //returns determinant value. once stack is finished, returns final determinant.
  }

    
    public Matrix inverse(){
       //return GrafProg.Matrix.Matrix.fromArray(invert(BigDecimalArray()));
       return new Matrix(invert(BigDecimalArray()));
    }
    
    private static BigDecimal[][] zeroTwoDimArray(BigDecimal[][] a){
        for (int i = 0; i < a.length; i++)
            for (int j = 0; j < a[0].length; j++)
                a[i][j]=new BigDecimal(0.0);
        return a;
        
    }
    
    private static BigDecimal[][] invert(BigDecimal[][] a){
        int n = a.length;
        BigDecimal[][] x = new BigDecimal[n][n];
        x = zeroTwoDimArray(x);
        BigDecimal[][] b = new BigDecimal[n][n];
        b = zeroTwoDimArray(b);
        int[] index = new int[n];
        //identity matrix
        for (int i=0; i<n; i++)
               b[i][i] = new BigDecimal(1);
        //row reduction and index stores pivot order
        gaussian(a, index);
        //update matrix b
        for (int i=0; i<n-1; i++)
            for (int j=i+1; j<n; j++)
                for (int k=0; k<n; k++){
                    //System.out.println("i: "+i+" j: "+j+" k: "+k);
                    b[index[j]][k] = 
                    b[index[j]][k].subtract(
                    a[index[j]][i].multiply(
                    b[index[i]][k]));
                }
        //sustitutions b to x
        for (int i=0; i<n; ++i){
            x[n-1][i] = b[index[n-1]][i].divide(a[index[n-1]][n-1],MathContext.DECIMAL128);
            for (int j=n-2; j>=0; --j){
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k){
                    x[j][i] = x[j][i].subtract(a[index[j]][k].multiply(x[k][i]));
                }
                x[j][i] = x[j][i].divide(a[index[j]][j],MathContext.DECIMAL128);
            }
        }
        return x;
    }
 
// Method to carry out the partial-pivoting Gaussian
// elimination.  Here index[] stores pivoting order.
 
    private static void gaussian(BigDecimal[][] a, int[] index){
        int n = index.length;
        BigDecimal[] c = new BigDecimal[n];
        BigDecimal pi1;
        BigDecimal pi0;
        BigDecimal c1;
        BigDecimal c0;
        for (int i=0; i<n; ++i) 
            index[i] = i;
        for (int i=0; i<n; ++i){
            c1 = new BigDecimal(0.0);
            for (int j=0; j<n; ++j){
                c0 = a[i][j].abs();
                if (c0.compareTo(c1) > 0 ) c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j=0; j<n-1; ++j){
            pi1 = new BigDecimal(0.0);
            for (int i=j; i<n; ++i){
                pi0 = a[index[i]][j].abs();
                pi0 = pi0.divide(c[index[i]], MathContext.DECIMAL128);
                if (pi0.compareTo(pi1) > 0){
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i){
                BigDecimal pj = a[index[i]][j].divide(a[index[j]][j], MathContext.DECIMAL128);
                a[index[i]][j] = pj;
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] = a[index[i]][l].subtract(pj.multiply(a[index[j]][l]));
            }
        }
    }
    
    public static Matrix identity(int dim){
       Matrix mat = new Matrix(dim, dim);
       for (int i = 1; i<=dim; i++)
           for (int j = 1; j<=dim; j++)
                if (i == j) mat.putValue(i,j,new BigDecimal(1)); else mat.putValue(i,j,new BigDecimal(0));
      return mat;
    }
    
    public Matrix copy(){
        int numRows = numRows();
        int numCols = numCols();
        Matrix xC = new Matrix(numRows, numCols);
        for (int r = 1; r<=numRows; r++)
            for (int c = 1; c<=numCols; c++)
                xC.putValue(r,c,getValue(r , c));
        return xC;
    }
    
    public Matrix swap(int row1, int col1, int row2, int col2){
        if (checkValidRow(row1) && checkValidColumn(col1)
            && checkValidRow(row2) && checkValidColumn(col2)){
                Matrix returnMatrix = this.copy(); 
                BigDecimal swap = returnMatrix.getValue(row1, col1);
                returnMatrix.putValue(row1, col1, returnMatrix.getValue(row2, col2));
                returnMatrix.putValue(row2, col2, swap);
                return returnMatrix;
            }else return null;
        
    }
         
    
    public void print(){
        for (int i = 1; i <= numRows(); i++){
            System.out.print("[");
            for (int j = 1; j < numCols(); j++)
                System.out.print(getValue(i,j)+", ");
            System.out.println(getValue(i, numCols())+"]");
        }
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= numRows(); i++){
            s.append("[");
            for (int j = 1; j < numCols(); j++)
                s.append(getValue(i, j)).append(", ");
            s.append(getValue(i, numCols())).append("]\n");
        }
        return s.toString();
    }
    
    

}
