package GrafProg.GrafObjects.Function;

import javax.swing.*;

//Orignal class GrafProg.GrafObjects.Function.FunctionString was large, so it has been broken into GrafProg.GrafObjects.Function.FunctionString, GrafProg.GrafObjects.Function.FunctionStringSubstring, FunctionShring Replacers, GrafProg.GrafObjects.Function.FunctionStringOperators and GrafProg.GrafObjects.Function.FunctionStringErrorChecks.
public class FunctionStringErrorChecks {

    //Checks that a string can be evaluated at a value.
    public static boolean isValidAtX(String functionString, double x){ //returns 0 for empty String. -1 for error 1 for OK
        if (functionString.equals("")) { JOptionPane.showMessageDialog(null, "Empty Function String! ", "Error!" , JOptionPane.ERROR_MESSAGE); return false;}
        try{
            return !Double.isNaN(FunctionString.fValue(functionString, x));
        } catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Invalid function! ", "Error!" , JOptionPane.ERROR_MESSAGE); return false;
        } catch (ArithmeticException e) { JOptionPane.showMessageDialog(null, "Function not defined at x = "+x+"!", "Error!" , JOptionPane.ERROR_MESSAGE); return false;
        } catch (Exception e) { return false;
        }
    }


    public static int checkFunctionString(String fString) {

        double r;
        int errorCode = 0;
        if (fString.equals("")) return -1;
        fString = fString.toUpperCase(); //all caps
        fString = FunctionStringReplacers.removeString(" ",fString); //remove blanks
        fString = FunctionStringReplacers.addMultSigns(fString); //add * signs where needed. ex: 2(3+5 -> 2*(3+5)
        fString = FunctionStringReplacers.replaceMinusX(fString);
        fString = FunctionStringReplacers.replaceMinusParen(fString);
        fString = FunctionStringReplacers.replaceX(fString,0);  //replace x with its value
        fString = FunctionStringReplacers.replacePI(fString); //replace pi with its approximation
        //System.out.println(fString);
        try {
            errorCode = checkErrors(fString);
            //errorMsg(errorCode); //check for non-matching parentheses and other typo errors
        }catch (Exception e){System.out.println(e.toString());}

        //errorMsg(errorCode);
        if (errorCode != 0) {
            return errorCode; } //possibly do something, but more likely implement a try-catch later

        try{

            r=Double.parseDouble(fString);

        } //if we can't parse what we have to a number,
        catch (NumberFormatException e){

            r = FunctionString.eval(fString);

            /*try{

                r = eval(fString);

            }
            catch (GrafProg.GrafProg.GrafUtils.DomainViolationException dve){return 9;}*/
        } //call eval to simplify

        return 0 ;
    }


    //returns true if the character is a non minus operator
    static boolean isaNonMinusOperator(char s){
        boolean nonMinusOperator = false;
        switch (s){
            case  '+':nonMinusOperator = true; break;
            case  '*':nonMinusOperator = true; break;
            case  '/':nonMinusOperator = true; break;
            case  '^':nonMinusOperator = true; break;
        }
        return nonMinusOperator;
    }





    // tests for symbols that are not a function, operator, decimal point or parentheses. returns false if it finds an invalid symbol
    private static boolean tokensOK(String fString){
        String middle = fString;
        int i;
        middle= FunctionStringReplacers.removeString("ABS",middle);
        middle= FunctionStringReplacers.removeString("ARCSIN",middle);
        middle= FunctionStringReplacers.removeString("ARCTAB",middle);
        middle= FunctionStringReplacers.removeString("ARCCOS",middle);
        middle= FunctionStringReplacers.removeString("COS",middle);
        middle= FunctionStringReplacers.removeString("COT",middle);
        middle= FunctionStringReplacers.removeString("CSC",middle);
        middle= FunctionStringReplacers.removeString("DTOR",middle);
        middle= FunctionStringReplacers.removeString("EXP",middle);
        middle= FunctionStringReplacers.removeString("frac",middle);
        middle= FunctionStringReplacers.removeString("INT",middle);
        middle= FunctionStringReplacers.removeString("LN",middle);
        middle= FunctionStringReplacers.removeString("LOG",middle);
        middle= FunctionStringReplacers.removeString("PI",middle);
        middle= FunctionStringReplacers.removeString("RTOD",middle);
        middle= FunctionStringReplacers.removeString("ROUND",middle);
        middle= FunctionStringReplacers.removeString("SIN",middle);
        middle= FunctionStringReplacers.removeString("SEC",middle);
        middle= FunctionStringReplacers.removeString("SQRT",middle);
        middle= FunctionStringReplacers.removeString("TAN",middle);
        middle= FunctionStringReplacers.removeString("TRUNC",middle);
        i = 0;
        if (middle.equals("")) return true;
        do{
            switch (middle.charAt(i)){
                case '+': break;
                case '*': break;
                case '/': break;
                case '^': break;
                case '.': break;
                case '-': break;
                //case '{': break;
                //case '}': break;
                case '(': break;
                case ')': break;
                case '0': break;
                case '1': break;
                case '2': break;
                case '3': break;
                case '4': break;
                case '5': break;
                case '6': break;
                case '7': break;
                case '8': break;
                case '9': break;
                case 'E': break;
                default : return false;
            }
            i = i+1;
        } while (i < middle.length());
        return true;
    }

    // checks for errors and returns a code; error 2: non matching parentheses; error 3: negative exponent; error 4: two consecutive operators; error 6: bad token
    static int checkErrors(String fString){  //throws GrafProg.GrafObjects.Function.FunctionFormatException{
        int errorCode = 0;
        int leftCount = 0;
        int rightCount = 0;
        if (tokensOK(fString)){
            for (int counter = 0; counter < fString.length(); counter++){
                if (fString.charAt(counter) =='(')  leftCount++;
                else if (fString.charAt(counter) ==')')  rightCount++;
                    //else if ((fString.charAt(counter) =='^') && (fString.charAt(counter+1) == '-')) errorCode = 3; //not sure why I disallowed negative exponents
                else if (isaNonMinusOperator(fString.charAt(counter)) && (isaNonMinusOperator(fString.charAt(counter+1)))) return 4; //don't allow two consecutive operators
                else if ((fString.charAt(counter) == '-') && (isaNonMinusOperator(fString.charAt(counter+1)))) return 4;
                else if (fString.substring(fString.length() - 1).equals("-")) return 4;
                else if (fString.substring(fString.length() - 1).equals("+")) return 4;
                else if (fString.substring(fString.length() - 1).equals("*")) return 4;
                else if (fString.substring(fString.length() - 1).equals("/")) return 4;
                else if (fString.substring(fString.length() - 1).equals("(")) return 4;
            }
        }else return 6; //bad tokens in string
        if (leftCount != rightCount)  return 2;//non-matching parentheses
        //if (errorCode != 0) throw new GrafProg.GrafObjects.Function.FunctionFormatException();
        return errorCode;
    }

    //returns error message for error code
    static int errorMsg(int eCode)
    {
        if (eCode == 0) return 0;
        String message = "";
        switch (eCode){
            case 1: message = "Division by zero"; break;
            case 2: message = "Non-matching parenthesis in function"; break;
            case 3: message = "Must enclose negative exponents in parentheses"; break;
            case 4: message = "Incorrect Operator Syntax"; break;
            case 5: message = "Rightfunctions must use parenthesis. (example: ABS(-2);"; break;
            case 6: message = "Unrecognized token in function srtring."; break;
            case 8: message = "Can not raise negative number to a fractional power."; break;
            case 10:message = "Input a real number with no operators"; break;
        }
        JOptionPane.showMessageDialog(null, message, "Error "+eCode, JOptionPane.ERROR_MESSAGE);
        return 0;
    }

}
