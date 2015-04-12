package rezeptsuperpos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class RecipeArchive extends GenericArchive{

   
    public RecipeArchive() {
        documentPathFile="db/recipes.xml";
        JDom jdom = new JDom();
        try {
        	document = jdom.readXML(documentPathFile);
        } catch (Exception ex) {
        	document = new Document();
        	document.addContent(new Element("recipes"));
        	try {
				saveRecipes();
			} catch (FileNotFoundException e) {					
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {					
				e.printStackTrace();
			}
        }
        synchronizeRecipeArr();        
    }
    
    public void synchronizeRecipeArr() {
        documentArr = new ArrayList<Element>();
        for (Element recipe: document.getRootElement().getChildren())
        	documentArr.add(recipe);
    }
    
    public void insertRecipe(Recipe recipe) throws FileNotFoundException, UnsupportedEncodingException {            
            Element recipeElement = recipe.toElement();
            document.getRootElement().addContent(recipeElement);
            documentArr.add(recipeElement);
            currElementOfDocument++;
            saveRecipes();
    }
    
    public void deleteRecipe(int recipeIdx2Delete) throws Exception{        
        int recipeIdx=0;        
        ArrayList<Element> elements = new ArrayList<Element>();
        
        for (Element element: document.getRootElement().getChildren()) {
            if (recipeIdx==recipeIdx2Delete) {                  
                elements.add(element);
            }
            recipeIdx++;
        };
        for (Element element : elements)
            element.getParent().removeContent(element);
        this.currElementOfDocument--;
        synchronizeRecipeArr();
        saveRecipes();
    }
    
    public int size() {
        return documentArr.size();
    }
    
    public Recipe getFirst() {          
                this.currElementOfDocument=0;
                Recipe retRecipe=null;
                try {
                    retRecipe=new Recipe(documentArr.get(currElementOfDocument));
                } catch (Exception ex) {
                    currElementOfDocument=-1;
                }
                return retRecipe;
	}
    
    public Recipe getCurrentRecipe() {
        return new Recipe(getCurrentElement());
    }
    
    public void saveRecipes() throws FileNotFoundException, UnsupportedEncodingException {
    	File ingredientFile = new File(documentPathFile);
    	PrintWriter ingredientPrintWriter = new PrintWriter(ingredientFile,"UTF-8");
    	Format format=Format.getPrettyFormat();
    	XMLOutputter xmlOutputter = new XMLOutputter();
    	xmlOutputter.setFormat(format);
		ingredientPrintWriter.println(xmlOutputter.outputString(document));
		ingredientPrintWriter.close();
    }

}
