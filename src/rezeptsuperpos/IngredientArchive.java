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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.util.*;

/**
 *
 * @author Hannes Hassler
 */
public class IngredientArchive extends GenericArchive{
   	
    public IngredientArchive() {
            documentPathFile="db/ingredients.xml";
            JDom jdom = new JDom();
            try {
            	document = jdom.readXML(documentPathFile);
            } catch (Exception ex) {
            	document = new Document();
            	document.addContent(new Element("ingredients"));
            	try {
					saveIngredients();
				} catch (FileNotFoundException|UnsupportedEncodingException e) {					
					e.printStackTrace();
				} 
            }
            for (Element ingredient: document.getRootElement().getChildren())
                documentArr.add(ingredient);
    }
    
    public static String nameRump(String fullName) {        
        String[] nameArr = fullName.split(",");
        return nameArr[0];
    }

        
    public Ingredient searchIngredient(String searchStr) {
            Ingredient retIngredient= searchIngredientExact(searchStr.toLowerCase());            
            if (retIngredient==null)
                retIngredient=searchIngredientExact(nameRump(searchStr).toLowerCase());
            return retIngredient;
    }
        
    public Ingredient searchIngredientExact(String searchStr) {
            Ingredient retIngredient=null;
            for (int ingredIdx=0; ingredIdx<documentArr.size(); ingredIdx++) {
                Ingredient ingredient = new Ingredient(documentArr.get(ingredIdx));
                if (    ingredient.name.toLowerCase().contains(searchStr) 
                     || ingredient.aliasCSV().toLowerCase().contains(searchStr)
                   )
                   {
                   currElementOfDocument=ingredIdx;
                   retIngredient=ingredient;
                }
            }
            return retIngredient;
    }
	
    public Ingredient getFirst() {          
                currElementOfDocument=0;
                Ingredient retIngredient=null;
                try {
                    retIngredient=new Ingredient(documentArr.get(currElementOfDocument));
                } catch (Exception ex) {
                    currElementOfDocument=-1;
                }
                return retIngredient;
    }
    
    public void deleteCurrentIngredient() {
        Element currIng =documentArr.get(this.currElementOfDocument);
        this.document.getRootElement().removeChildren("ingredient"); 
        documentArr.remove(this.currElementOfDocument);
        for (Element element: documentArr) 
            this.document.getRootElement().addContent(element);
        currElementOfDocument=0;
    }
            
    public int size() {
        return this.documentArr.size();
    }
    
    public void insertIngredient(Ingredient ingredient) throws FileNotFoundException, UnsupportedEncodingException {            
            Element ingredElement = ingredient.toElement();
            document.getRootElement().addContent(ingredElement);
            documentArr.add(ingredElement);
            currElementOfDocument++;
            saveIngredients();
    }
    
    
    
    public void insertIngredient(Element ingredient) throws FileNotFoundException, UnsupportedEncodingException {            
            document.getRootElement().addContent(ingredient);
            documentArr.add(ingredient);
            currElementOfDocument++;
            saveIngredients();
    }
    
    public void saveIngredients() throws FileNotFoundException, UnsupportedEncodingException {
    	File ingredientFile = new File(documentPathFile);
    	PrintWriter ingredientPrintWriter = new PrintWriter(ingredientFile,"UTF-8");
    	Format format=Format.getPrettyFormat();
    	XMLOutputter xmlOutputter = new XMLOutputter();
    	xmlOutputter.setFormat(format);
		ingredientPrintWriter.println(xmlOutputter.outputString(document));
		ingredientPrintWriter.close();
    }
     
}
