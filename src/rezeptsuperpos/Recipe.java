/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rezeptsuperpos;

import java.util.ArrayList;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import static rezeptsuperpos.Ingredient.stdTrenner;

/**
 *
 * @author Hannes Hassler
 */
public class Recipe {
    
        public String name;
        public String source="";
	public ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>(); 
        public String preparation="";
        public int   portions;
        public String cook;
            
        public Recipe(String name) {
            this.name=name;             
        }
    
        public Recipe(Element recipeElement) {
            this.name=recipeElement.getChildText("name");
            this.source=MyElement.tryGetString(recipeElement,"source");
            for (Element ingredient: recipeElement.getChild("ingredients").getChildren())
                ingredients.add(new Ingredient(ingredient));
            preparation=MyElement.tryGetString(recipeElement,"preparation");
            portions=MyElement.tryGetInt(recipeElement,"portions");
            cook=MyElement.tryGetString(recipeElement, "cook");
            
        }
        
        
        Element toElement() {
            Element recipeElement = new Element("recipe");                
                    recipeElement.addContent(MyElement.createElement("name",name));
                    recipeElement.addContent(MyElement.createElement("source",source));
                    recipeElement.addContent(new Element("ingredients"));
                    for (Ingredient ingredient: ingredients)
                        recipeElement.getChild("ingredients").addContent(ingredient.toElement());                
            return recipeElement;
        }
        
        @Override
	public String toString() {
		Format format=Format.getPrettyFormat();
                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(format);
        return xmlOutputter.outputString(toElement());
        }
}
