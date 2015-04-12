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

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Hannes Hassler
 */
public class Ingredient {
	
	public String name;
	public ArrayList<String> alias = new ArrayList<String>();
        public static String stdTrenner=",";
	//measures are considered per ml
        public double measure=0;
        public double measureInGram;        
        public String measureUnit=null;
        public double weightPerPiece=0;
	public double density=0;
	public double calSpec=0;
	public double fatSpec=0;
	public double carbSpec=0;
	public double protSpec=0;
	private Convert convert = new Convert();

	
	public Ingredient(String name) {
		this.name=name;		
	}
	
	public Ingredient(Element element) {
		this.name=element.getChildText("name");
		this.alias=MyElement.tryGetList(element,"alias", this); 
		this.density=MyElement.tryGetDouble(element,"density");
		this.calSpec=MyElement.tryGetDouble(element,"calSpec");
		this.fatSpec=MyElement.tryGetDouble(element,"fatSpec");
		this.carbSpec=MyElement.tryGetDouble(element,"carbSpec");
		this.protSpec=MyElement.tryGetDouble(element,"protSpec");
                this.measure=MyElement.tryGetDouble(element,"measure");
                this.measureInGram=MyElement.tryGetDouble(element,"measure_in_gram");
                this.measureUnit=MyElement.tryGetString(element,"measureUnit");
                this.weightPerPiece=MyElement.tryGetDouble(element, "weightPerPiece");
                if (weightPerPiece>0) {
                    Convert.setSpecialUnit(name, weightPerPiece);
                    for (String aliasName: alias)
                        Convert.setSpecialUnit(aliasName.trim(), weightPerPiece);
                }
	}        
        
        
        public Ingredient(double menge, String unit, String name, Ingredient genericIngred) {
		this.name=name;		
                this.measure=menge;
                this.measureUnit=unit;	
                if (genericIngred!=null && genericIngred.density>0) {  
                    DecimalFormat df2 = new DecimalFormat("###.#");                    
                    this.measureInGram=Double.parseDouble(df2.format(convert2gram(genericIngred.density)).replace(",","."));
                }
                
	}
        
        private double convert2gram(double density) {
            double measureInGram=0;
            
            try {
                measureInGram=convert.calc2gram(measureUnit, measure, density, name);
                
            } catch (Exception ex) {
                //stays on false
                //ex.printStackTrace();
                
            }
            return measureInGram;
        }
        
        
        
               
        public String aliasCSV() {
            return arrList2CSV(alias,stdTrenner);
        }
    
        @Override
	public String toString() {
		Format format=Format.getPrettyFormat();
                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(format);
        return xmlOutputter.outputString(toElement());
        }
	
	public String toXML() { 
        return toString();		
	}
        
     public Element toElement() {
                Element ingredientElement = new Element("ingredient");
                
                ingredientElement.addContent(MyElement.createElement("name",name));
                if (alias.size()>0) ingredientElement.addContent(MyElement.createElement("alias",arrList2CSV(alias, Ingredient.stdTrenner)));
                if (density>0) ingredientElement.addContent(MyElement.createElement("density",""+density));
                if (calSpec>0) ingredientElement.addContent(MyElement.createElement("calSpec",""+calSpec));
                if (carbSpec>0) ingredientElement.addContent(MyElement.createElement("carbSpec",""+carbSpec));
                if (fatSpec>0)  ingredientElement.addContent(MyElement.createElement("fatSpec",""+fatSpec));
                if (protSpec>0) ingredientElement.addContent(MyElement.createElement("protSpec",""+protSpec));
                if (measure>0) ingredientElement.addContent(MyElement.createElement("measure",""+measure));
                if (measureUnit!=null) ingredientElement.addContent(MyElement.createElement("measureUnit",measureUnit));
                ingredientElement.addContent(MyElement.createElement("measure_in_gram",""+measureInGram));
                
                
            return ingredientElement;
     }
     
     
     public String arrList2CSV(ArrayList<String> arrlist,String trenner) {
    	 StringBuilder retVal= new StringBuilder();
    	 for (String item : arrlist) 
    		 if(retVal.length()==0)	retVal.append(item);
    		 else 					retVal.append(stdTrenner+item);    			
    	 return retVal.toString();
     }
     
     public ArrayList<String> csv2ArrayList(String csvStr,String trenner) {    	 
    	 String[] strArr = csvStr.split(trenner);
    	 ArrayList<String> retList = new ArrayList<String>();
    	 for (String item : strArr) 
    		 retList.add(item);    		 				
    	 return retList;
     }
		
	
}
