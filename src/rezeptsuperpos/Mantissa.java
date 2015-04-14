/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rezeptsuperpos;

/**
 *
 * @author Hannes Hassler
 */
public class Mantissa {
    
    public static void main(String[] args) {
        
        String testVal="2.415248915553237";
        testVal="0.015248915553237";
        testVal="0.15248915553237";
        testVal="0.1524";
        testVal="2.415248915553237";
        testVal="241524.8915553237";   
        testVal="0.07";
        System.out.println(shorten(testVal,5));
        
        double testValDouble=2.415248915553237;
        System.out.println(shorten(testValDouble,5));
        
    }
    
    public static String shorten(double val, int desiredMantLength) {
        String valStr=""+val;
        return shorten(valStr,desiredMantLength);
    }
    
    public static String shorten(String val, int desiredMantLength) {
        int radixPosition=val.indexOf(".");
        int mantLeftPosition=0;
        int mantRightPosition=val.length()-1;
        String retVal=val;
        if (!(val.contains("E"))) {
            if (val.startsWith("0") && radixPosition==1) {
                boolean found=false;
                for (int i=2;i<val.length() && !found;i++)
                    if (!val.substring(i, i+1).equalsIgnoreCase("0")) {
                        mantLeftPosition=i;
                        mantRightPosition=Math.min(mantLeftPosition+desiredMantLength, val.length());
                        if (mantRightPosition<val.length());
                        found=true;
                    }
                if (found) retVal=val.substring(0, mantRightPosition);                        
            } else {
                retVal=val.substring(0,
                        Math.min(
                            val.length(),
                            Math.max(radixPosition+2, desiredMantLength+1)
                        )
                );
            }
        }
        return retVal; 
    }
}
