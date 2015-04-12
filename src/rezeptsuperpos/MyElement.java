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

/**
 *
 * @author Hannes Hassler
 */
import java.util.ArrayList;
import org.jdom2.Element;

public class MyElement {
    
    public static Element setChild(Element element, String childName, String childVal) {
        Element childElement=null;
        try {
            childElement=element.getChild(childName);
            childElement.setText(childVal);
        } catch (NullPointerException ex) {
            element.addContent(createElement(childName,childVal));
        }
        
        return element;
    }

    public static Element createElement(String name, String content) {
        Element retElement = new Element(name);
        retElement.setText(content);
        return retElement;
    }

    public static String tryGetString(Element element, String attrName) {
        String retVal = "";
        try {
            retVal = element.getChildText(attrName);
        } catch (Exception ex) {
            //ignore, return zero
        }
        return retVal;
    }

    public static double tryGetDouble(Element element, String attrName) {
        double retVal = 0;
        try {
            retVal = Double.parseDouble(element.getChildText(attrName));
        } catch (Exception ex) {
            //ignore, return zero
        }
        return retVal;
    }
    
    public static int tryGetInt(Element element, String attrName) {
        int retVal = 0;
        try {
            retVal = Integer.parseInt(element.getChildText(attrName));
        } catch (Exception ex) {
            //ignore, return zero
        }
        return retVal;
    }

    public static ArrayList tryGetList(Element element, String attrName, Ingredient ingredient) {
        ArrayList<String> retVal = new ArrayList<String>();
        try {
            retVal = ingredient.csv2ArrayList(element.getChildText(attrName), Ingredient.stdTrenner);
            ;
        } catch (Exception ex) {
            //ignore, return empty List
        }
        return retVal;
    }
}
