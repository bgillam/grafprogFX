//Orignal class FunctionString was large, so it has been broken into FunctionString, FunctionStringSubstring, FunctionShring Replacers, FunctionStringOperators and FunctionStringErrorChecks.
class FunctionStringReplacers {

    //adds multiplication signs in front of a variable if needed
    static String addMultSigns(String fString){
        int counter;
        counter = 1;
        //insert times signs between an letter and a number or ( and number
        if (fString.length() == 1) return fString;
        do{
            // keyCode = KeyEvent.getExtendedKeyCodeForChar(fString.charAt(counter));
            if ( (GrafInputHelpers.isAnAlphaChar(fString,counter)) || (fString.charAt(counter) == '(')) //if a letter or parentheses
                if ( GrafInputHelpers.isANumberChar(fString, counter-1))                                  // and the previous character is a number
                    fString = fString.substring(0,counter)+'*'+fString.substring(counter); //add a *

            counter++;
        }while (counter < fString.length());
        return fString;
    }

    //removes all occurrences of a particular string from passed fString.
    static String removeString(String token, String fString){
        int p;
        do {
            p = fString.indexOf(token);
            if (p != -1)
                fString = fString.substring(0,p)+fString.substring(p+token.length());
            else return fString;
        }while (true);
    }

    //replace each x in string with the passed double
    static String replaceX(String fString, double x){
        int pos = -1;
        if (Math.abs(x)<.000000000001) x = 0.0; //Hack to fix hang up on calculations. Need to fix logically instead.
        do{
            if (pos+1 >= fString.length()) break;
            pos = fString.indexOf('X',pos+1);
            if (pos == -1) break;
            if (!GrafInputHelpers.isAnAlphaChar(fString, pos + 1))
                if (pos == 0) fString = x+fString.substring(pos+1);
                else fString =  fString.substring(0,pos)+x+fString.substring(pos+1);

        }while (true);
        fString = fString.toUpperCase();
        return fString;
    }


    //replace "pi" with an approximation of pi
    static String replacePI(String fString){
        int pos = -1;
        do{
            pos = fString.indexOf("PI",pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+ Math.PI +fString.substring(pos+2);
        }while (true); //pos != -1);
        return fString;
    }

    //simplify double operators
    static String replaceDoubleOperator(String fString){
        int pos = -1;
        String token = "--";
        do{
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+"+"+fString.substring(pos+2);
         }while (true); //pos != -1);
        token = "++";
        do{
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+"+"+fString.substring(pos+2);
        }while (true); //pos != -1);
        token = "+-";
        do{
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+"-"+fString.substring(pos+2);
        }while (true); //pos != -1);
        token = "-+";
        do{
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+"-"+fString.substring(pos+2);
        }while (true); //(pos != -1);
        return fString;
    }

    //replace "-(" with "-1*("
    static String replaceMinusParen(String fString){
        int pos = -1;
        String token = "-(";
        do{
            pos = fString.indexOf(token,pos+1);
            if (pos == -1) break;
            fString =  fString.substring(0,pos)+"-1*("+fString.substring(pos+2);
        }while (true); //(pos != -1);
        return fString;
    }

    //replaces "-x" with "-1`*x"
    static String replaceMinusX(String fString){
        int minusXPos = fString.indexOf("-X");
        while (minusXPos != -1){
            String first = fString.substring(0,minusXPos);
            String last = fString.substring(minusXPos+2);
            fString = first+"-1*X"+last;
            minusXPos = fString.indexOf("-X");
        }


        return fString;
    }



}
