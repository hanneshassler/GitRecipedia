/*
 * Copyright (C) 2015  Hannes Hassler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rezeptsuperpos;

import java.util.*;

/**
 *
 * @author Hannes Hassler
 */
public class Convert {
    
    //Foundation units are ml and gram
    public static double ml=1.0;
    public static double dl=10;
    public static double l=1000;
    public static double t=4.929;//teaspoon
    public static double tsp=4.929;//teaspoon
    public static double tsps=4.929;//teaspoon
    public static double T=14.79;//tablespoon
    public static double tbsp=14.79;//tablespoon
    public static double tbsps=14.79;//tablespoon
    public static double floz=29.5735;//US fluid ounce
    public static double uk_floz=28.4131;//UK fluid ounce
    public static double cup=236.6;
    public static double pt=473.2;
    public static double qt=946.4;
    public static double gal=3785;
    
    public static double oz=28.3495;//ounce
    public static double lb=453.6;//pound
    
    public HashMap<String,Double> volumeUnitVal = new HashMap<String,Double>();
    public HashMap<String,Double> weightUnitVal = new HashMap<String,Double>();
    public static HashMap<String,Double> specialUnit  = new HashMap<String, Double>();
    
    
     public Convert() {
    	 initialize();
     }
        
    public void initialize() {
        volumeUnitVal.put("ml",1.0);
        volumeUnitVal.put("cl",100.0);
        volumeUnitVal.put("dl",100.0);
        volumeUnitVal.put("l",1000.0);
        volumeUnitVal.put("t",4.929);
        volumeUnitVal.put("tsp",4.929);
        volumeUnitVal.put("tsps ",4.929);
        volumeUnitVal.put("T",14.79);
        volumeUnitVal.put("tbsp",14.79);
        volumeUnitVal.put("tbsps",14.79);
        volumeUnitVal.put("floz",29.5735);
        volumeUnitVal.put("uk_floz",28.4131);
        volumeUnitVal.put("cup",236.6);
        volumeUnitVal.put("pt",473.2);
        volumeUnitVal.put("qt",946.4);
        volumeUnitVal.put("gal",3785.0);
        
        weightUnitVal.put("g",1.0);
        weightUnitVal.put("dag",10.0);
        weightUnitVal.put("kg",1000.0);        
        weightUnitVal.put("oz",28.3495);
        weightUnitVal.put("lb", 453.6);   
        
        //4.3 cm ginger weigh 32 gram
        specialUnit.put("cm,ingwer",32/4.3);
        specialUnit.put("cm,ginger",32/4.3);
        
        //1 knob of butter
        specialUnit.put("knob,butter",10.0);
        
        
        //13 bay leaves weight about 2 gram
        specialUnit.put("Stk,lorbeer",2/13.0);
        specialUnit.put("Stk,lorbeerblatt",2/13.0);
        specialUnit.put("Stk,lorbeer blatt",2/13.0);
        specialUnit.put("Stk,bay leave",2/13.0);
        specialUnit.put("pc,bay leave",2/13.0);
        specialUnit.put("pcs,bay leave",2/13.0);
        
        //1 cinnamon stick weighs about 3 gram
        specialUnit.put("Stk,zimtstange",3/1.0);
        specialUnit.put("Stk,cinnamon stick",3/1.0);
        specialUnit.put("pc,zimtstange",3/1.0);
        specialUnit.put("pcs,cinnamon stick",3/1.0);
        
        //3 garlic clove weighs about 14 gram
        specialUnit.put("pc,garlic",14/3.0);
        specialUnit.put("pc,garlic clove",14/3.0);
        specialUnit.put("pcs,garlic",14/3.0);
        specialUnit.put("pcs,garlic clove",14/3.0);
        specialUnit.put("Stk,garlic",14/3.0);
        specialUnit.put("Stk,garlic clove",14/3.0);        
        specialUnit.put("Stk,knoblauch",14/3.0);
        specialUnit.put("Stk,knoblauchzehe",14/3.0);
        
        //1 onion weighs about 114 gram
        specialUnit.put("pc,onion",114/1.0);
        specialUnit.put("pcs,onion",114/1.0);
        specialUnit.put("Stk,onion",114/1.0);         
        specialUnit.put("Stk,zwiebel",114/1.0);
        
               
        
        
    }
    
    public static void setSpecialUnit(String ingredientName, double weightPerPiece) {
        specialUnit.put("pc,"+ingredientName.toLowerCase(),weightPerPiece);
        specialUnit.put("pcs,"+ingredientName.toLowerCase(),weightPerPiece);
        specialUnit.put("Stk,"+ingredientName.toLowerCase(),weightPerPiece);
        specialUnit.put("stk,"+ingredientName.toLowerCase(),weightPerPiece);
    }
    
    
    public double calc2gram(String oldUnit, double oldUnitVal, double density_g_p_ml, String name) 
    throws Exception {
        double volume_ml;
        double weight_g=0;        
        
        try {
        if (name !=null && name.contains(","))
            name=name.split(",")[0];
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            if (name!=null && specialUnit.containsKey(oldUnit+","+name.trim().toLowerCase())) {
            weight_g=specialUnit.get(oldUnit+","+name.trim().toLowerCase())*oldUnitVal;
            }
        } catch (Exception ex ) {
            //ex.printStackTrace();
        }
        
        
        if (weight_g==0) {
            if (density_g_p_ml==0) throw new Exception(ExceptionMessage.densityIsNull);
        
            if (volumeUnitVal.containsKey(oldUnit)) {            
                volume_ml=volumeUnitVal.get(oldUnit)*oldUnitVal;            
                weight_g=volume_ml*density_g_p_ml;                        
            }
            else {
                weight_g=weightUnitVal.get(oldUnit)*oldUnitVal; 
            }
        
        }
        
        return weight_g;
    }
    
    public double units(String oldUnit, String newUnit, double oldUnitVal, double density_g_p_ml, String name) 
            throws Exception
    {
        
        double volumeOld_ml;
        double weightOld_g;
        double retVal=0;
        try {
        if (name !=null && name.contains(","))
            name=name.split(",")[0];
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        try {
            if (name!=null && specialUnit.containsKey(oldUnit+","+name.trim().toLowerCase())) {
            retVal=specialUnit.get(oldUnit+","+name.trim().toLowerCase())*oldUnitVal;
            }
        } catch (Exception ex ) {
            //ex.printStackTrace();
        }
        /*
        if (oldUnit.equalsIgnoreCase("cm") && (name.toLowerCase().contains("ingwer") || name.toLowerCase().contains("ginger"))) {
            retVal=oldUnitVal*32/4.3;
        }*/
        
        
        if (retVal==0) {
            if (density_g_p_ml==0) throw new Exception(ExceptionMessage.densityIsNull);
        
            if (volumeUnitVal.containsKey(oldUnit)) {            
                volumeOld_ml=volumeUnitVal.get(oldUnit);            
                weightOld_g=volumeOld_ml*density_g_p_ml;                        
            }
            else {
                weightOld_g=weightUnitVal.get(oldUnit);            
                volumeOld_ml=weightOld_g/density_g_p_ml;
            }
        
            double valuePerGram=oldUnitVal/weightOld_g;
            double valuePerMl=oldUnitVal/volumeOld_ml;
            
        
        
            if (volumeUnitVal.containsKey(newUnit)) {
                retVal=valuePerMl*volumeUnitVal.get(newUnit);            
            } else {
                retVal=valuePerGram*weightUnitVal.get(newUnit);            
            } 
        }
        
        return retVal;
        
        
    }
    
    
}
